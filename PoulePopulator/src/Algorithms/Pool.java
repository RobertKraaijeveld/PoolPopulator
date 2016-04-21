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
    int poolNumber;
    int currentSize = 0;
    final int maximumSize;
    int amountOfEliteFightersInThisPool = 0;
    int amountOfSchoolMatesInThisPool = 0;
    int maxEliteFighters;
    int maxSchoolMates;
    ArrayList<Fighter> fightersInThisPool = new ArrayList<Fighter>();

    
    public Pool(int poolNumber, int maximumSize, int maxAmountOfEliteFighters, int maxAmountOfSchoolMates) {
        this.poolNumber = poolNumber;
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
            this.amountOfSchoolMatesInThisPool = calculateTotalAmountOfSchoolMates();    
            
            if(fighterToBeAdded.isFighterElite() == true)
                this.amountOfEliteFightersInThisPool++;
        }
    }
    
    private int calculateTotalAmountOfSchoolMates()
    {
        ArrayList<Fighter> fightersToCrossReference = this.fightersInThisPool;
        int schoolMatesCounter = 0;
        
        for(Fighter f : this.fightersInThisPool)
        {
            for(Fighter f2 : fightersToCrossReference)
            {
                /*
                We also check wether the two names are equal: If they are, the 2 fighters
                Are the same person and the schoolMatesCounter shouldnt be incremented; 
                a fighter cannot be his/her own schoolmate.
                */
                
                if(f.getSchoolName().equals(f2.getSchoolName()) 
                && f.getFighterName().equals(f2.getFighterName()) == false)
                {
                    schoolMatesCounter++;
                }
            }
        }
        return schoolMatesCounter;
    }
    
    public class exceededPoolEliteLimitException extends Exception {}
    public class exceededPoolSchoolMateLimitException extends Exception {}
}
