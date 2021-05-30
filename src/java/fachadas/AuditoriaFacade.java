/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Auditoria;
import entidades.Bancas;
import entidades.Ciius;
import entidades.Estados;
import entidades.Garantias;
import entidades.Gestores;
import entidades.GruposEconomicos;
import entidades.Modalidades;
import entidades.Monedas;
import entidades.Periodos;
import entidades.RpModulos;
import entidades.RpPermisos;
import entidades.RpRoles;
import entidades.RpSubmodulos;
import entidades.RpUsuarios;
import entidades.Tasas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author x356167
 */
@Stateless
public class AuditoriaFacade extends AbstractFacade<Auditoria> {
    @PersistenceContext(unitName = "cuposPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuditoriaFacade() {
        super(Auditoria.class);
    }
    
    public void guardar(Auditoria auditoria){
        em.persist(auditoria);
    }
    
    public void guardaro(Auditoria auditoria,Object objeto){
        String comentario = "";
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Bancas"))
       {
                Bancas banca = (Bancas) objeto;
                if(em.find(Bancas.class, banca.getId())==null){
                    comentario="Se ha creado la banca " + banca.getNombre();
                }else{
                    comentario="Se ha modificado la banca " + banca.getNombre();
                }
       }
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Ciius"))
       {
                Ciius ciius = (Ciius) objeto;
                if(em.find(Bancas.class, ciius.getId())==null){
                    comentario="Se ha creado el ciius " + ciius.getNombre();
                }else{
                    comentario="Se ha modificado el ciius " + ciius.getNombre();
                }
       }
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Estados"))
       {
                Estados estados = (Estados) objeto;
                if(em.find(Estados.class, estados.getId())==null){
                    comentario="Se ha creado el Estado " + estados.getNombre();
                }else{
                    comentario="Se ha modificado el Estado " + estados.getNombre();
                }
       }         
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Garantias"))
       {
                Garantias garantia = (Garantias) objeto;
                if(em.find(Garantias.class, garantia.getId())==null){
                    comentario="Se ha creado la garantia " + garantia.getNombre();
                }else{
                    comentario="Se ha modificado la garantia " + garantia.getNombre();
                }
       }
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Gestores"))
       {
                Gestores gestores = (Gestores) objeto;
                if(em.find(Gestores.class, gestores.getCedula())==null){
                    comentario="Se ha creado el Gestor " + gestores.getNombre();
                }else{
                    comentario="Se ha modificado el Gestor " + gestores.getNombre();
                }
       }         
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.GruposEconomicos")){
                GruposEconomicos grupo = (GruposEconomicos) objeto;
                if(em.find(GruposEconomicos.class, grupo.getCodigoGrupo())==null){
                    comentario="Se ha creado el Grupo " + grupo.getNombre();
                }else{
                    comentario="Se ha modificado el Grupo " + grupo.getNombre();
                }
       }           
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Modalidades")){
                Modalidades modalidad = (Modalidades) objeto;
                if(em.find(Modalidades.class, modalidad.getId())==null){
                    comentario="Se ha creado la modalidad " + modalidad.getNombre();
                }else{
                    comentario="Se ha modificado la modalidad " + modalidad.getNombre();
                }
       }        
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Monedas")){
                Monedas moneda = (Monedas) objeto;
                if(em.find(Monedas.class, moneda.getId())==null){
                    comentario="Se ha creado la monedad " + moneda.getNombre();
                }else{
                    comentario="Se ha modificado la monedad " + moneda.getNombre();
                }
       }       
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Periodos")){
                Periodos periodo = (Periodos) objeto;
                if(em.find(Periodos.class, periodo.getId())==null){
                    comentario="Se ha creado el periodo " + periodo.getNombre();
                }else{
                    comentario="Se ha modificado el periodo " + periodo.getNombre();
                }
       }        
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpModulos")){
                RpModulos modulo = (RpModulos) objeto;
                if(em.find(RpModulos.class, modulo.getCodigo())==null){
                    comentario="Se ha creado el modulo " + modulo.getNombre();
                }else{
                    comentario="Se ha modificado el modulo " + modulo.getNombre();
                }
       }          
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpPermisos")){
                RpPermisos permiso = (RpPermisos) objeto;
                if(em.find(RpPermisos.class, permiso.getCodigo())==null){
                    comentario="Se ha creado el permiso para el rol " + permiso.getRol() + " del modulo " + permiso.getModulo();
                }else{
                    comentario="Se ha modificado el permiso para el rol " + permiso.getRol() + " del modulo " + permiso.getModulo();
                }
       }
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpRoles")){
                RpRoles roles = (RpRoles) objeto;
                if(em.find(RpRoles.class, roles.getCodigo())==null){
                    comentario="Se ha creado el rol " + roles.getNombre();
                }else{
                    comentario="Se ha modificado el rol " + roles.getNombre();
                }
       }      
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpSubmodulos")){
                RpSubmodulos sub = (RpSubmodulos) objeto;
                if(em.find(RpRoles.class, sub.getCodigo())==null){
                    comentario="Se ha creado el Submodulo " + sub.getNombre();
                }else{
                    comentario="Se ha modificado el Submodulo " + sub.getNombre();
                }
       }     
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpUsuarios")){
                RpUsuarios usu = (RpUsuarios) objeto;
                if(em.find(RpRoles.class, usu.getCodigo())==null){
                    comentario="Se ha creado el usuario " + usu.getNombre();
                }else{
                    comentario="Se ha modificado el el usuario " + usu.getNombre();
                }
       }     
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Tasas")){
                Tasas tasa = (Tasas) objeto;
                if(em.find(Tasas.class, tasa.getId())==null){
                    comentario="Se ha creado la tasa " + tasa.getNombre();
                }else{
                    comentario="Se ha modificado la tasa " + tasa.getNombre();
                }
        }                      
                
        auditoria.setAccion(comentario);
        em.persist(auditoria);
    }
 public void borraro(Auditoria auditoria,Object objeto){
       String comentario = "Se ha borrado ";
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Bancas"))
       {
                Bancas banca = (Bancas) objeto;
                comentario+="la banca " + banca.getNombre();
       }
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Ciius"))
       {
                 Ciius ciius = (Ciius) objeto;
                    comentario=" el ciius " + ciius.getNombre();
       }
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Estados"))
       {
                 Estados estados = (Estados) objeto;
                 comentario=" el Estado " + estados.getNombre();       
       }
                
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Garantias"))
       {
                 Garantias garantia = (Garantias) objeto;
                 comentario=" la garantia " + garantia.getNombre();
       }       
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Gestores"))
       {
                 Gestores gestores = (Gestores) objeto;
                    comentario="el Gestor " + gestores.getNombre();
       }       
                
       if(objeto.getClass().toString().equalsIgnoreCase("class entidades.GruposEconomicos")){
                    GruposEconomicos grupo = (GruposEconomicos) objeto;
                    comentario=" el Grupo " + grupo.getNombre();
       }           
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Modalidades")){
                Modalidades modalidad = (Modalidades) objeto;
                comentario="la modalidad " + modalidad.getNombre();
        }    
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Monedas")){
                 Monedas moneda = (Monedas) objeto;
                 comentario="la monedad " + moneda.getNombre();
        }        
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Periodos")){
                Periodos periodo = (Periodos) objeto;
                comentario="el periodo " + periodo.getNombre();
        }   
          if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpModulos")){
                RpModulos modulo = (RpModulos) objeto;
                comentario="el modulo " + modulo.getNombre();
          }
         if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpPermisos")){
                  RpPermisos permiso = (RpPermisos) objeto;
                  comentario="el permiso para el rol " + permiso.getRol() + " del modulo " + permiso.getModulo();
         }         
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpRoles")){
                  RpRoles roles = (RpRoles) objeto;
                  comentario="el rol " + roles.getNombre(); 
        }     
             
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpSubmodulos")){
                  RpSubmodulos sub = (RpSubmodulos) objeto;
                    comentario=" el Submodulo " + sub.getNombre();
        }        
             
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.RpUsuarios")){
                  RpUsuarios usu = (RpUsuarios) objeto;
                  comentario=" el usuario " + usu.getNombre();
        }   
             
                      
        if(objeto.getClass().toString().equalsIgnoreCase("class entidades.Tasas")){
                  Tasas tasa = (Tasas) objeto;
                    comentario=" la tasa " + tasa.getNombre();
        }   
                      

        auditoria.setAccion(comentario);
        em.remove(auditoria);
    }   
    
    
}
