package it.unicam.cs.pa.jbudget097845.model.budget;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import it.unicam.cs.pa.jbudget097845.TagDeserializer;
import it.unicam.cs.pa.jbudget097845.exc.tag.TagDeserializationException;
import it.unicam.cs.pa.jbudget097845.model.GeneralTag;
import it.unicam.cs.pa.jbudget097845.model.Ledger;
import it.unicam.cs.pa.jbudget097845.model.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that store the expected values for each tag
 *
 * @see Budget
 */
@JsonTypeName("general_budget")
public class GeneralBudget implements Budget {

    @JsonDeserialize(keyUsing = TagDeserializer.class)
    private Map<Tag, Double> budgetValues = new HashMap<>();

    @Override
    @JsonIgnore
    public List<Tag> getTags() {
        return new ArrayList<>(budgetValues.keySet());
    }

    @Override
    public void set(Tag t, double expectedAmount) {
        budgetValues.put(t, expectedAmount);
    }

    @Override
    public double get(Tag t) {
        return budgetValues.get(t);
    }

}
