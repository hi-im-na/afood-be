package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link bkdn.afoodbe.entity.Order}
 */
@Builder
public record OrderDto(Integer id, Instant orderInTime, Instant orderOutTime, Integer tableSittingId, Integer staffId,
                       String orderStatus) implements Serializable {
    public static OrderDto toOrderDto(bkdn.afoodbe.entity.Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .orderInTime(order.getOrderInTime())
                .orderOutTime(order.getOrderOutTime())
                .tableSittingId(order.getTableSitting().getId())
                .staffId(order.getStaff().getId())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
