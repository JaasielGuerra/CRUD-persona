/*
 * Utilidades para JTable
 */
package com.apioriente.appsoporte.util;

import com.apioriente.appsoporte.msj.MsjException;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jaasiel
 */
public class TablaUtil {

    public static int ALIN_IZQUIERDA = 2;
    public static int ALIN_DERECHA = 4;
    public static int ALIN_CENTRO = 0;

    private TablaUtil() {

    }

    /**
     * Dar un ancho minimo a una columna de un JTable
     *
     * @param tabla
     * @param indice
     * @param anchoMin
     */
    public static void anchoMinimoColumna(JTable tabla, int indice, int anchoMin) {
        tabla.getColumnModel().getColumn(indice).setMinWidth(anchoMin);
    }

    /**
     * Borrar todas las filas de un JTable
     *
     * @param tabla
     */
    public static void limpiarTabla(JTable tabla) {
        DefaultTableModel m = (DefaultTableModel) tabla.getModel();
        m.setRowCount(0);
    }

    /**
     * Dar un render a las celdas de una columna de una tabla
     *
     * @param table
     * @param colum
     * @param render
     */
    public static void setCellRender(JTable table, int colum, DefaultTableCellRenderer render) {
        table.getColumnModel().getColumn(colum).setCellRenderer(render);
    }

    /**
     * TENGO QUE ANALIZAR MAS ESTO, YA QUE QUIERO QUE LA INTERFAZ LE PUEDA PASAR
     * CUALQUIER TIPO DE DATO
     */
//    /**
//     * Ejecuta la accion implementada en el metodo de la interfaz
//     * <code>IEntityFilaSeleccionadaTabla</code> y pasa como parametro la
//     * entidad de la fila seleccionada en un JTable
//     *
//     * @param t
//     * @param nColEntity
//     * @param entityFilaSeleccionadaTabla Ejecuta el metodo <code>acction</code>
//     * si la fila esta seleccionada, en caso contrario no la ejecuta
//     */
//    public static void getEntityFilaSeleccionada(JTable t, int nColEntity,
//            IEntityFilaSeleccionadaTabla entityFilaSeleccionadaTabla) {
//
//        Object m = null;
//        int fila = t.getSelectedRow();
//        if (fila > -1) {
//            m = t.getValueAt(fila, nColEntity);
//            entityFilaSeleccionadaTabla.accion(m);
//        }
//
//    }
    /**
     * Devuelve el objeto entidad de la fila seleccionada en un JTable
     *
     * @param <T> Plantilla para el tipo de retorno
     * @param t la tabla
     * @param nColEntity el No. de columna de la entidad
     * @param tipo El tipo de Dato entidad
     * @return La entidad, o null en caso de que la fila no este seleccionada
     */
    public static <T> T getEntityFilaSeleccionada(JTable t, int nColEntity, Class<T> tipo) {

        T m = null;
        int fila = t.getSelectedRow();
        if (fila > -1) {
            m = (T) t.getValueAt(fila, nColEntity);
        }
        return m;
    }

