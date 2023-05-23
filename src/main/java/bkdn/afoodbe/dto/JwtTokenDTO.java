package bkdn.afoodbe.dto;

import java.io.Serializable;

public record JwtTokenDTO(String token, long duration) implements Serializable {
}