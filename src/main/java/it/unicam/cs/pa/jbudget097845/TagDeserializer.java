package it.unicam.cs.pa.jbudget097845;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import it.unicam.cs.pa.jbudget097845.exc.tag.TagDeserializationException;
import it.unicam.cs.pa.jbudget097845.model.GeneralTag;
import it.unicam.cs.pa.jbudget097845.model.Tag;

/**
 * Custom deserializer for class of type GeneralTag
 */
public class TagDeserializer extends KeyDeserializer {
    @Override
    public Tag deserializeKey(String rawTag,
                              DeserializationContext ctxt) {
        String[] parsedTag = rawTag.split(" \\| ");
        // if the length is not 2 probably the tag is serialized in a wrong way
        // the first two elements of the array must contain the name and the description
        // of the tag
        if (parsedTag.length != 2)
            throw new TagDeserializationException(
                    "Cannot deserialize Tag string with more or less than two values");

        return new GeneralTag(parsedTag[0], parsedTag[1]);
    }
}
