package bkdn.afoodbe.dto;

import bkdn.afoodbe.entity.Menu;
import lombok.Builder;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link bkdn.afoodbe.entity.Menu}
 */
@Builder
public record MenuDto(Integer id, String name, String description, Instant dateUpdated) implements Serializable {
    public static MenuDto toMenuDto(Menu menu) {
        return MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .dateUpdated(menu.getDateUpdated())
                .build();
    }
}