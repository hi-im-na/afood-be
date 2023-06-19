package bkdn.afoodbe.dto;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.Order}
 */
public record CreateOrderDto(Integer tableSittingId, Integer staffId) implements Serializable {
}
