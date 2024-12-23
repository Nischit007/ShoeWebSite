package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.ResponseOrderDTO;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Entity.Shoe;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Repository.OrderRepository;
import com.example.demo.Repository.ShoeRepository;

@Service
public class OrderService {

    @Autowired
    private ShoeRepository shoeRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Order buyOrder(List<OrderDTO> orderDto, Users user) throws Exception {
        List<OrderItem> orderItemList = new ArrayList<>();
        double totalPrice = 0;

        for (OrderDTO dto : orderDto) {
            Shoe shoe = shoeRepository.findById(dto.getShoeId())
                    .orElseThrow(() -> new Exception("Shoe not found"));
            if (shoe.getQuantity() < dto.getQuantity()) {
                throw new Exception("Insufficient stock for shoe ID: " + shoe.getId());
            }
            shoe.setQuantity(shoe.getQuantity() - dto.getQuantity());
            shoeRepository.save(shoe);

            OrderItem orderItem = new OrderItem();
            orderItem.setShoe(shoe);
            orderItem.setQuantity(dto.getQuantity());
            orderItem.setTotalPrice(dto.getTotalPrice());
            totalPrice += dto.getTotalPrice();
            orderItemList.add(orderItem);
        }

        orderItemRepository.saveAll(orderItemList);

        Order order = new Order();
        order.setOrderItems(orderItemList);
        order.setTotalAmount(totalPrice);
        order.setUser(user);
        return orderRepository.save(order);
    }

    public List<ResponseOrderDTO> getUserOrderDTOs(Users user) {
        List<Order> orders = orderRepository.findByUserId(user.getId());
        return convertToOrderDTO(orders);
    }

    private List<ResponseOrderDTO> convertToOrderDTO(List<Order> orders) {
        List<ResponseOrderDTO> dtoList = new ArrayList<>();
        for (Order order : orders) {
            for (OrderItem orderItem : order.getOrderItems()) {
                ResponseOrderDTO orderDto = new ResponseOrderDTO();
                orderDto.setSize(orderItem.getShoe().getSize());
                orderDto.setShoeName(orderItem.getShoe().getName());
                orderDto.setShoeId(orderItem.getShoe().getId());
                orderDto.setShoeImage(orderItem.getShoe().getShoeImage());
                orderDto.setQuantity(orderItem.getQuantity());
                orderDto.setTotalPrice(orderItem.getTotalPrice());
                dtoList.add(orderDto);
            }
        }
        return dtoList;
    }
}
