/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import controladores.util.JsfUtil;
import entidades.Clientes;
import entidades.GruposEconomicos;
import entidades.RelacionClienteGrupo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author x302266
 */
@Stateless
public class RelacionClienteGrupoFacade extends AbstractFacade<RelacionClienteGrupo> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionClienteGrupoFacade() {
        super(RelacionClienteGrupo.class);
    }
/*
            
UPDATE RelacionClienteGrupo r SET r.gruposEconomico=:grupo, r.tipoVinculo=:vinculo, r.tipoRelacion=:relacion, 
r.porcParticipacion=:porcentaje, r.cantidadEmpleado, r.rolJerarquico, r.facturacion, r.totalActivo WHERE r.cliente=:id"
    
    */
    public void updateRowByClient(GruposEconomicos grupo, String vinculo, String relacion, Double porcentaje, Long empleados, String jerarquia, Double facturacion, Double activo, Clientes id) {

            Query q = em.createNamedQuery("RelacionClienteGrupo.deleteRowByClient");
            q.setParameter("grupo", grupo);
            q.setParameter("vinculo", vinculo);
            q.setParameter("relacion", relacion);
            q.setParameter("porcentaje", porcentaje);
            q.setParameter("empleados", empleados);
            q.setParameter("jerarquia", jerarquia);
            q.setParameter("facturacion", facturacion);
            q.setParameter("activo", activo);
            q.setParameter("id", id);
            q.executeUpdate();

    }
    
    public Double sumPorcParticionGroup(Long codigo){
        Query q = em.createNamedQuery("RelacionClienteGrupo.relacionByGrupo");
        q.setParameter("codigo", codigo);
        Double resultado = (Double) q.getSingleResult();
        return resultado;
        
    }
    
    public RelacionClienteGrupo relacionGrupoAnt(GruposEconomicos grupo, Clientes cliente){
        Query q = em.createNamedQuery("RelacionClienteGrupo.relacionByGrupoAndClient");
        q.setParameter("grupo", grupo);
        q.setParameter("cliente", cliente);
        RelacionClienteGrupo relacion = (RelacionClienteGrupo) q.getSingleResult();
        return relacion;
    }
    public RelacionClienteGrupo relacionByClient(Clientes cliente){
        Query q = em.createNamedQuery("RelacionClienteGrupo.relacionByClient");
        q.setParameter("id", cliente);
        RelacionClienteGrupo relacion = (RelacionClienteGrupo) q.getSingleResult();
        return relacion;
    }
}
