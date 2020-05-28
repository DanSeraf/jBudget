package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Tag;
import it.unicam.cs.pa.jbudget097845.core.transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public interface BudgetReport {

    List<Tag> tags();

    Map<Tag, Double> report();

    Budget getBudget();

    double get(Tag t);

}
