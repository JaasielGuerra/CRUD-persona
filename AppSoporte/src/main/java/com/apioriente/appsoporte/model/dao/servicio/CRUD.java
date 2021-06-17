/*
 * Interfaz define los metodos necesarios para realizar un CRUD
 */
package com.apioriente.appsoporte.model.dao.servicio;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaasi
 * @param <Entity>
 * @param <Id>
 */
public interface CRUD<Entity, Id> extends Serializable{
    
    /**
     * Insertar una entidad
     *
     * @param entity la entidad a insertar
     * @throws Exception en caso de error
     */
    public void create(Entity entity) throws Exception;

    /**
     * Leer todos los registros
     *
     * @return Una lista con los datos
     */
    public List<Entity> read();

    /**
     * Actualizar una entidad
     *
     * @param entity la entidad a actualizar
     * @throws Exception en caso de error
     */
    public void update(Entity entity) throws Exception;

    /**
     * Eliminar un registro
     *
     * @param id el id del registro a eliminar
     * @throws Exception en caso de error
     */
    public void delete(Id id) throws Exception;
}
