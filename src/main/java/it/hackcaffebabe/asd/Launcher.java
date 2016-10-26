package it.hackcaffebabe.asd;

import it.hackcaffebabe.asd.gen.NumbersFileGenerator;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FileNotFoundException;

import static it.hackcaffebabe.asd.LauncherOptions.*;

/**
 * Entry point to ASDPlayground application
 */
public class Launcher {
    private static Options ARGS_OPTIONS = new Options();
    private static CommandLine ARGS_CLI;

    public static void main(String...args){
        checkArgs(args);

        if( ARGS_CLI.hasOption( OPTION_HELP_CMD ) ) {
            printHelpAndExit();
        }

        boolean generateVectorFlag = ARGS_CLI.hasOption( OPTION_GEN_VECTOR_CMD );
        boolean generateMatrixFlag = ARGS_CLI.hasOption( OPTION_GEN_MATRIX_CMD );
        boolean generateIntervalFlag = ARGS_CLI.hasOption( OPTION_GEN_INTERVAL_CMD );
        boolean generateOrderedVectorASCFlag = ARGS_CLI.hasOption(OPTION_GEN_ORDERED_ASC_VECTOR_CMD);
        boolean generateOrderedVectorDESFlag = ARGS_CLI.hasOption(OPTION_GEN_ORDERED_DES_VECTOR_CMD);
        if( generateVectorFlag ) {
            Long length = new Long(ARGS_CLI.getOptionValue( OPTION_GEN_VECTOR_CMD ));
            if( length <= 0 ) {
                System.out.println("Argument in -"+OPTION_GEN_VECTOR_CMD+
                        " | --"+OPTION_GEN_VECTOR_CMD_LONG+" is invalid: <= 0.");
                printHelpAndExit();
            }

            try {
                NumbersFileGenerator nfg = getOptionalArgs(false, false);
                nfg.generateRandomVector(length);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.err);
            }

        }else if( generateMatrixFlag ){
            String[] matrix_args = ARGS_CLI.getOptionValues( OPTION_GEN_MATRIX_CMD );
            Long rows = new Long(matrix_args[0]);
            if( rows <= 0 ){
                System.out.println("Argument in -"+OPTION_GEN_MATRIX_CMD+
                        " | --"+OPTION_GEN_MATRIX_CMD_LONG+" is invalid: rows <= 0.");
                printHelpAndExit();
            }
            Long cols = new Long(matrix_args[1]);
            if( cols <= 0){
                System.out.println("Argument in -"+OPTION_GEN_MATRIX_CMD+
                        "| --"+OPTION_GEN_MATRIX_CMD_LONG+" is invalid: cols <= 0.");
                printHelpAndExit();
            }

            try {
                NumbersFileGenerator nfg = getOptionalArgs(false, false);
                nfg.generateRandomMatrix(rows, cols);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.err);
            }

        }else if( generateIntervalFlag ) {
            String[] interval_args = ARGS_CLI.getOptionValues( OPTION_GEN_INTERVAL_CMD );
            Long length = new Long(interval_args[0]);
            if( length <= 0 ){
                System.out.println("Argument in -"+OPTION_GEN_INTERVAL_CMD+
                        " | --"+OPTION_GEN_INTERVAL_CMD_LONG+" is invalid: " +
                        "amount of interval <= 0.");
                printHelpAndExit();
            }

            try{
                NumbersFileGenerator nfg = getOptionalArgs(false, false);
                nfg.generateRandomInterval(length);
            }catch (FileNotFoundException e){
                e.printStackTrace(System.err);
            }

        }else if( generateOrderedVectorASCFlag ) {
            String[] ordered_vector_args = ARGS_CLI.getOptionValues(OPTION_GEN_ORDERED_ASC_VECTOR_CMD);
            Long length = new Long(ordered_vector_args[0]);
            if (length <= 0) {
                System.out.println("Argument in -" + OPTION_GEN_ORDERED_ASC_VECTOR_CMD +
                        " | --" + OPTION_GEN_ORDERED_ASC_VECTOR_CMD_LONG + " is invalid: " +
                        "vector length <= 0.");
                printHelpAndExit();
            }

            Long maxStep = new Long(ordered_vector_args[1]);
            if (maxStep < 1) {
                System.out.println("Argument in -" + OPTION_GEN_ORDERED_ASC_VECTOR_CMD +
                        " | --" + OPTION_GEN_ORDERED_ASC_VECTOR_CMD_LONG + " is invalid: " +
                        "maxStep < 1.");
                printHelpAndExit();
            }

            try {
                NumbersFileGenerator nfg = getOptionalArgs(true, false);
                nfg.generateOrderedVectorASC(length, maxStep);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.err);
            }

        }else if(generateOrderedVectorDESFlag){
            String[] ordered_vector_args = ARGS_CLI.getOptionValues(OPTION_GEN_ORDERED_DES_VECTOR_CMD);
            Long length = new Long(ordered_vector_args[0]);
            if (length <= 0) {
                System.out.println("Argument in -" + OPTION_GEN_ORDERED_DES_VECTOR_CMD +
                        " | --" + OPTION_GEN_ORDERED_DES_VECTOR_CMD_LONG + " is invalid: " +
                        "vector length <= 0.");
                printHelpAndExit();
            }

            Long maxStep = new Long(ordered_vector_args[1]);
            if (maxStep < 1) {
                System.out.println("Argument in -" + OPTION_GEN_ORDERED_DES_VECTOR_CMD +
                        " | --" + OPTION_GEN_ORDERED_DES_VECTOR_CMD_LONG + " is invalid: " +
                        "maxStep < 1.");
                printHelpAndExit();
            }

            try {
                NumbersFileGenerator nfg = getOptionalArgs(false, true);
                nfg.generateOrderedVectorDES(length, maxStep);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.err);
            }
        }else{
            printHelpAndExit();
        }
    }

