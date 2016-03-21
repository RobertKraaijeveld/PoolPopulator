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

/**
 *
 * @author Kraaijeveld
 */
public class CsvFile 
{
    //Allow the user to specify the columns:
    //Count each header column name and put them in an array
    //Compare each of these to the given columns
    //Only print the value if their position == 
    
    //What this class should do:
    //- Take the given row names
    //- Check if they actually exist in the header names
    //- If so, store their relative positions in a data structure
    //- And echo out (later we will store them) all values at those positions
    
    private File selectedFile;
    private ArrayList<String> givenHeaderColumnNames;
    private int nameHeaderPosition;
    private int skillHeaderPosition;
    private int schoolHeaderPosition;
    
    public void printValuesForGivenColumnHeaders()
    {
        try
        {
            if(doGivenHeaderColumnNamesExist() == true)
            {

            }
            else
            {
                JOptionPane.showMessageDialog
                (null, "The columns you specified were not found in the given CSV file.");
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
    
    private void setHeaderColumnPositions()
    {
        
    }
    
    //Take the given headercolumnnames, check if they are existant in the header columns of the file
    private boolean doGivenHeaderColumnNamesExist() throws Exception
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
                if(!givenHeaderColumnNames.contains(s))
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
}
