package it.unicam.cs.pa.jbudget097845;

import it.unicam.cs.pa.jbudget097845.gui.AppMain;
import it.unicam.cs.pa.jbudget097845.state.ApplicationState;
import javafx.application.Application;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //CommandLineParser.init();
        //CommandLine cmd = CommandLineParser.getCommands(args);
        //String config_path = cmd.getOptionValue("config");
        //String host = cmd.getOptionValue("host") != null ? cmd.getOptionValue("host") : "0.0.0.0";
        //int port = cmd.getOptionValue("port") != null ? Integer.parseInt(cmd.getOptionValue("port")) : 22333;

        ApplicationState state = new ApplicationState();
        File registry_file = new File("./data/save.json");

        if (registry_file.exists()) {
            state.load();
        }

        Application.launch(AppMain.class, args);
        //Server.run(host, port);
    }
}
