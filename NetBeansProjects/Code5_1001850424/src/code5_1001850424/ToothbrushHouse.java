/*
 * Rolando Rosales 1001850424 Coding Assignment 5
 */
package code5_1001850424;

import java.util.HashMap;
import java.util.Random;

public class ToothbrushHouse extends House
{

    public ToothbrushHouse(String houseName, HashMap<String, Integer> CandyList)
    {
        super(houseName, CandyList);
    }
    
    public synchronized String ringDoorbell(TrickOrTreater TOT) throws InterruptedException 
    {
        int randomCandies = 0;
        Random rn = new Random();
        String Candy = new String();
        TOT.addToPath("-");
        
        Thread.sleep(3000);
        randomCandies = rn.nextInt(CandyList.size()) + 1;
                
        for (HashMap.Entry it : CandyList.entrySet())
        {
            if((int)it.getValue() == randomCandies)
                Candy = (String)it.getKey();
        }
        
        return Candy;
    }
}
