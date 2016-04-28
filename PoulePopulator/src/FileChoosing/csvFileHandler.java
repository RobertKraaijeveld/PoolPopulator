package FileChoosing;

import Algorithms.Fighter;
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

import java.util.List;

/**
 * @author Kraaijeveld
 */

public class csvFileHandler 
{
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
        File file = f;
        selectedFile = file;
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
            algorithmGUI.setAmountOfFightersFound();

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
        
        //Array indexes start at 0, so our first find must be 0.
        int positionCounter = -1;
        
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
                }   
                else if(s.equals(skillHeaderColumn)) 
                {
                    skillHeaderPosition = positionCounter;
                    returnList.add(skillHeaderPosition);
                }
                else if (s.equals(schoolHeaderColumn))
                {
                    schoolHeaderPosition = positionCounter;
                    returnList.add(schoolHeaderPosition);
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
        return false;
    }
    
    /* 
    * Meta-data (for use as argument to Algorithms-class)
    */
    
    public class CsvFileMetaData
    {
        private File SelectedFile;
        private ArrayList<Integer> listOfHeaderPositions;
        private ArrayList<Fighter> allFightersInCsv = new ArrayList<Fighter>();

        
        
        public CsvFileMetaData(File file, ArrayList<Integer> list)
        {
            SelectedFile = file;
            listOfHeaderPositions = list;
            allFightersInCsv = constructListOfFighters();
        }
        
        public File getSelectedFile()
        {
            return this.SelectedFile;
        }
        
        public ArrayList<Fighter> getAllFightersInCsv() 
        {
            return this.allFightersInCsv;
        }
        
        public int getAmountOfFightersInCSV() 
        {
            String pathToFile = SelectedFile.getAbsolutePath();
            BufferedReader reader = null;
            String currentLine = null;
            String delimiter = ";";
            int Counter = 0;

            try 
            {
                reader = new BufferedReader(new FileReader(pathToFile));
                while ((currentLine = reader.readLine()) != null) 
                {
                    Counter++;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "The file you specified was not found. Did you move it?");                
                return 0;
            }
            //We do not count the header row.
            Counter--;
            return Counter;
        }
        
        private ArrayList<Integer> getListOfHeaderPositions()
        {
            return listOfHeaderPositions;
        }
        
        private ArrayList<Fighter> constructListOfFighters()
        {
            String pathToFile = SelectedFile.getAbsolutePath();
            BufferedReader reader = null;
            String currentLine = null;
            String delimiter = ";";
            int Counter = 0;
            
            try 
            {
                reader = new BufferedReader(new FileReader(pathToFile));
                while ((currentLine = reader.readLine()) != null) 
                {
                    //The first line contains only headers, so we skip it.
                    if(Counter != 0)
                    {
                        String[] currentLineArray = currentLine.split(delimiter);
                        Fighter x = createNewFighterFromCSVline(currentLineArray);
                        allFightersInCsv.add(x);
                    }
                    Counter++;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Please make sure your CSV file is correct, "
                + "and that the skills column contains only numbers.");                
            }
            return allFightersInCsv;
        }
        
        private Fighter createNewFighterFromCSVline(String[] csvLineArray)
        {
            /*
            listOfHeaderPositions contains which positions (IE, after the 1st delimiter)       
            of the given csv files' line contain the information we want, (For instance, a fighters name)
            So we look there to get the values for a new Fighter object.
            */ 

            String name = csvLineArray[this.listOfHeaderPositions.get(0)];
            name = name.replace("\"", "");
            
            String school = csvLineArray[this.listOfHeaderPositions.get(1)];
            school = school.replace("\"", "");
            
            String skillString = csvLineArray[this.listOfHeaderPositions.get(2)];
            skillString = skillString.replace("\"", "");
            int skill = Integer.parseInt(skillString);
            
            Fighter newFighter = new Fighter(skill, school, name);         
            return newFighter;
        }
    }
    
}
