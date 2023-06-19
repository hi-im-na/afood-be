package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link bkdn.afoodbe.entity.TableSitting}
 */
@Builder
public record TableSittingDto(Integer id, Instant inTime, Instant outTime, Integer numPersonSitting,
                              Integer tableId) implements Serializable {
    public static TableSittingDto toTableSittingDto(bkdn.afoodbe.entity.TableSitting tableSitting) {
        return TableSittingDto.builder()
                .id(tableSitting.getId())
                .inTime(tableSitting.getInTime())
                .outTime(tableSitting.getOutTime())
                .numPersonSitting(tableSitting.getNumPersonSitting())
                .tableId(tableSitting.getTable().getId())
                .build();
    }
}
