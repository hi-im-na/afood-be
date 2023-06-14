package bkdn.afoodbe.dto;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.TableRestaurant}
 */
@Builder
public record TableRestaurantDto(Integer id, Integer tableAreaId, Integer maxCapacity, Integer servingStaffId,
                                 String tableStatus) implements Serializable {
    public static TableRestaurantDto toTableRestaurantDto(bkdn.afoodbe.entity.TableRestaurant tableRestaurant) {
        return TableRestaurantDto.builder()
                .id(tableRestaurant.getId())
                .tableAreaId(tableRestaurant.getTableArea().getId())
                .maxCapacity(tableRestaurant.getMaxCapacity())
                .servingStaffId(tableRestaurant.getServingStaff().getId())
                .tableStatus(tableRestaurant.getTableStatus())
                .build();
    }
}
