package it.hackcaffebabe.asd.gen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

/**
 * I am lazy
 */
public class NumbersFileGenerator {
    private PrintStream pS;
    private long seed;
    private Integer min, max;

    /**
     * This constructor make a NumbersFileGenerator object with a seed given
     * @param seed {@link Long} the seed given to generate random numbers.
     */
    public NumbersFileGenerator( long seed ){ this.setSeed(seed); }

    /**
     * This method set the file to generate the random structure.
     * @param file {@link File} the file to write the random structure.
     * @return {@link NumbersFileGenerator} the object with file set.
     * @throws FileNotFoundException if errors occurs while creating stream to
     *                               given file.
     * @throws IllegalArgumentException if file given is null or is a directory.
     */
    public NumbersFileGenerator setFile( File file )
            throws FileNotFoundException, IllegalArgumentException {

        if( file == null )
            throw new IllegalArgumentException("Argument file is null.");
        if( file.isDirectory() )
            throw new IllegalArgumentException("Argument fis invalid: " +
                    "directory found.");

        this.pS = new PrintStream( file.getAbsolutePath() );
        return this;
    }

    /**
     * This method set the seed for random numbers generator.
     * @param seed {@link Long} the seed given to generate random numbers.
     * @return {@link NumbersFileGenerator} the object with seed set.
     */
    public NumbersFileGenerator setSeed( long seed ){
        this.seed = seed;
        return this;
    }

    /**
     * This method set the lower and upper bound of random number generator.
     * @param min {@link Integer} the lower bound.
     * @param max {@link Integer} the upper bound.
     * @throws IllegalArgumentException if argument min or max are null, or
     *                                  if min > max.
     * @return {@link NumbersFileGenerator} the object with bounds set.
     */
    public NumbersFileGenerator setBound( Integer min, Integer max )
            throws IllegalArgumentException{

        if( min == null )
            throw new IllegalArgumentException("Argument min given is null.");
        if( max == null )
            throw new IllegalArgumentException("Argument max given is null.");
        if( min > max )
            throw new IllegalArgumentException("Argument given is min > max.");

        this.min = min;
        this.max = max;
        return this;
    }

    /**
     * This method generate a random Vector structure, with given length, seed,
     * file and bounds set.
     * @param amount {@link Integer} the length's Vector.
     * @throws IllegalArgumentException if length given is null or <= 0.
     */
    public void generateRandomVector( Integer amount )
            throws IllegalArgumentException{

        generateRandomVector(
                this.pS,
                new Random( this.seed ),
                amount,
                true
        );
        this.pS.flush();
        this.pS.close();
    }

    /* Generate a random vector: using given PrintStream, Random object
     * generator, Vector's length and a flag to print into the print stream the
     * vector's length.
     */
    private void generateRandomVector( PrintStream pS, Random r, Integer amount,
                       boolean printAmount ) throws IllegalArgumentException{

        if( amount == null )
            throw new IllegalArgumentException("Vector length passed is null.");
        if( amount <= 0 )
            throw new IllegalArgumentException("Vector length passed is <= 0.");

        // if argument printAmount is true, print the vector length on stream
        if( printAmount )
            pS.println(amount);
        for( int i=0; i<amount; i++ ){
            // print the i-th random vector's element
            pS.printf( "%d ", RandUtil.random(r, this.min, this.max) );
        }
    }

    /**
     * This method generate a random matrix into file with given dimensions.
     * @param rows {@link Integer} the number of rows.
     * @param cols {@link Integer} the number of columns.
     * @throws IllegalArgumentException if row or columns ae null or <= 0.
     */
    public void generateRandomMatrix( Integer rows, Integer cols )
            throws IllegalArgumentException{

        if( rows == null )
            throw new IllegalArgumentException("Argument rows passed is null.");
        if( cols == null )
            throw new IllegalArgumentException("Argument cols passed is null.");
        if( rows <= 0 || cols <= 0 )
            throw new IllegalArgumentException("Argument rows|cols passed " +
                    "is <= 0");

        Random r = new Random( this.seed );

        this.pS.printf("%d %d\n", rows, cols);
        for( Integer i=0; i<rows; i++ ){
            generateRandomVector( this.pS, r, cols, false );
            pS.println();
        }

        this.pS.flush();
        this.pS.close();
    }

    /**
     * This method generate a set of interval and printed into file.
     * An interval is pair of two integers <x, y> where x < y and at least
     * (y-x) >= 1.
     * @param length {@link Integer} the numbers of interval to generate.
     * @param maxStep {@link Integer} the maximum step between lower and upper
     *                               bound of interval.
     * @throws IllegalArgumentException if arguments given are null or <= 0|1
     *                                  respectively
     */
    public void generateRandomInterval( Integer length, Integer maxStep )
            throws IllegalArgumentException{

        if( length == null )
            throw new IllegalArgumentException("Argument length given is null.");
        if( maxStep == null)
            throw new IllegalArgumentException("Argument maxStep given is null.");
        if( maxStep <= 1 || length <= 0 )
            throw new IllegalArgumentException("Argument maxStep <= 1 or length "+
                    "given <= 0.");

        Random r = new Random( this.seed );

        this.pS.println(length);
        Integer base, step;
        for( Integer i=0; i<length; i++ ){
            base = RandUtil.random( r, this.min, this.max );
            step = RandUtil.random( 1, maxStep );
            this.pS.printf("%d %d\n", base, base+step);
        }

        this.pS.flush();
        this.pS.close();
    }

    /* This method generate an ordered vector in ascending or in descending
     * order */
    private void generateOrderedVector( Random r, Integer length,
                                        Integer maxStep, boolean isAscending )
            throws IllegalArgumentException{

        if( length == null )
            throw new IllegalArgumentException("Argument length given is null.");
        if( length <= 0 )
            throw new IllegalArgumentException("Argument length given is <= 0.");
        if( maxStep == null )
            throw new IllegalArgumentException("Argument maxStep given is null.");
        if( maxStep <= 1 )
            throw new IllegalArgumentException("Argument maxStep given is <= 1.");

        Integer base;
        if( isAscending )
            base = this.min;
        else
            base = this.max;

        this.pS.println(length);
        Integer step, gen;
        for( Integer i=0; i<length; i++ ) {
            step = RandUtil.random( r, 1, maxStep );
            if( isAscending )
                gen = base + step;
            else
                gen = base - step;

            this.pS.printf("%d ", gen);
        }

        this.pS.flush();
        this.pS.close();
    }

    /**
     * This method generate a vector with all elements ordered as the follow:
     * x1 < x2 < [...] < xn
     * @param length {@link Integer} the vector length.
     * @param maxStep {@link Integer} the maximum step between xi and xj where
     *                               i < j.
     * @throws IllegalArgumentException if arguments are null or <= 0.
     */
    public void generateOrderedVectorASC( Integer length, Integer maxStep )
            throws IllegalArgumentException {

        generateOrderedVector( new Random(this.seed), length, maxStep, true );
    }

    /**
     * This method generate a vector with all elements ordered as the follow:
     * x1 > x2 > [...] > xn
     * @param length {@link Integer} the vector length.
     * @param maxStep {@link Integer} the maximum step between xi and xj where
     *                               i > j.
     * @throws IllegalArgumentException if arguments are null or <= 0.
     */
    public void generateOrderedVectorDES( Integer length, Integer maxStep )
            throws IllegalArgumentException{

        generateOrderedVector( new Random(this.seed), length, maxStep, false );
    }
}
