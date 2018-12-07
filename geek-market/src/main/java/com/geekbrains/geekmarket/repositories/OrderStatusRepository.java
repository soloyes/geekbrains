package com.geekbrains.geekmarket.repositories;

import com.geekbrains.geekmarket.entities.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {
}
