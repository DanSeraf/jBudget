package it.unicam.cs.pa.jbudget097845;

import it.unicam.cs.pa.jbudget097845.core.*;
import it.unicam.cs.pa.jbudget097845.server.Server;
import org.apache.commons.cli.CommandLine;

import java.io.File;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        CommandLineParser.init();
        CommandLine cmd = CommandLineParser.getCommands(args);
        //String config_path = cmd.getOptionValue("config");
        String host = cmd.getOptionValue("host") != null ? cmd.getOptionValue("host") : "0.0.0.0";
        int port = cmd.getOptionValue("port") != null ? Integer.parseInt(cmd.getOptionValue("port")) : 22333;

        ApplicationState.init();
        Registry r = (Registry) ApplicationState.load(StateType.REGISTRY);
        Controller.init(r);
        Server.run(host, port);
    }
}
