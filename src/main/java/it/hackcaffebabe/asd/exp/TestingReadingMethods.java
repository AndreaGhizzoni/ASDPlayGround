package it.hackcaffebabe.asd.exp;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * testing reading file methods
 */
public class TestingReadingMethods {
    private File file;

    public TestingReadingMethods(File f){
        this.file = f;
    }

    public Integer[] readVectorAlt() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.file))
        );

        // read the first line that contains only the vector dimension.
        Integer dim = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer tokenizer = new StringTokenizer(
                bufferedReader.readLine(), " ", false
        );

        Integer[] vector = new Integer[dim];
        Integer i=0;
        while(tokenizer.hasMoreTokens()){
            vector[i++] = Integer.parseInt(tokenizer.nextToken());
        }

        return vector;
    }

    public Integer[][] readMatrixAlt() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(this.file))
        );

        StringTokenizer tokenizer = new StringTokenizer(
                bufferedReader.readLine(), " ", false
        );

        // get matrix dimension from the first line.
        Integer rows = Integer.parseInt( tokenizer.nextToken() );
        Integer cols = Integer.parseInt( tokenizer.nextToken() );

        Integer[][] matrix = new Integer[rows][cols];
        Integer j, i;
        for( i=0; i<rows; i++ ){
            tokenizer = new StringTokenizer(
                    bufferedReader.readLine(), " ", false
            );
            j=0;
            while( tokenizer.hasMoreTokens() ){
                matrix[i][j++] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        return matrix;
    }

    public Integer[] readVector() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);

        Integer dim = scanner.nextInt();
        Integer[] vector = new Integer[dim];

        int i=0;
        while( scanner.hasNextInt() ){
            vector[i++] = scanner.nextInt();
        }
        return vector;
    }

    public Integer[][] readMatrix() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);
        Integer rows = scanner.nextInt();
        Integer cols = scanner.nextInt();

        Integer[][] matrix = new Integer[rows][cols];
        for( Integer i=0; i<rows; i++ ){
            for( Integer j=0; j<cols; j++ ){
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    /**
     * Example of output:
     *
     * Vector with Scanner: vector-1000000 652 ms
     * Vector with BufferedReader: vector-1000000 160 ms
     * Matrix with Scanner: matrix-1000-1000 633 ms
     * Matrix with BufferedReader: matrix-1000-1000 82 ms
     */
    public static void main(String...args){
        try {
            long s, e;

            File input = Paths.get("/home/andrea/vector-1000000").toFile();
            TestingReadingMethods rif = new TestingReadingMethods(input);

            System.out.print("Vector with Scanner: ");
            System.out.print(input.getName());
            s = System.currentTimeMillis();
            rif.readVector();
            e = System.currentTimeMillis();
            System.out.println(String.format(" %d ms", e-s));

            System.out.print("Vector with BufferedReader: ");
            System.out.print(input.getName());
            s = System.currentTimeMillis();
            rif.readVectorAlt();
            e = System.currentTimeMillis();
            System.out.println(String.format(" %d ms", e-s));


            input = Paths.get("/home/andrea/matrix-1000-1000").toFile();
            rif = new TestingReadingMethods(input);

            System.out.print("Matrix with Scanner: ");
            System.out.print(input.getName());
            s = System.currentTimeMillis();
            rif.readMatrix();
            e = System.currentTimeMillis();
            System.out.println(String.format(" %d ms", e-s));

            System.out.print("Matrix with BufferedReader: ");
            System.out.print(input.getName());
            s = System.currentTimeMillis();
            rif.readMatrixAlt();
            e = System.currentTimeMillis();
            System.out.println(String.format(" %d ms", e-s));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
