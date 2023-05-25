package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.CreateFoodDto;
import bkdn.afoodbe.dto.CreateMenuDto;
import bkdn.afoodbe.dto.FoodDto;
import bkdn.afoodbe.dto.MenuDto;
import bkdn.afoodbe.entity.MenuFood;
import bkdn.afoodbe.service.IFoodService;
import bkdn.afoodbe.service.IMenuService;
import bkdn.afoodbe.service.MenuFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@PreAuthorize(value = "hasRole('MANAGER') or hasRole('ADMIN')")
public class ManagerController {
    private final IFoodService foodService;
    private final IMenuService menuService;
    private final MenuFoodService menuFoodService;


    // ********** FOOD **********
    @GetMapping("/foods")
    public ResponseEntity<Object> getAllFood() {
        List<FoodDto> foods = foodService.getAllFood();
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/foods/create")
    public ResponseEntity<Object> addFood(CreateFoodDto createFoodDto) {
        FoodDto food = foodService.addFood(createFoodDto);
        return ResponseEntity.ok(food);
    }

    @GetMapping("/foods/findallfoodbyname")
    public ResponseEntity<Object> findAllFoodByName(String name) {
        List<FoodDto> foods = foodService.findAllFoodByName(name);
        return ResponseEntity.ok(foods);
    }

    @PostMapping("/foods/addfoodtomenu")
    public ResponseEntity<Object> addFoodToMenu(int menuId, int foodId) {
        MenuFood menuFood = menuFoodService.addMenuFood(menuId, foodId);
        return ResponseEntity.ok(menuFood);
    }

    @GetMapping("/foods/findallfoodfrommenu")
    public ResponseEntity<Object> findAllFoodByMenuId(int menuId) {
        Set<FoodDto> foods = foodService.findAllFoodByMenuId(menuId);
        return ResponseEntity.ok(foods);
    }

    @DeleteMapping("/foods/deletefoodfrommenu")
    public ResponseEntity<Object> deleteFoodFromMenu(int menuId, int foodId) {
        menuFoodService.deleteMenuFood(menuId, foodId);
        return ResponseEntity.ok("Delete success");
    }



    // ********** MENU **********

    @GetMapping("/menus/findallmenubyname")
    public ResponseEntity<Object> findAllMenuByName(String name) {
        List<MenuDto> menus = menuService.findAllMenuByName(name);
        return ResponseEntity.ok(menus);
    }

    @PostMapping("/menus/create")
    public ResponseEntity<Object> createMenu(CreateMenuDto createMenuDto) {
        MenuDto menu = menuService.createMenu(createMenuDto);
        return ResponseEntity.ok(menu);
    }
}
