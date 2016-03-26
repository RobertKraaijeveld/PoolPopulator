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
 * 
 * 
 * Actual CSV:
 * Skill;Name;School;
 * 0     1    2
 * skillv;namev;schoolv;
 * skillv;namev;schoolv;
 * skillv;namev;schoolv;
 * skillv;namev;schoolv;
 * skillv;namev;schoolv;
 * 
 * ArrayList of given names
 * IE: 
 * NameColumn = X, SkillColumn = Y, SchoolColumn = Z
 * 0                  1                    2
 * 
 * 
 * We have to count where we first find the given column
 * when looping through the CSV. 
 * 
 * We loop through the csv, keeping track with a counter.
 * If we find a value, for instance, 'Skill' that is present
 * in the givenRowNames list, we set that columns count-variable
 * to be the current value of the counter.
 */

public class csvFileHandler 
{
    //Allow the user to specify the columns:
    //Count each header column name and put them in an array
    //Compare each of these to the given columns
    //Only print the value if their position % their appropriate headers' position == 0
    
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
    
    private void getHeaderColumnPositions() throws Exception
    {
        String pathToFile = selectedFile.getAbsolutePath();
        BufferedReader reader = null;
        String currentLine = "";
        String delimiter = ";";
        reader = new BufferedReader(new FileReader(pathToFile));

        int positionCounter = 0;
        
        if((currentLine = reader.readLine()) != null)
        {
            String[] headerColumn = currentLine.split(delimiter);
            Arrays.asList(headerColumn);
            
            for(String s : headerColumn)
            {
                positionCounter++;
                if(givenHeaderColumnNames.contains(s))
                {
                    //We know that this header is contained in the givenheaders,
                    //but we dont know which header it is, so we dont know which
                    //headerPosition variable deserves to get the positioncounter assigned to it
                }
            }
        }
        return positionReturnList;
    }

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
        
        public CsvFileMetaData(File f, ArrayList<Integer> listOfHeaderPositions)
        {
            selectedFile = f;
            //for(int headerPosition : listOfHeaderPositions)
        }
    }
    
}
