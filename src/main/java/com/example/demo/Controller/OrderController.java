package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.PaymentResponse;
import com.example.demo.DTO.ResponseOrderDTO;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.Users;
import com.example.demo.Service.OrderService;
import com.example.demo.Service.PaymentService;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/buy")
    public ResponseEntity<PaymentResponse> orderShoe(@RequestBody List<OrderDTO> orderDto, @RequestHeader("Authorization") String jwt) throws Exception {
        Users user = userService.findUserprofileByJwt(jwt);
        Order order = orderService.buyOrder(orderDto, user);
        PaymentResponse res = paymentService.createPaymentLink(order);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/showUserOrders")
    public ResponseEntity<List<ResponseOrderDTO>> showOrderItem(@RequestHeader("Authorization") String jwt) throws Exception {
        Users user = userService.findUserprofileByJwt(jwt);
        List<ResponseOrderDTO> dto = orderService.getUserOrderDTOs(user);
        return new ResponseEntity<>(dto, HttpStatus.OK); 
    } 
}
