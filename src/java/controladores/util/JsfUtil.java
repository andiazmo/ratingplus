package controladores.util;

import bsnc.util.excel.ExcelHelper;
import entidades.RpUsuarios;
import entidades.Vclientes;
import fachadas.VClienteFacade;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import session.RescateSeccion;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.model.SelectItem;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.codec.binary.Base64;

public class JsfUtil {

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        FacesContext.getCurrentInstance().validationFailed(); // Invalidate JSF page if we raise an error message

    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static Throwable getRootCause(Throwable cause) {
        if (cause != null) {
            Throwable source = cause.getCause();
            if (source != null) {
                return getRootCause(source);
            } else {
                return cause;
            }
        }
        return null;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static boolean isDummySelectItem(UIComponent component, String value) {
        for (UIComponent children : component.getChildren()) {
            if (children instanceof UISelectItem) {
                UISelectItem item = (UISelectItem) children;
                if ((item.getItemValue() != null) || (!item.getItemLabel().equals(value))) {
                    break;
                }
                return true;
            }
        }
        return false;
    }
    
    public static String getComponentMessages(String clientComponent, String defaultMessage) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent component = UIComponent.getCurrentComponent(fc).findComponent(clientComponent);
        if (component instanceof UIInput) {
            UIInput inputComponent = (UIInput) component;
            if (inputComponent.isValid()) {
                return defaultMessage;
            } else {
                Iterator<FacesMessage> iter = fc.getMessages(inputComponent.getClientId());
                if (iter.hasNext()) {
                    return ((FacesMessage) iter.next()).getDetail();
                }
            }
        }
        return "";
    }

    public static RpUsuarios Usuario() {
        ELContext contextoEL = FacesContext.getCurrentInstance().getELContext();
        Application apli = FacesContext.getCurrentInstance().getApplication();
        ExpressionFactory ef = apli.getExpressionFactory();
        ValueExpression ve = ef.createValueExpression(contextoEL, "#{rescateSeccion}", RescateSeccion.class);
        RescateSeccion sec = (RescateSeccion) ve.getValue(contextoEL);
        return sec.Usuario();
    }

    public static boolean isNumero(String posibleNumero) {
        if (posibleNumero.matches("\\d*")) {
            return true;
        }
        return false;
    }

    public static int comparaFecha(Date fecha1, Date fecha2) {
        if (fecha1.getTime() > fecha2.getTime()) {
            return 1;
        } else if (fecha2.getTime() > fecha1.getTime()) {
            return 2;
        } else {
            return 0;
        }
    }

    public static String numeroFormateado(Double numero) {
        NumberFormat formatoNumero = NumberFormat.getNumberInstance();
        formatoNumero.setMaximumFractionDigits(2);
        return formatoNumero.format(numero);
    }

    public static int compararFechasSinTiempo(Date fecha1, Date fecha2) {
        try {
            /**
             * Obtenemos las fechas enviadas en el formato a comparar
             */
            SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate1 = formateador.parse(formateador.format(fecha1));
            Date fechaDate2 = formateador.parse(formateador.format(fecha2));
            // es menor
            if (fechaDate1.before(fechaDate2)) {
                return 1;
            } else {
                //es mayor
                if (fechaDate2.before(fechaDate1)) {
                    return 2;
                } else {
                    //es igual
                    return 0;
                }
            }
        } catch (ParseException e) {
            System.out.println("Se Produjo un Error!!!  " + e.getMessage());
            return 3;
        }

    }

    public static void reportePdf(JRBeanCollectionDataSource beanCollectionDataSource, HashMap hash, String reporte, String nombre) {
        try {
            String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(reporte);
            JasperPrint jasperPrint;

            jasperPrint = JasperFillManager.fillReport(reporte, hash, beanCollectionDataSource);
            HttpServletResponse response = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nombre + ".pdf");
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

                outputStream.flush();
            } catch (IOException ex) {
                System.out.println(ex);
            } catch (JRException ex) {
                System.out.println(ex);
            }
            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void reporteXLS(JRBeanCollectionDataSource beanCollectionDataSource, HashMap hash, String reporte, String nombre) {
        try {
            JasperReport report = JasperCompileManager.compileReport(reporte.replace(".jasper", ".jrxml"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, hash, beanCollectionDataSource);

//        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, hash , beanCollectionDataSource);
            HttpServletResponse response = (HttpServletResponse) FacesContext
                    .getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/xls");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + nombre + ".xls");

            JExcelApiExporter exporter = new JExcelApiExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
            exporter.exportReport();
            try {
                outputStream.flush();
                outputStream.close();
                FacesContext.getCurrentInstance().responseComplete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (JRException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void reporteXLS(List<Vclientes> vclientes, String nombre) {
        VClienteFacade clienteFacade = new VClienteFacade();
        Object[][] records = clienteFacade.convertListToObjectArray(vclientes);

        ExcelHelper.execute(records, nombre);

        HttpServletResponse response = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        response.setContentType("application/xlsx");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + nombre + ".xlsx\"");

        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try {
            outputStream.flush();
            outputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }

//    } catch (JRException ex) {
//        Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
//    }
    }

    public static void downloadFile(List<Vclientes> vclientes, String nombre) {
        VClienteFacade clienteFacade = new VClienteFacade();
        Object[][] records = clienteFacade.convertListToObjectArray(vclientes);

        ExcelHelper.execute(records, nombre);

        File file = new File(nombre + ".xlsx");
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        response.setHeader("Content-Disposition", "attachment;filename=informe.xlsx");
        response.setContentLength((int) file.length());
        ServletOutputStream out = null;
        try {
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            out = response.getOutputStream();
            int i = 0;
            while ((i = input.read(buffer)) != -1) {
                out.write(buffer);
                out.flush();
            }
            FacesContext.getCurrentInstance().getResponseComplete();
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }

    }

    public static String encriptar(String password) {
        String secretKey = "lasangredeCRISTOtienepoder"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = password.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String desencriptar(String password) {
        String secretKey = "lasangredeCRISTOtienepoder"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(password.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public static String fechaFormateada(Date fecha, String formato) {
        SimpleDateFormat sm = new SimpleDateFormat(formato);
        return sm.format(fecha);
    }

    public static Date fechaApartirDeTexto(String entrada) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.parse(entrada);
        } catch (ParseException ex) {
            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }

    public static Double doubleApartirDeTexto(String valor) {
        return Double.parseDouble(valor);
    }

    /**
     * Metodo que retorna un aproximado de la diferencia entre dos fechas.
     *
     * @param fechaCliente
     * @param fechaLimite
     * @return Aproximado de la diferencia entre dos fechas.
     */
    public static String DiasRestantes(Date fechaCliente, Date fechaLimite) {
        String resta;
        long startTime = fechaCliente.getTime();
        long endTime = fechaLimite.getTime();
        long diffTime = Math.abs(endTime - startTime);
        int dias = (int) TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
        if (dias > 30) {
            int meses = dias / 30;
            resta = meses > 1 ? meses + " meses" : meses + " mes";
            if (meses >= 12) {
                int anio = meses / 12;
                resta = anio > 1 ? anio + " años" : anio + " año";
            }
        } else {
            resta = dias > 1 ? dias + " dias" : dias + " dia";
        }
        return resta;
    }
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
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
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

}