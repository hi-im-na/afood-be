package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.FoodDto;
import bkdn.afoodbe.dto.MenuDto;
import bkdn.afoodbe.service.IFoodService;
import bkdn.afoodbe.service.IMenuService;
import bkdn.afoodbe.service.TableAreaService;
import bkdn.afoodbe.service.TableRestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PublicController {

    private final IFoodService foodService;
    private final IMenuService menuService;
    private final TableAreaService tableAreaService;
    private final TableRestaurantService tableRestaurantService;

    @GetMapping("/menus")
    public ResponseEntity<Object> getAllMenu() {
        List<MenuDto> menus = menuService.getAllMenu();

        return ResponseEntity.ok(menus);
    }

    @GetMapping("/foods")
    public ResponseEntity<Object> getAllFood() {
        List<FoodDto> foods = foodService.getAllFood();
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/foods/findfoodsbymenuid")
    public ResponseEntity<Object> findFoodsByMenuId(int menuId) {
        Set<FoodDto> foods = foodService.findFoodsByMenuId(menuId);
        return ResponseEntity.ok(foods);
    }

    @GetMapping("/tables")
    public ResponseEntity<Object> getAllTable() {
        return ResponseEntity.ok(tableRestaurantService.getAllTableRestaurant());
    }

    @GetMapping("/tableareas")
    public ResponseEntity<Object> getAllTableArea() {
        return ResponseEntity.ok(tableAreaService.getAllTableArea());
    }

    @GetMapping("/getTablesByAreaId")
    public ResponseEntity<Object> getTablesByAreaId(int areaId) {
        return ResponseEntity.ok(tableRestaurantService.getTablesByAreaId(areaId));
    }

}
