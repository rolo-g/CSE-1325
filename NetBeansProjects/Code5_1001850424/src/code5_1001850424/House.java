/*
 * Rolando Rosales 1001850424 Coding Assignment 5
 */
package code5_1001850424;

import java.util.HashMap;

public class House
{
    private String houseName;
    HashMap<String, Integer> CandyList;
    
    public House(String houseName, HashMap<String, Integer> CandyList) 
    {
        this.houseName = houseName;
        this.CandyList = CandyList;
    }
    public synchronized String ringDoorbell(TrickOrTreater TOTName) throws InterruptedException 
    {
        return "";
    }
}
