/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fachadas;

import entidades.GruposClientes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author x356167
 */
@Stateless
public class ConsultaSubjetivoFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaSubjetivoFacade() {
        super(GruposClientes.class);
    }
    
    public boolean registrarSubjetivo(String cliente, String tipoProducto, 
            String gerenciaCapacidad, String abanicoBancario, String mecanismoFinanciacion, 
            String estructuraCostos, String capacidadAtenderCalendario, 
            String gradoAutoFinanciacion, String existenciaDeuda, String perfilPago, 
            String calidadActivos, String tipoAccionista,String usuario){
    
        Query q = em.createNativeQuery(" INSERT INTO ri.modulo_subjetivo(\n" +
                "id, id_cliente, tipo_producto_servicio, gerencia_capacidad_profesionalidad_experiencia, "
                + "abanico_bancario_facilidad_sustitucion, mecanismos_financiacion, "
                + "estructura_costos, capacidad_atender_calendario_pago_deuda, "
                + "grado_autofinanciacion_inversiones, existencia_deudas_compromisos_fuera_alcance, "
                + "perfil_pago_deuda, calidad_activos_circulantes, tipo_accionista,"
                + " usuario, fecha_insercion)"
                + " VALUES ((SELECT COALESCE(MAX(id), 0) from ri.modulo_subjetivo)+ 1, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q.setParameter(1, cliente).setParameter(2, tipoProducto).
                setParameter(3, gerenciaCapacidad).setParameter(4, abanicoBancario).
                setParameter(5, mecanismoFinanciacion).setParameter(6, estructuraCostos).
                setParameter(7, capacidadAtenderCalendario).setParameter(8, gradoAutoFinanciacion).
                setParameter(9, existenciaDeuda).setParameter(10, perfilPago).setParameter(11, calidadActivos).
                setParameter(12, tipoAccionista).setParameter(13, usuario).executeUpdate() != 0;
    }
    
}