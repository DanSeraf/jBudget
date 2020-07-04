package it.unicam.cs.pa.jbudget097845;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.unicam.cs.pa.jbudget097845.exc.DirectoryError;
import it.unicam.cs.pa.jbudget097845.model.Ledger;

import java.io.File;
import java.io.IOException;

/**
 * This class is responsible of saving the application data.
 *
 * At the moment since all the application data reside inside the Registry it provides
 * a serialization into a JSON.
 *
 * The structure of the JSON have reference to all the application data and each class
 * serialized has the defined annotation to let the Jackson library understand how a class
 * should be serialized.
 *
 */
public class ApplicationState {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File DEFAULT_DIR_PATH = new File("./data");
    private final File DEFAULT_REGISTRY_PATH = new File("./data/save.json");
    private File SAVE_PATH;

    public ApplicationState(File save_path) throws DirectoryError {
        if (!save_path.getParentFile().mkdirs())
            throw new DirectoryError("Error creating directory to store application data");

        SAVE_PATH = save_path;

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.registerModule(new JavaTimeModule());
    }

    public ApplicationState() throws DirectoryError {
        if (!DEFAULT_DIR_PATH.exists())
            if(!DEFAULT_DIR_PATH.mkdir())
                throw new DirectoryError("Error creating directory to store application data");

        SAVE_PATH = DEFAULT_REGISTRY_PATH;

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.registerModule(new JavaTimeModule());
    }

    public void save() throws UnsupportedOperationException {
        // to avoid problems with tests
        if (SAVE_PATH == null) return;

        try {
            mapper.writeValue(SAVE_PATH, StateWrapper.instance());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while storing application data");
        }
    }

    public void load() throws UnsupportedOperationException {
        try {
            StateWrapper sw = mapper.readValue(SAVE_PATH, StateWrapper.class);
            StateWrapper.setInstance(sw);
            sw.loadInstances();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
