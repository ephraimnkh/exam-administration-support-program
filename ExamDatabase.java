import java.util.ArrayList;
import java.io.*;
public class ExamDatabase
{
    private static ArrayList<Student> allStudents;
    private static ArrayList<Unit> allUnits;
    private static ArrayList<Venue> allVenues;
    private File examInfo;
    
    public ExamDatabase()
    {
        allStudents = new ArrayList<Student>();
        allUnits = new ArrayList<Unit>();
        allVenues = new ArrayList<Venue>();
        examInfo = new File("exams.dat");
        
        if (examInfo.isFile())
         {
             try
             {
                 FileInputStream fis = new FileInputStream(examInfo);
                 ObjectInputStream ois = new ObjectInputStream(fis);
                 allVenues = (ArrayList<Venue>) ois.readObject();
                 allUnits = (ArrayList<Unit>) ois.readObject();
                 allStudents = (ArrayList<Student>) ois.readObject();
                 ois.close();
                 fis.close();
                 System.out.println("The system is now set up and ready for use\n");
             }
             catch (FileNotFoundException fnfe)
             {
                System.out.println("File was not found");
             }
             catch (IOException ioe)
             {
                 System.out.println("IO Exception");
             }
             catch (Exception e)
             {
                 System.out.println("This is a general exception");
             }
             finally
             {
                 /*System.out.println(allVenues);
                 System.out.println(allUnits);
                 System.out.println(allStudents); */
             }
         }
        else
        {
            System.out.println("Please run the Setup Program to setup the Exam Database");
        }
    }
    
    public void saveData() 
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(examInfo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allVenues); //allVenues
            oos.writeObject(allUnits); //allUnits
            oos.writeObject(allStudents); //allStudents
            oos.close();
            System.out.println("The system is now set up and ready for use\n");
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("File was not found");
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception");
            ioe.printStackTrace();
        }
        catch (Exception e)
        {
            System.out.println("This is a general exception");
        }
    }
    
    public static void setAllVenues(ArrayList<Venue> venues) 
    {
        allVenues = venues;
    }
    
    public static void setAllUnits(ArrayList<Unit> units) 
    {
        allUnits = units;
    }
    
    public static void setAllStudents(ArrayList<Student> students) 
    {
        allStudents = students;
    }
    
    public static ArrayList<Venue> getAllVenues()
    {
        ArrayList<Venue> copy = (ArrayList<Venue>) allVenues.clone();
        return copy;
    }
    
    public static ArrayList<Unit> getAllUnits()
    {
        ArrayList<Unit> copy = (ArrayList<Unit>) allUnits.clone();
        return copy;
    }
    
    public static ArrayList<Student> getAllStudents()
    {
        ArrayList<Student> copy = (ArrayList<Student>) allStudents.clone();
        return copy;
    }
}