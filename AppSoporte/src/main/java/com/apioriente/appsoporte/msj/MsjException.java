/*
 * Mensajes comunes de excepciones
 */
package com.apioriente.appsoporte.msj;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Jaasiel
 */
public class MsjException {

    private MsjException() {

    }

    /**
     * Mensaje de error al crear un registro
     *
     * @param componentePadre
     * @param msjException
     */
    public static void msjErrorGuardar(Component componentePadre, String msjException) {
        JOptionPane.showMessageDialog(componentePadre,
                "Ocurrió un error al registrar:\n" + msjException, "Error interno",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Mensaje de error al actualizar
     *
     * @param componentePadre
     * @param msjException
     */
    public static void msjErrorActualizar(Component componentePadre, String msjException) {
        JOptionPane.showMessageDialog(componentePadre,
                "Ocurrió un error al actualizar:\n" + msjException, "Error interno",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Mensaje de error al eliminar registro
     *
     * @param componentePadre
     * @param msjException
     */
    public static void msjErrorEliminar(Component componentePadre, String msjException) {
        JOptionPane.showMessageDialog(componentePadre,
                "Ocurrió un error al eliminar:\n" + msjException, "Error interno",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Mostrar mensaje de error estandar
     *
     * @param componentePadre
     * @param msjException
     */
    public static void msjErrorEstandar(Component componentePadre, String msjException) {
        JOptionPane.showMessageDialog(componentePadre,
                "Ocurrió un error en la acción ejecutada:\n" + msjException, "Error interno",
                JOptionPane.ERROR_MESSAGE);
    }
}
