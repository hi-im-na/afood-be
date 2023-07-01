package bkdn.afoodbe.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link bkdn.afoodbe.entity.NgayCong}
 */
public record CreateNgayCongDto(Integer staffId, Instant workedDate, BigDecimal timeWorked) implements Serializable {
}
