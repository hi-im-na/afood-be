package bkdn.afoodbe.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link bkdn.afoodbe.entity.Food}
 */
public record CreateFoodDto(String name, String description, BigDecimal cost, String image) implements Serializable {
}