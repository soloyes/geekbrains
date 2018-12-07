package com.geekbrains.geekmarket.repositories;

import com.geekbrains.geekmarket.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
