package code4_1001850424;

public class CokeMachine
{
    public enum ACTION
    {
        DISPENSECHANGE, INSUFFICIENTCHANGE, INSUFFICIENTFUNDS, EXACTPAYMENT
    }
        
    private String machineName;
    private int changeLevel;
    private int maxChangeCapacity = 5000;
    private int inventoryLevel;
    private int maxInventoryCapacity = 100;
    private int CokePrice;
    private int changeDispensed;
    private static int numberOfCokesSold = 0;
    
    public CokeMachine(String name, int cost, int change, int inventory)
    {
        machineName = name;
        CokePrice = cost;
        changeLevel = change;
        inventoryLevel = inventory;
    }
    public String getMachineName()
    {
        return machineName;
    }
    public int getChangeLevel()
    {
        return changeLevel;
    }
    public int getMaxChangeCapacity()
    {
        return maxChangeCapacity;
    }
    public int getInventoryLevel()
    {
        return inventoryLevel;
    }
    public int getMaxInventoryLevel()
    {
        return maxInventoryCapacity;
    }
    public int getCokePrice()
    {
        return CokePrice;
    }
    public int getChangeDispensed()
    {
        return changeDispensed;
    }
    public int getNumberOfCokesSold()
    {
        return numberOfCokesSold;
    }
    public boolean incrementInventoryLevel(int amountToAdd)
    {
        if(amountToAdd + inventoryLevel <= maxInventoryCapacity)
        {
            inventoryLevel += amountToAdd;
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean incrementChangeLevel(int amountToAdd)
    {
        if(amountToAdd + changeLevel <= maxChangeCapacity)
        {
            changeLevel += amountToAdd;
            return true;
        }
        else
        {
            return false;
        }
    }
    public ACTION buyACoke(int payment)
    {
        if(payment > changeLevel)
        {
            return ACTION.INSUFFICIENTCHANGE;
        }
        if(payment == CokePrice)
        {
            inventoryLevel -= 1;
            changeLevel += CokePrice;
            numberOfCokesSold++;
            return ACTION.EXACTPAYMENT;
        }
        if(payment > CokePrice)
        {
            changeDispensed = payment - CokePrice;
            changeLevel += CokePrice;
            inventoryLevel -= 1;
            numberOfCokesSold++;
            return ACTION.DISPENSECHANGE;
        }
        if(payment < CokePrice)
        {
            return ACTION.INSUFFICIENTFUNDS;
        }
        
        return ACTION.INSUFFICIENTFUNDS;
    }
    
    @Override
    public String toString()
    {
        return String.format("Machine Name            %s\nCurrent Inventory Level %d\nCurrent Change Level    %d\nLast Change Dispensed   %d\nInventory Capacity      %d\nChange Capacity         %d\nCoke Price              %d\nCokes Sold              %d", machineName, inventoryLevel, changeLevel, changeDispensed, maxInventoryCapacity, maxChangeCapacity, CokePrice, numberOfCokesSold);
    }   
}