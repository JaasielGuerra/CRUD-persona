/**
 * Clase base que contiene los metodos necesarios para realizar un CRUD
 */
package com.apioriente.appsoporte.model.dao.servicio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class CRUDImpl<Entity, Id> implements CRUD<Entity, Id>, Serializable {

    private final Class<Entity> type;
    private final EntityManager em;

    public CRUDImpl(Class<Entity> t, EntityManager em) {
        type = t;
        this.em = em;
    }

    @Override
    public void create(Entity entity) throws Exception {

        try {
            em.getTransaction().begin();

            em.persist(entity);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {

        }
    }

    @Override
    public List<Entity> read() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(type));
        Query q = em.createQuery(cq);
        List resultList = q.getResultList();

        return resultList;
    }

    @Override
    public void update(Entity entity) throws Exception {

        try {
            em.getTransaction().begin();

            em.merge(entity);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {

        }

    }

    @Override
    public void delete(Id id) throws Exception {

        try {
            em.getTransaction().begin();
            em.remove(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {

        }
    }

}
