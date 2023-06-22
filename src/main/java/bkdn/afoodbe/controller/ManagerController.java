package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.*;
import bkdn.afoodbe.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PreAuthorize(value = "hasRole('MANAGER') or hasRole('ADMIN')")
public class ManagerController {
    private final IFoodService foodService;
    private final IMenuService menuService;
    private final OrderService orderService;
    private final MenuFoodService menuFoodService;
    private final OrderFoodService orderFoodService;

    // ********** FOOD **********
    @GetMapping("/foods")
    public ResponseEntity<Object> getAllFood() {
        List<FoodDto> foods = foodService.getAllFood();
        return ResponseEntity.ok(foods);
    }

    @PostMapping("/foods/create")
    public ResponseEntity<Object> addFood(CreateFoodDto createFoodDto) {
        FoodDto food = foodService.addFood(createFoodDto);
        return ResponseEntity.ok(food);
    }

    @GetMapping("/foods/findallfoodbyname")
    public ResponseEntity<Object> findAllFoodByName(String name) {
        List<FoodDto> foods = foodService.findAllFoodByName(name);
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/foods/findfoodsbyorderid")
    public ResponseEntity<Object> findFoodsByOrderId(int orderId) {
        Set<FoodDto> foods = foodService.findFoodsByOrderId(orderId);
        return ResponseEntity.ok(foods);
    }

    @PostMapping("/foods/addfoodtomenu")
    public ResponseEntity<Object> addFoodToMenu(int menuId, int foodId) {
        MenuFoodDto menuFood = menuFoodService.addMenuFood(menuId, foodId);
        return ResponseEntity.ok(menuFood);
    }

    @DeleteMapping("/foods/deletefoodfrommenu")
    public ResponseEntity<Object> deleteFoodFromMenu(int menuId, int foodId) {
        menuFoodService.deleteMenuFood(menuId, foodId);
        return ResponseEntity.ok("Delete success");
    }


    // ********** MENU **********

    @GetMapping("/menus/findallmenubyname")
    public ResponseEntity<Object> findAllMenuByName(String name) {
        List<MenuDto> menus = menuService.findAllMenuByName(name);
        return ResponseEntity.ok(menus);
    }

    @PostMapping("/menus/create")
    public ResponseEntity<Object> createMenu(CreateMenuDto createMenuDto) {
        MenuDto menu = menuService.createMenu(createMenuDto);
        return ResponseEntity.ok(menu);
    }

    // ********** ORDER **********
    @GetMapping("/orders")
    public ResponseEntity<Object> getAllOrder() {
        List<OrderDto> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/countorders")
    public ResponseEntity<Object> countOrders() {
        Long count = orderService.countOrders();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/orders/totalCostAllOrder")
    public ResponseEntity<Object> totalCostAllOrder() {
        BigDecimal totalCost = orderService.sumTotalCost();
        return ResponseEntity.ok(totalCost);
    }

    @GetMapping("/orders/averagecost")
    public ResponseEntity<Object> averageCost() {
        BigDecimal averageCost = orderService.averageCost();
        return ResponseEntity.ok(averageCost);
    }

    @GetMapping("/orders/conversionrate")
    public ResponseEntity<Object> conversionRate() {
        BigDecimal conversionRate = orderService.conversionRate();
        return ResponseEntity.ok(conversionRate);
    }

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

    @DeleteMapping("/orders/deletefoodfromorder")
    public ResponseEntity<Object> deleteFoodFromOrder(int orderId, int foodId) {
        orderFoodService.deleteOrderFood(orderId, foodId);
        return ResponseEntity.ok("Delete success");
    }

    @PutMapping("/orders/updatestatus")
    public ResponseEntity<Object> updateStatusOrder(int orderId, String status) {
        OrderDto order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(order);
    }


    // ********** ORDER FOOD **********
    @GetMapping("/orderfood/findbyorderid")
    public ResponseEntity<Object> findOrderFoodByOrderId(int orderId) {
        Set<OrderFoodDto> orderFoods = orderFoodService.findByOrderId(orderId);
        return ResponseEntity.ok(orderFoods);
    }
}
