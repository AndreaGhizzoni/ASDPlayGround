package it.hackcaffebabe.asd.exp;

import it.hackcaffebabe.asd.util.ArrayUtil;

import java.io.*;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * Reading input file from file given.
 */
public class ReadingInputFiles {
    private File file;

    /**
     * Build an object to read input files.
     * @param file {@link File} the input files.
     * @throws IllegalArgumentException if argument is null or a directory.
     */
    public ReadingInputFiles(File file ) throws IllegalArgumentException {
        if( file == null )
            throw new IllegalArgumentException("File argument given is null.");
        if( file.isDirectory() )
            throw new IllegalArgumentException("File argument given is a " +
                    "directory: file needed.");

        this.file = file;
    }

    /**
     * This method read an input file that contains a vector.
     * @return Array of Integer that contains all the data from file given.
     * @throws IOException if read or parsing the file fail.
     */
    public Integer[] readVector() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.file))
        );

        return readVector( reader, 0, true );
    }

    /* This method read vector from a buffered reader given
     * - bufferedReader the buffer to read the line
     * - dimension how many integer to read
     * - readDimension set to true to read the first line of bufferedRead as
     *                 dimension of vector
     */
    private Integer[] readVector( BufferedReader bufferedReader,
                                  Integer dimension, boolean readDimension )
            throws IOException {

        Integer dim;
        if( readDimension ) {
            // read the first line that contains only the vector dimension.
            dim = Integer.parseInt(bufferedReader.readLine());
        }else {
            dim = dimension;
        }

        StringTokenizer tokenizer = new StringTokenizer(
                bufferedReader.readLine(), " ", false
        );

        Integer[] vector = new Integer[dim];
        Integer i=0;
        while(tokenizer.hasMoreTokens()){
            vector[i++] = Integer.parseInt(tokenizer.nextToken());
        }
        return vector;
    }

    /**
     * This method read an input file that contains a matrix.
     * @return Matrix of Integer that contains all the data from file given.
     * @throws IOException if read or parsing the file fail.
     */
    public Integer[][] readMatrix() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.file))
        );

        StringTokenizer tokenizer = new StringTokenizer(
                bufferedReader.readLine(), " ", false
        );

        // get matrix dimension from the first line.
        Integer rows = Integer.parseInt( tokenizer.nextToken() );
        Integer cols = Integer.parseInt( tokenizer.nextToken() );

        Integer[][] matrix = new Integer[rows][cols];
        Integer i;
        for( i=0; i<rows; i++ ){
            matrix[i] = readVector(bufferedReader, cols, false);
        }
        return matrix;
    }

    public static void main(String... args){
        try{
            File vector = Paths.get("data/vector-10").toFile();
            File matrix = Paths.get("data/matrix-10-10").toFile();

            ReadingInputFiles rVector = new ReadingInputFiles(vector);
            ArrayUtil.printVector( rVector.readVector() );

            ReadingInputFiles rMatrix = new ReadingInputFiles(matrix);
            ArrayUtil.printMatrix( rMatrix.readMatrix() );
        }catch (Exception e){
            e.printStackTrace(System.err);
        }
    }
}
