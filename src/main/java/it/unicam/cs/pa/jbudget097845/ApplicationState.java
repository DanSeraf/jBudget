package it.unicam.cs.pa.jbudget097845;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetReport;
import it.unicam.cs.pa.jbudget097845.exc.DirectoryError;

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

    private static ApplicationState class_instance = new ApplicationState();
    private final ObjectMapper mapper = new ObjectMapper();
    private final File DEFAULT_DIR_PATH = new File("./data");
    private final File DEFAULT_REGISTRY_PATH = new File("./data/registry.json");
    private final File DEFAULT_REPORT_PATH = new File("./data/report.json");
    private File registryPath;
    private File reportPath;

    private ApplicationState() {
    }

    public static ApplicationState instance() {
        if (class_instance == null)
            class_instance = new ApplicationState();
        return class_instance;
    }

    public void init(File registry_path, File report_path) throws DirectoryError {
        if (!registry_path.getParentFile().mkdirs() || report_path.getParentFile().mkdirs())
            throw new DirectoryError("Error creating directory to store application data");

        registryPath = registry_path;
        reportPath = report_path;

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.registerModule(new JavaTimeModule());
    }

    public void init() throws DirectoryError {
        if (!DEFAULT_DIR_PATH.exists())
            if(!DEFAULT_DIR_PATH.mkdir())
                throw new DirectoryError("Error creating directory to store application data");

        registryPath = DEFAULT_REGISTRY_PATH;
        reportPath = DEFAULT_REPORT_PATH;

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.registerModule(new JavaTimeModule());
    }

    public void save(Object o) throws UnsupportedOperationException {
        // to avoid problems with tests
        if (registryPath == null) return;

        try {
            if (o instanceof Registry) mapper.writeValue(registryPath, o);
            else if (o instanceof BudgetReport) mapper.writeValue(reportPath, o);
            else throw new UnsupportedOperationException("The storing of the object provided is not implemented yet");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error while storing application data");
        }
    }

    public Object load(StateType type) throws UnsupportedOperationException {
        try {
            switch (type) {
                case REGISTRY:
                    return mapper.readValue(registryPath, Registry.class);
                case REPORT:
                    return mapper.readValue(reportPath, BudgetReport.class);
                default:
                    throw new UnsupportedOperationException("The load of the state provided is not implemented yet");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