//==============================================================================
//  UTILITY METHODS
//==============================================================================
    private static NumbersFileGenerator getOptionalArgs( boolean skipMaxFlag,
                                                         boolean skipMinFlag )
            throws FileNotFoundException {

        boolean outFileFlag = ARGS_CLI.hasOption( OPTION_OUTPUT_CMD );
        if( !outFileFlag )
            printGenerateMessageHelpAndExit("File path");

        boolean minFlag = ARGS_CLI.hasOption( OPTION_MINIMUM_CMD );
        if( !minFlag && !skipMinFlag )
            printGenerateMessageHelpAndExit("Lower bound of numbers");

        boolean maxFlag = ARGS_CLI.hasOption( OPTION_MAXIMUM_CMD );
        if( !maxFlag && !skipMaxFlag )
            printGenerateMessageHelpAndExit("Upper bound of numbers");

        String outFile = ARGS_CLI.getOptionValue( OPTION_OUTPUT_CMD );
        Long min = Long.MIN_VALUE;
        if( !skipMinFlag )
            min = new Long(ARGS_CLI.getOptionValue( OPTION_MINIMUM_CMD ));
        Long max = Long.MAX_VALUE;
        if( !skipMaxFlag )
            max = new Long(ARGS_CLI.getOptionValue( OPTION_MAXIMUM_CMD ));
        Long seed;
        if( ARGS_CLI.getOptionValue( OPTION_SEED_CMD ) == null ){
            seed = System.nanoTime();
        }else{
            seed = new Long( ARGS_CLI.getOptionValue( OPTION_SEED_CMD ));
        }

        return new NumbersFileGenerator(seed)
                        .setFile(new File(outFile))
                        .setBound(min, max);
    }

    private static void printGenerateMessageHelpAndExit(String message){
        System.out.println(message+" to generate missing.");
        printHelpAndExit();
    }

    private static void printHelpAndExit(){
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(
                80, // width
                "./ASDPlayground [OPTIONS]", // cli syntax
                "Where OPTIONS are listed below:", // header
                ARGS_OPTIONS, ""); // footer
        System.exit(0);
    }

    private static void checkArgs(String...args){
        try{
            ARGS_OPTIONS.addOption(
                    OPTION_HELP_CMD,
                    OPTION_HELP_CMD_LONG,
                    false,
                    OPTION_HELP_DESCRIPTION
            );

            Option generateVector = new Option(
                    OPTION_GEN_VECTOR_CMD,
                    OPTION_GEN_VECTOR_CMD_LONG,
                    true,
                    OPTION_GEN_VECTOR_DESCRIPTION);
            generateVector.setArgs(1);
            ARGS_OPTIONS.addOption(generateVector);

            Option generateMatrix = new Option(
                    OPTION_GEN_MATRIX_CMD,
                    OPTION_GEN_MATRIX_CMD_LONG,
                    true,
                    OPTION_GEN_MATRIX_DESCRIPTION);
            generateMatrix.setArgs(2);
            ARGS_OPTIONS.addOption(generateMatrix);

            Option generateInterval = new Option(
                    OPTION_GEN_INTERVAL_CMD,
                    OPTION_GEN_INTERVAL_CMD_LONG,
                    true,
                    OPTION_GEN_INTERVAL_DESCRIPTION);
            generateInterval.setArgs(1);
            ARGS_OPTIONS.addOption(generateInterval);

            Option generateOrderedVectorASC = new Option(
                    OPTION_GEN_ORDERED_ASC_VECTOR_CMD,
                    OPTION_GEN_ORDERED_ASC_VECTOR_CMD_LONG,
                    true,
                    OPTION_GEN_ORDERED_ASC_VECTOR_DESCRIPTION
            );
            generateOrderedVectorASC.setArgs(2);
            ARGS_OPTIONS.addOption(generateOrderedVectorASC);

            Option generatedOrderedVectorDES = new Option(
                    OPTION_GEN_ORDERED_DES_VECTOR_CMD,
                    OPTION_GEN_ORDERED_DES_VECTOR_CMD_LONG,
                    true,
                    OPTION_GEN_ORDERED_DES_VECTOR_DESCRIPTION
            );
            generatedOrderedVectorDES.setArgs(2);
            ARGS_OPTIONS.addOption(generatedOrderedVectorDES);

            // options used to generate random stuff
            ARGS_OPTIONS.addOption(
                    OPTION_OUTPUT_CMD,
                    OPTION_OUTPUT_CMD_LONG,
                    true,
                    OPTION_OUTPUT_DESCRIPTION
            );
            ARGS_OPTIONS.addOption(
                    OPTION_MINIMUM_CMD,
                    OPTION_MINIMUM_CMD_LONG,
                    true,
                    OPTION_MINIMUM_DESCRIPTION
            );
            ARGS_OPTIONS.addOption(
                    OPTION_MAXIMUM_CMD,
                    OPTION_MAXIMUM_CMD_LONG,
                    true,
                    OPTION_MAXIMUM_DESCRIPTION
            );
            ARGS_OPTIONS.addOption(
                    OPTION_SEED_CMD,
                    OPTION_SEED_CMD_LONG,
                    true,
                    OPTION_SEED_DESCRIPTION
            );

            ARGS_CLI = new DefaultParser().parse(ARGS_OPTIONS, args);
        }catch (Exception e){
            System.out.println(e.getMessage());
            printHelpAndExit();
        }
    }
}
