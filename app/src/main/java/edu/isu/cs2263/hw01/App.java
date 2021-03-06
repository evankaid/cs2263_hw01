/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.isu.cs2263.hw01;



import org.apache.commons.cli.*;


import java.io.IOException;
import java.nio.file.*;




public class App {
    

   /* Main method to run program
      Version 1.0.0 */
    public static void main(String[] args)  {

        Options options = createOptions();
        
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args); 

            if(cmd.hasOption("help")){
                printHelpMessage(options);
                System.exit(0);
            } else if(cmd.hasOption("batch")){
                batchDisplay(cmd);
            } else if(cmd.hasOption("output")){
                outputDisplay(cmd);

            }
            } catch (ParseException e) {
                System.out.println("Failed to parse: " + e.getMessage());
       
            }
        
    }
    /* Creates the options for command line input */
    private static Options createOptions(){
        Options options = new Options();
        Option helpOption = Option.builder("h").longOpt("help").desc("Print usage message.").build();
        Option batchOption = Option.builder("b").longOpt("batch").argName("file").hasArg().desc("Batch file that has expressions to evaluate").build();
        Option outputOption = Option.builder("o").longOpt("output").argName("file").hasArg().desc("Output File").build();

        options.addOption(helpOption);
        options.addOption(batchOption);
        options.addOption(outputOption);

        return options;
    }

    /* Formats the output when using the help option
       Passes in all of the created options */
    private static void printHelpMessage(Options options){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("eval [OPTIONS] \n\n Evaluation of simple mathematical expressions\n\n", options);
        }

    /* Displays the batch file for user */
    private static String batchDisplay(CommandLine cmd){
        String file = "";
        if(cmd.hasOption("batch")){
            file = cmd.getOptionValue("batch");
            Path filePath = Path.of(file);
            if(Files.exists(filePath)){
                System.out.println(filePath);
            } else {
                System.out.println("The file doesn't exist for the batch input.");
                System.exit(1);
            }
        }
        return file;
    }

    /*  Uses the file input to change where results are output */
    private static void outputDisplay(CommandLine cmd){
        if(cmd.hasOption("output")){
            String file = cmd.getOptionValue("output");
            Path filePath = Path.of(file);
            try {
                Files.deleteIfExists(filePath);
                Files.createFile(filePath);
                System.out.println(filePath);

            } catch(IOException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }














    public static String getGreeting(){
            return "Hi";
    }
}
