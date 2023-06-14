package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.TableRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant, Integer> {
    List<TableRestaurant> findByTableAreaId(int areaId);
}
