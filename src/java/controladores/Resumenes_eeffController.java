package controladores;

import entidades.Resumenes_eeff;
import controladores.util.JsfUtil;
import controladores.util.PaginationHelper;
import fachadas.Resumenes_eeffFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;


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
@Named("resumenes_eeffController")
@SessionScoped
public class Resumenes_eeffController implements Serializable {

    private Resumenes_eeff current;
    private DataModel items = null;
    @EJB
    private fachadas.Resumenes_eeffFacade ejbFacadeResumenes;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private List<Resumenes_eeff> listResumenes_eeff;
    private List<Resumenes_eeff> listResumenes_eeffDetail;
    private List<Resumenes_eeff> listResumenesCSV;

    private String fechaUltimoCargue;
    private Long estudiosCargadosPorMes;
    private Date fechaActa;
    private String nit;
    private Date fechaCargue;
    private String aprobado;
    private String empresa;

    public Resumenes_eeffController() {
    }

    @PostConstruct
    public void init() {
        listResumenes_eeff = null;
        listResumenes_eeffDetail = null;
        initialData();
    }

    public void initialData() {
        setFechaUltimoCargue(ejbFacadeResumenes.fechaUltimoCargue());
        setEstudiosCargadosPorMes(ejbFacadeResumenes.cargadosMes());
    }

    public Resumenes_eeff getSelected() {
        if (current == null) {
            current = new Resumenes_eeff();
            selectedItemIndex = -1;
        }
        return current;
    }

    private Resumenes_eeffFacade getFacade() {
        return ejbFacadeResumenes;
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

    public String destroy() {
        current = (Resumenes_eeff) getItems().getRowData();
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
            return "ViewDetail";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Resumenes_eeffDeleted"));
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
        return JsfUtil.getSelectItems(ejbFacadeResumenes.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacadeResumenes.findAll(), true);
    }

    public Resumenes_eeff getResumenes_eeff(java.lang.Integer id) {
        return ejbFacadeResumenes.find(id);
    }

    public List<Resumenes_eeff> getListResumenes_eeffDetail() {
        return listResumenes_eeffDetail;
    }

    public void setListResumenes_eeffDetail(List<Resumenes_eeff> listResumenes_eeffDetail) {
        this.listResumenes_eeffDetail = listResumenes_eeffDetail;
    }

    public fachadas.Resumenes_eeffFacade getEjbFacadeResumenes() {
        return ejbFacadeResumenes;
    }

    public void setEjbFacadeResumenes(fachadas.Resumenes_eeffFacade ejbFacadeResumenes) {
        this.ejbFacadeResumenes = ejbFacadeResumenes;
    }

    @FacesConverter(forClass = Resumenes_eeff.class)
    public static class Resumenes_eeffControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Resumenes_eeffController controller = (Resumenes_eeffController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "resumenes_eeffController");
            return controller.getResumenes_eeff(getKey(value));
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
            if (object instanceof Resumenes_eeff) {
                Resumenes_eeff o = (Resumenes_eeff) object;
                return getStringKey(o.getIdResumeneeff());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Resumenes_eeff.class.getName());
            }
        }

    }

    /**
     * Metodo
     *
     * @throws ParseException
     */
    public void getData() throws ParseException {
        RequestContext contex = RequestContext.getCurrentInstance();
        setListResumenes_eeff(ejbFacadeResumenes.consultaCargue(getFechaActa(), getNit(), getFechaCargue(), getAprobado(), getEmpresa()));
        setListResumenesCSV(ejbFacadeResumenes.dataCSV(getFechaActa(), getNit(), getFechaCargue(), getAprobado(), getEmpresa()));
    }

    public void getDataDetail(String nit) throws ParseException {
        //init();
        RequestContext contex = RequestContext.getCurrentInstance();
        FacesContext contexf = FacesContext.getCurrentInstance();
        listResumenes_eeffDetail = ejbFacadeResumenes.consultaDetalle(nit);
        try {
            contexf.getExternalContext().redirect("ViewDetail.xhtml");
        } catch (IOException ex) {
            contex.execute("alert('Error al redireccionar a Informacion Financiera')");
        }
    }
