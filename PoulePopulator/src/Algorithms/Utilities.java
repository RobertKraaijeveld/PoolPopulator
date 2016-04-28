/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author Kraaijeveld
 */

public class Utilities 
{
    public Fighter getRandomFighterFromShuffledList(ArrayList<Fighter> fighterList)
    {
        fighterList = shuffleFighterList(fighterList);
        return fighterList.get(0);
    }
    
    private ArrayList<Fighter> shuffleFighterList(ArrayList<Fighter> fighterList)
    {
        Collections.shuffle(fighterList);
        return fighterList;     
    }
    
    public boolean listOfFightersContainsOnlyElites(ArrayList<Fighter> fighterList)
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
    
    public boolean listOfFightersContainsOnlyMates(ArrayList<Fighter> fighterList, Pool thisFightersPool)
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
