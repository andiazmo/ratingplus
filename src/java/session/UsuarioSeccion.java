/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package session;


import entidades.Cupos;
import entidades.RpUsuarios;
import java.io.Serializable;
import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Scope;

/**
 *
 * @author ANGELICA M
 */
@Stateful(name="usuarioSeccion",mappedName="usuarioSeccion")
@SessionScoped

public class UsuarioSeccion implements Serializable{

  private RpUsuarios  usuario;
  private Cupos cupo;

    public RpUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(RpUsuarios usuario) {
        this.usuario = usuario;
    }

    public Cupos getCupo() {
        return cupo;
    }

    public void setCupo(Cupos cupo) {
        this.cupo = cupo;
    }

   
  

}
