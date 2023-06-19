package bkdn.afoodbe.model;

public enum DeliveryStatus {

    DELIVERING,
    DELIVERED,
    CANCELLED;

    public static DeliveryStatus fromString(String status) {
        return switch (status.toUpperCase()) {
            case "DELIVERING" -> DELIVERING;
            case "CANCELLED" -> CANCELLED;
            case "DELIVERED" -> DELIVERED;
            default -> throw new IllegalStateException("Unexpected value: " + status);
        };
    }
}
