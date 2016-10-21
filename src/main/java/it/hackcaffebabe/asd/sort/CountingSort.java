package it.hackcaffebabe.asd.sort;

import it.hackcaffebabe.asd.benchmark.SpeedTestFileOutput;
import it.hackcaffebabe.asd.Util;
import it.hackcaffebabe.asd.util.ArrayUtil;

/**
 * Counting Sort Algorithm
 */
public class CountingSort {

    public static void main( String...args ){
        // this vector contains the length of the random array to sort
        Integer[] lengthToTest = {3, 5, 10, 50, 100, 1000, 10000, 100000};

        // print-to-file class
        SpeedTestFileOutput s = new SpeedTestFileOutput("CountingSort-result");
        s.open();

        // test insertionSort algorithm for every vector length specified before
        Integer[] vector;
        long startTime, finishTime;
        for( Integer l : lengthToTest ) {
            vector = Util.makeRandomVector(l);

            startTime = System.currentTimeMillis();
            countingSort(
                    vector,
                    vector.length,
                    ArrayUtil.max(vector, vector.length)
            );
            finishTime = System.currentTimeMillis();

            s.print(l, finishTime-startTime);
        }

        // close file
        s.finish();
    }

    public static void countingSort( Integer[] vector, Integer length, Integer maxValue){
        Integer i;
        maxValue++;
        Integer[] countVector= new Integer[maxValue];
        for( i = 0; i<maxValue; i++ ){
            countVector[i] = 0;
        }
        for( i = 0; i<length; i++ ){
            countVector[vector[i]]++;
        }
        Integer j = 0;
        for( i = 0; i<maxValue; i++ ){
            while( countVector[i] > 0 ){
                vector[j] = i;
                j++;
                countVector[i]--;
            }
        }
    }
}
