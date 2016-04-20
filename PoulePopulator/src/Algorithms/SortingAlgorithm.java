
package Algorithms;

import FileChoosing.csvFileHandler.CsvFileMetaData;
import java.util.ArrayList;
import java.util.Random;
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
        this.fightersPerPool = fightersProPool;
        this.eliteFightersPerPool= elitesPerPool;
        this.schoolMatesPerPool = schoolMates;
    }
    
    public int getTotalFightersAmount()
    {
        return this.totalFightersAmount;
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
    
    private void recursivelyFillPools(ArrayList<Pool> emptyPools)
    {
        ArrayList<Pool> filledPools = new ArrayList<Pool>();
        
        Fighter randomFighter = drawRandomFighter();
        Pool randomPool = drawRandomPool(emptyPools);
        
        
        //if chosen random pool is not full, randomly fill it (non-recursive). 
        //else, discard it from the empty list and add it to the filledlist.
        
        
        if(randomPool.isPoolFull() == false)
        {
            
        }
        else
        {
            
        }
            
            
    }
    
    private int determineAmountOfPools()
    {
        int returnValue = (int) Math.floor(totalFightersAmount / fightersPerPool) + 1;
        System.out.println("We need " + returnValue + " pools.");
        return returnValue;
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
    
    private Pool drawRandomPool(ArrayList<Pool> poolList)
    {
        Random r = new Random();
        int randomNumber = r.nextInt(poolList.size());
        
        Pool randomPool = poolList.get(randomNumber);
        return randomPool;
    }
    
    private Fighter drawRandomFighter()
    {
        Random r = new Random();
        int randomNumber = r.nextInt(this.totalFightersAmount);
        
        Fighter randomFighter = this.csvFileMetaData.getAllFightersInCsv().get(randomNumber);
        this.csvFileMetaData.getAllFightersInCsv().remove(randomFighter);
        
        return randomFighter;
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
    
    
}