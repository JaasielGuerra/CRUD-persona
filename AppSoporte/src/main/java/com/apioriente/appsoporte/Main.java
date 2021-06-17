package com.apioriente.appsoporte;

import com.apioriente.appsoporte.controller.CrudPersonaController;
import com.apioriente.appsoporte.view.FrmCrudPersona;
import javax.swing.SwingUtilities;

/**
 *
 * @author jaasiel
 */
public class Main {

    public static void main(String[] args) {

        /**
         * Es una buena practica hacer la instancia de la aplicacion dentro del
         * hilo que swing proporciona
         */
        SwingUtilities.invokeLater(() -> {

            FrmCrudPersona frmCrudPersona = new FrmCrudPersona();
            CrudPersonaController crudPersonaController = new CrudPersonaController(frmCrudPersona);

        });
    }
}
