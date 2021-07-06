package com.apioriente.appsoporte.controller;

import com.apioriente.appsoporte.model.DAOManager;
import com.apioriente.appsoporte.model.Parameter;
import com.apioriente.appsoporte.model.dao.PersonaDAO;
import com.apioriente.appsoporte.model.entidades.Persona;
import com.apioriente.appsoporte.msj.MsjException;
import com.apioriente.appsoporte.msj.MsjInfo;
import com.apioriente.appsoporte.msj.MsjValidacion;
import com.apioriente.appsoporte.util.TablaUtil;
import com.apioriente.appsoporte.util.ValidarJTextField;
import com.apioriente.appsoporte.view.FrmCrudPersona;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jaasiel
 */
public class CrudPersonaController {

    //constante para el directorio donde se guardan las imagenes
    private final String DIR_IMG = System.getProperty("user.dir") + File.separator + "fotos";

    //constante para la imagen por defecto
    private final String IMG_DEFAULT = "image-default.png";

    //el DAO para persistir
    private PersonaDAO personaDAO;

    //entidad auxiliar para editar un registro
    private Persona personaEditar;

    //vista para este controlador
    private final FrmCrudPersona frmCrudPersona;

    /**
     * El constructor recibe la vista como parametro para tomar el control de
     * ella
     *
     * @param frmCrudPersona
     */
    public CrudPersonaController(FrmCrudPersona frmCrudPersona) {
        this.frmCrudPersona = frmCrudPersona;
        init();
    }

    /**
     * Programar los eventos aqui, u otras rutinas de inicializacion, como por
     * ejemplo consultas a base de datos, reinicio de formularios, etc...
     *
     * NOTA: Recordar que este metodo solo se llama una sola vez cuando esta
     * clase es instanciada
     */
    private void init() {
        frmCrudPersona.btnGuardar.addActionListener((ae) -> {
            registrarPersona();
        });
        frmCrudPersona.btnActualizar.addActionListener((ae) -> {
            actualizarPersona();
        });
        frmCrudPersona.btnEditar.addActionListener((ae) -> {
            editarPersona();
        });
        frmCrudPersona.btnEliminar.addActionListener((ae) -> {
            eliminarPersona();
        });
        frmCrudPersona.btnConsultar.addActionListener((ae) -> {
            consultarPersonas();
        });

        personaDAO = DAOManager.getInstancia().getPersonaDAO();

        resetFormulario();
        consultarPersonas();

        frmCrudPersona.setVisible(true);
    }

    /**
     * Eventos de la vista
     */
    private void registrarPersona() {

        /**
         * Primero se valida el formulario usando la utilidad ValidarJTextField
         */
        boolean camposVacios = ValidarJTextField.camposVacios(frmCrudPersona.txtNombre, frmCrudPersona.txtApellido);
        if (camposVacios) {
            MsjValidacion.msjJTextFieldRequeridos(frmCrudPersona);
            return; //retornar para no continuar con la ejecucion del metodo
        }

        /**
         * Si todo ha salido bien llegamos a este punto para registrar
         */
        if (!guardarImagen()) {
            return; //retornar para no continuar con la ejecucion del metodo
        }

        if (!persistir()) {
            return; //retornar para no continuar con la ejecucion del metodo
        }

        resetFormulario(); //resetear formulario
        consultarPersonas(); //consultar la base de datos

        MsjInfo.msjRegistroExitoso(frmCrudPersona);

    }

    private void actualizarPersona() {

        /**
         * Primero se valida el formulario usando la utilidad ValidarJTextField
         */
        boolean camposVacios = ValidarJTextField.camposVacios(frmCrudPersona.txtNombre, frmCrudPersona.txtApellido);
        if (camposVacios) {
            MsjValidacion.msjJTextFieldRequeridos(frmCrudPersona);
            return; //retornar para no continuar con la ejecucion del metodo
        }

        /**
         * Si todo ha salido bien llegamos a este punto para actualizar
         */
        if (!guardarImagen()) {
            return; //retornar para no continuar con la ejecucion del metodo
        }

        if (!actualizar()) {
            return; //retornar para no continuar con la ejecucion del metodo
        }

        resetFormulario(); //resetear formulario
        consultarPersonas(); //consultar la base de datos

        MsjInfo.msjActualizacionExitosa(frmCrudPersona);
    }

    private void editarPersona() {

        /**
         * Para esta tarea se usa la utilidad para obtener la entidad de la
         * tabla
         */
        Persona p = TablaUtil.getEntityFilaSeleccionada(frmCrudPersona.tblPersonas, 0, Persona.class);

        //se puede validar si devuelve null
        if (p == null) {
            MsjInfo.msjSeleccioneFila(frmCrudPersona);
            return;
        }

        /**
         * Si todo va bien, llega hasta aqui la ejecucion del codigo
         */
        personaEditar = p;
        frmCrudPersona.txtNombre.setText(p.getNombre());
        frmCrudPersona.txtApellido.setText(p.getApellido());
        frmCrudPersona.fotoPersona.setImagePath(p.getFoto());

        frmCrudPersona.btnGuardar.setEnabled(false);
        frmCrudPersona.btnActualizar.setEnabled(true);

    }

