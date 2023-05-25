package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Integer> {
    Food findById(int id);

    List<Food> findAllByNameContainsIgnoreCase(String name);

    boolean existsByName(String name);

}