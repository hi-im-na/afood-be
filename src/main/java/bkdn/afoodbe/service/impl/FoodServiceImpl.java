package bkdn.afoodbe.service.impl;

import bkdn.afoodbe.dto.CreateFoodDto;
import bkdn.afoodbe.dto.FoodDto;
import bkdn.afoodbe.entity.Food;
import bkdn.afoodbe.entity.Menu;
import bkdn.afoodbe.entity.MenuFood;
import bkdn.afoodbe.repository.FoodRepository;
import bkdn.afoodbe.repository.MenuRepository;
import bkdn.afoodbe.service.IFoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FoodServiceImpl implements IFoodService {

    private final FoodRepository foodRepository;
    private final MenuRepository menuRepository;

    @Override
    public List<FoodDto> getAllFood() {
        List<Food> foodList = foodRepository.findAll();
        if (foodList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food list is empty");
        }
        return foodList.stream().map(FoodDto::toFoodDto).collect(Collectors.toList());
    }

    @Override
    public FoodDto findFoodById(int id) {
        Food food = foodRepository.findById(id);
        if (food == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food with id " + id + " not found");
        }
        return FoodDto.toFoodDto(food);
    }

    @Override
    public List<FoodDto> findAllFoodByName(String name) {
        List<Food> food = foodRepository.findAllByNameContainsIgnoreCase(name);
        if (food.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food with name " + name + " not found");
        }
        return food.stream().map(FoodDto::toFoodDto).collect(Collectors.toList());
    }

    @Override
    public FoodDto addFood(CreateFoodDto createFoodDto) {
        if (foodRepository.existsByName(createFoodDto.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Food with name " + createFoodDto.name() + " already exists");
        }
        Food food = Food.builder()
                .name(createFoodDto.name())
                .description(createFoodDto.description())
                .cost(createFoodDto.cost())
                .image(createFoodDto.image())
                .build();
        foodRepository.saveAndFlush(food);
        return FoodDto.toFoodDto(food);
    }

    @Override
    public void deleteFood(int id) {
        if (!foodRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food with id " + id + " not found");
        }
        foodRepository.deleteById(id);
    }

    @Override
    public Set<FoodDto> findAllFoodByMenuId(int menuId) {
        Menu menu = menuRepository.findById(menuId);
        if (menu == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with id " + menuId + " not found");
        }
        Set<MenuFood> menuFoods = menu.getMenuFoods();

        return menuFoods.stream()
                .map(MenuFood::getFood)
                .map(FoodDto::toFoodDto)
                .collect(Collectors.toSet());
    }
}
