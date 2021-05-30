/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK1
 *Fecha del cambio : 26-06-2018
 --------------------------------------------------------------------------------
 */
package fachadas;

import entidades.RpUsuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author siervo
 */
@Stateless
public class RpUsuariosFacade extends AbstractFacade<RpUsuarios> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RpUsuariosFacade() {
        super(RpUsuarios.class);
    }

    public RpUsuarios autentica(String clave, String nombre) {
        List<RpUsuarios> usuarios = em.createNamedQuery("RpUsuarios.findByNombreClave").setParameter("clave", clave).setParameter("nombre", nombre).getResultList();
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    public RpUsuarios porNombre(String nombre) {
        List<RpUsuarios> usuarios = em.createNamedQuery("RpUsuarios.findByNombre").setParameter("nombre", nombre).getResultList();
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        } else {
            return null;
        }

    }

    public void guardar(RpUsuarios usuario) {
        em.persist(usuario);
    }

    public void modificar(RpUsuarios usuario) {
        try {
            em.merge(usuario);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void modificarClave(RpUsuarios usuario) {
        try {
            RpUsuarios usuarioExitente = buscarRpUsuariosXCodigo(usuario.getCodigo());
            usuarioExitente.setClave(usuario.getClave());
            em.merge(usuarioExitente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public RpUsuarios buscarRpUsuarios(String nombreUsuario) {
        // FIXPACK1 - inicio
        RpUsuarios rpUsuario = null;
        // FIXPACK1 - fin
        Query query = em.createNamedQuery("RpUsuarios.findByNombre");
        query.setParameter("nombre", nombreUsuario);
        // FIXPACK1 - inicio
        if (!query.getResultList().isEmpty()) {
            rpUsuario = (RpUsuarios) query.getResultList().get(0);
        }
        return rpUsuario;
        // FIXPACK1 - fin
    }

    public RpUsuarios buscarRpUsuariosXCodigo(String codigo) {
        Query query = em.createNamedQuery("RpUsuarios.findByCodigo");
        query.setParameter("codigo", codigo);
        List<RpUsuarios> result = query.getResultList();
        return result.get(0);
    }

}
