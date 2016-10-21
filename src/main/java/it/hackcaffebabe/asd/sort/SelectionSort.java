package it.hackcaffebabe.asd.sort;

import it.hackcaffebabe.asd.benchmark.SpeedTestFileOutput;
import it.hackcaffebabe.asd.Util;
import it.hackcaffebabe.asd.util.ArrayUtil;

import java.util.ArrayList;

/**
 * Selection Sort Algorithm
 */
public class SelectionSort {

    public static void main(String...args){
        // this vector contains the length of the random array to sort
        Integer[] lengthToTest = {3, 5, 10, 50, 100, 1000, 10000, 100000};

        // print-to-file class
        SpeedTestFileOutput s = new SpeedTestFileOutput("SelectionSort-result");
        s.open();

        // test insertionSort algorithm for every vector length specified before
        Integer[] vector;
        long startTime, finishTime;
        for( Integer l : lengthToTest ) {
            vector = Util.makeRandomVector(l);

            startTime = System.currentTimeMillis();
            selectionSort(vector, vector.length);
            finishTime = System.currentTimeMillis();

            s.print(l, finishTime-startTime);
        }

        // close file
        s.finish();
    }

    public static void selectionSort(Integer[] vector, Integer length){
        Integer min;
        for( Integer i=0; i<length; i++ ){
            min = ArrayUtil.minInPosition(vector, i, length);
            ArrayUtil.swap(vector, i, min);
        }
    }
}
