
package FileChoosing;
import GUI.FileContentsListerGUIframe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/*
 * @author robert
 */

public class FileUnpacker
{
    private File selectedFile;
   
    public FileUnpacker(File fileSelectedByUser)
    {
        selectedFile = fileSelectedByUser;
    }
    
    public void writeFileContents()
    {
        String pathToFile = selectedFile.getAbsolutePath();
        BufferedReader reader = null;
	String currentLine = "";
	String delimiter = ",";
        
        try
        {
            //todo Make this stuff customizable
            reader = new BufferedReader(new FileReader(pathToFile));
            while ((currentLine = reader.readLine()) != null) 
            {
                //Set every value from the currentline to be an array element
                String[] fullRow = currentLine.split(delimiter);

		System.out.println("Name: " + fullRow[1] + " Skill: "
                + fullRow[1] + " School: " + fullRow[2]);
            }
        }
        catch (FileNotFoundException e) 
        {
		e.printStackTrace();
	} 
        catch (IOException e) 
        {
		e.printStackTrace();
	} 
        finally 
        {
            if (reader != null) {
                try 
                {
                    reader.close();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
	}   
    }
    
    public void setCsvColumns(String nameColumn, String skillColumn, String schoolColumn)
    {
        
    }
}
