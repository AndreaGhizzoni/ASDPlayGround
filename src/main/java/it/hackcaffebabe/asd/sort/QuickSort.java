package it.hackcaffebabe.asd.sort;

import it.hackcaffebabe.asd.benchmark.SpeedTestFileOutput;
import it.hackcaffebabe.asd.Util;
import it.hackcaffebabe.asd.util.ArrayUtil;

/**
 * QuickSort algorithm
 */
public class QuickSort {
    public static Integer perno( Integer[] vector, Integer first, Integer last ){
        Integer x = vector[first];
        Integer j = first;
        for( Integer i=first; i<=last; i++ ){
            if( vector[i]<x ){
                j++;
                ArrayUtil.swap(vector, i, j);
            }
        }
        vector[first] = vector[j];
        vector[j] = x;
        return j;
    }

    public static void quickSort( Integer[] vector, Integer first, Integer last ){
        if( first < last ){
            Integer j = perno(vector, first, last);
            quickSort(vector, first, j-1);
            quickSort(vector, j+1, last);
        }
    }

    public static void main(String...args) {
        // this vector contains the length of the random array to sort
        Integer[] lengthToTest = {3, 5, 10, 50, 100, 1000, 10000, 100000};

        // print-to-file class
        SpeedTestFileOutput s = new SpeedTestFileOutput("QuickSort-result");
        s.open();

        // test insertionSort algorithm for every vector length specified before
        Integer[] vector;
        long startTime, finishTime;
        for( Integer l : lengthToTest ) {
            vector = Util.makeRandomVector(l);

            startTime = System.currentTimeMillis();
            quickSort(vector, 0, vector.length-1);
            finishTime = System.currentTimeMillis();

            s.print(l, finishTime-startTime);
        }

        // close file
        s.finish();
    }
}
