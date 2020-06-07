package it.unicam.cs.pa.jbudget097845;

import org.apache.commons.cli.*;

public class CommandLineParser {

    private CommandLineParser class_instance = null;
    private static final DefaultParser parser = new DefaultParser();
    private static final HelpFormatter formatter = new HelpFormatter();
    private static final Options options = new Options();

    private CommandLineParser() {}

    public static void init() {
        Option host = Option.builder("h").required(false)
                .longOpt("host")
                .desc("server host")
                .hasArg(true)
                .build();
        host.getValue("0.0.0.0");

        Option port = Option.builder("p").required(false)
                .longOpt("port")
                .desc("server port")
                .hasArg(true)
                .build();

        options.addOption(host).addOption(port);
    }

    public CommandLineParser CommandLineParser() {
        if (this.class_instance == null) this.class_instance = new CommandLineParser();
        return this.class_instance;
    }

    public static CommandLine getCommands(String[] args) {
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            formatter.printHelp("jBudget", options);
            System.exit(1);
        }
        return cmd;
    }
}
