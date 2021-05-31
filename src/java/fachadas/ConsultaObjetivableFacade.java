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
public class ConsultaObjetivableFacade extends AbstractFacade {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaObjetivableFacade() {
        super(GruposClientes.class);
    }
     
    public boolean registrarObjetivable(String cliente, String evolucionEsperada, 
            String posicionMercado, String dependenciaCliente, String concentracionProveedor, 
            String voluntadCapacidadApoyo, String garantiasAdicionales, String calidadRevisorFiscal, 
            String informeRevisorFiscal, String usuario){
    
        Query q = em.createNativeQuery(" INSERT INTO ri.modulo_objetivable(\n" +
                "id, id_cliente, evolucion_esperada_actividad, posicion_mercado, "
                + "dependencia_clientes, concentracion_proveedor, "
                + "voluntad_capacidad_apoyo_financiero_accionistas, garantias_adicionales, "
                + "calidad_revisor_fiscal, informe_revisor_fiscal, usuario, fecha_insercion)"
                + " VALUES ((SELECT MAX(id) from ri.modulo_objetivable)+ 1, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, (SELECT now()::timestamp))");
   
        return q.setParameter(1, cliente).setParameter(2, evolucionEsperada).
                setParameter(3, posicionMercado).setParameter(4, dependenciaCliente).
                setParameter(5, concentracionProveedor).setParameter(6, voluntadCapacidadApoyo).
                setParameter(7, garantiasAdicionales).setParameter(8, calidadRevisorFiscal).
                setParameter(9, informeRevisorFiscal).setParameter(10, usuario).executeUpdate() != 0;
    }
    
}