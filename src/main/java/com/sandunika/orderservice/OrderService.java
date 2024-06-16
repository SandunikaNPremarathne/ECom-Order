package com.sandunika.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

//    @Cacheable(value = "orderCache", key = "#id")
    public Order getOrderById(long id)
    {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order)
    {
        return orderRepository.save(order);
    }

//    @CachePut(value = "orderCache", key = "#order.id")
    public Order updateOrder(long id, Order order)
    {
        Order oldOrder = orderRepository.findById(id).orElse(null);
        if( oldOrder!=null)
        {
            oldOrder.setCustomerName(order.getCustomerName());
            orderRepository.save(oldOrder);
        }
        return oldOrder;
    }

//    @CacheEvict(value = "orderCache", key = "#id")
    public void deleteOrder(long id)
    {
        orderRepository.deleteById(id);
    }
}
