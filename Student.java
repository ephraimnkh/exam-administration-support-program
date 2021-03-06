import java.util.*;
import java.io.*;
public class Student implements Serializable
{
    private String name;
    private String id; 
    private Venue examVenue;
    private int examVenueCode;
    private Unit[] units = new Unit[4];
    private Exam[] examStatus = new Exam[4];
    private Unit firstUnit;
    private Unit secondUnit;
    private Unit thirdUnit;
    private Unit fourthUnit;
    private int myNo;
    private static int noOfStudent;
    private Exam unitExamStatus;
    
    public Student(String id, String name, Venue examVenue, Unit[] units)
    {
        this.name = name;
        this.examVenue = examVenue;
        this.examVenueCode = examVenue.getCode();
        this.units = units;
        noOfStudent++;
        myNo = noOfStudent;
        setID(id);
        examStatus[0] = new Exam();
        examStatus[1] = new Exam();
        examStatus[2] = new Exam();
        examStatus[3] = new Exam();
    }
    
    public void setName(String name)
    {
        if (name.length() > 0)
        {
            this.name = name;
        }
    }
    
    public void setID(String id)
    {
        if (id.length() == 8)
        {
            if (id.charAt(0) == '1' || id.charAt(0) == '2' || id.charAt(0) == '9')
                this.id = id;
                else 
                System.err.println("Please pick a student number that starts with 1,2 or 9 for " + getName());
        }
        else
        {
            System.err.println("Please pick a student number with 8 digits for " + getName());
        }
    }
    
    public void setExamStatus(int i, int choice)
    {
        examStatus[i].changeStatus(choice);
    }
    
    public String getExamStatus(int i)
    {
        return examStatus[i].getStatus();
    }
    
    public String getID()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getNo()
    {
        return myNo;
    }
    
    public int getExamVenueCode()
    {
        return examVenueCode;
    }
    
    public Unit getFirstUnit()
    {
        return units[0];
    }
    
    public Unit getSecondUnit()
    {
        return units[1];
    }
    
    public Unit getThirdUnit()
    {
        return units[2];
    }
    
    public Unit getFourthUnit()
    {
        return units[3];
    }
    
    public Unit[] getUnits()
    {
        Unit[] copy = (Unit[]) units.clone();
        return copy;
        //return units;
    }
    
    public Exam[] getStudentExamStatuses()
    {
        Exam[] copy = (Exam[]) examStatus.clone();
        return copy;
    }
    
    public String toString()
    {
        return getName() +"\n" + getID() + "\n" + getExamVenueCode() + "\n" + getFirstUnit().getCode() + "\n" 
        + getSecondUnit().getCode() + "\n" + getThirdUnit().getCode() + "\n" + getFourthUnit().getCode();
    }
}