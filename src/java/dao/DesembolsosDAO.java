/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Desembolsos;

/**
 *
 * @author User
 */
public class DesembolsosDAO extends GenericDAOJPA<Desembolsos>{

    public DesembolsosDAO() {
        super(Desembolsos.class);
        em = factory.createEntityManager();
    }
    
    
    
}
