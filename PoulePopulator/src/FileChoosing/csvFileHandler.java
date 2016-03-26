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
 * 
 **/

public class csvFileHandler 
{
    //Only print the value if their position % their appropriate headers' position == 0
    
    private File selectedFile;
    private ArrayList<String> givenHeaderColumnNames;
    private int nameHeaderPosition;
    private int skillHeaderPosition;
    private int schoolHeaderPosition;

    public void sendCsvFileToAlgorithm()
    {
        try
        {
            if(givenHeaderColumnNamesExist()) {
                setHeaderColumnPositions();
                SortingAlgorithm algorithm = new SortingAlgorithm();
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
    
    private void setHeaderColumnPositions() throws Exception
    {
        String pathToFile = selectedFile.getAbsolutePath();
        BufferedReader reader = null;
        String currentLine = "";
        String delimiter = ";";
        reader = new BufferedReader(new FileReader(pathToFile));

        int positionCounter = 0;
        
        //These values represent what the name, skills and school columnns are called in the CSV file.
        final String nameHeaderColumn = givenHeaderColumnNames.get(0);
        final String skillHeaderColumn = givenHeaderColumnNames.get(1);
        final String schoolHeaderColumn = givenHeaderColumnNames.get(2);
        
        if((currentLine = reader.readLine()) != null)
        {
            String[] headerColumn = currentLine.split(delimiter);
            
            for(String s : headerColumn)
            {
                positionCounter++;

                if(s.equals(nameHeaderColumn)) {
                    nameHeaderPosition = positionCounter;
                }   
                else if(s.equals(skillHeaderColumn)) {
                    skillHeaderPosition = positionCounter;
                }
                else {
                    schoolHeaderPosition = positionCounter;
                }         
            }
        }
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
        
        public CsvFileMetaData(File f, ArrayList<Integer> listOfHeaderPositions)
        {
            selectedFile = f;
            //for(int headerPosition : listOfHeaderPositions)
        }
    }
    
}
