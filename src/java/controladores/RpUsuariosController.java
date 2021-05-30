/*
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web
 *Programador: Wittman Gutiérrez
 *Tag de cambio: FIXPACK1
 *Fecha del cambio : 26-06-2018
 --------------------------------------------------------------------------------
 *Proyecto : Mejoras Cupos Web
 *Programador: Julio Beltran
 *Tag de cambio: FIXPACK2
 *Fecha del cambio : 10-10-2018
 --------------------------------------------------------------------------------
 */
package controladores;

import controladores.util.JsfUtil;
import entidades.HistoricoClave;
import entidades.RpUsuarios;
import fachadas.HistoricoAccesoUsuarioFacade;
import fachadas.HistoricoClaveFacade;
import fachadas.IntentoFallidoFacade;
import fachadas.RpUsuariosFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import session.UsuarioSeccion;

@ManagedBean(name = "rpUsuariosController")
@ViewScoped
public class RpUsuariosController extends AbstractController<RpUsuarios> implements Serializable {

    @EJB
    private RpUsuariosFacade ejbFacade;
    @EJB
    private UsuarioSeccion seccion;
    @EJB
    private HistoricoClaveFacade historicoClaveFacade;
    @EJB
    private IntentoFallidoFacade intentoFallidoFacade;
    @EJB
    private HistoricoAccesoUsuarioFacade historicoAccesoUsuarioFacade;

    private String estado;
    //subida de foto
    private CroppedImage croppeFoto;
    private String imageTemp;
    private boolean mostrar;
    private String nom;
    private String user;
    private List<RpUsuarios> usuarios;
    private String mensajeValidacionUsuario;

    public RpUsuariosController() {
        super(RpUsuarios.class);
        this.estado = "";
        this.nom = "";
        this.user = "";

    }

