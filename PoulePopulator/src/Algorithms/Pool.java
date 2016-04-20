/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;

/**
 *
 * @author Kraaijeveld
 */
public class Pool 
{
    int currentSize = 0;
    final int maximumSize;
    int amountOfEliteFightersInThisPool = 0;
    int amountOfSchoolMatesInThisPool = 0;
    int maxEliteFighters;
    int maxSchoolMates;
    ArrayList<Fighter> fightersInThisPool = new ArrayList<Fighter>();

    
    public Pool(int maximumSize, int maxAmountOfEliteFighters, int maxAmountOfSchoolMates) {
        this.maximumSize = maximumSize;
        this.maxEliteFighters = maxAmountOfEliteFighters;
        this.maxSchoolMates = maxAmountOfSchoolMates;
    }
    
    
    public int getCurrentSize() {
        return this.currentSize;
    }

    public int getMaximumSize() {
        return this.maximumSize;
    }

    public int getAmountOfEliteFightersInThisPool() {
        return this.amountOfEliteFightersInThisPool;
    }

    public int getAmountOfSchoolMatesInThisPool() {
        return this.amountOfSchoolMatesInThisPool;
    }
    
    
    public boolean isPoolFull()
    {
        if(this.currentSize == this.maximumSize)
            return true;
        else
            return false;
    }
   
    public void addFighterToPool(Fighter fighterToBeAdded)
    throws exceededPoolEliteLimitException, 
    exceededPoolSchoolMateLimitException
    {
        if(this.amountOfEliteFightersInThisPool == this.maxEliteFighters)
        {
            throw new exceededPoolEliteLimitException();
        }
        else if(this.amountOfSchoolMatesInThisPool == this.maxSchoolMates)
        {
            throw new exceededPoolSchoolMateLimitException();
        }
        else
        {
            this.fightersInThisPool.add(fighterToBeAdded);
            this.currentSize++;
            
            //create schoolmate check method
            //this.maxSchoolMates;
            
            if(fighterToBeAdded.isFighterElite() == true)
            {
                this.amountOfEliteFightersInThisPool++;
            }
                
        }
    }
    
    public class exceededPoolEliteLimitException extends Exception {}
    public class exceededPoolSchoolMateLimitException extends Exception {}
}
