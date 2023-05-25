package bkdn.afoodbe.controller;

import bkdn.afoodbe.dto.FoodDto;
import bkdn.afoodbe.dto.MenuDto;
import bkdn.afoodbe.service.IFoodService;
import bkdn.afoodbe.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PublicController {

    private final IFoodService foodService;
    private final IMenuService menuService;

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
}
