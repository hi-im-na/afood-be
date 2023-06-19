package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Integer> {
    boolean existsByOrderIdAndFoodId(int orderId, int foodId);

    OrderFood findByOrderIdAndFoodId(int orderId, int foodId);

    Set<OrderFood> findByOrderId(int orderId);
}
