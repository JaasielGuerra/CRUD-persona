/*
 * Mensajes de informacion comunes para el usuario
 */
package com.apioriente.appsoporte.msj;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Jaasiel
 */
public class MsjInfo {

    private MsjInfo() {

    }

    /**
     * Muestra un mensaje indicando que no hay existencia del artículo
     *
     * @param padre
     * @param existencia
     */
    public static void msjNoExistenciaArticulo(Component padre, Integer existencia) {
        JOptionPane.showMessageDialog(padre, "No hay existencia. [Existencia=" + existencia + "]",
                "Sin existencia", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra un mensaje indicando que no hay suficiente stock
     *
     * @param padre
     * @param existencia
     */
    public static void msjStockInsufuciente(Component padre, Integer existencia) {
        JOptionPane.showMessageDialog(padre, "No hay suficiente existencia. [Existencia=" + existencia + "]",
                "Existencia insuficiente", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Indica que una deuda no tiene saldo pendiente
     *
     * @param padre
     */
    public static void msjNoSaldoPendiente(Component padre) {
        JOptionPane.showMessageDialog(padre, "No hay saldo pendiente para abonar.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Indica que una venta ya esta cancelada
     *
     * @param padre
     */
    public static void msjVentaYaCancelada(Component padre) {
        JOptionPane.showMessageDialog(padre, "Ya ha sido cancelada.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje indicando exito en el proceso
     *
     * @param padre
     */
    public static void msjExitoEstandar(Component padre) {
        JOptionPane.showMessageDialog(padre, "Se ha realizado con éxito.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje indicando que ya existe una instancia de la aplicacion
     *
     * @param padre
     */
    public static void msjExisteInstancia(Component padre) {
        JOptionPane.showMessageDialog(padre, "Ya hay una instancia en ejecución.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje que indica que todo salio con exito al cerrar el
     * sistema
     *
     * @param padre
     */
    public static void msjExitoCierreSistema(Component padre) {
        JOptionPane.showMessageDialog(padre, "Procesos realizados con éxito, pulse ACEPTAR para terminar.",
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mensaje para indicar que debe seleccionar una fila de la tabla
     *
     * @param padre
     */
    public static void msjSeleccioneFila(Component padre) {
        JOptionPane.showMessageDialog(padre, "Primero debe seleccionar una fila de la tabla.",
                "Selección", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de registro exitoso
     *
     * @param padre
     */
    public static void msjRegistroExitoso(Component padre) {
        JOptionPane.showMessageDialog(padre, "Se ha registrado con éxito.",
                "Registro", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mensaje de informacion de actualizacion
     *
     * @param padre
     */
    public static void msjActualizacionExitosa(Component padre) {
        JOptionPane.showMessageDialog(padre, "Se ha actualizado con éxito.",
                "Registro", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mostrar mensaje indicando que un registro esta activado
     *
     * @param padre
     */
    public static void msjRegistroYaActivado(Component padre) {
        JOptionPane.showMessageDialog(padre, "El registro ya está activado.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Mostra un mensaje indicando que el registro ya esta desactivado
     *
     * @param padre
     */
    public static void msjRegistroYaDesactivado(Component padre) {
        JOptionPane.showMessageDialog(padre, "El registro ya está desactivado.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje indicando que ya ha sido cancelado.
     *
     * @param padre
     */
    public static void msjYaCancelado(Component padre) {
        JOptionPane.showMessageDialog(padre, "Ya ha sido cancelado.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de pregunta para confirmar la desactivacion de un
     * registro
     *
     * @param padre
     * @return
     */
    public static int msjConfimacionDesactivar(Component padre) {
        int opcion = JOptionPane.showConfirmDialog(padre,
                "Al desactivar este registro, el sistema ya no lo tomará en cuenta,\n"
                + "sin embargo siempre quedará guardado. ¿Desea continuar?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion;
    }

    /**
     * Muestra un mensaje para confirmar el cierre de sistema
     *
     * @param padre
     * @return
     */
    public static int msjConfimacionCerrarSistema(Component padre) {
        int opcion = JOptionPane.showConfirmDialog(padre,
                "Está a punto de cerrar el sistema. ¿Desea continuar?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion;
    }

    /**
     * Muestra un mensaje para preguntar al usuario si desea guardar las ventas
     * como pendiente
     *
     * @param padre
     * @return
     */
    public static int msjConfimacionGuardarVentas(Component padre) {
        int opcion = JOptionPane.showConfirmDialog(padre,
                "Una o más ventas no han sido realizadas. "
                + "¿Desea guardarlas para continuar más tarde?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion;
    }

    /**
     * Muestra un mensaje de confirmacion para eliminar el tab de una venta
     *
     * @param padre
     * @return
     */
    public static int msjConfimacionEliminarTabVenta(Component padre) {
        int opcion = JOptionPane.showConfirmDialog(padre,
                "¿Está seguro de eliminar esta venta?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion;
    }

    /**
     * Muestra un mensaje para preguntar si esta seguro de eliminar un articulo
     * de la tabla de ventas
     *
     * @param padre
     * @return
     */
    public static int msjConfimacionEliminarArticuloTablaVenta(Component padre) {
        int opcion = JOptionPane.showConfirmDialog(padre,
                "¿Está seguro de eliminar este artículo?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        return opcion;
    }

    /**
     * Informar que los codigo(s) ya existen
     *
     * @param padre
     */
    public static void msjCodigosExistentes(Component padre) {
        JOptionPane.showMessageDialog(padre, "Uno o más códigos ya existen.",
                "Validación", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje indicando que no hay resultados para el codigo
     *
     * @param padre
     * @param codigo
     */
    public static void msjNoResultadoCodigo(Component padre, String codigo) {
        JOptionPane.showMessageDialog(padre, "No se encontraron resultados para código: "
                + codigo, "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Indica que aun no se buscado un articulo y sugiere hacerlo
     *
     * @param padre
     */
    public static void msjNoArticuloBuscado(Component padre) {
        JOptionPane.showMessageDialog(padre, "Aún no se ha buscado nada.\n"
                + "Digíte el código del artículo y presione ENTER.",
                "No seleccionado", JOptionPane.INFORMATION_MESSAGE);
    }
}
