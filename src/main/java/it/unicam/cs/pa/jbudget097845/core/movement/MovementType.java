package it.unicam.cs.pa.jbudget097845.core.movement;

/**
 * Enumeration the define the types of movement
 */
public enum MovementType {
    DEBIT,
    CREDIT;

    @Override
    public String toString() {
        switch (this) {
            case DEBIT: return "debit";
            case CREDIT: return "credit";
        }
        return "";
    }
}
