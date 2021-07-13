/**
 * InformacionComercialWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.asobancaria.cifin.infocomercial;

public class InformacionComercialWSServiceLocator extends org.apache.axis.client.Service implements com.asobancaria.cifin.infocomercial.InformacionComercialWSService {

    public InformacionComercialWSServiceLocator() {
    }
    
    public InformacionComercialWSServiceLocator( String url ) {
        InformacionComercial_address = url;
    }

    public InformacionComercialWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InformacionComercialWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InformacionComercial
    private java.lang.String InformacionComercial_address = "https://ttuweb3.universo.corp/InformacionComercialWS/services/InformacionComercial";

    public java.lang.String getInformacionComercialAddress() {
        return InformacionComercial_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InformacionComercialWSDDServiceName = "InformacionComercial";

    public java.lang.String getInformacionComercialWSDDServiceName() {
        return InformacionComercialWSDDServiceName;
    }

    public void setInformacionComercialWSDDServiceName(java.lang.String name) {
        InformacionComercialWSDDServiceName = name;
    }

    public com.asobancaria.cifin.infocomercial.InformacionComercialWS getInformacionComercial() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            System.out.println("EndPoint:::"+InformacionComercial_address);
            endpoint = new java.net.URL(InformacionComercial_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInformacionComercial(endpoint);
    }
    
    public static void test(){
        
    }

    public com.asobancaria.cifin.infocomercial.InformacionComercialWS getInformacionComercial(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            System.out.println("this:::"+this);
            com.asobancaria.cifin.infocomercial.InformacionComercialSoapBindingStub _stub = new com.asobancaria.cifin.infocomercial.InformacionComercialSoapBindingStub(portAddress, this);
            _stub.setPortName(getInformacionComercialWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInformacionComercialEndpointAddress(java.lang.String address) {
        InformacionComercial_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.asobancaria.cifin.infocomercial.InformacionComercialWS.class.isAssignableFrom(serviceEndpointInterface)) {
                com.asobancaria.cifin.infocomercial.InformacionComercialSoapBindingStub _stub = new com.asobancaria.cifin.infocomercial.InformacionComercialSoapBindingStub(new java.net.URL(InformacionComercial_address), this);
                _stub.setPortName(getInformacionComercialWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InformacionComercial".equals(inputPortName)) {
            return getInformacionComercial();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://infocomercial.cifin.asobancaria.com", "InformacionComercialWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://infocomercial.cifin.asobancaria.com", "InformacionComercial"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InformacionComercial".equals(portName)) {
            setInformacionComercialEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
