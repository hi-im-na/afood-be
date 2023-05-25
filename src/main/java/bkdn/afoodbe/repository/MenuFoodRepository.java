package bkdn.afoodbe.repository;

import bkdn.afoodbe.entity.MenuFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuFoodRepository extends JpaRepository<MenuFood, Integer> {
    MenuFood findByMenuIdAndFoodId(int menuId, int foodId);
    boolean existsByMenuIdAndFoodId(int menuId, int foodId);
}