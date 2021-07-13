/**
 * InformacionComercialWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.asobancaria.cifin.infocomercial;

public interface InformacionComercialWS extends java.rmi.Remote {
    public java.lang.String consultaXml(com.asobancaria.cifin.infocomercial.dto.ParametrosConsultaDTO in0) throws java.rmi.RemoteException;
    public java.lang.String consultaPlano(com.asobancaria.cifin.infocomercial.dto.ParametrosConsultaDTO in0) throws java.rmi.RemoteException;
    public java.lang.String cambioPassword(java.lang.String in0) throws java.rmi.RemoteException;
}
