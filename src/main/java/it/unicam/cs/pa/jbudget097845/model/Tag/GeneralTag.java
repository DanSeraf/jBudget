package it.unicam.cs.pa.jbudget097845.model.Tag;

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

    @Override
    public String toString() {
        return this.name + " | " + this.description;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        Tag tag = (Tag) obj;

        return (tag.getName().equals(this.name)  && tag.getDescription().equals(this.description));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
