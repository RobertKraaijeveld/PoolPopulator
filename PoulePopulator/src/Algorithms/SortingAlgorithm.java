
package Algorithms;

import FileChoosing.csvFileHandler.CsvFileMetaData;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * @author Kraaijeveld
 */
public class SortingAlgorithm 
{
    private CsvFileMetaData csvFileMetaData;
    private int totalFightersAmount;
    private int fightersPerPool;
    private int eliteFightersPerPool;
    private int schoolMatesPerPool;
    
    public SortingAlgorithm(CsvFileMetaData metaData)
    {
        csvFileMetaData = metaData;
        totalFightersAmount = metaData.getAmountOfFightersInCSV();
    }  
    
    public void setAlgorithmParameters(int fightersProPool, int elitesPerPool, int schoolMates)
    {
        fightersPerPool = fightersProPool;
        eliteFightersPerPool= elitesPerPool;
        schoolMatesPerPool = schoolMates;
    }
    
    public int getTotalFightersAmount()
    {
        return totalFightersAmount;
    }
    
    public void runAlgorithm()
    {
        if(areParametersCorrect() == true)
        {
            int amountOfPools = determineAmountOfPools();
            ArrayList<Pool> poolsToBeFilled = createRequiredPools(amountOfPools);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your parameters are incorrect. "
            + "Make sure you have no more fighters per pool than your total amount of fighters.");
        }
    }
    
    private boolean areParametersCorrect()
    {
        if(fightersPerPool > totalFightersAmount ||
        eliteFightersPerPool > fightersPerPool || 
        schoolMatesPerPool > fightersPerPool)
        {
            return false;
        }
        else
        {
            return true;
        }
    }   
    
    private int determineAmountOfPools()
    {
        System.out.println("We need " + Math.floor(totalFightersAmount / fightersPerPool) + " pools.");
        return (int) Math.floor(totalFightersAmount / fightersPerPool);
    }
    
    private ArrayList<Pool> createRequiredPools(int amount)
    {
        ArrayList<Pool> returnList = new ArrayList<Pool>();
        
        for(int i = 0; i < amount; i++)
        {
            Pool newPool = new Pool(this.fightersPerPool, 0,0);
            returnList.add(newPool);
        }
        return returnList;
    }
}