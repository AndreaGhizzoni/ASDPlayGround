package it.hackcaffebabe.asd.search;

import it.hackcaffebabe.asd.gen.RandUtil;
import it.hackcaffebabe.asd.benchmark.SpeedTestFileOutput;
import it.hackcaffebabe.asd.Util;

/**
 * Binary Search algorithm
 */
public class BinarySearch {
    public static Integer binarySearch( Integer[] vector, Integer wanted, Integer start, Integer end ){
        if( start > end ){
            return 0;
        }else{
            Integer middle = (start+end)/2;
            if( vector[middle].equals(wanted) ){
                return middle;
            }else if( vector[middle] < wanted ) {
                return binarySearch(vector, wanted, middle+1, end);
            }else{
                return binarySearch(vector, wanted, start, middle-1);
            }
        }
    }

    public static void main(String...args){
        // this vector contains the length of the random array to sort
        Integer[] lengthToTest = {10000, 100000, 1000000, 10000000, 50000000};

        // print-to-file class
        SpeedTestFileOutput s = new SpeedTestFileOutput("BinarySearch-result");
        s.open();

        // test insertionSort algorithm for every vector length specified before
        Integer[] vector;
        long startTime, finishTime;
        Integer index_of_wanted, wanted;
        for( Integer l : lengthToTest ) {
            System.out.println("== Starting test with length = "+l);
            vector = Util.makeOrderedVectorASC(l);
            wanted = vector[RandUtil.random(0, l)];
            System.out.println("Element wanted: "+wanted);

            System.out.println("Start searching...");
            startTime = System.currentTimeMillis();
            index_of_wanted = binarySearch(vector, wanted, 0, vector.length-1);
            finishTime = System.currentTimeMillis();

            System.out.println("Index found: "+ index_of_wanted);
            System.out.println("Found ? "+vector[index_of_wanted].equals(wanted) );

            s.print(l, finishTime-startTime);
        }

        // close file
        s.finish();

    }
}
