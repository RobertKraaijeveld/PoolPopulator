
package Algorithms;

import FileChoosing.csvFileHandler.CsvFileMetaData;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Kraaijeveld
 */

public class SortingAlgorithm 
{
    private CsvFileMetaData csvFileMetaData;
    
    public SortingAlgorithm(CsvFileMetaData metaData)
    {
        csvFileMetaData = metaData;
    }
    
    public String getAmountOfFightersInCSV() 
    {
        String pathToFile = csvFileMetaData.getSelectedFile().getAbsolutePath();
        System.out.println(pathToFile);
        BufferedReader reader = null;
        String currentLine = null;
        String delimiter = ";";
        int Counter = 0;
        
        try {
            reader = new BufferedReader(new FileReader(pathToFile));
            while ((currentLine = reader.readLine()) != null) 
            {
                Counter++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "The file you specified is not found. Did you move it?");                
            return "error";
        }
        
        String returnText = "Amount of fighters found: " + Counter;
        return returnText;
    }
    
    private void categorizeFighters()
    {
        
    }
    
    public class FighterCategory
    {
        public FighterCategory()
        {
            
        }        
    }
    
    
}