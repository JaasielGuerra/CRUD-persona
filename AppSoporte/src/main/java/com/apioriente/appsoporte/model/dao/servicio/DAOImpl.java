package com.apioriente.appsoporte.model.dao.servicio;

import com.apioriente.appsoporte.model.Parameter;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DAOImpl<Entity, Id> extends CRUDImpl<Entity, Id> implements DAO<Entity, Id>, Serializable {

    private final Class<Entity> type;
    private final EntityManager em;

    public DAOImpl(Class<Entity> t, EntityManager em) {
        super(t, em);
        type = t;
        this.em = em;
    }

    @Override
    public Entity readOne(Id id) {
        Entity find = em.find(type, id);

        return find;
    }

    @Override
    public List<Entity> readByNameQuery(String nameQuery, String p, Object v) {
        TypedQuery<Entity> query = em.createNamedQuery(nameQuery, type);

        query.setParameter(p, v);

        List<Entity> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public List<Entity> readByQuery(String jpqlQuery, List<Parameter> parameters) {
        TypedQuery<Entity> query2 = em.createQuery(jpqlQuery, type);

        if (parameters != null) {

            parameters.forEach((parameter) -> {
                query2.setParameter(parameter.getParam(), parameter.getValue());
            });
        }

        List<Entity> resultList = query2.getResultList();

        return resultList;
    }

    @Override
    public List<Entity> readByQueryAndLimit(String jpqlQuery, List<Parameter> parameters, int startPosition, int maxResult) {
        TypedQuery<Entity> query2 = em.createQuery(jpqlQuery, type)
                .setFirstResult(startPosition).setMaxResults(maxResult);

        parameters.forEach((parameter) -> {
            query2.setParameter(parameter.getParam(), parameter.getValue());
        });

        List<Entity> resultList = query2.getResultList();

        return resultList;
    }

    @Override
    public int count(String jpqlQuery, List<Parameter> parameters) {
        TypedQuery<Object> query2 = em.createQuery(jpqlQuery, Object.class);

        parameters.forEach((parameter) -> {
            query2.setParameter(parameter.getParam(), parameter.getValue());
        });

        Object o = query2.getSingleResult();

        return Integer.valueOf(o.toString());
    }

    @Override
    public List<Object[]> readByQueryObjectCol(String jpqlQuery, List<Parameter> parameters) {
        TypedQuery<Object[]> query2 = em.createQuery(jpqlQuery, Object[].class);

        parameters.forEach((parameter) -> {
            query2.setParameter(parameter.getParam(), parameter.getValue());
        });

        List<Object[]> resultList = query2.getResultList();

        return resultList;
    }

    @Override
    public List<Entity> readByQuery(String jpqlQuery) {
        return readByQuery(jpqlQuery, null);
    }

}
