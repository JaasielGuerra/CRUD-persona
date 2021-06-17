/*
 * Objeto que contiene todos los objetos DAO para tener una unica instancia
 * haciendo uso del patron singleton
 */
package com.apioriente.appsoporte.model;

import com.apioriente.appsoporte.model.dao.PersonaDAO;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Jaasiel Guerra
 */
public class DAOManager {

    private static DAOManager dAOManager;

    private final EntityManagerFactory emf = EMFactory.getEMFactory();

    /**
     * Aqui van los DAO que vayamos creando
     */
    private final PersonaDAO personaDAO;

    //evitar instancias con new
    private DAOManager() {
        personaDAO = new PersonaDAO(emf);
    }

    public static DAOManager getInstancia() {
        if (dAOManager == null) {
            dAOManager = new DAOManager();
        }
        return dAOManager;
    }

    /**
     * Metodo para devolver el DAO
     *
     * @return
     */
    public PersonaDAO getPersonaDAO() {
        return personaDAO;
    }

}
