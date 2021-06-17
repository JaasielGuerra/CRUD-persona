/*
 * Validar campos JTextField
 */
package com.apioriente.appsoporte.util;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Jaasiel
 */
public class ValidarJTextField {

    private static final Color defaultColor = UIManager.getColor("JTextField.background");
    private static final Color failedColor = Color.pink;

    private ValidarJTextField() {

    }

    /**
     * Reinicia los valores y el color a los JTextField
     *
     * @param campos
     */
    public static void resetValues(JTextField[] campos) {
        for (JTextField campo : campos) {
            campo.setBackground(defaultColor);
            campo.setText("");
        }
    }

    /**
     * Reiniciar color de los campos
     *
     * @param campos
     */
    public static void resetColor(JTextField[] campos) {
        for (JTextField campo : campos) {
            campo.setBackground(defaultColor);
        }
    }

    /**
     * Valida si algun JTextField esta vacio
     *
     * @param campos
     * @return true en caso de estar vacio, false en caso contrario
     */
    public static boolean camposVacios(JTextField... campos) {
        boolean vacio = false;

        for (JTextField campo : campos) {
            if (campo.getText().isEmpty()) {
                vacio = true;
                campo.setBackground(failedColor);
            } else {
                campo.setBackground(defaultColor);
            }
        }

        return vacio;
    }

    /**
     * validar un JTextField para saber si esta vacio
     *
     * @param campo
     * @return devuelte true si el campo es vacio, false en caso contrario
     */
    public static boolean campoVacio(JTextField campo) {
        boolean vacio = false;

        if (campo.getText().isEmpty()) {
            vacio = true;
            campo.setBackground(failedColor);
        } else {
            campo.setBackground(defaultColor);
        }

        return vacio;
    }
}
