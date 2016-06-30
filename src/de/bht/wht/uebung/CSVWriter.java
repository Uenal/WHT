package de.bht.wht.uebung;

import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
	//Delimiter used in CSV file
    private static final String DELIMITER = ";";
    
    private static final String NEW_LINE_SEPARATOR = "\n";
    //CSV file header

    private static final String FILE_HEADER = "User;Counter;Color;Brightness Random;Brightness User;Deviation";

    public static void writeCsvFile(String fileName, String user, String[] colorsRedGreenBlue,float[] brightnessRandom, float[] brightnessUser) {
    	
    	
        FileWriter fileWriter = null;               

        try {
            fileWriter = new FileWriter(fileName + ".csv");

            // write the CSV file header and add a new line
            fileWriter.append(FILE_HEADER);
            fileWriter.append(NEW_LINE_SEPARATOR);
            
            for (int i = 0; i < colorsRedGreenBlue.length; i++) {
            	
            	fileWriter.append(user);
                fileWriter.append(DELIMITER);
                fileWriter.append("" + (i + 1));
                fileWriter.append(DELIMITER);
                fileWriter.append(colorsRedGreenBlue[i]);
                fileWriter.append(DELIMITER);
                fileWriter.append("" + brightnessRandom[i]);
                fileWriter.append(DELIMITER);
                fileWriter.append("" + brightnessUser[i]);
                fileWriter.append(DELIMITER);
                fileWriter.append("" +  Math.abs(brightnessRandom[i] - brightnessUser[i]));
                fileWriter.append(NEW_LINE_SEPARATOR);
                
            }
            
            System.out.println("CSV file was created successfully!!!");
            
        } catch (Exception e) {
        	
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
            
        } finally {
        	
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
            
        }
    }
}