package com.aiti.preauthorizer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que tiene funciones de
 *     proposito general
 *
 * @author Angel Contreras Torres
 * @since  13-10-2016
 */

public class GeneralUtils {

    public static String maskPan(String pan){
        String maskPan = "";
        if( pan != null && pan.length() >= 16 ){
            String bin = pan.substring( 0, 4 );
            String lasDigits = pan.substring( 12, 16 );

            maskPan = bin + "  ****  ****  " + lasDigits;
        }
        return maskPan;
    }

    public static String getTime(String format){
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