//     public void getDetail() throws ParseException {
//
//        setListResumenes_eeff(ejbFacade.ConsultaDetalle();
//    }

    /**
     * Metodo que se encarga de cambiarle el formato a una fecha por dd/MM/yyyy
     *
     * @param fecha
     * @return
     */
    public String formatDate(Date fecha) {
        if (null == fecha) {
            return "";
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    private long stream(InputStream input, OutputStream output) throws IOException {
        try (ReadableByteChannel inputChannel = Channels.newChannel(input); WritableByteChannel outputChannel = Channels.newChannel(output)) {
            ByteBuffer buffer = ByteBuffer.allocate(10240);
            long size = 0;
            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                size += outputChannel.write(buffer);
                buffer.clear();
            }
            return size;
        }
    }

    public void generateCsvResumenes() throws FileNotFoundException, IOException {
        String fileName = "Reporte Calificacion Cartera.csv";
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType("text/csv");
        ec.setResponseHeader("Content-Disposition", "attachment;filename=" + fileName);
        OutputStream output = ec.getResponseOutputStream();

        List<String> strings = new ArrayList<>();
        strings.add(encabezadoCSV());
        if (getListResumenesCSV() == null || getListResumenesCSV().isEmpty()) {
            strings.add("No se consultaron registros");
            fc.addMessage(null, new FacesMessage("No se cuenta con registros", null));
        } else {
            for (Resumenes_eeff obj : getListResumenesCSV()) {
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

    /**
     * Metodo que se encarga de generar el encabezado del archivo CSV
     *
     * @return
     */
    public String encabezadoCSV() {
        String[] encabezado = {
            "NIT",
            "NOMBRE",
            "SECTOR",
            "RATING",
            "AÑO BALANCE",
            "FECHA DE ACTA",
            "ESTADO APROBACIÓN",
            "INGRESOS NETOS",
            "COSTE DE VENTAS",
            "RESULTADO BRUTO",
            "GASTOS GENERALES",
            "OTROS INGRESOS (GASTOS) OP.",
            "PROVISIONES",
            "RESULTADO EXPLOTACIÓN/EBITDA",
            "MARGEN EBITDA",
            "AMORTIZACIONES",
            "RESULTADO OPERATIVO / EBIT 1",
            "MARGEN EBIT",
            "INGRESOS SOCIEDADES GRUPO",
            "OTROS INGRESOS FINANCIEROS",
            "GASTOS FINANCIEROS",
            "RESULTADO FINANCIERO",
            "AJUSTES CAMBIARIOS / INFLACIÓN",
            "AMORT. FONDO COMERCIO CONSOLIDACION",
            "RESULTADOS PUESTA EN EQUIVALENCIA",
            "RESULTADO ORDINARIO",
            "RESULTADO EXTRAORDINARIO ",
            "RESULTADO ANTES DE IMPUESTOS",
            "IMPUESTOS",
            "RESULTADO NETO",
            "MARGEN NETO",
            "INTERESES MINORITARIOS",
            "RESULTADO ATRIBUIBLE",
            "DIVIDENDOS APROBADOS",
            "BENEFICIO RETENIDO",
            "NO. DE ACCIONES",
            "BENEFICIO POR ACCIÓN",
            "DIVIDENDO POR ACCIÓN",
            "PAYOUT",
            "AÑO BALANCE RO",
            "RESULTADO OPERATIVO / EBIT 2",
            "AMORTIZACIONES / PROVISIONES",
            "VARIACIÓN CAPITAL CIRCULANTE OPERATIVO",
            "OTROS AJUSTES 1",
            "IMPUESTOS RO",
            "CF OPERATIVO (CFO)",
            "CAPEX",
            "DESINVERSIONES POR VENTA DE ACTIVOS",
            "INVERSIONES FINANCIERAS",
            "DESINVERSIONES FINANCIERAS",
            "COBROS FINANCIEROS",
            "PRÉSTAMOS AL GRUPO",
            "OTRAS INVERSIONES/DESINVERSIONES",
            "CF APLICADO A INVERSIÓN (CFI)",
            "CF ANTES DE FINANCIACIÓN (CFAF CFOCFI)",
            "DIVIDENDOS PAGADOS",
            "VARIACIÓN NETA DEUDA A L/P",
            "VARIACIÓN NETA DEUDA A C/P",
            "PAGOS FINANCIEROS (INTERESES)",
            "VARIACIÓN DE RECURSOS PROPIOS",
            "OTROS AJUSTES CF",
            "CF DERIVADO DE FINANCIACIÓN (CFF)",
            "CF DESPUÉS DE FINANCIACIÓN (CFDF  CFAF  CFF)",
            "EFECTO DE LAS DIFERENCIAS DE CAMBIO EN TESORERÍA",
            "AUMENTO/DISMINUCIÓN NETA DE TESORERÍA",
            "CAJA AL INICIO DEL EJERCICIO",
            "CAJA AL FINAL DEL PERÍODO",
            "NET WORKING INVESTMENT",
            "AÑO BALANCE TAF",
            "TESORERÍA  ACTIVOS FÁCILMENTE LÍQUIDABLES",
            "CUENTAS A COBRAR ",
            "EXISTENCIAS",
            "ADMINISTRACIONES PÚBLICAS",
            "OTROS ACTIVOS CIRCULANTES",
            "TOTAL ACTIVO CIRCULANTE",
            "INMOVILIZADO MATERIAL NETO",
            "ACTIVOS INTANGIBLES",
            "INMOVILIZADO FINANCIERO",
            "OTROS ACTIVOS L/P",
            "TOTAL ACTIVO FIJO",
            "TOTAL ACTIVO",
            "ACTIVOS CONTINGENTES",
            "DEUDA FINANCIERA A C/P",
            "DEUDA GRUPO C/P",
            "CUENTAS A PAGAR",
            "CUENTAS A PAGAR A LA ADMINISTRACIÓN",
            "OTROS PASIVOS C/P",
            "TOTAL EXIGIBLE A CORTO",
            "DEUDA FINANCIERA A L/P",
            "DEUDA GRUPO A L/P",
            "OTROS PASIVOS L/P",
            "PROVISIONES TEC",
            "TOTAL EXIGIBLE A LARGO",
            "CAPITAL SOCIAL",
            "RESERVAS ACUMULADAS",
            "SOCIOS EXTERNOS",
            "RECURSOS PROPIOS",
            "TOTAL EXIGIBLE",
            "TOTAL PASIVO",
            "PASIVOS CONTINGENTES",
            //DESDE AQUI SE AGREGARON LAS NUEVAS COLUMNAS
            "INDICADORES",
            "DEUDAFINANCIERABRUTA",
            "DEUDAFINANCIERANETA DFN",
            "CAPITALIZACION FFPP AT",
            "APALANCAMIENTO",
            "DFN FFPP",
            "DFB EBITDA",
            "DFN EBITDA",
            "DFN CFL",
            "LIQUIDEZ",
            "LIQUIDEZACIDA",
            "ROE BNETO FFPP",
            "ROA BNETO AT",
            "EBITDA GASTOSFINANCIEROS",
            "COBERTURADEINTERES CFOPERAT GASTOSFINANCIEROS",
            "CAPITALDETRABAJO ACTIVOCIRCULANTE PASIVOCIRCULANTE",
            "CAPITALDETRABAJO VENTAS",
            "CAPITALDETRABAJOOPERATIVO",
            "CAPITALDETRABAJOOPERATIVO VENTAS",
            "ROTACIONDECLIENTES DIAS",
            "ROTACIONDEINVENTARIOS DIAS",
            "ROTACIONDEPROVEEDORES DIAS",
            "USUARIO",
            "VENTAS",
            "UTILIDAD",
            "TOTAL ACTIVOS",
            "TOTAL PASIVOS",
            "PATRIMONIO",
            "TOTAL INGRESOS",
            "TOTAL GASTOS",
            "ID RESUMENEEFF",
            "FECHAINSERCION"

        };

        Iterator i = Arrays.asList(encabezado).iterator();
        String cabecera = "";
        while (i.hasNext()) {
            cabecera += i.next() + ";";
        }
        cabecera = cabecera.substring(0, cabecera.length() - 1) + "\n";
        return cabecera;
    }

    public String contenidoCSV(Resumenes_eeff obj) {
        StringBuilder fila = new StringBuilder();

        fila.append(validaDato(obj.getResumenes_eeffPK().getNit()));
        fila.append(validaDato(obj.getNombreempresa()));
        fila.append(validaDato(obj.getSector()));
        fila.append(validaDato(obj.getRating()));
        fila.append(validaDato(formatDate(obj.getResumenes_eeffPK().getAniobalance())));
        fila.append(validaDato(formatDate(obj.getFechaacta())));
        fila.append(validaDato(obj.getEstadoaprobacion()));
        fila.append(validaDato(obj.getIngresosnetos()));
        fila.append(validaDato(obj.getCostedeventas()));
        fila.append(validaDato(obj.getResultadobruto()));
        fila.append(validaDato(obj.getGastosgenerales()));
        fila.append(validaDato(obj.getOtrosingresosGastosOp()));
        fila.append(validaDato(obj.getProvisiones()));
        fila.append(validaDato(obj.getResultadoexplotacionEbitda()));
        fila.append(validaDato(obj.getMargenebitda()));
        fila.append(validaDato(obj.getAmortizaciones()));
        fila.append(validaDato(obj.getResultadooperativoEbit()));
        fila.append(validaDato(obj.getMargenebit()));
        fila.append(validaDato(obj.getIngresossociedadesgrupo()));
        fila.append(validaDato(obj.getOtrosingresosfinancieros()));
        fila.append(validaDato(obj.getGastosfinancieros()));
        fila.append(validaDato(obj.getResultadofinanciero()));
        fila.append(validaDato(obj.getAjustescambiariosInflacion()));
        fila.append(validaDato(obj.getAmortFondocomercioconsolidacion()));
        fila.append(validaDato(obj.getResultadospuestaenequivalencia()));
        fila.append(validaDato(obj.getResultadoordinario()));
        fila.append(validaDato(obj.getResultadoextraordinario()));
        fila.append(validaDato(obj.getResultadoantesdeimpuestos()));
        fila.append(validaDato(obj.getImpuestos()));
        fila.append(validaDato(obj.getResultadoneto()));
        fila.append(validaDato(obj.getMargenneto()));
        fila.append(validaDato(obj.getInteresesminoritarios()));
        fila.append(validaDato(obj.getResultadoatribuible()));
        fila.append(validaDato(obj.getDividendosaprobados()));
        fila.append(validaDato(obj.getBeneficioretenido()));
        fila.append(validaDato(obj.getNoDeacciones()));
        fila.append(validaDato(obj.getBeneficioporaccion()));
        fila.append(validaDato(obj.getDividendoporaccion()));
        fila.append(validaDato(obj.getPayOut()));
        fila.append(validaDato(formatDate(obj.getAniobalanceRo())));
        fila.append(validaDato(obj.getResultadoperativoEbit()));
        fila.append(validaDato(obj.getAmortizacionesProvisiones()));
        fila.append(validaDato(obj.getVariacioncapitalcirculanteoperativo()));
        fila.append(validaDato(obj.getOtrosajustesRo()));
        fila.append(validaDato(obj.getImpuestosRo()));
        fila.append(validaDato(obj.getCfoperativoCfo()));
        fila.append(validaDato(obj.getCapex()));
        fila.append(validaDato(obj.getDesinversionesporventadeactivos()));
        fila.append(validaDato(obj.getInversionesfinancieras()));
        fila.append(validaDato(obj.getDesinversionesfinancieras()));
        fila.append(validaDato(obj.getCobrosfinancieros()));
        fila.append(validaDato(obj.getPrestamosalgrupo()));
        fila.append(validaDato(obj.getOtrasinversionesDesinversiones()));
        fila.append(validaDato(obj.getCfaplicadoainversionCfi()));
        fila.append(validaDato(obj.getCfantesdefinanciacionCfafCfoCfi()));
        fila.append(validaDato(obj.getDividendospagados()));
        fila.append(validaDato(obj.getVariacionnetadeudaalP()));
        fila.append(validaDato(obj.getVariacionnetadeudaacP()));
        fila.append(validaDato(obj.getPagosfinancierosIntereses()));
        fila.append(validaDato(obj.getVariacionderecursospropios()));
        fila.append(validaDato(obj.getOtrosajustescf()));
        fila.append(validaDato(obj.getCfderivadodefinanciacionCff()));
        fila.append(validaDato(obj.getCfdespuesdefinanciacionCfdfCfafCff()));
        fila.append(validaDato(obj.getEfectodelasdiferenciasdecambioentesoreria()));
        fila.append(validaDato(obj.getAumentoDisminucionnetadetesoreria()));
        fila.append(validaDato(obj.getCajaaliniciodelejercicio()));
        fila.append(validaDato(obj.getCajaalfinaldelperiodo()));
        fila.append(validaDato(obj.getNetworkinginvestment()));
        fila.append(validaDato(formatDate(obj.getAniobalanceTaf())));
        fila.append(validaDato(obj.getTesoreriaActivosfacilmenteliquidables()));
        fila.append(validaDato(obj.getCuentasacobrarAdt()));
        fila.append(validaDato(obj.getExistencias()));
        fila.append(validaDato(obj.getAdministracionespublicas()));
        fila.append(validaDato(obj.getOtrosactivoscirculantes()));
        fila.append(validaDato(obj.getTotalactivocirculante()));
        fila.append(validaDato(obj.getInmovilizadomaterialneto()));
        fila.append(validaDato(obj.getActivosintangibles()));
        fila.append(validaDato(obj.getInmovilizadofinanciero()));
        fila.append(validaDato(obj.getOtrosactivoslP()));
        fila.append(validaDato(obj.getTotalactivofijo()));
        fila.append(validaDato(obj.getTotalactivo()));
        fila.append(validaDato(obj.getActivoscontingentes()));
        fila.append(validaDato(obj.getDeudafinancieraacP()));
        fila.append(validaDato(obj.getDeudagrupocP()));
        fila.append(validaDato(obj.getCuentasapagar()));
        fila.append(validaDato(obj.getCuentasapagaralaadministracion()));
        fila.append(validaDato(obj.getOtrospasivoscP()));
        fila.append(validaDato(obj.getTotalexigibleacorto()));
        fila.append(validaDato(obj.getDeudafinancieraalP()));
        fila.append(validaDato(obj.getDeudagrupoalP()));
        fila.append(validaDato(obj.getOtrospasivoslP()));
        fila.append(validaDato(obj.getProvisionesTec()));
        fila.append(validaDato(obj.getTotalexigiblealargo()));
        fila.append(validaDato(obj.getCapitalsocial()));
        fila.append(validaDato(obj.getReservasacumuladas()));
        fila.append(validaDato(obj.getSociosexternos()));
        fila.append(validaDato(obj.getRecursospropios()));
        fila.append(validaDato(obj.getTotalexigible()));
        fila.append(validaDato(obj.getTotalpasivo()));
        fila.append(validaDato(obj.getPasivoscontingentes()));
        // Nuevas Columnas
        fila.append(validaDato(obj.getIndicadores()));
        fila.append(validaDato(obj.getDeudafinancierabruta()));
        fila.append(validaDato(obj.getDeudafinancieranetaDfn()));
        fila.append(validaDato(obj.getCapitalizacionFfppAt()));
        fila.append(validaDato(obj.getApalancamiento()));
        fila.append(validaDato(obj.getDfnFfpp()));
        fila.append(validaDato(obj.getDfbEbitda()));
        fila.append(validaDato(obj.getDfnEbitda()));
        fila.append(validaDato(obj.getDfnCfl()));
        fila.append(validaDato(obj.getLiquidez()));
        fila.append(validaDato(obj.getLiquidezacida()));
        fila.append(validaDato(obj.getRoeBnetoFfpp()));
        fila.append(validaDato(obj.getRoaBnetoAt()));
        fila.append(validaDato(obj.getEbitdaGastosfinancieros()));
        fila.append(validaDato(obj.getCoberturadeinteresCfoperatGastosfinancieros()));
        fila.append(validaDato(obj.getCapitaldetrabajoActivocirculantePasivocirculante()));
        fila.append(validaDato(obj.getCapitaldetrabajoVentas()));
        fila.append(validaDato(obj.getCapitaldetrabajooperativo()));
        fila.append(validaDato(obj.getCapitaldetrabajooperativoVentas()));
        fila.append(validaDato(obj.getRotaciondeclientesDias()));
        fila.append(validaDato(obj.getRotaciondeinventariosDias()));
        fila.append(validaDato(obj.getRotaciondeproveedoresDias()));
        fila.append(validaDato(obj.getUsuario()));
        fila.append(validaDato(obj.getVentas()));
        fila.append(validaDato(obj.getUtilidad()));
        fila.append(validaDato(obj.getTotalActivos()));
        fila.append(validaDato(obj.getTotalPasivos()));
        fila.append(validaDato(obj.getPatrimonio()));
        fila.append(validaDato(obj.getTotalIngresos()));
        fila.append(validaDato(obj.getTotalGastos()));
        fila.append(validaDato(obj.getIdResumeneeff()));
        fila.append(validaDato(formatDate(obj.getFechainsercion())));
        return fila.toString();
    }

    public String validaDato(Object valor) {
        return (null == valor) ? "".concat(";") : valor.toString().concat(";");
    }

    public Resumenes_eeff getCurrent() {
        return current;
    }

    public void setCurrent(Resumenes_eeff current) {
        this.current = current;
    }

    public Resumenes_eeffFacade getEjbFacade() {
        return ejbFacadeResumenes;
    }

    public void setEjbFacade(Resumenes_eeffFacade ejbFacadeResumenes) {
        this.ejbFacadeResumenes = ejbFacadeResumenes;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public List<Resumenes_eeff> getListResumenes_eeff() {
        return listResumenes_eeff;
    }

    public void setListResumenes_eeff(List<Resumenes_eeff> listResumenes_eeff) {
        this.listResumenes_eeff = listResumenes_eeff;
    }

    public List<Resumenes_eeff> getListResumenesCSV() {
        return listResumenesCSV;
    }

    public void setListResumenesCSV(List<Resumenes_eeff> listResumenesCSV) {
        this.listResumenesCSV = listResumenesCSV;
    }

    public String getFechaUltimoCargue() {
        return fechaUltimoCargue;
    }

    public void setFechaUltimoCargue(String fechaUltimoCargue) {
        this.fechaUltimoCargue = fechaUltimoCargue;
    }

    public Long getEstudiosCargadosPorMes() {
        return estudiosCargadosPorMes;
    }

    public void setEstudiosCargadosPorMes(Long estudiosCargadosPorMes) {
        this.estudiosCargadosPorMes = estudiosCargadosPorMes;
    }

    public Date getFechaActa() {
        return fechaActa;
    }

    public void setFechaActa(Date fechaActa) {
        this.fechaActa = fechaActa;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public Date getFechaCargue() {
        return fechaCargue;
    }

    public void setFechaCargue(Date fechaCargue) {
        this.fechaCargue = fechaCargue;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
