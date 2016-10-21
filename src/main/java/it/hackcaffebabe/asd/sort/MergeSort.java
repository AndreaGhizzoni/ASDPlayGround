package it.hackcaffebabe.asd.sort;

import it.hackcaffebabe.asd.benchmark.SpeedTestFileOutput;
import it.hackcaffebabe.asd.Util;

/**
 * merge Sort Algorithm
 */
public class MergeSort {

    public static void main(String...args){
        // this vector contains the length of the random array to sort
        Integer[] lengthToTest = {3, 5, 10, 50, 100, 1000, 10000, 100000};

        // print-to-file class
        SpeedTestFileOutput s = new SpeedTestFileOutput("MergeSort-result");
        s.open();

        // test insertionSort algorithm for every vector length specified before
        Integer[] vector;
        long startTime, finishTime;
        for( Integer l : lengthToTest ) {
            vector = Util.makeRandomVector(l);

            startTime = System.currentTimeMillis();
            mergeSort(vector, 0, vector.length-1);
            finishTime = System.currentTimeMillis();

            s.print(l, finishTime-startTime);
        }

        // close file
        s.finish();
    }

    public static void mergeSort(Integer[] vector, Integer first, Integer last ){
        if( first < last ){
            Integer middle = (first + last)/2;

            mergeSort(vector, first, middle);
            mergeSort(vector, middle+1, last);
            merge(vector, first, last, middle);
        }
    }

    public static void merge(Integer[] vector, Integer first, Integer last, Integer middle ){
        Integer[] tmp = new Integer[last+1];
        for( Integer t=0; t<tmp.length; t++ ) tmp[t]=0;
        Integer i, j, k;
        i = first;
        j = middle +1;
        k = first;

        while( i<=middle && j<=last ){
            if( vector[i] <= vector[j] ){
                tmp[k++] = vector[i++];
            }else{
                tmp[k++] = vector[j++];
            }
        }

        j = last;
        for( Integer h = middle; h>=i; h-- ){
            vector[j--] = vector[h];
        }

        for( j = first; j<=k-1; j++ ){
            vector[j] = tmp[j];
        }
    }


}
