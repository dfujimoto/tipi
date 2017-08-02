package com.petiteestudio.dao;

import com.petiteestudio.entities.Persona;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel on 20/07/2017.
 */
public interface PersonaDAO extends CrudRepository<Persona, Long> {
}
