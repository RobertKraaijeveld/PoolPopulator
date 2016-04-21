
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
            fillPools(poolsToBeFilled);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Your parameters are incorrect. "
            + "Make sure you have no more fighters per pool than your total amount of fighters.");
        }
    }
    
    //NOTE: The danger in this is that it might go on forever.
    //TODO: Add pool and fighter size checks.
    //TODO: Cover other cases, such as elites only.
    private void fillPools(ArrayList<Pool> emptyPools)
    {
        ArrayList<Pool> filledPools = new ArrayList<Pool>();
        
        Fighter randomFighter = drawRandomFighter(this.csvFileMetaData.getAllFightersInCsv());
        Pool randomPool = drawRandomPool(emptyPools);

        if(randomPool.isPoolFull() == false)
        {
            filledPools.add(randomPool);
            emptyPools.remove(randomPool);
            fillPools(emptyPools);
        }
        else
        {
           try
           {
               randomPool.addFighterToPool(randomFighter);
               fillPools(emptyPools);
           }
           catch(Exception e)
           {
               fillPools(emptyPools);        
           }
        }
        loopThroughFilledPools(filledPools);
    }
    
    //TEMP: FOR DEBUGGING ONLY
    private void loopThroughFilledPools(ArrayList<Pool> filledPools)
    {
        for(Pool currentPool : filledPools)
        {
            System.out.println("new pool");
            for(Fighter fighterInThisPool : currentPool.fightersInThisPool)
            {
                System.out.println(fighterInThisPool.getFighterName());
            }
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
            Pool newPool = new Pool(i, this.fightersPerPool, 0,0);
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
    
    private Fighter drawRandomFighter(ArrayList<Fighter> fighterList)
    {
        Random r = new Random();
        int randomNumber = r.nextInt(fighterList.size());
        
        Fighter randomFighter = fighterList.get(randomNumber);
        
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