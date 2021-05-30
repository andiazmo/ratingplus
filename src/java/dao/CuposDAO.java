/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cupos;

/**
 *
 * @author User
 */
public class CuposDAO extends GenericDAOJPA<Cupos>{

    public CuposDAO() {
        super(Cupos.class);
        em = factory.createEntityManager();
    }
    
}
