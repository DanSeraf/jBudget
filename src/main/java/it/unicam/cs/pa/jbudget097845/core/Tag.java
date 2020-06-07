package it.unicam.cs.pa.jbudget097845.core;

import com.fasterxml.jackson.annotation.*;
import it.unicam.cs.pa.jbudget097845.core.account.Account;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        scope= Tag.class)
@JsonSubTypes(
        @JsonSubTypes.Type(value=GeneralTag.class, name="general_tag")
)
public interface Tag {
    int getId();

    String getName();

    String getDescription();

    TagType getType();
}
