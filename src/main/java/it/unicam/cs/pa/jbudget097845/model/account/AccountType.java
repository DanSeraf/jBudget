package it.unicam.cs.pa.jbudget097845.model.account;

/**
 * Enumeration to the define the Account Types
 */
public enum AccountType {
    ASSETS("assets"),
    LIABILITIES("liabilities");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public static AccountType of(String rawType) {
        for (AccountType aType: AccountType.values()) {
            if (aType.value.equalsIgnoreCase(rawType)) {
                return aType;
            }
        }
        throw new IllegalArgumentException(
                String.format("No constant with value %s found", rawType)
        );
    }
    @Override
    public String toString() {
       switch (this) {
           case ASSETS: return "assets";
           case LIABILITIES: return "liabilities";
       }
       return "";
    }
}
