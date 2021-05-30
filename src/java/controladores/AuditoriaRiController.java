package controladores;

import entidades.Auditorias_Ri;
import controladores.util.JsfUtil;
import controladores.util.PaginationHelper;
import entidades.Resumenes_eeff;
import fachadas.AuditoriaRiFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;


/*
 --------------------------------------------------------------------------------
 *Proveedor : Samtel
 *Proyecto : Calificacion Semestral de Cartera
 *Programador: Jeferson Camargo
 *Tag de cambio: BSNC-19-0098
 *Fecha del cambio : 28-08-2019
 **************Inicio**************
 --------------------------------------------------------------------------------
 */
@Named("auditoriaRiController")
@ViewScoped
public class AuditoriaRiController implements Serializable {

    private Auditorias_Ri current;
    private DataModel items = null;
    @EJB
    private fachadas.AuditoriaRiFacade ejbFacade;
    @EJB
    private fachadas.Resumenes_eeffFacade ejbFacadeResumen;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private String fechaUltimoCargue;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Auditorias_Ri> listAuditorias_Ri;
    private List<Auditorias_Ri> listAuditoriaCSV;

    @PostConstruct
    public void init() {
        listAuditorias_Ri = null;
        initialData();
    }

    public void initialData() {
        setFechaUltimoCargue(ejbFacadeResumen.fechaUltimoCargue());
    }

    public AuditoriaRiController() {
    }

    public Auditorias_Ri getSelected() {
        if (current == null) {
            current = new Auditorias_Ri();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AuditoriaRiFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Auditorias_Ri) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Auditorias_Ri();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AuditoriaRiCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Auditorias_Ri) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AuditoriaRiUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Auditorias_Ri) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AuditoriaRiDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Auditorias_Ri getAuditoriaRi(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    public String getFechaUltimoCargue() {
        return fechaUltimoCargue;
    }

    public void setFechaUltimoCargue(String fechaUltimoCargue) {
        this.fechaUltimoCargue = fechaUltimoCargue;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Auditorias_Ri> getListAuditorias_Ri() {
        return listAuditorias_Ri;
    }

    public void setListAuditorias_Ri(List<Auditorias_Ri> listAuditorias_Ri) {
        this.listAuditorias_Ri = listAuditorias_Ri;
    }

    public List<Auditorias_Ri> getListAuditoriaCSV() {
        return listAuditoriaCSV;
    }

    public void setListAuditoriaCSV(List<Auditorias_Ri> listAuditoriaCSV) {
        this.listAuditoriaCSV = listAuditoriaCSV;
    }

    @FacesConverter(forClass = Auditorias_Ri.class)
    public static class AuditoriaRiControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AuditoriaRiController controller = (AuditoriaRiController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "auditoriaRiController");
            return controller.getAuditoriaRi(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }

            if (object instanceof Auditorias_Ri) {
                Auditorias_Ri o = (Auditorias_Ri) object;
                return getStringKey(o.getIdAuditoriaRi());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Auditorias_Ri.class.getName());
            }

        }
    }

    /**
     *
     */
    public void getData() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (compararFechasConDate()) {
            setListAuditorias_Ri(ejbFacade.getLogByDate(getFechaInicio(), getFechaFin()));
            if (getListAuditorias_Ri().isEmpty()) {
                context.addMessage(null, new FacesMessage("No se encuentran registros", null));
            }
        } else {
            listAuditorias_Ri = null;            
            context.addMessage(null, new FacesMessage("La Fecha Fin no puede ser menor a la Fecha Inicio", null));
        }
    }

    private Boolean compararFechasConDate() {
        Boolean bandera = Boolean.FALSE;
        try {
            if (getFechaInicio().before(getFechaFin())) {
                bandera = Boolean.TRUE;
            } else {
                if (getFechaFin().before(getFechaInicio())) {
                    bandera = Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            System.out.println("Se Produjo un Error validando las fechas: " + e.getMessage());
        }
        return bandera;
    }

    public void generateCsvAuditoria() throws FileNotFoundException, IOException {
        String fileName = "Reporte Log Calificacion Cartera.csv";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("text/comma-separated-values");
        ec.setResponseHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream output = ec.getResponseOutputStream();

        List<String> strings = new ArrayList<>();
        strings.add(encabezadoCSV());
        setListAuditoriaCSV(ejbFacade.getLogByDate(getFechaInicio(), getFechaFin()));
        if (getListAuditoriaCSV() == null || getListAuditoriaCSV().isEmpty()) {
            strings.add("No se obtuvieron registros");
        } else {
            for (Auditorias_Ri obj : getListAuditoriaCSV()) {
                strings.add(contenidoCSV(obj).concat("\n"));
            }
        }

        for (String s : strings) {
            output.write(s.getBytes());
        }
        output.flush();
        output.close();
        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }

    public String encabezadoCSV() {
        String[] encabezado = {
            "FECHA ACCION",
            "USUARIO",
            "ACCION",
            "TABLA DESTINO",
            "NIT",
            "FECHA ACTA",
            "AÑO BALANCE",
            "INFORMACION_TABLA_RESUMEN"
        };

        Iterator i = Arrays.asList(encabezado).iterator();
        String cabecera = "";
        while (i.hasNext()) {
            cabecera += i.next() + ";";
        }
        cabecera = cabecera.substring(0, cabecera.length() - 1) + "\n";
        return cabecera;
    }

    public String contenidoCSV(Auditorias_Ri obj) {
        StringBuilder fila = new StringBuilder();
        fila.append(formatDate(obj.getFechaInsercion()).concat(";"));
        fila.append(obj.getUsuario().concat(";"));
        fila.append(obj.getOperacion().concat(";"));
        fila.append(obj.getTablaDestino().concat(";"));
        fila.append(obj.getNit().concat(";"));
        fila.append(formatDate(obj.getFechaacta()).concat(";"));
        fila.append(formatDate(obj.getAniobalance()).concat(";"));
        fila.append(obj.getDetalle().replace(';', '|'));
        return fila.toString();
    }

    public String formatDate(Date fecha) {
        if (null == fecha) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

}
/*
 --------------------------------------------------------------------------------
 *Proveedor : Samtel
 *Proyecto : Calificacion Semestral de Cartera
 *Programador: Jeferson Camargo
 *Tag de cambio: BSNC-19-0098
 *Fecha del cambio : 28-08-2019
 **************Fin**************
 --------------------------------------------------------------------------------
 */
