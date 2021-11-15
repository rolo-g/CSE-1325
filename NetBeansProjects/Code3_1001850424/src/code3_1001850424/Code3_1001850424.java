/*
 * Rolando Rosales 1001850424 Coding Assignment 3
 */
package code3_1001850424;

import java.util.Scanner;

public class Code3_1001850424
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
            
    public static void main(String[] args)
    {
        int MenuChoice = 1;
        int payment;

        CokeMachine MyCokeMachine = new CokeMachine("CSE 1325 Coke Machine", 50, 500, 100);
        
        while(MenuChoice != 0)
        {
            MenuChoice = CokeMenu(MenuChoice, MyCokeMachine.getMachineName());
        
            switch(MenuChoice)
            {
                case 0 :
                {
                    break;
                }
                case 1 :
                {
                    if (MyCokeMachine.getInventoryLevel() == 0)
                    {
                        System.out.print("\nWe are out of Coke!");
                    }
                    
                    else
                    {   
                        System.out.printf("Your total is ");
                        System.out.printf(displayMoney(MyCokeMachine.getCokePrice()));
                        System.out.printf("\nEnter your payment (in cents) ");
                        Scanner in = new Scanner(System.in);
                        payment = in.nextInt();
       
                        switch(MyCokeMachine.buyACoke(payment))
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
                                        , displayMoney(MyCokeMachine.getChangeDispensed()));
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
                    
                    if(MyCokeMachine.incrementInventoryLevel(amountToAdd) == true)
                    {
                        System.out.printf("Your machine has been restocked"
                                + "\n\nYour inventory level is %d", MyCokeMachine.getInventoryLevel());
                    }
                    else
                    {
                        System.out.printf("You have exceeded your machine's max capacity"
                                + " - no inventory was added"
                                + "\n\nYour inventory level is %d", MyCokeMachine.getInventoryLevel());
                    }
                    break;
                }
                case 3 :
                {
                    System.out.printf("\nHow much change are you adding to the machine? ");
                    Scanner in = new Scanner(System.in);
                    int amountToAdd = in.nextInt();
                    
                    if(MyCokeMachine.incrementChangeLevel(amountToAdd) == true)
                    {
                        System.out.printf("Your change level has been updated"
                                + "\n\nYour change level is %s"
                                + " with a max capacity of %s"
                                + "", displayMoney(MyCokeMachine.getChangeLevel()), displayMoney(MyCokeMachine.getMaxChangeCapacity()));
                    }
                    else
                    {
                        System.out.printf("You exceeded your machine's max change capacity"
                                + " - no change added"
                                + "Your change level has been updated"
                                + "\n\nYour change level is %s"
                                + " with a max capacity of %s"
                                + "", displayMoney(MyCokeMachine.getChangeLevel()), displayMoney(MyCokeMachine.getMaxChangeCapacity()));
                    }
                    break;
                }
                case 4 :
                {
                    System.out.print(MyCokeMachine.toString());
                    break;
                }
                default :
                {
                    System.out.printf("\nThis should never happen\n");
                    break;
                }
            }
        }
        
        if(MyCokeMachine.getNumberOfCokesSold() > 0)
        {
            System.out.printf("\nEnjoy your Coke. Bye!\n");
        }
        else
        {
            System.out.printf("\n*You walk away without buying any Coke*\n");
        }
    }
    static int CokeMenu(int MenuChoice, String MachineName)
    {
        System.out.printf("\n\n%s", MachineName);
        MenuChoice = -1;
        while(MenuChoice < 0 || MenuChoice > 4)
        {
            System.out.print("\n\n0. Walk away"
                    + "\n1. Buy a Coke"
                    + "\n2. Restock Machine"
                    + "\n3. Add change"
                    + "\n4. Display Machine Info");
            
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
            
            if(MenuChoice < 0 || MenuChoice > 4)
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
}
