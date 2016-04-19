/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *
 * @author Kraaijeveld
 */
public class Pool 
{
    int currentSize;
    int maximumSize;
    int amountOfEliteFightersInThisPool;
    int amountOfSchoolMatesInThisPool;

    public Pool(int maximumSize, int amountOfEliteFighters, int amountOfSchoolMates) {
        currentSize = 0;
        this.maximumSize = maximumSize;
        this.amountOfEliteFightersInThisPool = amountOfEliteFighters;
        this.amountOfSchoolMatesInThisPool = amountOfSchoolMates;
    }
    
    public int getCurrentSize() {
        return currentSize;
    }

    public int getMaximumSize() {
        return maximumSize;
    }

    public int getAmountOfEliteFightersInThisPool() {
        return amountOfEliteFightersInThisPool;
    }

    public int getAmountOfSchoolMatesInThisPool() {
        return amountOfSchoolMatesInThisPool;
    }
    
    public boolean isPoolFull()
    {
        if(currentSize == maximumSize)
            return true;
        else
            return false;
    }
}
