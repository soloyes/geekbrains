package com.geekbrains.geekmarket.repositories;

import com.geekbrains.geekmarket.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
