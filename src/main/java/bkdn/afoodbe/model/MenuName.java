package bkdn.afoodbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuName {
    APPETIZER(1),
    MAIN_COURSE(2),
    DESSERT(3),
    DRINK(4),
    OTHER(5);

    private final int numVal;
}
