package com.apioriente.appsoporte.model.dao;

import com.apioriente.appsoporte.model.dao.servicio.DAOImpl;
import com.apioriente.appsoporte.model.entidades.Persona;
import java.io.Serializable;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jaasi
 */
public class PersonaDAO extends DAOImpl<Persona, Integer> implements Serializable {

    public PersonaDAO(EntityManagerFactory emf) {

        super(Persona.class, emf.createEntityManager());

    }

}
