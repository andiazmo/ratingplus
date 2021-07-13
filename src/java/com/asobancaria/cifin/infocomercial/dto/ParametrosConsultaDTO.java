/**
 * ParametrosConsultaDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.asobancaria.cifin.infocomercial.dto;

public class ParametrosConsultaDTO  implements java.io.Serializable {
    private java.lang.String codigoInformacion;

    private java.lang.String motivoConsulta;

    private java.lang.String numeroIdentificacion;

    private java.lang.String tipoIdentificacion;

    public ParametrosConsultaDTO() {
    }

    public ParametrosConsultaDTO(
           java.lang.String codigoInformacion,
           java.lang.String motivoConsulta,
           java.lang.String numeroIdentificacion,
           java.lang.String tipoIdentificacion) {
           this.codigoInformacion = codigoInformacion;
           this.motivoConsulta = motivoConsulta;
           this.numeroIdentificacion = numeroIdentificacion;
           this.tipoIdentificacion = tipoIdentificacion;
    }


    /**
     * Gets the codigoInformacion value for this ParametrosConsultaDTO.
     * 
     * @return codigoInformacion
     */
    public java.lang.String getCodigoInformacion() {
        return codigoInformacion;
    }


    /**
     * Sets the codigoInformacion value for this ParametrosConsultaDTO.
     * 
     * @param codigoInformacion
     */
    public void setCodigoInformacion(java.lang.String codigoInformacion) {
        this.codigoInformacion = codigoInformacion;
    }


    /**
     * Gets the motivoConsulta value for this ParametrosConsultaDTO.
     * 
     * @return motivoConsulta
     */
    public java.lang.String getMotivoConsulta() {
        return motivoConsulta;
    }


    /**
     * Sets the motivoConsulta value for this ParametrosConsultaDTO.
     * 
     * @param motivoConsulta
     */
    public void setMotivoConsulta(java.lang.String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }


    /**
     * Gets the numeroIdentificacion value for this ParametrosConsultaDTO.
     * 
     * @return numeroIdentificacion
     */
    public java.lang.String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }


    /**
     * Sets the numeroIdentificacion value for this ParametrosConsultaDTO.
     * 
     * @param numeroIdentificacion
     */
    public void setNumeroIdentificacion(java.lang.String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }


    /**
     * Gets the tipoIdentificacion value for this ParametrosConsultaDTO.
     * 
     * @return tipoIdentificacion
     */
    public java.lang.String getTipoIdentificacion() {
        return tipoIdentificacion;
    }


    /**
     * Sets the tipoIdentificacion value for this ParametrosConsultaDTO.
     * 
     * @param tipoIdentificacion
     */
    public void setTipoIdentificacion(java.lang.String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ParametrosConsultaDTO)) return false;
        ParametrosConsultaDTO other = (ParametrosConsultaDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoInformacion==null && other.getCodigoInformacion()==null) || 
             (this.codigoInformacion!=null &&
              this.codigoInformacion.equals(other.getCodigoInformacion()))) &&
            ((this.motivoConsulta==null && other.getMotivoConsulta()==null) || 
             (this.motivoConsulta!=null &&
              this.motivoConsulta.equals(other.getMotivoConsulta()))) &&
            ((this.numeroIdentificacion==null && other.getNumeroIdentificacion()==null) || 
             (this.numeroIdentificacion!=null &&
              this.numeroIdentificacion.equals(other.getNumeroIdentificacion()))) &&
            ((this.tipoIdentificacion==null && other.getTipoIdentificacion()==null) || 
             (this.tipoIdentificacion!=null &&
              this.tipoIdentificacion.equals(other.getTipoIdentificacion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodigoInformacion() != null) {
            _hashCode += getCodigoInformacion().hashCode();
        }
        if (getMotivoConsulta() != null) {
            _hashCode += getMotivoConsulta().hashCode();
        }
        if (getNumeroIdentificacion() != null) {
            _hashCode += getNumeroIdentificacion().hashCode();
        }
        if (getTipoIdentificacion() != null) {
            _hashCode += getTipoIdentificacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ParametrosConsultaDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dto.infocomercial.cifin.asobancaria.com", "ParametrosConsultaDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoInformacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoInformacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("motivoConsulta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "motivoConsulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroIdentificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroIdentificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoIdentificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
