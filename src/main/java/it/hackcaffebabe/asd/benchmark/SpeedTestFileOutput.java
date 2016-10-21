package it.hackcaffebabe.asd.benchmark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Simple class to write results run to file
 */
public class SpeedTestFileOutput {
    private String name;
    private FileWriter fw;

    public SpeedTestFileOutput(String testName){
        this.name = testName;
    }

    public void open(){
        if( this.fw == null ) {
            try {
                fw = new FileWriter(new File(this.name+".txt"));
                this.printHeader();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printHeader(){
        try {
            this.fw.append("# ").append(this.name).append("\n");
            this.fw.append(String.format("%-15s", "Input Length"))
                   .append(String.format("%-20s", "Elapsed Time")).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(Integer inputLength, long duration){
       if( this.fw != null ) {
           String toPrint = String.format("%-15d%-20d ms", inputLength, duration);
           try {
               this.fw.append(toPrint).append("\n");
               this.fw.flush();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
    }

    public void finish(){
        if( this.fw != null ){
            try {
                this.fw.close();
                this.fw = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
