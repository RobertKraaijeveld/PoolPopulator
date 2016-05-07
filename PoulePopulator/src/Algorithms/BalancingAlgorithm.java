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
public class BalancingAlgorithm 
{
    private ArrayList<Pool> unbalancedPools = new ArrayList<Pool>();
    
    public BalancingAlgorithm(ArrayList<Pool> listOfPools)
    {
        this.unbalancedPools = listOfPools;
    }
    
    public ArrayList<Pool> run()
    {
        for(Pool p : unbalancedPools)
        {
            if(p.getCurrentSize() != getAverageUnbalancedPoolSize())
            {
                
            }
        }
    }
    
    private ArrayList
    
    private int getAverageUnbalancedPoolSize()
    {
        int poolSizeCounter = 0;
        
        for(Pool p : unbalancedPools)
        {
            poolSizeCounter += p.fightersInThisPool.size();
        }
        
        return (int) Math.floor(poolSizeCounter / unbalancedPools.size());
    }
}
