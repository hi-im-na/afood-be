package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateOrderDto;
import bkdn.afoodbe.dto.OrderDto;
import bkdn.afoodbe.entity.Order;
import bkdn.afoodbe.entity.Staff;
import bkdn.afoodbe.entity.TableSitting;
import bkdn.afoodbe.model.OrderStatus;
import bkdn.afoodbe.repository.OrderRepository;
import bkdn.afoodbe.repository.StaffRepository;
import bkdn.afoodbe.repository.TableSittingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderService {
    private final OrderRepository orderRepository;
    private final StaffRepository staffRepository;
    private final TableSittingRepository tableSittingRepository;

    public List<OrderDto> getAllOrder() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order list is empty");
        }
        return orderList.stream().map(OrderDto::toOrderDto).collect(Collectors.toList());
    }

    public Long countOrders() {
        return orderRepository.count();
    }

    public BigDecimal sumTotalCost() {
        return orderRepository.sumTotalCost();
    }

    public BigDecimal averageCost() {
        return orderRepository.averageCost().setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal conversionRate() {
        Long paidOrders = orderRepository.countPaidOrders();
        long unpaidOrders = orderRepository.count();

        if (paidOrders == 0) {
            return BigDecimal.ZERO;
        }
        if (unpaidOrders == 0) {
            return BigDecimal.ONE;
        }
        return BigDecimal.valueOf(paidOrders).divide(BigDecimal.valueOf(unpaidOrders), 2, RoundingMode.HALF_UP);
    }

    public OrderDto addOrder(CreateOrderDto createOrderDto) {
        Optional<Staff> staff = staffRepository.findById(createOrderDto.staffId());
        if (staff.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff with id " + createOrderDto.staffId() + " not found");
        }
        Optional<TableSitting> tableSitting = tableSittingRepository.findById(createOrderDto.tableSittingId());
        if (tableSitting.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Table sitting with id " + createOrderDto.tableSittingId() + " not found");
        }
        Order order = Order.builder()
                .orderInTime(Instant.now())
                .orderStatus(OrderStatus.SERVING.toString())
                .staff(staff.get())
                .tableSitting(tableSitting.get())
                .totalCost(createOrderDto.totalCost())
                .build();
        orderRepository.save(order);
        return OrderDto.toOrderDto(order);
    }

    public void deleteOrder(int id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id " + id + " not found");
        }
        orderRepository.delete(order);
    }

    public OrderDto updateOrderTimeOut(int id) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id " + id + " not found");
        }
        order.setOrderOutTime(Instant.now());
        orderRepository.saveAndFlush(order);
        return OrderDto.toOrderDto(order);
    }

    public OrderDto updateOrderStatus(int id, String status) {
        Order order = orderRepository.findById(id);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order with id " + id + " not found");
        }
        order.setOrderStatus(status);
        order.setOrderOutTime(Instant.now());
        orderRepository.saveAndFlush(order);
        return OrderDto.toOrderDto(order);
    }
}
