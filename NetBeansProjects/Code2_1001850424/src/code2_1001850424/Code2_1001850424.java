/*
 * Rolando Rosales 1001850424 Coding Assignment 2
 */
package code2_1001850424;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.PrintWriter;

public class Code2_1001850424
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
            
    public static void main(String[] args) throws FileNotFoundException
    {
        final int price = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));
        final int ChangePass = 0;
        final int InventoryPass = 1;
        int PencilsPurchased = 0;
        int Payment = 0;
        int Total = 0;
        int MenuChoice = 1;
        int IntPass[] = {0, 0};
        String userChange[] = new String[1];
        ArrayList<String> PencilColor = new ArrayList<>();
        
        String FileName = new String();
        FileName = args[0].substring(args[0].indexOf('=') + 1);
        ReadFile(IntPass, FileName, PencilColor);

        int Change = IntPass[ChangePass];
        int Inventory = IntPass[InventoryPass];
        
        System.out.print("Welcome to my pencil machine");
        
        while(MenuChoice != 0)
        {
            MenuChoice = PencilMenu(MenuChoice);
            
            switch(MenuChoice)
            {
                case 0 :
                {
                    break;
                }
                case 1 :
                {
                    if (Inventory == 0)
                    {
                        System.out.print("\nWe are out of pencils!");
                    }
                    else
                    {   
                        PencilsPurchased = 0;
                        System.out.print("\nA pencil costs ");
                        System.out.printf(displayMoney(price));
                        Scanner in = new Scanner(System.in);
                        System.out.print("\nHow many pencils would you like to purchase? ");
                        PencilsPurchased = in.nextInt();
                        while(PencilsPurchased <= 0 || PencilsPurchased > Inventory)
                        {
                            System.out.print("\nCannot sell that quantity of pencils."
                                    + " Please reenter quantity ");
                            PencilsPurchased = 0;
                            PencilsPurchased = in.nextInt();
                        }
                        Total = PencilsPurchased * price;
                        System.out.printf("Your total is ");
                        System.out.printf(displayMoney(Total));
                        System.out.printf("\nEnter your payment (in cents) ");
                        Payment = in.nextInt();
                        
                        IntPass[ChangePass] = Change;
                        IntPass[InventoryPass] = Inventory;
                        ACTION action = buyPencils(IntPass, Payment, price, PencilsPurchased, userChange);
                        Change = IntPass[ChangePass];
                        Inventory = IntPass[InventoryPass];
                        switch(action)
                        {
                            case EXACTPAYMENT :
                            {
                                System.out.printf("\nHere is your %s pencils."
                                        + " Thank you for exact payment", ColorPicker(PencilColor));
                                break;
                            }
                            case DISPENSECHANGE :
                            {
                                System.out.printf("\nHere's your %s pencils and your change of %s"
                                        , ColorPicker(PencilColor), userChange[0]);
                                break;
                            }
                            case INSUFFICIENTCHANGE :
                            {
                                System.out.printf("\nThis Pencil Dispenser does not have enough change"
                                        + " and cannot accept your payment");
                                break;
                            }
                            case INSUFFICIENTFUNDS :
                            {
                                System.out.printf("\nYou did not enter a sufficient payment."
                                        + " No pencils for you.");
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
                    System.out.printf("\nThe current inventory level is %d", Inventory);
                    break;
                }
                case 3 :
                {
                    System.out.printf("\nThe current change level is ");
                    System.out.print(displayMoney(Change));
                    break;
                }
                default :
                {
                    System.out.printf("\nThis should never happen\n");
                    break;
                }
            }
        }
        
        PrintWriter out = new PrintWriter(FileName);
        
        out.printf("%d %d\n", Change, Inventory);
        
        for(String Current : PencilColor)
        {
            out.printf("%s ", Current);
        }
        
        out.close();
    }
    static int PencilMenu(int MenuChoice)
    {
        MenuChoice = -1;
        while(MenuChoice < 0 || MenuChoice > 3)
        {
            System.out.print("\n\nPlease choose from the following options");

            System.out.print("\n\n\n0. No pencils for me today"
                    + "\n1. Purchace pencils"
                    + "\n2. Check inventory level"
                    + "\n3. Check change level");
            
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
            
            if(MenuChoice < 0 || MenuChoice > 3)
            {
                System.out.printf("\nInvalid menu option. Please choose again.");
            }
        }
        
        return MenuChoice;
    }
    static String displayMoney(int amount)
    {
        String formatted = new String();
        
        formatted = "$" + String.valueOf(amount/100) + "." + String.valueOf(amount%100);
        
        if(amount % 100 == 0)
        {
            formatted += String.valueOf(0);
        }
        
        return formatted;
    }
    static ACTION buyPencils(int IntPass[], int Payment, int price, int PencilsPurchased, String userChange[])
    {
        final int ChangePass = 0;
        final int InventoryPass = 1;
        
        int Total = PencilsPurchased * price;
        
        if(Payment == Total)
        {
            IntPass[ChangePass] += Total;
            IntPass[InventoryPass] -= PencilsPurchased;
            return ACTION.EXACTPAYMENT;
        }
        if(Payment > IntPass[ChangePass])
        {
            return ACTION.INSUFFICIENTCHANGE;
        }
        if(Payment > Total)
        {
            IntPass[ChangePass] += Total;
            userChange[0] = displayMoney(Payment -= Total);
            IntPass[InventoryPass] -= PencilsPurchased;
            return ACTION.DISPENSECHANGE;
        }
        if(Payment < Total)
        {
            return ACTION.INSUFFICIENTFUNDS;
        }
        
        return ACTION.INSUFFICIENTFUNDS; //should never reach this
    }
    
    static void ReadFile(int IntPass[], String FileName, ArrayList<String> PencilColor)
    {
        final int ChangePass = 0;
        final int InventoryPass = 1;
        
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
        
        String SplitInt[] = in.nextLine().split("[ ]");
        IntPass[ChangePass] = Integer.parseInt(SplitInt[0]);
        IntPass[InventoryPass] = Integer.parseInt(SplitInt[1]);
        
        for (String CurrentColor : in.nextLine().split("[ ]"))
        {
            PencilColor.add(CurrentColor);
        }
        
        in.close(); 
    }
    
    static String ColorPicker(ArrayList<String> PencilColor)
    {
        Random rn = new Random();
        
        return PencilColor.get(rn.nextInt(PencilColor.size()));
    }
}