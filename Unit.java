import java.io.*;
import java.util.*;
public class Unit implements Serializable, Cloneable
{
    private String unitCode;
    private String unitName;
    
    public Unit(String unitCode, String unitName) throws FileNotFoundException
    {
        setUnitCode(unitCode);
        this.unitName = unitName;
    }
    
    public void setUnitCode(String unitCode)
    {
        if (isADigitString(unitCode.substring(3,7)))
        {
            String part1 = unitCode.substring(0,3);
            String part2 = unitCode.substring(3,7);
            this.unitCode = part1 + part2;
        }
    }
    
    public String getCode() 
    {
        return unitCode;
    }
    
    public String getName()
    {
        return unitName;
    }
    
    public String toString()
    {
        return getCode() + " - " + getName();
    }
    
    public boolean isADigitString(String text) //Checks if string entered is fully comprised of digits
    {
        int counter = 0;
        for(int i = 0; i < text.length(); i++)
            if (text.codePointAt(i) >=48 && text.codePointAt(i) <= 57)
                counter++;
        if (counter  == (int) text.length())
            return true;            
        else  
            return false;         
    }
}
