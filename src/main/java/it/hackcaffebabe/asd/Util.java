package it.hackcaffebabe.asd;

import it.hackcaffebabe.asd.gen.RandUtil;

import java.util.Stack;

/**
 * Util package
 */
public class Util {
    public static Integer[] makeRandomVector(Integer length){
        Integer[] vector = new Integer[length];
        for( Integer i=0; i<length; i++ ){
            vector[i] = RandUtil.random(0, 1000);
        }
        return vector;
    }

    public static Integer[] makeOrderedVectorASC(Integer length){
        Integer[] vector = new Integer[length];
        Integer base = RandUtil.random(0, 100);
        vector[0] = base;

        Integer step;
        for( int i=1; i<length; i++ ){
            step = RandUtil.random(1, 100);
            vector[i] = base + step;
            base = vector[i];
        }
        return vector;
    }

    public static Integer[] makeOrderedVectorDEC(Integer length){
        Integer[] v = makeOrderedVectorASC(length);
        Stack<Integer> stack = new Stack<>();
        for( Integer i : v ) stack.push(i);

        for( Integer i=0; i<length; i++ ){
            v[i] = stack.pop();
        }
        return v;
    }
}