    /**
     * Llenar una tabla sin una columna para respaldar una entidad
     *
     * @param tabla la tabla para llenar
     * @param datos una lista con los datos
     * @param campos el nombre de los campos segun la entidad
     */
    public static void llenarTablaSinEntity(JTable tabla, List datos, String[] campos) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        datos.forEach((dato) -> {
            Object[] fila = new Object[campos.length];
            try {
                for (int i = 0; i < campos.length; i++) {

                    if (campos[i].contains(".")) {
                        fila[i] = getValorSubCampo(campos[i], dato);
                    } else {
                        // obtener el valor del campo de la entidad de manera dinamica
                        Field f = dato.getClass().getDeclaredField(campos[i]);
                        f.setAccessible(true);// para poder acceder al campo
                        fila[i] = f.get(dato);
                    }
                }
                modelo.addRow(fila);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                MsjException.msjErrorEstandar(null, e.getMessage());
            }
        });

    }

    /**
     * Llenar una tabla que tiene una columna para respaldar una entidad
     *
     * @param tabla la tabla para llenar
     * @param datos una lista con los datos
     * @param campos el nombre de los campos segun la entidad
     * @param nColEntity el No. de columna en donde se salva la entidad
     */
    public static void llenarTablaConEntity(JTable tabla, List datos, String[] campos, int nColEntity) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        datos.forEach((dato) -> {
            Object[] fila = new Object[campos.length + 1];//sumar 1 por la columna entity
            try {
                int j = 0;// este indice es auxiliar para obtener el valor del arreglo campos
                for (int i = 0; i < campos.length + 1; i++) {
                    if (nColEntity == i) {
                        fila[i] = dato;// la columna entity
                    } else {
                        if (campos[j].contains(".")) {
                            fila[i] = getValorSubCampo(campos[j], dato);
                        } else {
                            //obtener el valor del campo de la entidad de manera dinamica
                            Field f = dato.getClass().getDeclaredField(campos[j]);
                            f.setAccessible(true);// para poder acceder al campo
                            fila[i] = f.get(dato);
                        }
                        j++;// aumentar el indice
                    }
                }
                modelo.addRow(fila);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                MsjException.msjErrorEstandar(null, e.getMessage());
            }
        });

    }

    /**
     * Este metodo obtiene el valor de un sub campo de un objeto, dada la
     * sintaxis: objetoPadre.campo1.campo2, usando Java Reflection. Ejemplo:
     * dada la consulta persona.idMunicipio.nombre, devolvera el valor del campo
     * nombre
     *
     * @param consulta
     * @param dato
     * @return El valor del ultimo campo indicado en la consulta
     */
    private static Object getValorSubCampo(String consulta, Object dato) {
        long nPuntos = consulta.chars().filter((caracter) -> caracter == '.').count();
        Object valor = null;
        Object objetoTemp = null;
        String[] c = consulta.split("\\.");

        try {

            Field f = dato.getClass().getDeclaredField(c[0]);

            for (int i = 1; i <= nPuntos; i++) {

                f.setAccessible(true);
                if (i == 1) {
                    valor = f.get(dato);
                } else {
                    valor = f.get(objetoTemp);
                }
                objetoTemp = valor;

                f = valor.getClass().getDeclaredField(c[i]);
            }
            f.setAccessible(true);
            valor = f.get(objetoTemp);

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            MsjException.msjErrorEstandar(null, e.getMessage());
        }

        return valor;
    }

    /**
     * Ocultar una columna de JTable
     *
     * @param t la tabla
     * @param nCol el No. de columna
     */
    public static void ocultarCol(JTable t, int nCol) {
        t.getColumnModel().getColumn(nCol).setMaxWidth(0);
        t.getColumnModel().getColumn(nCol).setMinWidth(0);
        t.getColumnModel().getColumn(nCol).setPreferredWidth(0);
        t.getColumnModel().getColumn(nCol).setResizable(false);
    }

    /**
     * Alinear una columna de una JTable
     *
     * @param t la tabla
     * @param nCol el No. de columna
     * @param alineacion la alineacion puede ser una constante: ALIN_IZQUIERDA,
     * ALIN_DERECHA, ALIN_CENTRO
     */
    public static void alinearCol(JTable t, int nCol, int alineacion) {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(alineacion);
        t.getColumnModel().getColumn(nCol).setCellRenderer(dtcr);
    }

    /**
     * Alinear todas las columnas de un JTable
     *
     * @param t la tabla
     * @param alineacion la alineacion puede ser una constante: ALIN_IZQUIERDA,
     * ALIN_DERECHA, ALIN_CENTRO
     */
    public static void alinearCols(JTable t, int alineacion) {
        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
        dtcr.setHorizontalAlignment(alineacion);

        for (int i = 0; i < t.getColumnCount(); i++) {
            t.getColumnModel().getColumn(i).setCellRenderer(dtcr);
        }

    }

}
