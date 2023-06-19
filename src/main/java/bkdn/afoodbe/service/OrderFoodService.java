package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.OrderFoodDto;
import bkdn.afoodbe.entity.Food;
import bkdn.afoodbe.entity.Order;
import bkdn.afoodbe.entity.OrderFood;
import bkdn.afoodbe.repository.FoodRepository;
import bkdn.afoodbe.repository.OrderFoodRepository;
import bkdn.afoodbe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderFoodService {
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final OrderFoodRepository orderFoodRepository;

    public Set<OrderFoodDto> findByOrderId(int orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        Set<OrderFood> orderFoodList = orderFoodRepository.findByOrderId(orderId);
        return orderFoodList.stream().map(OrderFoodDto::toOrderFoodDto).collect(Collectors.toSet());
    }

    public OrderFoodDto addOrderFood(int orderId, int foodId, int quantity) {
        Order order = orderRepository.findById(orderId);
        Food food = foodRepository.findById(foodId);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        if (food == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        }
        if (orderFoodRepository.existsByOrderIdAndFoodId(orderId, foodId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food already exists in order");
        }
        OrderFood orderFood = new OrderFood();
        orderFood.setOrder(order);
        orderFood.setFood(food);
        orderFood.setQuantity(quantity);

        OrderFood newOrderFood = orderFoodRepository.saveAndFlush(orderFood);
        return OrderFoodDto.toOrderFoodDto(newOrderFood);
    }

    public void deleteOrderFood(int orderId, int foodId) {
        Order order = orderRepository.findById(orderId);
        Food food = foodRepository.findById(foodId);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        if (food == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        }

        OrderFood orderFood = orderFoodRepository.findByOrderIdAndFoodId(orderId, foodId);
        if (orderFood == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found in order");
        }
        orderFoodRepository.delete(orderFood);
    }
}
