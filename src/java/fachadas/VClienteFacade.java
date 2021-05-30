/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import bsnc.util.Util;
import entidades.Vclientes;
import java.util.List;

/**
 *
 * @author User
 */
public class VClienteFacade {
    
    private final String[] heads = {"NN", "NIT", "DV", "NOMBRE", "CIIU", 
        "RATING", "BANCA","ESTADO", "Estado Reconduccion", "ALTA CLIENTE", 
        "GESTOR COMERCIAL", 
        "FECHA RATING", "ESTADO FEVE", "VALOR ACTIVO", 
        "GRUPO ECONOMICO", "FECHA BALANCE", "FECHA ALTA CUPO", 
        "FECHA VENCIMIENTO", "MONEDA", "LIMITE TOTAL", "LIMITE CONSUMIDO"};
    
    public Object[][] convertListToObjectArray(List<Vclientes> vclientes){
        Object[][] result = new Object[vclientes.size()+1][];
        result[0] = heads;
        
        int i = 1;
        for(Vclientes vcliente: vclientes){
            result[i++]= convertVClientesToStringArray(vcliente);
        }
        
        return result;
    }

    public String[] convertVClientesToStringArray(Vclientes vcliente) {
        String[] result  = new String[heads.length];
        
        result[0] = Util.validateNull(vcliente.getNn());
        result[1] = Util.validateNull(vcliente.getNit());
        result[2] = Util.validateNull(vcliente.getDigitochequeo());
        result[3] = Util.validateNull(vcliente.getNombre());
        result[4] = Util.validateNull(vcliente.getCiiu());
        result[5] = Util.validateNull(Util.roundStr(vcliente.getRating().doubleValue(), 2));
        result[6] = Util.validateNull(vcliente.getBanca());
        result[7] = Util.validateNull(vcliente.getEstadocliente());
        result[8] = Util.validateNull(vcliente.getReconduccion());
        result[9] = Util.validateNull(Util.getFecha(vcliente.getDesde()));
        result[10] = Util.validateNull(vcliente.getGestor());
        result[11] = Util.validateNull(Util.getFecha(vcliente.getFecharating()));
        result[12] = Util.validateNull(vcliente.getEstadofeve());
        result[13] = Util.validateNull(Util.roundStr(vcliente.getValoractivo(), 2));
        result[14] = Util.validateNull(vcliente.getGrupo());
        result[15] = Util.validateNull(Util.getFecha(vcliente.getFechabalance()));
        result[16] = Util.validateNull(Util.getFecha(vcliente.getFechaalta()));
        result[17] = Util.validateNull(Util.getFecha(vcliente.getFechavencimiento()));
        result[18] = Util.validateNull(vcliente.getMoneda());
        
        
        if(vcliente.getLimitetotal()!=null){
            result[19] = Util.roundStr(vcliente.getLimitetotal(), 2);
        }
        else{
            result[19] = "";
        }
        
        if(vcliente.getLimiteconsumido()!=null){
            result[19] = Util.roundStr(vcliente.getLimiteconsumido(), 2);
        }
        else{
            result[19] = "";
        }
        
        return result;
    }
    
    
}
