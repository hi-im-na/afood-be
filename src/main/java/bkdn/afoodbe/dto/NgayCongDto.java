package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link bkdn.afoodbe.entity.NgayCong}
 */
@Builder
public record NgayCongDto(Integer id, Integer staffId, Instant workedDate,
                          BigDecimal timeWorked) implements Serializable {
    public static NgayCongDto toNgayCongDto(bkdn.afoodbe.entity.NgayCong ngayCong) {
        return NgayCongDto.builder()
                .id(ngayCong.getId())
                .staffId(ngayCong.getStaff().getId())
                .workedDate(ngayCong.getWorkedDate())
                .timeWorked(ngayCong.getTimeWorked())
                .build();
    }
}
