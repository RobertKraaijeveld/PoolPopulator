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
    public final int poolNumber;
    public boolean EliteLimitExceeded = false;
    public boolean SchoolmateLimitExceeded = false;
    private int currentSize = 0;
    private final int maximumSize;
    private int amountOfEliteFightersInThisPool = 0;
    private int amountOfSchoolMatesInThisPool = 0;
    private final int maxEliteFighters;
    private final int maxSchoolMates;
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
    
    public int getMaxEliteFighters() {
        return maxEliteFighters;
    }

    public int getMaxSchoolMates() {
        return maxSchoolMates;
    }
    
    public boolean isPoolFull()
    {
        if(this.currentSize == this.maximumSize)
            return true;
        else
            return false;
    }
   
    public void addFighterToPool(Fighter fighterToBeAdded)
    {
        //WHAT TO DO WITH THE FIFGHTER THAT WANTS TO GET ADDED?
        //might not be a problem
        if(this.amountOfEliteFightersInThisPool == this.maxEliteFighters)
        {
            this.EliteLimitExceeded = true;
        }
        else if (this.amountOfSchoolMatesInThisPool == this.maxSchoolMates)
        {
            this.SchoolmateLimitExceeded = true;
        }
        else
        {
            this.fightersInThisPool.add(fighterToBeAdded);
            calculateTotalAmountOfSchoolMates(); 
            this.currentSize++;

            if(fighterToBeAdded.isFighterElite() == true)
                this.amountOfEliteFightersInThisPool++;
        }
    }
    
    //TODO: Clean this up, especially the if
    private void calculateTotalAmountOfSchoolMates()
    {
        ArrayList<Fighter> fightersToCrossReference = this.fightersInThisPool;
        
        for(Fighter f : this.fightersInThisPool)
        {
            for(Fighter f2 : fightersToCrossReference)
            {
                if (areGivenFightersNotSchoolmatesAlready(f, f2) == true
                    && areGivenFightersAlreadySchoolmates(f, f2) == false)
                {
                    System.out.println(f.getFighterName() + " is now a mate of " + f2.getFighterName()
                    + " since both are from " + f.getSchoolName());
                    f.getThisFightersSchoolMates().add(f2);
                    f2.getThisFightersSchoolMates().add(f);
                    this.amountOfSchoolMatesInThisPool++;
                }
            }
        }
    }
    
    //These might need some cleaning up
    private boolean areGivenFightersNotSchoolmatesAlready(Fighter fighter1, Fighter fighter2)
    {
        if (areGivenFightersAlreadySchoolmates(fighter1, fighter2) == true)
           return false;
        else if (fighter1.getSchoolName().equals(fighter2.getSchoolName()))
            return true;
        else
            return false;
    }
    
    public boolean areGivenFightersAlreadySchoolmates(Fighter fighter1, Fighter fighter2)
    {
        /*
        We also check wether the two names are equal: If they are, the 2 fighters
        Are the same person and the schoolMatesCounter shouldnt be incremented; 
        a fighter cannot be his/her own schoolmate.
        */
                
        if (fighter1.getThisFightersSchoolMates().contains(fighter2)
        || fighter2.getThisFightersSchoolMates().contains(fighter1)
        || fighter1.getFighterName().equals(fighter2.getFighterName()))
        {
           return true;
        }
        else
        {
            return false;
        }
    }
}
