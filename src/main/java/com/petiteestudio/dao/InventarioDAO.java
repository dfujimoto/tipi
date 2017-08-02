package com.petiteestudio.dao;

import com.petiteestudio.entities.Inventario;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel on 20/07/2017.
 */
public interface InventarioDAO extends CrudRepository<Inventario, Long> {
}
