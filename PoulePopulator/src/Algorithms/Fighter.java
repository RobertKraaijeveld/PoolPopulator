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
public class Fighter 
{
    private String schoolName;
    private String fighterName;
    private int skillLevel;
    
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
  
    public boolean isFighterElite()
    {
        //Should we make this customizable?
        //In any case, we should inform our users
        //And check for validity of skill; name and school can be pretty much anything, this can only be an int
        if(this.skillLevel == 1)
            return true;
        else
            return false;
    }
}
