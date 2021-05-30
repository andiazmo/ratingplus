package controladores;

import entidades.Ciius;
import fachadas.CiiusFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

@ManagedBean(name = "ciiusController")
@ViewScoped
public class CiiusController extends AbstractController<Ciius> {

    @EJB
    private CiiusFacade ejbFacade;

    /**
     * Initialize the concrete Ciius controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        this.setSubmodulo(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("v"));
        getPermisos();
    }

    public CiiusController() {
        // Inform the Abstract parent controller of the concrete Ciius?cap_first Entity
        super(Ciius.class);
    }
    /*public void handleFileUpload(FileUploadEvent event) {
     FileReader fr = null;
     BufferedReader br = null;
     try {
     // Apertura del fichero y creacion de BufferedReader para poder
     // hacer una lectura comoda (disponer del metodo readLine()).
           
     InputStreamReader reader = new InputStreamReader(event.getFile().getInputstream());
           
     br = new BufferedReader(reader);
     // Lectura del fichero
     String linea;
     int i = 1;
     while ((linea = br.readLine()) != null) {
     try{ 
     PersonaJuridicas persona = 
     new PersonaJuridicas(linea.substring(4, 12).toString(), linea.substring(0, 4).toString(), linea.substring(27, 56).toString().trim(), linea.substring(12, 14).toString(), linea.substring(15, 25).toString(),linea.substring(97, 137).toString().trim(),linea.substring(57, 76).toString().trim(),linea.substring(77, 96).toString().trim(),linea.substring(151, 152).toString()) ;
     this.ejbFacade.guardar(persona);
     personaCarga.setRegistrosCorrectos(personaCarga.getRegistrosCorrectos()+1);
     }catch(Exception e){
     try{
     this.ejbFacadeLog.guardar(new Logsubida(i,"Problemas al guardar",personaCarga.getNombre(),"ALTAIR"));
     personaCarga.setRegistrosErrados(personaCarga.getRegistrosErrados()+1);
     }catch(Exception ex){
                       
     System.out.println("Reventao "+ ex);
     }
     }
     i++;
     }
     this.ejbCargasFacade.modificar(personaCarga);
     } catch (Exception e) {
     e.printStackTrace();
     } finally {
     // En el finally cerramos el fichero, para asegurarnos
     // que se cierra tanto si todo va bien como si salta 
     // una excepcion.
     try {
     if (null != fr) {
     fr.close();
     }
     } catch (Exception e2) {
     e2.printStackTrace();
     }
     }
     this.personas = this.ejbFacade.findAll();
     FacesMessage msg = new FacesMessage("Ha finalizado la subidad de la población");
     FacesContext.getCurrentInstance().addMessage(null, msg);
     }*/

}
