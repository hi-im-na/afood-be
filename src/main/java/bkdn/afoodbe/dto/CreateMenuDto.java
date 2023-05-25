package bkdn.afoodbe.dto;

import java.io.Serializable;

/**
 * DTO for {@link bkdn.afoodbe.entity.Menu}
 */
public record CreateMenuDto(String name, String description) implements Serializable {
}