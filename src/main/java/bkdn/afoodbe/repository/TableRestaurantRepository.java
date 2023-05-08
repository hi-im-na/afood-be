package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {
}