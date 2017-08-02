package com.petiteestudio.dao;

import com.petiteestudio.entities.Producto;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel on 20/07/2017.
 */
public interface ProductoDAO extends CrudRepository<Producto, Long> {
}
