/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

/**
 *
 * @author aecr
 */
public class DV {
    public static byte generarDv(long nit) {        
        int[] nums = {
            3, 7, 13, 17, 19, 23, 29, 37, 41, 43, 47, 53, 59, 67, 71
        };

        int sum = 0;

        String str = String.valueOf(nit);
        for (int i = str.length() - 1, j=0; i >= 0; i--, j++) {
            sum += Character.digit(str.charAt(i), 10) * nums[j];
        }

        byte dv = (byte)((sum % 11) > 1 ? (11 - (sum % 11)) : (sum % 11));
        return dv;
}
}
