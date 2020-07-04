package it.unicam.cs.pa.jbudget097845.model.budget;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import it.unicam.cs.pa.jbudget097845.model.Tag.Tag;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

/**
 * Interface that provides the main methods to implement a new `Budget`.
 * The idea is to associate an expected amount for each tag.
 *
 * @see Tag
 *
 */
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Budget.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value = GeneralBudget.class, name = "general_budget")
)
public interface Budget {

    /**
     * @return the tags associated to the Budget
     */
    List<Tag> getTags();

    /**
     * @param t the Tag to add
     * @param expected the gain/expense expected for the Tag
     */
    void set(Tag t, double expected);

    /**
     * @param t a Tag
     * @return the gain/expense expected for the Tag
     */
    double get(Tag t);
}
