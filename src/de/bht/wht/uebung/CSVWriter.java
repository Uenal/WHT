package de.bht.wht.uebung;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CSVWriter {
	//Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";
    
    private static final String NEW_LINE_SEPARATOR = "\n";
    //CSV file header

    private static final String FILE_HEADER = "user, zaehler, color, brightnessRandom, brightnessUser";

    public static void writeCsvFile(String fileName, String user, String[] colorsRedGreenBlue,float[] brightnessRandom, float[] brightnessUser) {
    	
    	
        FileWriter fileWriter = null;               

        try {
            fileWriter = new FileWriter(fileName);

            //Write the CSV file header

            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header

            fileWriter.append(NEW_LINE_SEPARATOR);
            
            for (int i = 0; i < colorsRedGreenBlue.length; i++){
            	
            	fileWriter.append(user);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(""+i);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(colorsRedGreenBlue[i]);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(""+ brightnessRandom[i]);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(""+ brightnessUser[i]);
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