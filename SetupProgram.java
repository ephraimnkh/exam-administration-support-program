import java.io.*;
import java.util.*;
public class SetupProgram 
{
    private static ArrayList<Student> allStudents;
    private static ArrayList<Unit> allUnits;
    private static ArrayList<Venue> allVenues;
    private ExamDatabase eb;
    private File examInfo;

    public SetupProgram() throws FileNotFoundException
    {
         eb = new ExamDatabase();
         allStudents = new ArrayList<Student>();
         allUnits = new ArrayList<Unit>();
         allVenues = new ArrayList<Venue>();
         examInfo = new File("exams.dat");

         if (examInfo.isFile())
         {
             System.out.println("Setup has already run, so Use Exam Database for data");
         }
         else
         {
             venueLoading();
             unitLoading();
             studentLoading();
             saveData();
         }
    }
    
    public static void main(String[] args) throws FileNotFoundException
    {        
        try
        {
            new SetupProgram();   
        }
        catch (FileNotFoundException f)
        {
            System.out.println("File was not found");
        }
    }
    
    private void studentLoading() throws FileNotFoundException
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream("students.dat");
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception");
        }
        catch (Exception e)
        {
            System.out.println("This is a general exception");
        }
        Scanner scan = new Scanner(fis);
        scan.nextLine();
        Student s;
        String name = "";
        String id = "";
        Venue venue = null;
        while (scan.hasNextLine())
        {    
            Unit[] units = new Unit[4];
            for (int i = 0; i < 7; i++)
            {
                String thing = scan.nextLine();
                switch (i)
                {
                    case 0:
                        name = thing;
                        break;
                    case 1:
                        id = thing;
                        break;
                    case 2:
                        venue = allVenues.get(Integer.parseInt(thing) - 1);
                        break;
                    case 3:
                        for (Unit u: eb.getAllUnits())
                            if (u.getCode().equals(thing))
                                units[0] = u;
                        break;
                    case 4: 
                        for (Unit u: eb.getAllUnits())
                            if (u.getCode().equals(thing))
                                units[1] = u;
                        break;
                    case 5: 
                        for (Unit u: eb.getAllUnits())
                            if (u.getCode().equals(thing))
                                units[2] = u;
                        break;
                    case 6: 
                        for (Unit u: eb.getAllUnits())
                            if (u.getCode().equals(thing))
                                units[3] = u;
                        break;
                }
            }
            s = new Student(id,name,venue,units);
            allStudents.add(s);
        }
        eb.setAllStudents(allStudents);
        System.out.println(eb.getAllStudents());
    }
    
    private void unitLoading() throws FileNotFoundException
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream("units.dat");
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception");
        }
        catch (Exception e)
        {
            System.out.println("This is a general exception");
        }
        Scanner scan = new Scanner(fis);
        scan.nextLine();
        Unit u;
        String code = "";
        String name = "";
        while (scan.hasNextLine())
        {    
            for (int i = 0; i < 2; i++)
            {
                String thing = scan.nextLine();  
                switch (i)
                {
                    case 0: 
                        code = thing;
                        break;
                    case 1:
                        name = thing;
                        break;
                }
            }
            u = new Unit(code,name);
            allUnits.add(u);
        }
        eb.setAllUnits(allUnits);
        System.out.println(eb.getAllUnits());
    }
    
    private void venueLoading() throws FileNotFoundException
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream("venues.dat");
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception");
        }
        catch (Exception e)
        {
            System.out.println("This is a general exception");
        }
        Scanner scan = new Scanner(fis);
        while (scan.hasNextLine())
        {    
            for (int i = 0; i < 1; i++)
            {
                allVenues.add(new Venue (scan.nextLine()));
            }
        }
        eb.setAllVenues(allVenues);
        System.out.println(eb.getAllVenues());
    }
    
    public void saveData() 
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(examInfo);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(eb.getAllVenues()); //allVenues
            oos.writeObject(eb.getAllUnits()); //allUnits
            oos.writeObject(eb.getAllStudents()); //allStudents
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
        finally
        {
            System.out.println("Exitting....");
            System.exit(0);
        }
    }
}