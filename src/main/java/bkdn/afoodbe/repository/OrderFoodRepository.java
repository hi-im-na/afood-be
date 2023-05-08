package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.OrderFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderFoodRepository extends JpaRepository<OrderFood, Integer> {
}