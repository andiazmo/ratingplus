/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Limitesautorizados;

/**
 *
 * @author User
 */
public class LimitesautorizadosDAO extends GenericDAOJPA<Limitesautorizados>{

    public LimitesautorizadosDAO() {
        super(Limitesautorizados.class);
        em = factory.createEntityManager();
    }
    
}
