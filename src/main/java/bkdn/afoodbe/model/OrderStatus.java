package bkdn.afoodbe.model;

public enum OrderStatus {
    SERVING,
    CANCELLED,
    PAID;

    public static OrderStatus fromString(String status) {
        return switch (status.toUpperCase()) {
            case "SERVING" -> SERVING;
            case "CANCELLED" -> CANCELLED;
            case "PAID" -> PAID;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
