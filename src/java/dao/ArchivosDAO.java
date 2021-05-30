/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Archivos;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class ArchivosDAO extends GenericDAOJPA<Archivos>{
    
    private List<Archivos> archivosNoProcesados;
    
    public ArchivosDAO() {
        super(Archivos.class);
        em = factory.createEntityManager();
        archivosNoProcesados = noProcesados();
    }
    
    @Override
    public List<Archivos> findAllEntity() {
        String strQuery = "Select t from Archivos t";
        Query q = em.createQuery(strQuery);
        List<Archivos> entities = q.getResultList();
        return entities;
    }
    
    public List<Archivos> noProcesados(){
        List<Archivos> result = em.createNamedQuery("Archivos.findByProcesado")
                .setParameter("procesado", false).getResultList();
        return result;
    }
    
    public boolean existeResgistro(String nombre){
        Query query = em.createNamedQuery("Archivos.findByNombre");
        query.setParameter("nombre", nombre);
        return ( query.getResultList().size() > 0 );
    }
    
    public void editar(Archivos archivo){
        try{
//            int i = archivosNoProcesados.indexOf(archivo);
//            em.merge(archivosNoProcesados.get(i));
//            Archivos archivoTemp = em.find(Archivos.class, archivo.getId());
//            em.getTransaction().begin();
            
            em.getTransaction().begin();
            em.merge(archivo);
            em.getTransaction().commit();
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    

}
