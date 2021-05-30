package fachadas;

import entidades.Auditorias_Ri;
import entidades.Historicos_eeff;
import entidades.Resumenes_eeff;
import entidades.Resumenes_eeffPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/*
 --------------------------------------------------------------------------------
 *Proveedor : Samtel
 *Proyecto : Calificacion Semestral de Cartera
 *Programador: Jeferson Camargo
 *Tag de cambio: BSNC-19-0098
 *Fecha del cambio : 28-08-2019
 **************Inicio**************
 --------------------------------------------------------------------------------
 */
@Stateless
public class Resumenes_eeffFacade extends AbstractFacade<Resumenes_eeff> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Resumenes_eeffFacade() {
        super(Resumenes_eeff.class);
    }

    /**
     * Metodo que se encarga de consultar la ultima fecha de cargue en la base
     * de datos.
     *
     * @return
     */
    public String fechaUltimoCargue() {
        Auditorias_Ri auditorias_Ri = null;
        Query query = em.createNamedQuery("Auditorias_Ri.findByOperacionAndTablaDestino");
        query.setParameter("operacion", "Insert");
        query.setParameter("tablaDestino", "ri.resumeneeff");
        if (!query.getResultList().isEmpty()) {
            auditorias_Ri = (Auditorias_Ri) query.getResultList().get(0);
        }
        return new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(auditorias_Ri.getFechaInsercion());
    }

    /**
     * Metodo que se encarga de consultar los estudios cargados por mes actual
     * en la base de datos.
     *
     * @return
     */
    public Long cargadosMes() {
        Long cargadosMes = Long.valueOf(0);
        try {
            TypedQuery<Long> query = (TypedQuery<Long>) em.createNamedQuery("Resumenes_eeff.cargadosMes");
            query.setParameter("fechaInicio", new SimpleDateFormat("dd/MM/yyyy").parse("01/".concat(fechaActual())));
            query.setParameter("fechaFin", new SimpleDateFormat("dd/MM/yyyy").parse("31/".concat(fechaActual())));
            if (!query.getResultList().isEmpty()) {
                cargadosMes = query.getSingleResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cargadosMes;
    }

    /**
     * Metodo que se encarga de retornar la fecha actual
     *
     * @return
     */
    public String fechaActual() {
        //Fecha actual desglosada:
        Calendar fecha = Calendar.getInstance();
        int annio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        return ((Integer.toString(mes).length() == 1) ? "0".concat(Integer.toString(mes)) : Integer.toString(mes)).concat("/".concat(Integer.toString(annio)));
    }

    /**
     * Metodo que se encarga de realizar la consulta segun el filtro que se
     * utilice en la vista
     *
     * @param fechaActa
     * @param nit
     * @param fechainsercion
     * @param aprobado
     * @param empresa
     * @return
     */
    public List<Resumenes_eeff> consultaCargue(Date fechaActa, String nit, Date fechainsercion, String aprobado, String empresa) {
        List<Resumenes_eeff> listResumenes_eeff = new ArrayList<>();
        Query query = em.createNamedQuery("Resumenes_eeff.findByFilter");
        query.setParameter("nit", (nit.equalsIgnoreCase("")) ? null : nit);
        query.setParameter("fechaacta", (null == fechaActa) ? null : formatDate(fechaActa));
        query.setParameter("estadoaprobacion", aprobado);
        query.setParameter("nombreempresa", (empresa.equalsIgnoreCase("")) ? null : empresa);
        query.setParameter("fechainsercion", (null == fechainsercion) ? null : formatDate(fechainsercion));
        if (!query.getResultList().isEmpty()) {
            for (int i = 0; i < query.getResultList().size(); i++) {
                Resumenes_eeff resumnes_eeff = new Resumenes_eeff();
                Resumenes_eeffPK resumeneeffPK = new Resumenes_eeffPK();
                Object[] objeto = (Object[]) query.getResultList().get(i);
                resumeneeffPK.setNit((String) objeto[0]);
                resumnes_eeff.setResumenes_eeffPK(resumeneeffPK);
                resumnes_eeff.setNombreempresa((String) objeto[1]);
                resumnes_eeff.setSector((String) objeto[2]);
                resumnes_eeff.setFechaacta((Date) objeto[3]);
                resumnes_eeff.setEstadoaprobacion((String) objeto[4]);
                listResumenes_eeff.add(resumnes_eeff);
            }
        }
        return listResumenes_eeff;
    }

    /**
     * Metodo que se encarga de consultar el detalle de la tabla Resumenes_eeff
     * teniendo como filtro el NIT
     *
     * @param nit
     * @return
     */
    public List<Resumenes_eeff> consultaDetalle(String nit) {
        List<Resumenes_eeff> resumenes_eeff = new ArrayList<>();
        Query query = em.createNamedQuery("Resumenes_eeff.findByNit");
        query.setParameter("nit", (nit.equalsIgnoreCase("")) ? null : nit);
        resumenes_eeff = query.getResultList();
        if (resumenes_eeff.isEmpty()) {
            System.out.println("No existe detalle historico");
        }
        return resumenes_eeff;
    }

    /**
     * Metodo que se encarga de consultar los datos que se cargaran en el CSV
     *
     * @param fechaActa
     * @param nit
     * @param fechainsercion
     * @param aprobado
     * @param empresa
     * @return
     */
    public List<Resumenes_eeff> dataCSV(Date fechaActa, String nit, Date fechainsercion, String aprobado, String empresa) {
        List<Resumenes_eeff> listResumenes_eeff = (List<Resumenes_eeff>) em.createNativeQuery(queryCSV(), Resumenes_eeff.class)
                .setParameter(1, (nit.equalsIgnoreCase("")) ? null : nit)
                .setParameter(2, (null == fechaActa) ? null : formatDate(fechaActa))
                .setParameter(3, aprobado)
                .setParameter(4, (empresa.equalsIgnoreCase("")) ? null : empresa)
                .setParameter(5, (null == fechainsercion) ? null : formatDate(fechainsercion))
                .getResultList();
        Iterator i = listResumenes_eeff.iterator();
        Resumenes_eeff objResumen;
        while (i.hasNext()) {
            objResumen = (Resumenes_eeff) i.next();
        }

        return listResumenes_eeff;
    }

    /**
     * Metodo que se encarga de generar el Query para consultar los registros
     * para llenar el CSV
     *
     * @return
     */
    public String queryCSV() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT *                                                               ");
        sql.append("FROM ri.resumeneeff r                                                  ");
        sql.append("WHERE 1 = 1                                                            ");
        sql.append("AND ((?1 is null ) or trim(r.nit) = ?1 )                               ");
        sql.append("AND ((?2 is null ) or to_char(r.fechaacta, 'dd/mm/yyyy')  = ?2)        ");
        sql.append("AND ((?3 is null ) or trim(r.estadoaprobacion) = ?3)                   ");
        sql.append("AND ((?4 is null ) or trim(r.nombreempresa) = ?4)                      ");
        sql.append("AND ((?5 is null ) or to_char(r.fechainsercion, 'dd/mm/yyyy')  = ?5)   ");
        sql.append("union                                                                  ");
        sql.append("SELECT *                                                               ");
        sql.append("FROM ri.historicoreeff h                                               ");
        sql.append("WHERE 1 = 1                                                            ");
        sql.append("AND ((?1 is null ) or trim(h.nit) = ?1)                                ");
        sql.append("AND ((?2 is null ) or to_char(h.fechaacta, 'dd/mm/yyyy')  = ?2)        ");
        sql.append("AND ((?3 is null ) or trim(h.estadoaprobacion) = ?3)                   ");
        sql.append("AND ((?4 is null ) or trim(h.nombreempresa) = ?4)                      ");
        sql.append("AND ((?5 is null ) or to_char(h.fechainsercion, 'dd/mm/yyyy')  = ?5)   ");

        return sql.toString();
    }

    /**
     * Metodo que se encarga de sumar dias a una fecha.
     *
     * @param fecha
     * @param dias
     * @return
     */
    public Date sumarDiasAFecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    /**
     * Metodo que se encarga de cambiarle el formato a una fecha por dd/MM/yyyy
     *
     * @param fecha
     * @return
     */
    public String formatDate(Date fecha) {
        if (null == fecha) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    public Date stringToDate(String stringFecha) {
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(stringFecha);
        } catch (ParseException ex) {
            Logger.getLogger(Resumenes_eeffFacade.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fecha;
    }

}
/*
 --------------------------------------------------------------------------------
 *Proveedor : Samtel
 *Proyecto : Calificacion Semestral de Cartera
 *Programador: Jeferson Camargo
 *Tag de cambio: BSNC-19-0098
 *Fecha del cambio : 28-08-2019
 **************Fin**************
 --------------------------------------------------------------------------------
 */
