package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Integer> {
}