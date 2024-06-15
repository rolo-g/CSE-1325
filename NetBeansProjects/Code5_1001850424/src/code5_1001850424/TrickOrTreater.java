/*
 * Rolando Rosales 1001850424 Coding Assignment 5
 */
package code5_1001850424;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TrickOrTreater implements Runnable
{
    public static int ImDone = 0;
    private String name;
    private String path = ".";
    private ArrayList<House> ListOfHouses = new ArrayList<>();
    private HashMap<String, Integer> Bucket = new HashMap<>();
    
    public TrickOrTreater(String name, ArrayList<House> ListOfHouses)
    {
        this.name = name;
        this.ListOfHouses = ListOfHouses;
    }
    
    public String getName()
    {
        return name;
    }
    public String getPath()
    {
        return path;
    }
    public void addToPath(String input)
    {
        path += input;
    }
    private void walk(int speed) throws InterruptedException
    {
        int i;
        for(i = 0; i < 10; i++)
        {
            path += ".";
            Thread.sleep(speed);
        }
    }
    public String printBucket()
    {
        String Candy = new String();
        String BucketContents = new String();
        int CandyCount = 0;
        
        BucketContents = String.format("%-10s - ", name);
        for (HashMap.Entry it : Bucket.entrySet())
        {
            Candy = (String)it.getKey();
            CandyCount = (int)it.getValue();
            BucketContents += String.format("%15s %d ", Candy, CandyCount);
        }
        BucketContents += "\n";
        return BucketContents;
    }
    
    @Override
    public void run()
    {
        int speed = 0;
        int count = 0;
        String Candy = new String();
        
        for(House it : ListOfHouses)
        {
            Random rn = new Random();
            speed = (int)rn.nextInt(1501 + 1) + 1;
            try
            {
                walk(speed);
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            try
            {
                Candy = it.ringDoorbell(this);
            }
            catch (InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            
            if(Bucket.containsKey(Candy))
            {
                count = Bucket.get(Candy);
                count++;
                Bucket.put(Candy, count);
            }
            else
            {
                Bucket.put(Candy, 1);
            }
        }
        
        synchronized(this)
        {
            ImDone++;
        }
    }
}
