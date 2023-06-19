package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.OrderDelivery}
 */
@Builder
public record OrderDeliveryDto(Integer id, Integer orderFoodId, Integer staffId,
                               String deliveryStatus) implements Serializable {
    public static OrderDeliveryDto toOrderDeliveryDto(bkdn.afoodbe.entity.OrderDelivery orderDelivery) {
        return OrderDeliveryDto.builder()
                .id(orderDelivery.getId())
                .orderFoodId(orderDelivery.getOrderFood().getId())
                .staffId(orderDelivery.getStaff().getId())
                .deliveryStatus(orderDelivery.getDeliveryStatus())
                .build();
    }
}
