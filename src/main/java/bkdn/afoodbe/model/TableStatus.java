package bkdn.afoodbe.model;

public enum TableStatus {
    AVAILABLE,
    OCCUPIED,
    RESERVED;

    public static TableStatus fromString(String status) {
        return switch (status.toUpperCase()) {
            case "AVAILABLE" -> AVAILABLE;
            case "OCCUPIED" -> OCCUPIED;
            case "RESERVED" -> RESERVED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
