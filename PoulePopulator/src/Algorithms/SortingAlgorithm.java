
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
    private ArrayList<Pool> emptyPools = new ArrayList<Pool>();
    private ArrayList<Pool> filledPools = new ArrayList<Pool>();
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
            this.emptyPools = createRequiredPools(); 
            fillPools();
            loopThroughFilledPools();
        }
        else
        {
            determineAmountOfFightersPerPool();
            JOptionPane.showMessageDialog(null, "Your parameters are incorrect. "
            + "Make sure you have no more pools than your total amount of fighters.");
        }
    }
    
    private void fillPools()
    {
        if(this.emptyPools.size() > 0 
        && this.csvFileMetaData.getAllFightersInCsv().size() >= 1)
        {           
            Pool randomPool = drawRandomPool(emptyPools);
        
            if(randomPool.isPoolFull() == false)
            {
                System.out.println(this.csvFileMetaData.getAllFightersInCsv().size() + " fighters left to do");
                
                Fighter randomFighter = drawRandomFighter(this.csvFileMetaData.getAllFightersInCsv());
                this.csvFileMetaData.getAllFightersInCsv().remove(randomFighter);
                
                if(randomPool.EliteLimitExceeded == true)
                {
                    System.out.println("added nonelite or default");
                    Fighter randomNonEliteFighter = drawRandomNonEliteFighterOrDefault(this.csvFileMetaData.getAllFightersInCsv());
                    randomPool.addFighterToPool(randomNonEliteFighter);
                    
                    this.csvFileMetaData.getAllFightersInCsv().remove(randomNonEliteFighter);
                    fillPools();
                }
                else if (randomPool.SchoolmateLimitExceeded == true)
                {
                    System.out.println("added nonschoolmate or default");
                    Fighter randomNonSchoolMateFighter = drawRandomNonSchoolMateFighterOrDefault(this.csvFileMetaData.getAllFightersInCsv(), randomPool);
                    randomPool.addFighterToPool(randomNonSchoolMateFighter);
                    
                    this.csvFileMetaData.getAllFightersInCsv().remove(randomNonSchoolMateFighter);
                    fillPools();
                }
                else
                {
                    System.out.println("added normal");
                    randomPool.addFighterToPool(randomFighter);
                    fillPools();
                }
            }
            else
            {
               System.out.println("Pool no. " + randomPool.poolNumber + " has "
               + randomPool.fightersInThisPool.size() + " fighters out of " + randomPool.getMaximumSize());
               this.filledPools.add(randomPool);
               //this.emptyPools.remove(randomPool);
               fillPools();
            }
        }
    }
    
    /*
    * POOL CREATION AND FILLING
    */
    
    //TEMP: FOR DEBUGGING ONLY
    private void loopThroughFilledPools()
    {
        System.out.println("Total amount of pools: " + this.filledPools.size());
        
        for(Pool currentPool : this.filledPools)
        {
            System.out.println("Looking at pool no. " + currentPool.poolNumber);
            System.out.println("Pool no. " + currentPool.poolNumber + " has " + currentPool.getCurrentSize());
            
            for(Fighter fighterInThisPool : currentPool.fightersInThisPool)
            {
                System.out.println(fighterInThisPool.getFighterName()
                + " is a member of pool " + currentPool.poolNumber);
            }
        }
        
        for(Pool currentPool : this.emptyPools)
        {
            System.out.println("Emptypools");
            System.out.println("Looking at pool no. " + currentPool.poolNumber);
            System.out.println("Pool no. " + currentPool.poolNumber + " has " + currentPool.getCurrentSize());
            
            for(Fighter fighterInThisPool : currentPool.fightersInThisPool)
            {
                System.out.println(fighterInThisPool.getFighterName()
                + " is a member of pool " + currentPool.poolNumber);
            }
        }
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
        System.out.println("We have " + this.amountOfPools + " pools, each having " + amountOfFightersPerPool + " fighters");
        ArrayList<Pool> returnList = new ArrayList<Pool>();
        
        for(int i = 0; i < this.amountOfPools; i++)
        {
            //i++ so that the first pool is pool '1': Pool '0' would not be very politically correct.
            Pool newPool = new Pool(i, amountOfFightersPerPool, this.maxEliteFightersPerPool, this.maxSchoolMatesPerPool);
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
    
    /*
    * FIGHTER DRAWING: RANDOMNESS GIVES BOUND PROBLEMS
    */
    
    private Fighter drawRandomFighter(ArrayList<Fighter> fighterList)
    {
        Random r = new Random();
        int randomNumber = r.nextInt(fighterList.size());
        
        Fighter randomFighter = fighterList.get(randomNumber);
        
        return randomFighter;
    }
    
    private Fighter drawRandomNonEliteFighterOrDefault(ArrayList<Fighter> fighterList)
    {
        Random r = new Random();
        int randomNumber = r.nextInt(fighterList.size());
        
        Fighter randomFighter = fighterList.get(randomNumber);
        
        //If the fighter is an elite AND there are still other non-elites left to choose from
        if(randomFighter.isFighterElite() 
        && listOfFightersContainsOnlyElites(fighterList) == false)
        {
            return drawRandomNonEliteFighterOrDefault(fighterList);
        }
        //If there are no non-elites left to pick, we simply insert the elite.
        else
        {
            System.out.println("Drew random non elite or default fighter " + randomFighter.getFighterName());
            return randomFighter;
        }
    }
    
    private Fighter drawRandomNonSchoolMateFighterOrDefault(ArrayList<Fighter> fighterList, Pool poolToInsertTo)
    {
        Random r = new Random();
        int randomNumber = r.nextInt(fighterList.size());
        
        Fighter randomFighter = fighterList.get(randomNumber);
        
        //If the fighter is someones mate AND there are still other non-mates left to choose from
        if(randomFighter.isThisFighterSomeonesSchoolMate(poolToInsertTo)
           && listOfFightersContainsOnlyMates(fighterList, poolToInsertTo))
        {
            return drawRandomNonSchoolMateFighterOrDefault(fighterList, poolToInsertTo);
        }
        //If there are no non-mates left to pick, we simply insert the mate.
        else
        {
            System.out.println("Drew random non mate or default fighter " + randomFighter.getFighterName());
            return randomFighter;
        }
    }
    
    private boolean listOfFightersContainsOnlyElites(ArrayList<Fighter> fighterList)
    {
        int eliteCounter = 0;
        for(Fighter f : fighterList)
        {
            if(f.isFighterElite())
                eliteCounter++;
        }
        
        if(eliteCounter >= fighterList.size())
            return true;
        else
            return false;
    }
    
    private boolean listOfFightersContainsOnlyMates(ArrayList<Fighter> fighterList, Pool thisFightersPool)
    {
       int mateCounter = 0;
        for(Fighter f : fighterList)
        {
            if(f.isThisFighterSomeonesSchoolMate(thisFightersPool))
                mateCounter++;
        }
        
        if(mateCounter >= fighterList.size())
            return true;
        else
            return false; 
    }

}