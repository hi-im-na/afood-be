package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.CreateOrderDto;
import bkdn.afoodbe.dto.OrderDto;
import bkdn.afoodbe.dto.OrderFoodDto;
import bkdn.afoodbe.service.OrderFoodService;
import bkdn.afoodbe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PreAuthorize(value = "hasRole('MANAGER') or hasRole('ADMIN') or hasRole('STAFF')")
public class StaffController {
    private final OrderService orderService;
    private final OrderFoodService orderFoodService;

    @PostMapping("/orders/addorder")
    public ResponseEntity<Object> addOrder(CreateOrderDto createOrderDto) {
        System.out.println("createOrderDto = " + createOrderDto);
        OrderDto order = orderService.addOrder(createOrderDto);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders/addfoodtoorder")
    public ResponseEntity<Object> addFoodToOrder(int orderId, int foodId, int quantity) {
        OrderFoodDto orderFood = orderFoodService.addOrderFood(orderId, foodId, quantity);
        return ResponseEntity.ok(orderFood);
    }
}
