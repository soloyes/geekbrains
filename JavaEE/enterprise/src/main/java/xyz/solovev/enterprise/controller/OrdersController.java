package xyz.solovev.enterprise.controller;

import lombok.Data;
import xyz.solovev.enterprise.dao.OrdersDAO;
import xyz.solovev.enterprise.entity.Orders;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ViewScoped
@ManagedBean
@Data
public class OrdersController {
    @Inject
    private OrdersDAO ordersDAO;

    private List<Orders> ordersList;

    private Orders selectedOrder;

    @PostConstruct
    private void reload() {
        ordersList = ordersDAO.getAll();
    }

    public void add(Orders order) {
        ordersDAO.add(order);
        reload();
    }

    public void del(Long id) {
        ordersDAO.removeById(id);
        reload();
    }

    public void modify(Orders order) {
        ordersDAO.modify(order);
        reload();
    }
}
