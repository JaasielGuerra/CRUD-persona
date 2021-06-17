/*
 * Interfaz que define metodos para consultar 
 */
package com.apioriente.appsoporte.model.dao.servicio;

import com.apioriente.appsoporte.model.Parameter;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jaasi
 * @param <Entity>
 * @param <Id>
 */
public interface DAO<Entity, Id> extends CRUD<Entity, Id>, Serializable {

    /**
     * Leer un unico registro por su id
     *
     * @param id el id del registro a consultar
     * @return la entidad
     */
    public Entity readOne(Id id);

    /**
     * Consultar por nombre de query
     *
     * @param nameQuery el nombre del query
     * @param p el nombre del parametro
     * @param v el valor
     * @return una lista con los datos
     */
    public List<Entity> readByNameQuery(String nameQuery, String p, Object v);

    /**
     * Consultar por jpql query
     *
     * @param jpqlQuery la consulta jpql
     * @param parameters el listado de paramtros
     * @return el listado con los datos
     */
    public List<Entity> readByQuery(String jpqlQuery, List<Parameter> parameters);

    /**
     * Consultar por jpql Query
     *
     * @param jpqlQuery
     * @return
     */
    public List<Entity> readByQuery(String jpqlQuery);

    /**
     * COnsultar por jpql query y paginado
     *
     * @param jpqlQuery
     * @param parameters
     * @param startPosition
     * @param maxResult
     * @return
     */
    public List<Entity> readByQueryAndLimit(String jpqlQuery,
            List<Parameter> parameters, int startPosition, int maxResult);

    /**
     * Contar registros dada una consulta jpql
     *
     * @param jpqlQuery
     * @param parameters
     * @return
     */
    public int count(String jpqlQuery, List<Parameter> parameters);

    /**
     * Consultar columnas especificas de una tabla
     *
     * @param jpqlQuery
     * @param parameters
     * @return Objec[]
     */
    public List<Object[]> readByQueryObjectCol(String jpqlQuery, List<Parameter> parameters);
}
