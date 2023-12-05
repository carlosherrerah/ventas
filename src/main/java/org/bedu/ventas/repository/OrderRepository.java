package org.bedu.ventas.repository;

import java.util.List;

import org.bedu.ventas.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    List<Order> findAll();
}
