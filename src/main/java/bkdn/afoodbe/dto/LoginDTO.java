package bkdn.afoodbe.dto;

import java.io.Serializable;

public record LoginDTO(String username, String password) implements Serializable {
}