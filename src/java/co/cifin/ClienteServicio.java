/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.cifin;

import com.asobancaria.cifin.infocomercial.InformacionComercialSoapBindingStub;
import com.asobancaria.cifin.infocomercial.InformacionComercialWSServiceLocator;
import com.asobancaria.cifin.infocomercial.dto.ParametrosConsultaDTO;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceException;

public class ClienteServicio {
    public static void main (String args[]) throws MalformedURLException {
        try {
            InformacionComercialWSServiceLocator locator = 
                    new InformacionComercialWSServiceLocator("https://cifinpruebas.asobancaria.com/InformacionComercialWS/services/InformacionComercial?wsdl");
            String InformacionComercial_address = "https://cifinpruebas.asobancaria.com/InformacionComercialWS/services/InformacionComercial?wsdl";
            java.net.URL endpoint;
            endpoint = new java.net.URL(InformacionComercial_address);
            InformacionComercialSoapBindingStub stub = (InformacionComercialSoapBindingStub) locator.getInformacionComercial();
            System.out.println("stub:::"+stub);
            ParametrosConsultaDTO parametros = new ParametrosConsultaDTO();
            parametros.setCodigoInformacion("154");
            parametros.setMotivoConsulta("24");
            parametros.setNumeroIdentificacion("52325582");
            parametros.setTipoIdentificacion("1");
            stub.setUsername("276341");
            stub.setPassword("RATP12");
            String respXml = stub.consultaXml(parametros);
            System.out.println( respXml );
        }
        catch (ServiceException ex) {
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RemoteException ex) {
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
