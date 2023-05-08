package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}