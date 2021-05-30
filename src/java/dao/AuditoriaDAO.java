/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Auditoria;

/**
 *
 * @author User
 */
public class AuditoriaDAO extends GenericDAOJPA<Auditoria>{

    public AuditoriaDAO() {
        super(Auditoria.class);
        em = factory.createEntityManager();
    }
    
    
    
}
