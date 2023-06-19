package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.MenuFood}
 */
@Builder
public record MenuFoodDto(Integer id, Integer foodId, Integer menuId) implements Serializable {
    public static MenuFoodDto toMenuFoodDto(bkdn.afoodbe.entity.MenuFood menuFood) {
        return MenuFoodDto.builder()
                .id(menuFood.getId())
                .foodId(menuFood.getFood().getId())
                .menuId(menuFood.getMenu().getId())
                .build();
    }
}
