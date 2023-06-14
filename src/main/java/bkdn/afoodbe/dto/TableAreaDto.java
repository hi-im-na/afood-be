package bkdn.afoodbe.dto;

import bkdn.afoodbe.entity.TableArea;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.TableArea}
 */
@Builder
public record TableAreaDto(Integer id, String areaName, String areaDescription) implements Serializable {
    public static TableAreaDto toTableAreaDto(TableArea tableArea) {
        return TableAreaDto.builder()
                .id(tableArea.getId())
                .areaName(tableArea.getAreaName())
                .areaDescription(tableArea.getAreaDescription())
                .build();
    }
}
