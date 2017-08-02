package com.petiteestudio.dao;

import com.petiteestudio.entities.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel on 20/07/2017.
 */
public interface CategoriaDAO extends CrudRepository<Categoria, Long> {
}
