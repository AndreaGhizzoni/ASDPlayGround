package it.hackcaffebabe.asd;

/**
 * TODO add doc
 */
public class LauncherOptions {
    public static final String OPTION_HELP_CMD = "h";
    public static final String OPTION_HELP_CMD_LONG = "help";
    public static final String OPTION_HELP_DESCRIPTION = "print help";

    public static final String OPTION_GEN_VECTOR_CMD = "gv";
    public static final String OPTION_GEN_VECTOR_CMD_LONG = "generate-vector";
    public static final String OPTION_GEN_VECTOR_DESCRIPTION =
            "Generate a file with a vector of random numbers in it. "+
            "<arg> is the length of generated vector. "+
            "See options: -o -m -M -s.";

    public static final String OPTION_GEN_ORDERED_ASC_VECTOR_CMD = "gva";
    public static final String OPTION_GEN_ORDERED_ASC_VECTOR_CMD_LONG =
            "generate-vector-ascending";
    public static final String OPTION_GEN_ORDERED_ASC_VECTOR_DESCRIPTION =
            "Generate a file with vector, ordered in ascending ordered, of " +
            "integer numbers in it. " +
            "<arg> is intended as <length> <maxStep> where length is " +
            "the size of generated vector and <maxStep> the max gap " +
            "between two values. Option -M | --max will be ignored. "+
            "See options: -o -m -s.";

    public static final String OPTION_GEN_ORDERED_DES_VECTOR_CMD = "gvd";
    public static final String OPTION_GEN_ORDERED_DES_VECTOR_CMD_LONG =
            "generate-vector-descending";
    public static final String OPTION_GEN_ORDERED_DES_VECTOR_DESCRIPTION =
            "Generate a file with vector, in descending ordered, of " +
            "integer numbers in it. " +
            "<arg> is intended as <length> <maxStep> where length is " +
            "the size of generated vector and <maxStep> the max gap " +
            "between two values. Option -m | --min will be ignored. "+
            "See options: -o -M -s.";

    public static final String OPTION_GEN_MATRIX_CMD = "gm";
    public static final String OPTION_GEN_MATRIX_CMD_LONG = "generate-matrix";
    public static final String OPTION_GEN_MATRIX_DESCRIPTION =
            "Generate a file with a matrix of random numbers in it. "+
            "<arg> is intended as <rows> <columns>. "+
            "See options: -o -m -M -s.";

    public static final String OPTION_GEN_INTERVAL_CMD = "gi";
    public static final String OPTION_GEN_INTERVAL_CMD_LONG = "generate-interval";
    public static final String OPTION_GEN_INTERVAL_DESCRIPTION =
            "Generate a file with a list of interval in it. "+
            "<arg> is the amount of intervals to generate. " +
            "See options: -o -m -M -s.";

    public static final String OPTION_OUTPUT_CMD = "o";
    public static final String OPTION_OUTPUT_CMD_LONG = "output";
    public static final String OPTION_OUTPUT_DESCRIPTION =
            "USED ONLY WITH --"+OPTION_GEN_VECTOR_CMD_LONG+
                    " | --"+OPTION_GEN_MATRIX_CMD_LONG+
                    " | --"+OPTION_GEN_INTERVAL_CMD_LONG+": " +
            "specify the path of file that will be generated.";

    public static final String OPTION_MINIMUM_CMD = "m";
    public static final String OPTION_MINIMUM_CMD_LONG = "min";
    public static final String OPTION_MINIMUM_DESCRIPTION =
            "USED ONLY WITH --"+OPTION_GEN_VECTOR_CMD_LONG+
                    " | --"+OPTION_GEN_MATRIX_CMD_LONG+
                    " | --"+OPTION_GEN_INTERVAL_CMD_LONG+": " +
            "specify the lower bound of generated numbers.";

    public static final String OPTION_MAXIMUM_CMD = "M";
    public static final String OPTION_MAXIMUM_CMD_LONG = "max";
    public static final String OPTION_MAXIMUM_DESCRIPTION =
            "USED ONLY WITH --"+OPTION_GEN_VECTOR_CMD_LONG+
                    " | --"+OPTION_GEN_MATRIX_CMD_LONG+
                    " | --"+OPTION_GEN_INTERVAL_CMD_LONG+": " +
            "specify the upper bound of generated numbers.";

    public static final String OPTION_SEED_CMD = "s";
    public static final String OPTION_SEED_CMD_LONG = "seed";
    public static final String OPTION_SEED_DESCRIPTION =
            "USED ONLY WITH --"+OPTION_GEN_VECTOR_CMD_LONG+
                    " | --"+OPTION_GEN_MATRIX_CMD_LONG+
                    " | --"+OPTION_GEN_INTERVAL_CMD_LONG+": " +
            "specify a seed used to generate random numbers.";
}

