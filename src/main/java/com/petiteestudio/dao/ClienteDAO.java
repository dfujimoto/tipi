package com.petiteestudio.dao;

import com.petiteestudio.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel on 20/07/2017.
 */
public interface ClienteDAO extends CrudRepository<Cliente, Long>  {
}
