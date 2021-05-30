/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 --------------------------------------------------------------------------------
 *Proyecto : BSNC-18-0119 - Cupos Auditoria Dual 2018
 *Programador: Wittman Gutiérrez
 *Fecha del creacion : 26-07-2018
 --------------------------------------------------------------------------------
 */
package constantes;

/**
 *
 * @author Wittman Gutiérrez
 */
public class Constantes {
    /* Acciones sobre cupos */

    public static final String ACCION_AUTORIZA = "Autorizacion";
    public static final String ACCION_MODIFICA = "Modificacion";
    public static final String ACCION_CREA = "Creacion";
    /* Submodulos */
    public static final String MODULO_AUTOR_CUPOS = "Autoriza Cupos";
    public static final String MODULO_MODIF_CUPOS = "Modificacion de Cupos";
    public static final String MODULO_CREA_CUPOS = "Creacion de Cupos";
    
    
    //Codigos tablas parametria Grupos Economicos
    public static final String CODIGO_TABLA_GRUPO = "0009";
    public static final String CODIGO_TABLA_PAIS = "0005";
    public static final String CODIGO_TABLA_ACTIVIDAD = "0002";
    public static final String CODIGO_TABLA_SUCURSAL = "0008";
    public static final String CODIGO_TABLA_FORMA_JURIDICA = "0003";
    public static final String CODIGO_TABLA_SECTOR_ECONOMICO = "0007";
    public static final String CODIGO_MODO_OPERACION = "0004";
    public static final String CODIGO_TABLA_TIPO_VINCULO = "0011";
    public static final String CODIGO_TABLA_TIPO_RELACION = "0010";
    public static final String CODIGO_TABLA_ROL_JERARQUICO = "0006";
    
    //Codigos entidades parametria Grupos Economicos.
    public static final String CODIGO_ENTIDAD_PAIS = "0065";
    
    //Nombre reservado Grupo Economico
    public static final String NOMBRE_RESERVADO_GRUPO = "No Asignado";
    
    //Roles de Grupos Economicos;
    public static final String NOMBRE_ROL_RIESGOS = "Riesgos";
    public static final String NOMBRE_ROL_COMERCIAL = "Comercial";
    
    //Constante de Parametro limite de valor garantia.
    public static final String LIMITE_VALOR_GARANTIA = "LimiteVGarantia";
    
    
    //Constantes SubStandar
    public static final String ESTADO_SUBSTANDAR_DEFAULT = "Normal";
    public static final String DESCRIPCION_SUBSTANDAR_CW = "Estado SubStandar Normal asignado al crear el cliente en CW.";
}
