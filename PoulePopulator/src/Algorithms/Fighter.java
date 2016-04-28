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
public class Fighter 
{
    private String schoolName;
    private String fighterName;
    private int skillLevel;
    private ArrayList<Fighter> thisFightersSchoolMates = new ArrayList<Fighter>();

    
    public Fighter(int skill, String school, String name)
    {
        skillLevel = skill;
        schoolName = school;
        fighterName = name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }
    
    public String getFighterName() {
        return fighterName;
    }
    
    public String getSchoolName() {
        return schoolName;
    }
    
    public ArrayList<Fighter> getThisFightersSchoolMates() {
        return thisFightersSchoolMates;
    }

    public void setThisFightersSchoolMates(ArrayList<Fighter> thisFightersSchoolMates) {
        this.thisFightersSchoolMates = thisFightersSchoolMates;
    }

    
    public boolean isThisFighterSomeonesSchoolMate(Pool thisFightersPool)
    {
        for(Fighter f : thisFightersPool.fightersInThisPool)
        {
            if(thisFightersPool.areGivenFightersAlreadySchoolmates(this, f))
                return true;
            else
                return false;
        }
        return false;
    }
    
    public boolean isFighterElite()
    {
        //Should we make this customizable?
        //In any case, we should inform our users
        if(this.skillLevel == 1)
            return true;
        else
            return false;
    }
}
