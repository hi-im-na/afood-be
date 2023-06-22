package bkdn.afoodbe.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link bkdn.afoodbe.entity.Order}
 */
public record CreateOrderDto(Integer tableSittingId, Integer staffId, BigDecimal totalCost) implements Serializable {
}
