/*
 * Utilidades para numeros
 */
package com.apioriente.appsoporte.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Jaasiel
 */
public class NumeroUtil {

    private NumeroUtil() {

    }

    /**
     * Formatear numero con comas
     *
     * @param value double a formatear
     * @return un String formateado con comas
     */
    public static String formatearConComas(double value) {
        DecimalFormat df = new DecimalFormat("###,###,###.00");
        return df.format(value);
    }

    /**
     * Formatear numero con comas
     *
     * @param value long a formatear
     * @return un String formateado con comas
     */
    public static String formatearConComas(long value) {
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(value);
    }

    /**
     * Formatear el numero a porcentaje local
     *
     * @param value numero double a formatear
     * @return un String formateado a porcentaje local
     */
    public static String formatearAPorcentaje(double value) {
        NumberFormat nf = NumberFormat.getPercentInstance();
        return nf.format(value);
    }

    /**
     * Formatear el numero a moneda local
     *
     * @param value numero double a formatear
     * @return un String formateado a moneda local
     */
    public static String formatearAMoneda(double value) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(value);
    }

    /**
     * Redondear un numero decimal
     *
     * @param numero el numero a redondear
     * @param cantidadDecimales cantidad de decimales requeridos
     * @return un double con el numero redondeado
     */
    public static Double redondear(Double numero, Integer cantidadDecimales) {
        BigDecimal bigDecimal = new BigDecimal(numero);
        bigDecimal = bigDecimal.setScale(cantidadDecimales, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    /**
     * Calcular una diferencia restando el valorMenor al valorMayor
     *
     * @param valorMenor
     * @param valorMayor
     * @return el resultado de la operacion valorMayor - valorMenor
     */
    public static Integer calcularDiferencia(int valorMenor, int valorMayor) {
        return valorMayor - valorMenor;
    }
}
