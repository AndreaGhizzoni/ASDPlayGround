package it.hackcaffebabe.asd.gen;

import java.util.Random;

/**
 * Yes, I am a very lazy person
 */
public class RandUtil {
    /**
     * This method generate a random number in range min <= x < max using
     * current time as seed.
     * @param min {@link Integer} the lower bound of random number.
     * @param max {@link Integer} the upper bound of random number.
     * @throws IllegalArgumentException if min > max or some argument are null.
     * @return {@link Integer} as generate random number.
     */
    public static Integer random( Integer min, Integer max )
            throws IllegalArgumentException{
        return random(System.nanoTime(), min, max);
    }

    /**
     * TODO add doc
     * @param min
     * @param max
     * @return
     * @throws IllegalArgumentException
     */
    public static Long random( Long min, Long max )
            throws IllegalArgumentException{
        return random(System.nanoTime(), min, max);
    }

    /**
     * This method generate a random number in range min <= x < max using given
     * long argument as seed.
     * @param seed {@link Long} the seed to generate random number.
     * @param min {@link Integer} the lower bound of random number.
     * @param max {@link Integer} the upper bound of random number.
     * @throws IllegalArgumentException if min > max or some argument are null.
     * @return {@link Integer} as generate random number.
     */
    public static Integer random( long seed, Integer min, Integer max )
            throws IllegalArgumentException{
        return random( new Random(seed), min, max);
    }

    /**
     * TODO add doc
     * @param seed
     * @param min
     * @param max
     * @return
     * @throws IllegalArgumentException
     */
    public static Long random( long seed, Long min, Long max )
            throws IllegalArgumentException{
        return random( new Random(seed), min, max);
    }

    /**
     * This method generate a random number using the {@link Random} generator
     * given as argument.
     * @param r {@link Random} the random number generator.
     * @param min {@link Integer} the lower bound of random number.
     * @param max {@link Integer} the upper bound of random number.
     * @throws IllegalArgumentException if min > max or some argument are null.
     * @return {@link Integer} as generate random number.
     */
    public static Integer random( Random r, Integer min, Integer max )
            throws IllegalArgumentException{
        if( r == null )
            throw new IllegalArgumentException("Argument Random given is null.");
        if( min == null || max == null )
            throw new IllegalArgumentException("Argument min|max given is null.");
        if( min > max )
            throw new IllegalArgumentException("Argument given is min > max.");

        return r.nextInt((max-min)+1)+min;
    }

    /**
     * TODO add doc
     * @param r
     * @param min
     * @param max
     * @return
     * @throws IllegalArgumentException
     */
    public static Long random( Random r, Long min, Long max )
            throws IllegalArgumentException {

        if( r == null )
            throw new IllegalArgumentException("Argument Random given is null.");
        if( min == null || max == null )
            throw new IllegalArgumentException("Argument min|max given is null.");
        if( min > max )
            throw new IllegalArgumentException("Argument given is min > max.");

        return ((long)(r.nextDouble()*(max-min)))+min;
    }
}
