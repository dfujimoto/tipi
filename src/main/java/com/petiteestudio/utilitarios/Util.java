package com.petiteestudio.utilitarios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

    public static String toDDMMYYYY(Date date){
        if (isEmpty(date)){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String fechaComoCadena = sdf.format(date);

            return fechaComoCadena;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toYYYYMMDD(Date date){
        if (isEmpty(date)){
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fechaComoCadena = sdf.format(date);

            return fechaComoCadena;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getDate(){
        Date fecha = new Date();
        return fecha;
    }

    public static Long toLong(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Long.parseLong(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a integer.
     *
     */
    public static Integer toInteger(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Integer.parseInt(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a short.
     * 
     */
    public static Short toShort(Object objeto) {
        if (isEmpty( objeto )) {
            return null;
        }
        try {
            return Short.parseShort( objeto.toString() );
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Metodo convierte una cadena a double.
     * 
     */
    public static Double toDouble(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Double.parseDouble(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte una cadena a float.
     *
     */
    public static Float toFloat(Object objeto) {
        if (isEmpty(objeto)) {
            return null;
        }
        try {
            return Float.parseFloat(objeto.toString().replaceAll("\\,", ""));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo convierte un objeto a cadena
     * 
     * @param cadena
     * @return
     */
    public static String toStr(Object cadena) {
        return isEmpty(cadena) ? null : toBlank(cadena.toString());
    }

    /**
     * Metodo devuelve una cadena formateada.
     * 
     * @param cadena
     * @return
     */
    public static String toBlank(String cadena) {
        return isEmpty(cadena) ? "" : cadena;
    }

    /**
     * Metodo devuelve una cadena, cadena vacia si el objeto es null (uso para
     * grilla).
     * 
     * @param object
     * @return
     */
    public static String toBlankObject(Object object) {
        return isEmpty(object) ? "" : object.toString();
    }

    /**
     * Verifica si un objecto es vacio: 
     * - Object: Verifica si es nulo (o vacio de ser List)
     * - String: El valor es nulo o vacio
     * 
     * @author esalazar
     * @since 18.09.2013
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof String) {
            return object.toString().trim().length() == 0;
        }
        if (object instanceof StringBuilder) {
            return object.toString().trim().length() == 0;
        }
        if (object instanceof List<?> || object instanceof ArrayList<?>) {
            return ((List<?>) object).isEmpty();
        }
        return false;
    }

    /**
     * Verifica si todas las posiciones de un array son vacios.
     *
     */
    public static boolean isEmptyArray(Object[] array) {
        if (array == null) {
            return true;
        }
        if (array.length == 0) {
            return true;
        }
        for (Object object : array) {
            if (!isEmpty(object)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Compara si el valor de dos objetos es igual
     *
     */
    public static boolean equiv(Object object1, Object object2) {
        if (isEmpty(object1) && !isEmpty(object2) || !isEmpty(object1) && isEmpty(object2)) {
            return false;
        }
        if (isEmpty(object1) && isEmpty(object2) || object1 == object2) {
            return true;
        }

        if (object1 instanceof String && object2 instanceof String) {
            return toBlank(object1.toString()).equals(toBlank(object2.toString()));
        }
        return object1.equals(object2);
    }

    /**
     * Metodo busca una cadena en una arreglo de cadenas.
     */
    public static boolean inList(String cadena, String... valores) {
        for (String valor : valores) {
            //Inicio [Req 15-031] jtomasto 20.05.2015
            if(equiv(cadena, valor)){
             //fin [Req 15-031]
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que elimina una saltos de l�nea en una cadena.
     */
    public static String quitarSaltosLinea(String cadena){
        if(!isEmpty(cadena)){
            return cadena.replaceAll("[\n\r]","");
        }
        
        return cadena;
    }
    
    /**
     * Metodo que devuelve un subString desde la posicion 0 hasta una posicion final,
     *
     */
    public static String subStr(String cadena, int indexFin){
       return subStr(cadena, 0, indexFin);
    }
    
    /**
     * Metodo que devuelve un subString desde una posicion inicial hasta una posicion final,
     */
    public static String subStr(String cadena, int indexInicio, int indexFin){
       if(isEmpty(cadena)){
          return cadena;
       }
       int posicionFinal = cadena.length() - 1;
       if( posicionFinal < indexInicio){
          return null;
       }
       if(posicionFinal < indexFin){
          return cadena.substring(indexInicio);
       }
       
       return cadena.substring(indexInicio, indexFin);
    }
}
