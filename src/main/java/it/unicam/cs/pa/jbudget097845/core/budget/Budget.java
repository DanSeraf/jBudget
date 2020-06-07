package it.unicam.cs.pa.jbudget097845.core.budget;

import it.unicam.cs.pa.jbudget097845.core.Tag;

import java.util.List;

public interface Budget {

    List<Tag> getTags();

    void set(Tag t, double expected);

    double get(Tag t);
}
