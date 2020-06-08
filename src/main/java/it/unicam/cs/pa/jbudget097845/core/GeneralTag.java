package it.unicam.cs.pa.jbudget097845.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * General Tag implementation
 */
@JsonTypeName("general_tag")
public class GeneralTag implements Tag {

    private final String name;
    private final String description;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GeneralTag(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description)
    {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
