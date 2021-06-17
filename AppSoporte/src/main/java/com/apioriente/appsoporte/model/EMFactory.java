/*
 * Esta clase se encarga de crear la entidad de persistencia
 * para reutilzarla las veces que se necesite
 */
package com.apioriente.appsoporte.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jaasiel Guerra
 */
public class EMFactory {

    // atributos estaticos
    private static final String PERSISTENCE_UNIT_NAME = "persistence_unit";
    private static EntityManagerFactory factory;

    //evitar la instancia con operador new
    private EMFactory() {

    }

    /**
     * Devuelve el EntityManagerFactory
     *
     * @return
     */
    public static EntityManagerFactory getEMFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }


}
