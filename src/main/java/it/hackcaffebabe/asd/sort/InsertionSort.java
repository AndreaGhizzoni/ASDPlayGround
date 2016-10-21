package it.hackcaffebabe.asd.sort;

import it.hackcaffebabe.asd.benchmark.SpeedTestFileOutput;
import it.hackcaffebabe.asd.Util;

/**
 * InsertionSort Algorithm
 */
public class InsertionSort {
    public static void main(String...args){
        // this vector contains the length of the random array to sort
        Integer[] lengthToTest = {3, 5, 10, 50, 100, 1000, 10000, 100000};

        // print-to-file class
        SpeedTestFileOutput s = new SpeedTestFileOutput("InsertionSort-result");
        s.open();

        // test insertionSort algorithm for every vector length specified before
        Integer[] unorderedVector;
        long startTime, finishTime;
        for( Integer l : lengthToTest ){
            unorderedVector = Util.makeRandomVector(l);

            startTime = System.currentTimeMillis();
            insertionSort(unorderedVector, unorderedVector.length);
            finishTime = System.currentTimeMillis();

            s.print(l, finishTime-startTime);
        }

        // close file
        s.finish();
    }

    public static void insertionSort( Integer[] vector, Integer vectorLength ){
        int j;
        Integer temp;
        for( int i = 1; i < vectorLength; i++ ){
            temp = vector[i];
            j = i;
            while( (j > 0) && (vector[j-1] > temp) ) {
                vector[j] = vector[j-1];
                j--;
            }
            vector[j] = temp;
        }
    }
}