    private void eliminarPersona() {

        /**
         * Para esta tarea se usa la utilidad para obtener la entidad de la
         * tabla
         */
        Persona p = TablaUtil.getEntityFilaSeleccionada(frmCrudPersona.tblPersonas, 0, Persona.class);

        //se puede validar si devuelve null
        if (p == null) {
            MsjInfo.msjSeleccioneFila(frmCrudPersona);
            return;
        }

        p.setEstado(0);

        try {

            personaDAO.update(p);

            consultarPersonas();

            MsjInfo.msjExitoEstandar(frmCrudPersona);

        } catch (Exception ex) {
            MsjException.msjErrorActualizar(frmCrudPersona, ex.getMessage());
        }

    }

    private void consultarPersonas() {

        /**
         * Primero se crea la lista de parametros que se necesita para la
         * consulta
         */
        List<Parameter> p = new ArrayList<>();
        p.add(new Parameter("estado", 1));

        /**
         * Se usa el DAO para ejecutar la consulta y se pasa la lista de
         * parametros
         */
        List<Persona> personas = personaDAO.readByQuery("SELECT p FROM Persona p WHERE p.estado = :estado", p);

        /**
         * Se usa la utilidad para llenar la tabla con una columna de entity
         */
        String[] campos = {"idPersona", "nombre", "apellido"}; //se crea el arreglo de los campos deseados para mostrar en la tabla
        TablaUtil.llenarTablaConEntity(frmCrudPersona.tblPersonas, personas, campos, 0);
    }

    /**
     * Colocar de ultimo los metodos que son de utilidad internamente en la
     * clase
     */
    private void resetFormulario() {
        frmCrudPersona.txtNombre.setText("");
        frmCrudPersona.txtApellido.setText("");

        /**
         * Es importante darle una imagen por defecto al componente desde el
         * inicio
         */
        frmCrudPersona.fotoPersona.setImagePath(DIR_IMG + File.separator + IMG_DEFAULT);

        //reinicial entidad auxiliar
        personaEditar = null;

        //reiniciar estado botones
        frmCrudPersona.btnGuardar.setEnabled(true);
        frmCrudPersona.btnActualizar.setEnabled(false);
    }

    private boolean guardarImagen() {

        //nombre de la imagen a guardar
        String imagen = obtenerNombreImagen(frmCrudPersona.fotoPersona.getImagePath());

        //el componente JGImageChoose devuelve la ruta donde esta la imagen
        Path origen = Paths.get(frmCrudPersona.fotoPersona.getImagePath());

        //el destino es donde se desea guardar la imagen en disco
        //en este caso se guarda en el directorio del usuario
        Path destino = Paths.get(DIR_IMG + File.separator + imagen);

        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            MsjException.msjErrorEstandar(frmCrudPersona, ex.getMessage());
            return false;
        }

        return true;
    }

    private boolean persistir() {

        try {
            String imagen = obtenerNombreImagen(frmCrudPersona.fotoPersona.getImagePath());

            Persona p = new Persona();
            p.setNombre(frmCrudPersona.txtNombre.getText());
            p.setApellido(frmCrudPersona.txtApellido.getText());
            p.setFoto(DIR_IMG + File.separator + imagen);
            p.setEstado(1);

            personaDAO.create(p);

        } catch (Exception ex) {
            MsjException.msjErrorGuardar(frmCrudPersona, ex.getMessage());
            return false;
        }
        return true;
    }

    private boolean actualizar() {

        try {
            String imagen = obtenerNombreImagen(frmCrudPersona.fotoPersona.getImagePath());

            personaEditar.setNombre(frmCrudPersona.txtNombre.getText());
            personaEditar.setApellido(frmCrudPersona.txtApellido.getText());
            personaEditar.setFoto(DIR_IMG + File.separator + imagen);

            personaDAO.update(personaEditar);

        } catch (Exception ex) {
            MsjException.msjErrorActualizar(frmCrudPersona, ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Obtener el nombre de la imagen que se carga
     *
     * @param ruta
     * @return
     */
    private String obtenerNombreImagen(String ruta) {
        String separador = File.separator;// "\\";
        int ultimo_indice = ruta.lastIndexOf(separador);
        
        //String[] split = ruta.split(File.separator);
        //return split[split.length - 1];
        return ruta.substring(ultimo_indice+1);
    }

}
