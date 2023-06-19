package bkdn.afoodbe.service;

import bkdn.afoodbe.dto.MenuFoodDto;
import bkdn.afoodbe.entity.Food;
import bkdn.afoodbe.entity.Menu;
import bkdn.afoodbe.entity.MenuFood;
import bkdn.afoodbe.repository.FoodRepository;
import bkdn.afoodbe.repository.MenuFoodRepository;
import bkdn.afoodbe.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MenuFoodService {
    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;
    private final MenuFoodRepository menuFoodRepository;

    /**
     * Add food to menu by menu id and food id
     *
     * @param menuId menu id
     * @param foodId food id
     * @return {@link MenuFood}: a record in menu_food table
     */
    public MenuFoodDto addMenuFood(int menuId, int foodId) {
        Menu menu = menuRepository.findById(menuId);
        Food food = foodRepository.findById(foodId);
        if (menu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found");
        }
        if (food == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        }
        if (menuFoodRepository.existsByMenuIdAndFoodId(menuId, foodId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Food already exists in menu");
        }
        MenuFood menuFood = new MenuFood();
        menuFood.setMenu(menu);
        menuFood.setFood(food);

        MenuFood newMenuFood = menuFoodRepository.saveAndFlush(menuFood);
        return MenuFoodDto.toMenuFoodDto(newMenuFood);
    }

    public void deleteMenuFood(int menuId, int foodId) {
        Menu menu = menuRepository.findById(menuId);
        Food food = foodRepository.findById(foodId);
        if (menu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found");
        }
        if (food == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        }

        MenuFood menuFood = menuFoodRepository.findByMenuIdAndFoodId(menuId, foodId);
        if (menuFood == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found in menu");
        }
        menuFoodRepository.delete(menuFood);
    }
}
