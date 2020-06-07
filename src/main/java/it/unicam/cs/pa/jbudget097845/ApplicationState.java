package it.unicam.cs.pa.jbudget097845;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.unicam.cs.pa.jbudget097845.core.Registry;
import it.unicam.cs.pa.jbudget097845.core.budget.BudgetReport;
import it.unicam.cs.pa.jbudget097845.exc.DirectoryError;

import java.io.File;
import java.io.IOException;

/**
 * This class is responsible of managing the application state.
 * It can save and load the application state in a json format using the Jackson library.
 * It also provide the ObjectMapper, useful for writing and reading JSON.
 *
 * @author Daniele Serafini
 *
 */

public class ApplicationState {

    private ApplicationState class_instance = null;
    private final ObjectMapper mapper = new ObjectMapper();
    private final File DEFAULT_DIR_PATH = new File("./data");
    private final File DEFAULT_REGISTRY_PATH = new File("./data/registry.json");
    private final File DEFAULT_REPORT_PATH = new File("./data/report.json");
    private File registry_path;
    private File report_path;

    private ApplicationState() {}

    public ApplicationState ApplicationState() {
        if (this.class_instance == null)
            this.class_instance = new ApplicationState();
        return this.class_instance;
    }

    public void init(File registry_path, File report_path) throws DirectoryError {
        if (!registry_path.getParentFile().mkdirs() || report_path.getParentFile().mkdirs())
            throw new DirectoryError("Error creating directory to store application data");

        this.registry_path = registry_path;
        this.report_path = report_path;

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
    }

    public void init() throws DirectoryError {
        if (!this.DEFAULT_DIR_PATH.exists())
            if (!this.DEFAULT_DIR_PATH.mkdir())
                throw new DirectoryError("Error creating directory to store application data");

        this.registry_path = DEFAULT_REGISTRY_PATH;
        this.report_path = DEFAULT_REPORT_PATH;

        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
    }

    public void save(Object o) throws UnsupportedOperationException {
        try {
            if (o instanceof Registry) mapper.writeValue(registry_path, o);
            else if (o instanceof BudgetReport) mapper.writeValue(report_path, o);
            else throw new UnsupportedOperationException("The storing of the object provided is not implemented yet");
        } catch (IOException e) {
            System.err.println("Error while storing application data");
        }
    }

    public Object load(StateType type) throws UnsupportedOperationException {
        try {
            switch (type) {
                case REGISTRY:
                    return mapper.readValue(registry_path, Registry.class);
                case REPORT:
                    return mapper.readValue(report_path, BudgetReport.class);
                default:
                    throw new UnsupportedOperationException("The load of the state provided is not implemented yet");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }
}
