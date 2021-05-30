/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.time.LocalDate;
import java.util.Calendar;

/**
 *
 * @author x332015
 */
public class DateMaxUtil {

    public static String calculateDayMaxMonth(LocalDate date) {

        Integer año = date.getYear();
        Integer mes = date.getMonthValue();
        Integer dia = date.getDayOfMonth();

        Calendar calendario = Calendar.getInstance();
        calendario.set(año, mes - 2, dia);

        int diaMax = calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
        mes = mes - 1;
        String mesS = "";
        if (mes < 10) {
            mesS = "0" + mes;
        } else {
            mesS = mes.toString();
        }

        String fechaGeneratedCsv = año + "-" + mesS + "-" + diaMax;
        return fechaGeneratedCsv;
    }
}
