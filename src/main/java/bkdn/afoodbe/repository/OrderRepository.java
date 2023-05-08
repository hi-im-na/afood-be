package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}