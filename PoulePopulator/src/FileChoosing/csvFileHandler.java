package FileChoosing;

import GUI.MainGUIframe;
import Algorithms.SortingAlgorithm;
import GUI.AlgorithmParameterGUIframe;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * @author Kraaijeveld
 */

public class csvFileHandler 
{
    //Only print the value if their position % their appropriate headers' position == 0
    private File selectedFile;
    private ArrayList<String> givenHeaderColumnNames;
    private int nameHeaderPosition;
    private int skillHeaderPosition;
    private int schoolHeaderPosition;

    
    /* 
    * Setters 
    */
   
    public void setGivenHeaderColumnNames(ArrayList<String> givenColumnNames)
    {
        givenHeaderColumnNames = givenColumnNames;
    }
    
    public void setSelectedFile(File f)
    {
        selectedFile = f;
    }
    
    /* 
    * Senders to Algorithm-class
    */
    
    public void sendCsvFileToAlgorithm()
    {
        try
        {
            if(givenHeaderColumnNamesExist()) 
            {
                createAlgorithmInputGUI();
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "The columns you specified were not found in the given CSV file.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }        
    }
    
    private void createAlgorithmInputGUI()
    {
        closeAllFrames();
        try
        {
            ArrayList<Integer> headerPositions = createListOfHeaderColumnPositions();
            CsvFileMetaData metadata = new CsvFileMetaData(selectedFile, headerPositions);
            SortingAlgorithm algorithm = new SortingAlgorithm(metadata);
            
            AlgorithmParameterGUIframe algorithmGUI = new AlgorithmParameterGUIframe();
            algorithmGUI.setVisible(true);
            algorithmGUI.setAlgorithm(algorithm);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void closeAllFrames()
    {
        Frame[] allFrames = Frame.getFrames();
        for(int i = 0; i < allFrames.length; i++)
        {
            allFrames[i].setVisible(false);
        }
    }
    
    /* 
    * Header-checking
    */
    
    private ArrayList<Integer> createListOfHeaderColumnPositions() throws Exception
    {
        String pathToFile = selectedFile.getAbsolutePath();
        BufferedReader reader = null;
        String currentLine = "";
        String delimiter = ";";
        reader = new BufferedReader(new FileReader(pathToFile));

        ArrayList<Integer> returnList = new ArrayList<Integer>();
        int positionCounter = 0;
        
        //These values represent what the name, skills and school columnns are called in the CSV file.
        final String nameHeaderColumn = givenHeaderColumnNames.get(0);
        final String skillHeaderColumn = givenHeaderColumnNames.get(1);
        final String schoolHeaderColumn = givenHeaderColumnNames.get(2);
        
        if((currentLine = reader.readLine()) != null)
        {
            String[] headerColumn = currentLine.split(delimiter);
            List<String> headerColumnList = Arrays.asList(headerColumn);
            
            for(String s : headerColumnList)
            {
                positionCounter++;

                if(s.equals(nameHeaderColumn)) 
                {
                    nameHeaderPosition = positionCounter;
                    returnList.add(nameHeaderPosition);
                    System.out.println("Name header position: " + nameHeaderPosition);
                }   
                else if(s.equals(skillHeaderColumn)) 
                {
                    skillHeaderPosition = positionCounter;
                    returnList.add(skillHeaderPosition);
                    System.out.println("Skill header position: " + skillHeaderPosition);
                }
                else if (s.equals(schoolHeaderColumn))
                {
                    schoolHeaderPosition = positionCounter;
                    returnList.add(schoolHeaderPosition);
                    System.out.println("School header position: " + schoolHeaderPosition);
                }         
            }
        }
        return returnList;
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
            List<String> headerColumnList = Arrays.asList(headerColumn);

            //the given headers have to actually exist in the CSV file
            for(String givenHeader : givenHeaderColumnNames)
            {
                
                if(headerColumnList.contains(givenHeader) == false)
                {
                    return false;
                }
            }
            return true;
        }
        //In any other case, we return false
        return false;
    }
    
    /* 
    * Meta-data (for use as argument to Algorithms-class)
    */
    
    public class CsvFileMetaData
    {
        private File selectedFile;
        private ArrayList<Integer> listOfHeaderPositions;
        
        public CsvFileMetaData(File f, ArrayList<Integer> list)
        {
            selectedFile = f;
            listOfHeaderPositions = list;
        }
        
        public File getSelectedFile()
        {
            return selectedFile;
        }
        
        public ArrayList<Integer> getListOfHeaderPositions()
        {
            return listOfHeaderPositions;
        }
        
    }
    
}
