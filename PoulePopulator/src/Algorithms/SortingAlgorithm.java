
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
    private ArrayList<Pool> poolsToBeFilled = new ArrayList<Pool>();
    private int totalFightersAmount;
    private int amountOfPools;
    private int maxEliteFightersPerPool;
    private int maxSchoolMatesPerPool;
    
    public SortingAlgorithm(CsvFileMetaData metaData)
    {
        csvFileMetaData = metaData;
        totalFightersAmount = metaData.getAmountOfFightersInCSV();
    }  
    
    /*
    * ALGORITHM INFORMATION GETTERS AND EXECUTION
    */
    
    private boolean areParametersCorrect()
    {
        if(this.amountOfPools > this.totalFightersAmount ||
        this.maxEliteFightersPerPool > determineAmountOfFightersPerPool() || 
        this.maxSchoolMatesPerPool > determineAmountOfFightersPerPool())
        {
            return false;
        }
        else
        {
            return true;
        }
    }  
    
    public void setAlgorithmParameters(int amountOfPools, int elitesPerPool, int schoolMates)
    {
        this.amountOfPools = amountOfPools;
        this.maxEliteFightersPerPool= elitesPerPool;
        this.maxSchoolMatesPerPool = schoolMates;
    }
    
    public int getTotalFightersAmount()
    {
        return this.totalFightersAmount;
    }
    
    public void runAlgorithm()
    {
        if(areParametersCorrect() == true)
        {
            this.poolsToBeFilled = createRequiredPools(); 
            fillPools();
        }
        else
        {
            determineAmountOfFightersPerPool();
            JOptionPane.showMessageDialog(null, "Your parameters are incorrect. "
            + "Make sure you have no more pools than your total amount of fighters.");
        }
    }
    
    //If iterator gives problems, looping through normally and shuffling the collection is possible too
    
    private void fillPools()
    {
        for(Pool currentPool : this.poolsToBeFilled)
        {
            System.out.println(this.poolsToBeFilled.size());
            //it will do this i getMaximumSize() times
            for(int i = 0; i < currentPool.getMaximumSize(); i++)
            {
                if(this.csvFileMetaData.getAllFightersInCsv().isEmpty())
                {
                    break;
                }
                else if(currentPool.EliteLimitExceeded)
                {
                    Fighter randomNonEliteFighter = drawRandomNonEliteFighterOrDefault(this.csvFileMetaData.getAllFightersInCsv());
                    currentPool.addFighterToPool(randomNonEliteFighter);
                    this.csvFileMetaData.getAllFightersInCsv().remove(randomNonEliteFighter);
                }
                else if(currentPool.SchoolmateLimitExceeded)
                {
                    Fighter randomNonSchoolMateFighter = drawRandomNonSchoolMateFighterOrDefault(this.csvFileMetaData.getAllFightersInCsv(), currentPool);
                    currentPool.addFighterToPool(randomNonSchoolMateFighter);
                    this.csvFileMetaData.getAllFightersInCsv().remove(randomNonSchoolMateFighter);
                }
                else
                {
                    Fighter randomNormalFighter = drawRandomFighter(this.csvFileMetaData.getAllFightersInCsv());
                    currentPool.addFighterToPool(randomNormalFighter);
                    this.csvFileMetaData.getAllFightersInCsv().remove(randomNormalFighter);
                }
            }
        }
        loopThroughFilledPools();
    }
    
    /*
    * POOL CREATION AND FILLING
    */
    
    //TEMP: FOR DEBUGGING ONLY
    private void loopThroughFilledPools()
    {
        System.out.println("Total amount of pools: " + this.poolsToBeFilled.size());
        
        int totalFightersInPoolsAmount = 0;
        for(Pool filledPool : this.poolsToBeFilled)
        {
            System.out.println("Amount of fighters for this pool: " + filledPool.getCurrentSize());
            for(Fighter fighterInThisPool : filledPool.fightersInThisPool)
            {
                totalFightersInPoolsAmount++;
                System.out.println(fighterInThisPool.getFighterName() + " is a member of pool " + filledPool.poolNumber);
            }
        }
        System.out.println("Total amount of fighters in pooles " + totalFightersInPoolsAmount);
    }
    
    private int determineAmountOfFightersPerPool()
    {
        int result = (int) Math.floor(this.totalFightersAmount / this.amountOfPools) + 1;
        System.out.println(result + " fighters per pool");
        return result;
    }
    
    private ArrayList<Pool> createRequiredPools()
    {
        int amountOfFightersPerPool = determineAmountOfFightersPerPool();
        ArrayList<Pool> returnList = new ArrayList<Pool>();
        
        for(int i = 0; i < this.amountOfPools; i++)
        {
            //We increment poolNumber so that the first pool is pool '1': Pool '0' would not be very politically correct.
            int poolNumber = i;
            poolNumber++;
            
            Pool newPool = new Pool(poolNumber, amountOfFightersPerPool, this.maxEliteFightersPerPool, this.maxSchoolMatesPerPool);
            returnList.add(newPool);
        }
        return returnList;
    }
    
    /*
    * FIGHTER DRAWING
    */
    
    private Fighter drawRandomFighter(ArrayList<Fighter> fighterList)
    {
        Utilities u = new Utilities();
        Fighter randomFighter = u.getRandomFighterFromShuffledList(fighterList);
        
        return randomFighter;
    }
    
    private Fighter drawRandomNonEliteFighterOrDefault(ArrayList<Fighter> fighterList)
    {
        Utilities u = new Utilities();
        Fighter randomFighter = u.getRandomFighterFromShuffledList(fighterList);
                
        //If the fighter is an elite AND there are still other non-elites left to choose from
        if(randomFighter.isFighterElite() 
        && u.listOfFightersContainsOnlyElites(fighterList) == false)
        {
            return drawRandomNonEliteFighterOrDefault(fighterList);
        }
        //If there are no non-elites left to pick, we simply insert the elite.
        else
        {
            return randomFighter;
        }
    }
    
    private Fighter drawRandomNonSchoolMateFighterOrDefault(ArrayList<Fighter> fighterList, Pool poolToInsertTo)
    {
        Utilities u = new Utilities();
        Fighter randomFighter = u.getRandomFighterFromShuffledList(fighterList);
        
        //If the fighter is someones mate AND there are still other non-mates left to choose from
        if(randomFighter.isThisFighterSomeonesSchoolMate(poolToInsertTo)
           && u.listOfFightersContainsOnlyMates(fighterList, poolToInsertTo))
        {
            return drawRandomNonSchoolMateFighterOrDefault(fighterList, poolToInsertTo);
        }
        //If there are no non-mates left to pick, we simply insert the mate.
        else
        {
            return randomFighter;
        }
    }
}