/*
 * Rolando Rosales 1001850424 Coding Assignment 5
 */
package code5_1001850424;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Code5_1001850424
{
    public static void main(String[] args)
    {
        String CandyFile = new String();
        String HouseFile = new String();
        String TOTFile = new String();
        String ScreenOutput = new String();
        HashMap<String, Integer> CandyList = new HashMap<>();
        ArrayList<House> HouseList = new ArrayList<>();
        ArrayList<TrickOrTreater> TOTList = new ArrayList<>();
        
        if (args.length != 3)
        {
            System.out.printf("Missing command line parameters – - Usage : CANDY= HOUSE= TOT=");
            System.exit(0);
        }
        
        CandyFile = args[0].substring(args[0].indexOf('=') + 1);
        HouseFile = args[1].substring(args[1].indexOf('=') + 1);
        TOTFile = args[2].substring(args[2].indexOf('=') + 1);
        
        CreateCandyList(CandyFile, CandyList);
        String HouseHeading = CreateHouses(HouseFile, HouseList, CandyList);
        CreateTOTs(TOTFile, TOTList, HouseList);
                
        ExecutorService executorService = Executors.newCachedThreadPool();
        
        for(TrickOrTreater it : TOTList)
        {
            executorService.execute(it);
        }
        executorService.shutdown();
        
        TextAreaFrame TAF = new TextAreaFrame();
        TAF.setVisible(true);
        
        while(TrickOrTreater.ImDone != TOTList.size())
        {
            ScreenOutput = String.format("%s", HouseHeading);
            for(TrickOrTreater it : TOTList)
            {
                ScreenOutput += String.format("%s%s\n", it.getPath(), it.getName());
            }
            TAF.textArea.setText(ScreenOutput);
        }
        String BucketContents = "Candy!!\n\n";
        for(TrickOrTreater it : TOTList)
        {
            BucketContents += it.printBucket();
        }
        TAF.textArea.setText(BucketContents);
    }
    
    static void CreateCandyList(String CandyFile, HashMap<String, Integer> CandyList)
    {
        String array[] = null;
        File FH = new File(CandyFile);
        Scanner in = null;
        
        try
        {   
            in = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("nable to read input file", CandyFile);
            System.exit(0);
        }
        
        while(in.hasNextLine())
        {
            array = in.nextLine().split("[|]");
            CandyList.put(array[0], Integer.parseInt(array[1]));
        }
        
        in.close();
    }
    static String CreateHouses(String HouseFile, ArrayList<House> HouseList, HashMap<String, Integer> CandyList)
    {
        String HouseHeading = "           ";
        String FileLine = new String();
        
        File FH = new File(HouseFile);
        Scanner in = null;
        
        try
        {   
            in = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("nable to read input file", HouseFile);
            System.exit(0);
        }
        while(in.hasNextLine())
        {
            FileLine = in.nextLine();
            HouseHeading += FileLine;
            for (int i = 0; i < 11-FileLine.length(); i++)
            {
                HouseHeading += " ";
            }
            Random rn = new Random();
            if(rn.nextInt(2) == 0)
            {
                HouseList.add(new CandyHouse(FileLine, CandyList));
            }
            else
            {
                HouseList.add(new ToothbrushHouse(FileLine, CandyList));
            }
        }
        
        in.close();
        
        HouseHeading += "\n\n";
        
        return HouseHeading;
    }
    
    static void CreateTOTs(String TOTFile, ArrayList<TrickOrTreater> TOTList, ArrayList<House> HouseList)
    {
        File FH = new File(TOTFile);
        Scanner in = null;
        String FileLine = new String();
        
        try
        {   
            in = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("Unable to read input file", TOTFile);
            System.exit(0);
        }
        
        while(in.hasNextLine())
        {
            FileLine = in.nextLine();
            TOTList.add(new TrickOrTreater(FileLine, HouseList));
        }
        
        in.close();
    }
}
