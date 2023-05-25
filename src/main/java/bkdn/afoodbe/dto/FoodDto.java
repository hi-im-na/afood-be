package bkdn.afoodbe.dto;

import bkdn.afoodbe.entity.Food;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link bkdn.afoodbe.entity.Food}
 */
@Builder
public record FoodDto(Integer id, String name, String description, BigDecimal cost, String image,
                      Instant dateUpdated) implements Serializable {
    public static FoodDto toFoodDto(Food food) {
        return FoodDto.builder()
                .id(food.getId())
                .name(food.getName())
                .description(food.getDescription())
                .cost(food.getCost())
                .image(food.getImage())
                .dateUpdated(food.getDateUpdated())
                .build();
    }
}