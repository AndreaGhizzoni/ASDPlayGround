package it.hackcaffebabe.asd.util;

import java.util.ArrayList;

/**
 * Array Util class
 */
public class ArrayUtil {

    /**
     * This method print the string representation of given vector using
     * toString() method of each object.
     * @param vector of Objects to print.
     */
    public static void printVector( Object[] vector ){
        System.out.print("[ ");
        for( Object o : vector ) System.out.print( o.toString()+" " );
        System.out.println("]");
    }

    /**
     * This method print the string representation of given multidimensional
     * array using toString() method of each object.
     * @param matrix of Objects to print
     */
    public static void printMatrix( Object[][] matrix ){
        // the following 2 for loop is searching for the max columns span
        // for better visualization.
        Integer maxColSpan = 1;
        for( Object[] row: matrix ){
            for( Object element: row ){
                if( element.toString().length()+1 > maxColSpan )
                    maxColSpan = element.toString().length()+1;
            }
        }

        String border = "|";
        for( Object[] row : matrix ){
            System.out.print(border);
            for( Object element : row )
                System.out.printf("%"+maxColSpan+"s", element.toString());
            System.out.println(border);
        }
    }

    /**
     * This method swap two elements of given vector: swap the element indexed by
     * the first argument with the element indexed by the second argument.
     * @param vector of Objects to swap two elements.
     * @param one {@link Integer} the index first element to swap with the second.
     * @param two {@link Integer} the index of second element to swap.
     */
    public static void swap( Object[] vector, Integer one, Integer two ){
        Object tmp = vector[one];
        vector[one] = vector[two];
        vector[two] = tmp;
    }

    /**
     * This method checks if the Integer's array is in ascending order.
     * @param vector of Integer's object to check if is in ascending order.
     * @param start {@link Integer} index to start checking.
     * @param end {@link Integer} index to end checking.
     * @return {@link Boolean} true if is in ascending order, false otherwise.
     */
    public static boolean isOrderedASC( Integer[] vector, Integer start,
                                        Integer end ){
        Integer tmp = vector[start];
        boolean isOrdered = true;
        for( Integer i=start+1; i<end-1 && isOrdered; i++ ){
            if( tmp <= vector[i] )
                tmp = vector[i];
            else
                isOrdered = false;
        }
        return isOrdered;
    }

    /**
     * This method checks if the Integer's array is in descending order.
     * @param vector of Integer's object to check if is in descending order.
     * @param start {@link Integer} index to start checking.
     * @param end {@link Integer} index to end checking.
     * @return {@link Boolean} true if is in descending order, false otherwise.
     */
    public static boolean isOrderedDES( Integer[] vector, Integer start,
                                        Integer end ){
        Integer tmp = vector[start];
        boolean isOrdered = true;
        for( Integer i=start+1; i<end-1 && isOrdered; i++ ){
            if( tmp >= vector[i] )
                tmp = vector[i];
            else
                isOrdered = false;
        }
        return isOrdered;
    }

    /**
     * This method returns the maximum integer founded in the array given:
     * from 0 to vector.length.
     * @param vector of Integer's object to find the maximum.
     * @return {@link Integer} of maximum number found.
     */
    public static Integer max( Integer[] vector ){
        return max( vector, vector.length );
    }

    /**
     * This method returns the maximum integer founded in the array given:
     * from 0 to length, where length is the argument given.
     * @param vector of Integer's object to find the maximum.
     * @param length of vector passed as first argument.
     * @return {@link Integer} of maximum number found.
     */
    public static Integer max( Integer[] vector, Integer length ){
        Integer pos = maxInPosition( vector, length );
        return vector[pos];
    }

    /**
     * This method returns the position of the first maximum integer founded in
     * the array given: from 0 to length, where length is the argument given.
     * @param vector of Integer's object to find the maximum.
     * @param length of vector passed as first argument.
     * @return {@link Integer} of the position of the first maximum number found.
     */
    public static Integer maxInPosition( Integer[] vector, Integer length ){
        return maxInPosition(vector, 0, length);
    }

    /**
     * This method returns the position of the first maximum integer founded in
     * the array given: from start to end, where start and end are the arguments
     * given.
     * @param vector of Integer's object to find the maximum.
     * @param start  index of element to start searching for maximum.
     * @param end index of element to end searching for maximum.
     * @return {@link Integer} of the position of the first maximum number found.
     */
    public static Integer maxInPosition( Integer[] vector, Integer start,
                                         Integer end ){
        Integer tempMaxPosition = start;
        for( int i=1; i<end; i++ ){
            if( vector[i] > tempMaxPosition ) tempMaxPosition = i;
        }
        return tempMaxPosition;
    }

    /**
     * This method returns the minimum integer founded in the array given:
     * from 0 to vector.length.
     * @param vector of Integer's object to find the minimum.
     * @return {@link Integer} of minimum number found.
     */
    public static Integer min( Integer[] vector ){
        return min( vector, vector.length );
    }

    /**
     * This method returns the minimum integer founded in the array given:
     * from 0 to length, where length is the argument given.
     * @param vector of Integer's object to find the minimum.
     * @param length of vector passed as first argument.
     * @return {@link Integer} of minimum number found.
     */
    public static Integer min( Integer[] vector, Integer length ){
        Integer pos = minInPosition(vector, length);
        return vector[pos];
    }

    /**
     * This method returns the position of the first minimum integer founded in
     * the array given: from 0 to length, where length is the argument given.
     * @param vector of Integer's object to find the minimum.
     * @param length of vector passed as first argument.
     * @return {@link Integer} of the position of the first minimum number found.
     */
    public static Integer minInPosition( Integer[] vector, Integer length ){
        return minInPosition(vector, 0, length);
    }

    /**
     * This method returns the position of the first minimum integer founded in
     * the array given: from start to end, where start and end are the arguments
     * given.
     * @param vector of Integer's object to find the minimum.
     * @param start  index of element to start searching for minimum.
     * @param end index of element to end searching for minimum.
     * @return {@link Integer} of the position of the first minimum number found.
     */
    public static Integer minInPosition( Integer[] vector, Integer start,
                                         Integer end ){
        Integer tempMin = start;
        for( Integer i=start+1; i<end; i++ ){
            if( vector[i] < vector[tempMin] ) tempMin = i;
        }
        return tempMin;
    }
}
