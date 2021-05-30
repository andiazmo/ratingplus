package fachadas;

import entidades.Auditorias_Ri;
import entidades.Resumenes_eeff;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
public class AuditoriaRiFacade extends AbstractFacade<Auditorias_Ri> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuditoriaRiFacade() {
        super(Auditorias_Ri.class);
    }

    /**
     * Metodo que se encarga de realizar la consulta de la tabla auditoria en la
     * base de datos tomando como filtro una fecha inicio y una fecha fin.
     *
     * @param fechaInicio
     * @param fechaFin
     * @return
     */
    public List<Auditorias_Ri> getLogByDate(Date fechaInicio, Date fechaFin) {
        List<Auditorias_Ri> lista = new ArrayList<>();
        Query query = em.createNamedQuery("Auditorias_Ri.findByDate");
        query.setParameter("fechaInicio", (null == fechaInicio) ? null : formatDate(fechaInicio));
        query.setParameter("fechaFin", (null == fechaFin) ? null : formatDate(fechaFin));
        if (!query.getResultList().isEmpty()) {
            for (Object object : query.getResultList()) {
                lista.add((Auditorias_Ri) object);
            }
        }
        return lista;
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

    public String queryCSV() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT *                                                               ");
        sql.append("FROM ri.auditoria_ri r                                                  ");
        sql.append("WHERE 1 = 1                                                            ");
        sql.append("AND ((?2 is null ) or to_char(r.fechaacta, 'dd/mm/yyyy')  = ?2)        ");
        sql.append("AND ((?2 is null ) or to_char(r.fechaacta, 'dd/mm/yyyy')  = ?2)        ");
        return sql.toString();
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
