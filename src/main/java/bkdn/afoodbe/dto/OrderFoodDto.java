package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.OrderFood}
 */
@Builder
public record OrderFoodDto(Integer id, Integer orderId, Integer foodId, Integer quantity) implements Serializable {
    public static OrderFoodDto toOrderFoodDto(bkdn.afoodbe.entity.OrderFood orderFood) {
        return OrderFoodDto.builder()
                .id(orderFood.getId())
                .orderId(orderFood.getOrder().getId())
                .foodId(orderFood.getFood().getId())
                .quantity(orderFood.getQuantity())
                .build();
    }
}
