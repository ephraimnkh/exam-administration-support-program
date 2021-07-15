import java.util.*;
import java.io.*;
public class Venue implements Serializable
{
    private int code;
    private static int numberOfVenue;
    private String name;
    
    public Venue(String name)
    {
        this.name = name;
        numberOfVenue++;
        this.code = numberOfVenue;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String toString()
    {
        return "Venue Name: " + getName() + "\n";
    }
}