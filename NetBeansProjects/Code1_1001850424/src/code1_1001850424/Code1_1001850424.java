/*
 * Rolando Rosales 1001850424 Coding Assignment 1
 */
package code1_1001850424;

import java.util.Scanner;

public class Code1_1001850424
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
            
    public static void main(String[] args)
    {
        final int price = 60;
        final int PaymentPass = 0;
        final int ChangePass = 1;
        final int InventoryPass = 2;
        final int PurchasedPass = 3;
        int PencilsPurchased = 0;
        int Payment = 0;
        int Total = 0;
        int MenuChoice = 1;
        int Inventory = 100;
        int Change = 500;
        int IntPass[] = {0, 0, 0, 0};
       
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
                        
                        IntPass[PaymentPass] = Payment;
                        IntPass[ChangePass] = Change;
                        IntPass[InventoryPass] = Inventory;
                        IntPass[PurchasedPass] = PencilsPurchased;
                        ACTION action = buyPencils(IntPass);
                        Payment = IntPass[PaymentPass]; //'Payment' re-used to display change given
                        Change = IntPass[ChangePass];
                        Inventory = IntPass[InventoryPass];
                        switch(action)
                        {
                            case EXACTPAYMENT :
                            {
                                System.out.printf("\nHere is your pencils."
                                        + " Thank you for exact payment");
                                break;
                            }
                            case DISPENSECHANGE :
                            {
                                System.out.printf("\nHere's your pencils and your change of ");
                                System.out.print(displayMoney(Payment));
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
                    System.out.printf("\nInvalid menu option.");
                    break;
                }
            }
        }
    }
    static int PencilMenu(int MenuChoice)
    {
        System.out.print("\n\nPlease choose from the following options");
        
        System.out.print("\n\n0. No pencils for me today"
                + "\n1. Purchace pencils"
                + "\n2. Check inventory level"
                + "\n3. Check change level");
        
        Scanner in = new Scanner(System.in);
        System.out.print("\nChoice: ");
        MenuChoice = in.nextInt();
        
        return MenuChoice;
    }
    static String displayMoney(int amount)
    {
        String formatted = "$" + amount / 100 + "." + amount % 100;
        
        if((amount % 100) == 0)
        {
            formatted = formatted + "0";
        }
        
        return formatted;
    }
    static ACTION buyPencils(int IntPass[])
    {
        final int price = 60;
        final int PaymentPass = 0;
        final int ChangePass = 1;
        final int InventoryPass = 2;
        final int PurchasedPass = 3;
        
        int Total = IntPass[PurchasedPass] * price;
        
        if(IntPass[PaymentPass] == Total)
        {
            IntPass[ChangePass] += Total;
            IntPass[InventoryPass] -= IntPass[PurchasedPass];
            return ACTION.EXACTPAYMENT;
        }
        if(IntPass[PaymentPass] > IntPass[ChangePass])
        {
            return ACTION.INSUFFICIENTCHANGE;
        }
        if(IntPass[PaymentPass] > Total)
        {
            IntPass[ChangePass] += Total;
            IntPass[PaymentPass] -= Total;
            IntPass[InventoryPass] -= IntPass[PurchasedPass];
            return ACTION.DISPENSECHANGE;
        }
        if(IntPass[PaymentPass] < Total)
        {
            return ACTION.INSUFFICIENTFUNDS;
        }
        
        return ACTION.INSUFFICIENTFUNDS; //should never reach this
    }
}