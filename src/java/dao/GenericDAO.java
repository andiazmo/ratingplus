/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.Table;

/**
 *
 * @author User
 * @param <T>
 */
public class GenericDAO<T> {
    
    private Class aClass;
    private String entityName;
    private List<String> fieldNames;

    public GenericDAO(Class aClass) {
        this.aClass = aClass;
        loadEntityName();
        loadFields();
    }
    
    public void createEntity(T entity) {
        String strQuery = "INSERT INTO "+entityName+"("+showFieldNames()+")" +
                "VALUES("+showFieldValues()+")";
        
        try(Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement =
                        connection.prepareStatement(strQuery);)
        {
            ResultSet rs = preparedStatement.executeQuery(strQuery);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
//    
//    public List<T> findAllEntity(String entityName) {
//        EntityManager em = factory.createEntityManager();
//        String strQuery = "Select t from " + entityName + " t";
//        Query q = em.createQuery(strQuery);
//        List<T> entities = q.getResultList();
//        return entities;
//    }
//    
//    public T findEntity(String id){
//        String query = "SELECT * FROM "+entityName + "WHERE id = ?";
//        
//        try(Connection connection = ConnectionFactory.getConnection();
//                PreparedStatement preparedStatement =
//                        connection.prepareStatement(query);)
//        {
//            
//            preparedStatement.setString(1, id);
//            ResultSet rs = preparedStatement.executeQuery(query );
//            
//            while (rs.next()) {
//                for(String field: fields){
//                    
//                }
//                String userid = rs.getString("USER_ID");
//                String username = rs.getString("USERNAME");
//            }
//            
//            
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//        
//        return entity;
//    }
//    
//    public void editEntity(String query, String values[]){
//        try(Connection connection = ConnectionFactory.getConnection();
//                PreparedStatement preparedStatement =
//                        connection.prepareStatement(query);)
//        {
//            int i = 1;
//            for(String value: values){
//                preparedStatement.setString(i, value);
//                i++;
//            }
//            
//            preparedStatement.execute();
//            
//        }
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//    
//    public void deletePerson(String entityName, String id){
//        String query = "DELETE FROM "+ entityName + " WHERE id = ?";
//        
//        try(Connection connection = ConnectionFactory.getConnection();
//                PreparedStatement preparedStatement =
//                        connection.prepareStatement(query);)
//        {
//            preparedStatement.setString(1, id);
//            preparedStatement.execute();
//        }
//        
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//    }
//    
    private String loadEntityName(){
        Annotation[] annotations = aClass.getAnnotations();
        for(Annotation annotation : annotations){
            if(annotation instanceof Table){
                Table myAnnotation = (Table) annotation;
                return myAnnotation.name();
            }
        }
        return "";
    }
    
    private void loadFields(){
        for(Field field: aClass.getClass().getDeclaredFields()){
            field.setAccessible(true);
            fieldNames.add(field.getName());
        }
    }
    
    private String showFieldNames(){
        String strFields = fieldNames.get(0);
        for(int i=1; i<fieldNames.size(); i++ ){
            strFields += " ,"+fieldNames.get(i);
        }
        return strFields;
    }
    
    private String showFieldValues(){
        String strFields = fieldNames.get(0);
        for(int i=1; i<fieldNames.size(); i++ ){
            strFields += " ,"+fieldNames.get(i);
        }
        return strFields;
    }
    
    public String executeGetMethod(String field){
        try{
            Class noparams[] = {};
            Object obj = aClass.newInstance();
            for(String fieldName : fieldNames){
                Method method = aClass.getDeclaredMethod(
                        convertFieldToGetMethod(fieldName), noparams);
                method.invoke(obj);
            }
            return "";
        }
        catch(Exception ex){
            return "";
        }
    }
    
    private String convertFieldToGetMethod(String fieldName){
        return "get"+fieldName.substring(0).toUpperCase()+
                fieldName.substring(1);
    }
}
