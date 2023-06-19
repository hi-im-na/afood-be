package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.CreateFoodDto;
import bkdn.afoodbe.dto.FoodDto;

import java.util.List;
import java.util.Set;

public interface IFoodService {
    List<FoodDto> getAllFood();

    FoodDto findFoodById(int id);

    List<FoodDto> findAllFoodByName(String name);

    FoodDto addFood(CreateFoodDto createFoodDto);

    void deleteFood(int id);

    Set<FoodDto> findFoodsByMenuId(int menuId);

    Set<FoodDto> findFoodsByOrderId(int orderId);

}