    ;
    
    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
        this.usuarios = this.ejbFacade.findAll();
    }

    public CroppedImage getCroppeFoto() {
        return croppeFoto;
    }

    public void setCroppeFoto(CroppedImage croppeFoto) {
        this.croppeFoto = croppeFoto;
    }

    public String getImageTemp() {
        return imageTemp;
    }

    public void setImageTemp(String imageTemp) {
        this.imageTemp = imageTemp;
    }

    public List<RpUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<RpUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public void loguear() {
        RpUsuarios usuario = new RpUsuarios();
        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:nombre");
        String clave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:clave");

        if (!validarUsuario(nombre)) {
            JsfUtil.addSuccessMessage(mensajeValidacionUsuario);
            return;
        }

        usuario = ejbFacade.autentica(JsfUtil.encriptar(clave), nombre);
        if (usuario == null) {
            usuario = ejbFacade.porNombre(nombre);
            if (usuario != null) {
//                if(clave.equals("123genericasantander")){
                clave = JsfUtil.encriptar(clave);
                if (clave.equals(usuario.getClave())) {
                    try {
                        FacesContext contex = FacesContext.getCurrentInstance();
                        contex.getExternalContext().redirect("/cupos/cupos/cambiaClave.xhtml");
                        JsfUtil.addSuccessMessage("Su clave ha sido reseteada por favor inserte una nueva clave");
                    } catch (IOException ex) {
                        Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    this.user = "";
                    JsfUtil.addErrorMessage("La clave Ingresada no es correcta");
                    registarIntentoFallido(usuario);
                }
            } else {
                this.nom = "";
                JsfUtil.addErrorMessage("Usuario no existe dentro de nuestros registros");
            }
        } else {

            if (validarCambioClave(usuario)) {
                try {
                    System.out.println("validarCambioClave...");
                    FacesContext contex = FacesContext.getCurrentInstance();
                    contex.getExternalContext().redirect("/cupos/cupos/cambiaClave.xhtml");
                    JsfUtil.addSuccessMessage("Su clave ha caducado por favor inserte una nueva clave");
                } catch (IOException ex) {
                    Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                historicoAccesoUsuarioFacade.crear(usuario);
                seccion.setUsuario(usuario);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("seccion", seccion);
                FacesContext contex = FacesContext.getCurrentInstance();
                System.out.println("******************************************");
                System.out.println("******************************************");
                System.out.println("******************************************");
                System.out.println("******************************************");
                contex.getExternalContext().redirect("/cupos/cupos/index.xhtml");

            } catch (IOException ex) {
                JsfUtil.addErrorMessage("Usuario o clave no valido");
                Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void cerrar() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("seccion", null);
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/cupos/cupos/login.xhtml");
        } catch (IOException ex) {
            this.estado = "Ha ocurrido un error al loguearse";
            Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioSeccion getMisdatos() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        return (UsuarioSeccion) session.getAttribute("seccion");

    }

    public void guardarusuario() {
        try {
            String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("MisdatosForm:nombre");
            String clave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("MisdatosForm:clave");

            if (validarClave(nombre, clave)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                UsuarioSeccion usuario = (UsuarioSeccion) session.getAttribute("seccion");
                usuario.getUsuario().setNombre(nombre);
                usuario.getUsuario().setClave(clave);
                ejbFacade.edit(usuario.getUsuario());
                seccion.setUsuario(usuario.getUsuario());
                session.setAttribute("seccion", seccion);
                this.estado = "Se modificaron sus datos con exitos";
            } else {
                this.estado = "La clave no cumple con las condiciones";
            }

        } catch (Exception ex) {
            this.estado = "Ha ocurrido un error al loguearse";
            Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public void handleFileUpload(FileUploadEvent event) {
     try {
     WorkbookSettings ws = new WorkbookSettings();
     ws.setLocale(new Locale("en", "EN"));
     Workbook workbook = Workbook.getWorkbook(event.getFile().getInputstream(), ws);
     Sheet sheet = workbook.getSheet(0);
     Cell a1 = sheet.getCell(0,0);
     FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " columna " + a1.getContents() + " is uploaded.");
     FacesContext.getCurrentInstance().addMessage(null, msg);
     } catch (IOException ex) {
     Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (BiffException ex) {
     Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
     }
     }*/
    public void actionFoto() {
        this.croppeFoto = null;
        this.imageTemp = null;
    }

    public void actionGuardarFoto() {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = (String) servletContext.getRealPath("/");
        String archivo = path + "img\\" + File.separator + "id" + ".jpg";

        try {

            if (croppeFoto != null) {
                FileImageOutputStream imageOutput = new FileImageOutputStream(new File(archivo));
                imageOutput.write(croppeFoto.getBytes(), 0, croppeFoto.getBytes().length);
                imageOutput.close();
            } else {
                OutputStream outStream = new FileOutputStream(new File(archivo));
                InputStream inputStream = new FileInputStream(path + "/temp/" + this.getImageTemp());
                byte[] buffer = new byte[6124];
                int bulk;
                while (true) {
                    bulk = inputStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    outStream.write(buffer, 0, bulk);
                    outStream.flush();
                }
                outStream.close();
                inputStream.close();
            }

            actionFoto();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(FileUploadEvent event) {
        try {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = (String) servletContext.getRealPath("/");
            String archivo = path + "\\img\\temp" + File.separator + event.getFile().getFileName();

            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            byte[] buffer = new byte[6124];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }
            fileOutputStream.close();
            inputStream.close();

            this.setImageTemp(event.getFile().getFileName());

        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al subir el archivo"));
        }
    }

    public void cambioClave() {
        String nombre = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:nombre");
        String clave = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:clave");
        String clave1 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("form:clave1");
        RpUsuarios usuario = new RpUsuarios();
        if (clave.equalsIgnoreCase(clave1)) {
            if (validarClave(nombre, clave)) {
                usuario = this.ejbFacade.porNombre(nombre);
                usuario.setClave(JsfUtil.encriptar(clave));
                historicoClaveFacade.crear(usuario);
                this.ejbFacade.edit(usuario);
                JsfUtil.addSuccessMessage("Clave Cambiada con exito");
                try {

                    FacesContext contex = FacesContext.getCurrentInstance();
                    contex.getExternalContext().redirect("/cupos/cupos/index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(RpUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JsfUtil.addErrorMessage(mensajeValidacionUsuario);
            }
        } else {
            JsfUtil.addErrorMessage("Las claves ingresadas no coinciden");
        }

    }

    public void resetear(RpUsuarios usuario) {
        String clave = JsfUtil.encriptar(generarClaveGenerica(usuario.getNombres()));
        historicoClaveFacade.creaClaveReseto(usuario);
        usuario.setClave(clave);
        this.ejbFacade.modificarClave(usuario);
        JsfUtil.addSuccessMessage("Clave Reseteada con exito");
    }

    public String generarClaveGenerica(String nombres) {
        String result = "";
        String temp[] = nombres.split(" ");
        result += temp[0].charAt(0);
        if (temp.length > 1) {
            result += temp[1];
        }
        return result;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void guardar() {
        this.getSelected().setClave(JsfUtil.encriptar(this.getSelected().getClave()));
        // FIXPACK2 - inicio
        this.saveNew(null);
        this.usuarios = this.ejbFacade.findAll();
        // FIXPACK2 - fin
    }

    private boolean validarClave(String nombreUsuario, String clave) {
        if (clave.length() < 8) {
            mensajeValidacionUsuario = "La clave tiene menos de 8 caracteres";
            return false;
        }
        if (nombreUsuario.equals(clave)) {
            mensajeValidacionUsuario = "La clave no puede coincidir con el identificador del usuario";
            return false;
        }
        if (validarClaveAntiguedad(nombreUsuario, clave)) {
            mensajeValidacionUsuario = "Esta clave ha sido usada con anterioridad";
            return false;
        }
        return true;
    }

    private boolean validarClaveAntiguedad(String nombreUsuario, String clave) {
        clave = JsfUtil.encriptar(clave);

        RpUsuarios rPUsuario = ejbFacade.buscarRpUsuarios(nombreUsuario);
        List<HistoricoClave> historicoClaves = historicoClaveFacade.buscarUltimos5(rPUsuario);

        if (historicoClaves.isEmpty()) {
            return false;
        }

        for (HistoricoClave historicoClave : historicoClaves) {
            if (historicoClave.getClave().equals(clave)) {
                return true;
            }
        }
        return false;
    }

    private void registarIntentoFallido(RpUsuarios usuario) {
        intentoFallidoFacade.crear(usuario);
        validarIntentos(usuario);
    }

    private void validarIntentos(RpUsuarios usuario) {
        int intentos = intentoFallidoFacade.validarIntentos(usuario, new Date());
        if (intentos >= 3) {
            deshabilitarUsuario(usuario);
        }
    }

    private void deshabilitarUsuario(RpUsuarios usuario) {
        usuario.setActivado(false);
        ejbFacade.modificar(usuario);
    }

    private void bloquearUsuario(RpUsuarios usuario) {
        usuario.setBloqueado(true);
        ejbFacade.modificar(usuario);
    }

    public void activacionUsuario(RpUsuarios usuario) {
        boolean bool = usuario.getActivado();
        usuario.setActivado(!bool);
        ejbFacade.modificar(usuario);

        if (usuario.getActivado()) {
            intentoFallidoFacade.borrarIntentos(usuario, new Date());
        }
    }

    public void bloqueacionUsuario(RpUsuarios usuario) {
        boolean bool = !usuario.getBloqueado();
        usuario.setBloqueado(bool);
        ejbFacade.modificar(usuario);
    }

    private boolean validarUsuario(String nombre) {
        RpUsuarios rpUsuario = ejbFacade.buscarRpUsuarios(nombre);
        // FIXPACK1 - inicio
        if (rpUsuario == null) {
            mensajeValidacionUsuario = "El usuario no existe";
            return false;
        } else {
            // FIXPACK1 - fin
            return validarUsuarioBloqueado(rpUsuario)
                    && validarUsuarioDesactivado(rpUsuario);
        }
    }

    private boolean validarUsuarioBloqueado(RpUsuarios rpUsuario) {
        if (rpUsuario.getBloqueado()) {
            mensajeValidacionUsuario = "El usuario esta bloqueado";
            return false;
        }
        return true;
    }

    private boolean validarUsuarioDesactivado(RpUsuarios rpUsuario) {
        if (!rpUsuario.getActivado()) {
            mensajeValidacionUsuario = "El usuario esta desactivado";
            return false;
        }
        return true;
    }

    private boolean validarCambioClave(RpUsuarios usuario) {
        List<HistoricoClave> historicoClaves = historicoClaveFacade.buscarActiva(usuario);
        if (historicoClaves.size() > 0) {
            return false;
        }
        return true;
    }

//    public void delete(RpUsuarios usuario){
//        ejbFacade.borrar()
//    }
}
