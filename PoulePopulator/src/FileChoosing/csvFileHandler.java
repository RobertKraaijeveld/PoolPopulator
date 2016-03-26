/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileChoosing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import Algorithms.SortingAlgorithm;

/**
 *
 * @author Kraaijeveld
 */

public class csvFileHandler 
{
    //Allow the user to specify the columns:
    //Count each header column name and put them in an array
    //Compare each of these to the given columns
    //Only print the value if their position == 
    
    //What this class should do:
    //- If so, store their relative positions in a data structure
    //- And echo out (later we will store them) all values at those positions
    
    private File selectedFile;
    private ArrayList<String> givenHeaderColumnNames;

    public void sendCsvFileToAlgorithm()
    {
        try
        {
            if(givenHeaderColumnNamesExist()) {
                SortingAlgorithm s = new SortingAlgorithm();
            }
            else {
                JOptionPane.showMessageDialog(null, "The columns you specified were not found in the given CSV file.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
   
    public void setGivenHeaderColumnNames(ArrayList<String> givenColumnNames)
    {
        givenHeaderColumnNames = givenColumnNames;
    }
    
    public void setSelectedFile(File f)
    {
        selectedFile = f;
    }
    
    //todo
    private int[] getHeaderColumnPositions() throws Exception
    {
        String pathToFile = selectedFile.getAbsolutePath();
        BufferedReader reader = null;
        String currentLine = "";
        String delimiter = ";";
        
        reader = new BufferedReader(new FileReader(pathToFile));
        
        if((currentLine = reader.readLine()) != null)
        {
            int positionCounter = 0;
            int[] positionReturnList;
            String[] headerColumn = currentLine.split(delimiter);
            Arrays.asList(headerColumn);
            
            for(String s : headerColumn)
            {
                positionCounter++;
                if(givenHeaderColumnNames.contains(s))
                {
                    
                }
            }
        }
    }
    
    //Take the given headercolumnnames, check if they are existant in the header columns of the file
    private boolean givenHeaderColumnNamesExist() throws Exception
    {
        String pathToFile = selectedFile.getAbsolutePath();
        BufferedReader reader = null;
        String currentLine = "";
        String delimiter = ";";
        
        reader = new BufferedReader(new FileReader(pathToFile));
        
        if((currentLine = reader.readLine()) != null)
        {
            String[] headerColumn = currentLine.split(delimiter);
            Arrays.asList(headerColumn);
            
            for(String s : headerColumn)
            {
                //if S is contained within the givenheaders, proceed. Else, alert user.
                if(givenHeaderColumnNames.contains(s) == false)
                {
                    return false;
                }
                //If we came this far, we can safely return true.
                return true;
            }
        }
        //In any other case
        return false;
    }
    
    public class CsvFileMetaData
    {
        private File selectedFile;
        private int nameHeaderPosition;
        private int skillHeaderPosition;
        private int schoolHeaderPosition;
        
        public CsvFileMetaData(File f)
        {
            
        }
    }
    
}
