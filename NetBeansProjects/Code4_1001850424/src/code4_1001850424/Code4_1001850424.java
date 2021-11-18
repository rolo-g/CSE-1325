/*
 * Rolando Rosales 1001850424 Coding Assignment 4
 */
package code4_1001850424;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Code4_1001850424
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
            
    public static void main(String[] args)throws FileNotFoundException
    {
        int MachineMenuChoice = 1;
        int CokeMenuChoice = 1;
        int payment;
        
        ArrayList<CokeMachine> CM = new ArrayList<>();
        
        if (args.length != 2)
        {
            System.out.printf("Missing command line parameters – - Usage : INPUTFILENAME= OUTPUTFILENAME=");
            System.exit(0);
        }
        
        String inputFileName = new String();
        inputFileName = args[0].substring(args[0].indexOf('=') + 1);
        String outputFileName = new String();
        outputFileName = args[1].substring(args[1].indexOf('=') + 1);

            // DEBUG
        
            System.out.printf("\n\n(DEBUG) Use output as input? [1/0]\n");
            Scanner debug = new Scanner(System.in);
            if(debug.nextInt() == 1)
                inputFileName = outputFileName;
        
            // DEBUG
            
        ReadFile(inputFileName, CM);
        
        while(MachineMenuChoice != 0)
        {
            CokeMenuChoice = -1;
            MachineMenuChoice = MachineMenu(CM);
            
            if(MachineMenuChoice == CM.size() + 1)
            {
                CM.add(new CokeMachine());
            }
            else
            {
                while(CokeMenuChoice != 0 && MachineMenuChoice != 0)
                { 
                    CokeMachine CokeMachine = CM.get(MachineMenuChoice - 1);
                    CokeMenuChoice = CokeMenu(CokeMenuChoice, CokeMachine.getMachineName());

                    switch(CokeMenuChoice)
                    {
                        case 0 :
                        {
                            break;
                        }
                        case 1 :
                        {
                            if (CokeMachine.getInventoryLevel() == 0)
                            {
                                System.out.print("\nWe are out of Coke!");
                            }

                            else
                            {   
                                System.out.printf("Your total is ");
                                System.out.printf(displayMoney(CokeMachine.getCokePrice()));
                                System.out.printf("\nEnter your payment (in cents) ");
                                Scanner in = new Scanner(System.in);
                                payment = in.nextInt();

                                switch(CokeMachine.buyACoke(payment))
                                {
                                    case EXACTPAYMENT :
                                    {
                                        System.out.printf("\nThank you for exact payment."
                                                + "\nHere's your coke.");
                                        break;
                                    }
                                    case DISPENSECHANGE :
                                    {
                                        System.out.printf("\nHere's your Coke and your change of %s"
                                                , displayMoney(CokeMachine.getChangeDispensed()));
                                        break;
                                    }
                                    case INSUFFICIENTCHANGE :
                                    {
                                        System.out.printf("\nThis Coke Machine does not have enough change"
                                                + " and cannot accept your payment");
                                        break;
                                    }
                                    case INSUFFICIENTFUNDS :
                                    {
                                        System.out.printf("\nYou did not enter a sufficient payment."
                                                + " No coke for you.");
                                        break;
                                    }
                                    default :
                                    {
                                        System.out.printf("\nThis should never happen\n");
                                        break;
                                    }
                                }
                            }   
                            break;
                        }
                        case 2 :
                        {
                            System.out.printf("\nHow much product are you adding to the machine? ");
                            Scanner in = new Scanner(System.in);
                            int amountToAdd = in.nextInt();

                            if(CokeMachine.incrementInventoryLevel(amountToAdd) == true)
                            {
                                System.out.printf("Your machine has been restocked"
                                        + "\n\nYour inventory level is %d", CokeMachine.getInventoryLevel());
                            }
                            else
                            {
                                System.out.printf("You have exceeded your machine's max capacity"
                                        + " - no inventory was added"
                                        + "\n\nYour inventory level is %d", CokeMachine.getInventoryLevel());
                            }
                            break;
                        }
                        case 3 :
                        {
                            System.out.printf("\nHow much change are you adding to the machine? ");
                            Scanner in = new Scanner(System.in);
                            int amountToAdd = in.nextInt();

                            if(CokeMachine.incrementChangeLevel(amountToAdd) == true)
                            {
                                System.out.printf("Your change level has been updated"
                                        + "\n\nYour change level is %s"
                                        + " with a max capacity of %s"
                                        + "", displayMoney(CokeMachine.getChangeLevel()), displayMoney(CokeMachine.getMaxChangeCapacity()));
                            }
                            else
                            {
                                System.out.printf("You exceeded your machine's max change capacity"
                                        + " - no change added"
                                        + "Your change level has been updated"
                                        + "\n\nYour change level is %s"
                                        + " with a max capacity of %s"
                                        + "", displayMoney(CokeMachine.getChangeLevel()), displayMoney(CokeMachine.getMaxChangeCapacity()));
                            }
                            break;
                        }
                        case 4 :
                        {
                            System.out.print(CokeMachine.toString());
                            break;
                        }
                        case 5 :
                        {
                            System.out.printf("\nEnter a new machine name\n");
                            Scanner in = new Scanner(System.in);
                            CokeMachine.setMachineName(in.nextLine());
                            System.out.printf("\nMachine name has been updated");
                            break;
                        }
                        case 6 : 
                        {
                            System.out.printf("\nEnter a new Coke price\n");
                            Scanner in = new Scanner(System.in);
                            CokeMachine.setCokePrice(in.nextInt());
                            System.out.printf("\nCoke price has been updated");
                            break;
                        }
                        default :
                        {
                            System.out.printf("\nThis should never happen\n");
                            break;
                        }
                    }
                }
            }
        }
        WriteFile(CM, outputFileName);
    }
    static int CokeMenu(int MenuChoice, String MachineName)
    {
        System.out.printf("\n\n%s", MachineName);
        MenuChoice = -1;
        while(MenuChoice < 0 || MenuChoice > 6)
        {
            System.out.print("\n\n0. Walk away"
                    + "\n1. Buy a Coke"
                    + "\n2. Restock Machine"
                    + "\n3. Add change"
                    + "\n4. Display Machine Info"
                    + "\n5. Update Machine Name"
                    + "\n6. Update Coke Price");
            
            Scanner in = new Scanner(System.in);
            System.out.print("\n\nChoice: ");
            
            try
            {
                MenuChoice = in.nextInt();
            }
            catch (Exception e)
            {
                MenuChoice = -1;
                in.nextLine();
            }
            
            if(MenuChoice < 0 || MenuChoice > 6)
            {
                System.out.printf("\nInvalid menu option. Please choose again.");
            }
        }
        
        return MenuChoice;
    }
    static String displayMoney(int amount)
    {
        return String.format("$%s.%s%s"
                + "", String.valueOf(amount/100)
                + "", amount >= 10 ? String.valueOf(amount%100) : 00 + String.valueOf(amount%100)
                + "", (amount % 100) == 0 ? "0" : "");
        //formatting wizardry
    }
    
    static void ReadFile(String FileName, ArrayList<CokeMachine> CM)
    {
        File FH = new File(FileName);
        Scanner in = null;
        
        try
        {   
            in = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("FATAL ERROR: Could not open file \"%s\". Exiting...\n", FileName);
            System.exit(0);
        }
        
        while(in.hasNextLine())
        {
            String Split[] = in.nextLine().split("[|]");
            CM.add(new CokeMachine(Split[0], Integer.parseInt(Split[1]), Integer.parseInt(Split[2]), Integer.parseInt(Split[3])));
        }
        
        in.close(); 
    }
    static int MachineMenu(ArrayList<CokeMachine> CM)
    {
        int MenuChoice = -1;

        while(MenuChoice < 0 || MenuChoice > CM.size()+1)
        {
            System.out.printf("Pick a Coke Machine!\n\n");
            int i = 0;

            System.out.printf("%d. Exit\n", i);
            for(i = 0; i < CM.size(); i++)
            {
               System.out.printf("%d. %s\n", i+1, CM.get(i).getMachineName());
            }
            System.out.printf("%d. Add new machine\n", i+1);

            Scanner in = new Scanner(System.in);
            System.out.print("\n\nChoice: ");

            try
            {
                MenuChoice = in.nextInt();
            }
            catch (Exception e)
            {
                MenuChoice = -1;
                in.nextLine();
            }
            
            if(MenuChoice < 0 || MenuChoice > i+1)
            {
                System.out.printf("\nInvalid menu option. Please choose again.");
            }
        }
        
        return MenuChoice;
    }
    static void WriteFile(ArrayList<CokeMachine> CM, String FileName) throws FileNotFoundException
    {
        File FH = new File(FileName);
        Scanner in = null;
        
        try
        {   
            in = new Scanner(FH);
        }
        catch (Exception e)
        {
            System.out.printf("Unable to write output file");
        }
        PrintWriter out = new PrintWriter(FileName);
        
        for(CokeMachine current : CM)
        {
            out.printf("%s|%s|%s|%s"
                    + "\n", current.getMachineName(), current.getCokePrice(), current.getChangeLevel(), current.getInventoryLevel());
        }
        out.close();
    }
}