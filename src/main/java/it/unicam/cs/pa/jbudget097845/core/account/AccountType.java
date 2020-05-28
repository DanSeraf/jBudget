package it.unicam.cs.pa.jbudget097845.core.account;

public enum AccountType {
    ASSETS,
    LIABILITIES;

    @Override
    public String toString() {
       switch (this) {
           case ASSETS: return "assets";
           case LIABILITIES: return "liabilities";
       }
       return "";
    }
}
