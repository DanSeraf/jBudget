package it.unicam.cs.pa.jbudget097845.model.movement;

/**
 * Enumeration the define the types of movement
 */
public enum MovementType {
    DEBIT("debit"),
    CREDIT("credit");

    private final String value;

    MovementType(String value) {
        this.value = value;
    }

    public static MovementType of(String rawType) {
        for (MovementType mType: MovementType.values()) {
            if (mType.value.equalsIgnoreCase(rawType)) {
                return mType;
            }
        }
        throw new IllegalArgumentException(
                String.format("No constant with value %s found", rawType)
        );
    }

    @Override
    public String toString() {
        switch (this) {
            case DEBIT: return "debit";
            case CREDIT: return "credit";
        }
        return "";
    }
}
