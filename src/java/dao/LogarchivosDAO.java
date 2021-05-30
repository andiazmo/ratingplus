/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Logarchivos;

/**
 *
 * @author User
 */
public class LogarchivosDAO extends GenericDAOJPA<Logarchivos>{

    public LogarchivosDAO() {
        super(Logarchivos.class);
        em = factory.createEntityManager();
    }
    
    
    
}
