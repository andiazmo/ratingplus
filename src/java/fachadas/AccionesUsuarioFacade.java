/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Fecha del creacion : 26-07-2018
 --------------------------------------------------------------------------------
 */
package fachadas;

import static constantes.Constantes.*;
import entidades.AccionesUsuario;
import entidades.Clientes;
import entidades.RpUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Wittman Gutiérrez
 */
@Stateless
public class AccionesUsuarioFacade extends AbstractFacade<AccionesUsuario> {

    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccionesUsuarioFacade() {
        super(AccionesUsuario.class);
    }

    public void guardar(AccionesUsuario accionesUsuario) {
        em.persist(accionesUsuario);
    }

    public void modificar(AccionesUsuario accionesUsuario) {
        em.merge(accionesUsuario);
    }

    /**
     * Construye lista de nits de clientes que no puede autorizar un usuario
     *
     * @param clientes lista de clientes cargados
     * @param rpUsuarios usuario conectado que intenta autorizar
     * @return Lista de nits de clientes no permitidos
     */
    public List<Clientes> clientesNoPermitidos(List<Clientes> clientes, RpUsuarios rpUsuarios) {
        List<Clientes> clientesNoPermitidos = new ArrayList<Clientes>();
        AccionesUsuario auxAcciones = null;
        for (Clientes cliente : clientes) {
            auxAcciones = accionesXNit(cliente.getNit());
            // Valida si la ultima acción realizada sobre el cliente la hizo el usuario conectado y si fué una modificacion o creación 
            if (auxAcciones != null) {
                if (auxAcciones.getUsuario().equals(rpUsuarios)
                        && (auxAcciones.getAccion().matches(ACCION_MODIFICA)
                        || auxAcciones.getAccion().matches(ACCION_CREA))) {

                    clientesNoPermitidos.add(cliente);
                }
            }
        }

        return clientesNoPermitidos;
    }

    /**
     * 
     * @param cliente
     * @param rpUsuarios
     * @return 
     */
    public Clientes clienteNoPermitido(Clientes cliente, RpUsuarios rpUsuarios) {
        Clientes clienteNoPermitido = null;
        AccionesUsuario auxAcciones = null;

        auxAcciones = accionesXNit(cliente.getNit());
        // Valida si la ultima acción realizada sobre el cliente la hizo el usuario conectado y si fué una modificacion o creación 
        if (auxAcciones != null) {
            if (auxAcciones.getUsuario().equals(rpUsuarios)
                    && (auxAcciones.getAccion().matches(ACCION_MODIFICA)
                    || auxAcciones.getAccion().matches(ACCION_CREA))) {

                clienteNoPermitido = cliente;
            }
        }

        return clienteNoPermitido;
    }

    /**
     * Busca la última acción realizada a un nit
     *
     * @param nit nit del cliente buscado
     * @return Última acción realizada sobre el registro
     */
    public AccionesUsuario accionesXNit(String nit) {
        List<AccionesUsuario> accionesList = new ArrayList<>();
        AccionesUsuario result = null;

        accionesList = em.createNamedQuery("AccionesUsuario.findByNitMaxFecha")
                .setParameter("nit", nit).getResultList();

        if (accionesList.size() > 0) {
            result = accionesList.get(0);
        }

        if (result != null && nit.equals("90300400")) {
            System.out.println("cliente: " + result.getNit());
        }
        return result;
    }

}
