package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findById(int id);

    @Query("SELECT sum(o.totalCost) from Order o")
    BigDecimal sumTotalCost();

    @Query("SELECT avg(o.totalCost) from Order o")
    BigDecimal averageCost();

    @Query("SELECT count(o) from Order o where o.orderStatus = 'PAID'")
    Long countPaidOrders();

    @Query("SELECT count(o) from Order o where o.orderStatus = 'CANCELLED'")
    Long countUnpaidOrders();
}
