import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class MainProgram
{
    private static Scanner scan = new Scanner(System.in);
    private static ExamDatabase eb;
    public static boolean run = false;
    
    public static void main(String[] args) throws FileNotFoundException
    {
        eb = new ExamDatabase();
        if (eb.getAllVenues().size() == 0 || eb.getAllUnits().size() == 0 || eb.getAllStudents().size() == 0)
        {
            boolean process = true;
            while (process)
            {
                System.out.println("Press 1 to exit");
                String choice = scan.nextLine();
                switch (choice)
                {
                    case "1": 
                        System.out.println("\nExitting....");
                        process = false;
                        break;
                    default:
                        System.out.println("\nYou can only enter 1");
                        break;
                }
            }
        }
        
        else
            mainMenu();
    }
    
    public static void mainMenu() throws FileNotFoundException
    {
        boolean process = true;
        while (process)
        {
            System.out.println("\n1. Produce a list of students sitting an exam at a location");
            System.out.println("2. Produce a list of total numbers for each venue for some unit");
            System.out.println("3. Show status of a student's exam");
            System.out.println("4. Change status of a student's exam");
            System.out.println("5. Exit and Save Changes");
            System.out.println("Enter a selection (1-5):");
            String choice = scan.nextLine();
            switch (choice)
            {
                case "1":
                    System.out.println("The following units are managed by the system:");
                    venueList();
                    //System.out.println("You selected 1");
                    //process = false;
                    break;
                case "2":
                    produceVenueList();
                    //System.out.println("You selected 2");
                    //process = false;
                    break;
                case "3":
                    showExamStatus();
                    //System.out.println("You selected 3");
                    //process = false;
                    break;   
                case "4":
                    changeExamStatus();
                    //System.out.println("You selected 4");
                    //process = false;
                    break;
                case "5":
                    saveAllAndExit();
                    System.out.println("Exiting and saving changes");
                    process = false;
                    System.exit(0);
                    break;  
                default:
                    System.out.println("Please pick an option from above!\n");
                    break;
            }
        }
    }
    
    public static void venueList()
    {
        ArrayList<Student> noOfStudents;
        Unit unitOfChoice = null;
        for (Unit u: eb.getAllUnits())
        {
            System.out.println(" " + u);
        }
        boolean repeat = true;
        do
        {
            System.out.println("Which unit do you wish to generate the report for?");
            String choice = scan.nextLine();
            for (Unit u: eb.getAllUnits())
            {
                if (u.getCode().equals(choice))
                {
                    System.out.println("\n" + "Unit: " + u + "\n");
                    unitOfChoice = u;
                    repeat = false;
                }
            }
            if (unitOfChoice == null)
            {
                System.out.println("\nPlease select a valid unit by entering its unit code  - e.g FIT1001");
            }
        } while (repeat);
        
        for (Venue v: eb.getAllVenues())
        {
            noOfStudents = new ArrayList<Student>(); 
            for (Student s: eb.getAllStudents())
            {
                for (Unit us: s.getUnits())
                {
                    if (us.getCode().equals(unitOfChoice.getCode()) && s.getExamVenueCode() == v.getCode())
                    {
                        noOfStudents.add(s);
                    }
                }
            }
            System.out.println("Venue name: " + v.getName() + "\nNumber of Students writing at venue: " + noOfStudents.size() + "\n");
        }
    }
    
    public static void produceVenueList() throws FileNotFoundException
    {
        ArrayList<Student> noOfStudents;
        Unit unitOfChoice = null;
        String choice = null;
        PrintStream p = null;
        String saveName = null;
        String fullSaveName = null;
        for (Unit u: eb.getAllUnits())
        {
            System.out.println(" " + u);
        }
        boolean repeat = true;
        do
        {
            System.out.println("Which unit do you wish to generate the report for?");
            choice = scan.nextLine();
            for (Unit u: eb.getAllUnits())
            {
                if (u.getCode().equals(choice))
                {
                    unitOfChoice = u;
                    repeat = false;
                }
            }
            if (unitOfChoice == null)
            {
                System.out.println("\nPlease select a valid unit by entering its unit code - e.g FIT1001");
            }
        } while (repeat);
        boolean repeat2 = true;
        do
        {
            System.out.println("What name would you like to give for your save file");
            saveName = scan.nextLine();
            saveName = removeWhiteSpace(saveName);
            if (saveName.length() > 0)
            {   
                fullSaveName = saveName + ".dat";
                p = new PrintStream(fullSaveName);
                repeat2 = false;
            }
            else
                System.out.println("Please enter text for the name of your file");
        } while (repeat2);
        for (Unit u: eb.getAllUnits())
        {
            if (u.getCode().equals(choice))
            {
                p.println("\n" + "Unit: " + u + "\n");
                p.println();
                unitOfChoice = u;
            }
        }
        for (Venue v: eb.getAllVenues())
        {
            noOfStudents = new ArrayList<Student>(); 
            p.println("Venue name: " + v.getName()); 
            p.println();
            p.println("Students sitting the exam: ");
            for (Student s: eb.getAllStudents())
            {
                for (Unit us: s.getUnits())
                {
                    if (us.getCode().equals(unitOfChoice.getCode()) && s.getExamVenueCode() == v.getCode())
                    {
                        noOfStudents.add(s);
                        p.println(s.getName() + " - " + s.getID());
                    }
                }
            }
            if (noOfStudents.size() == 0)
                p.println("None");
            p.println();
            p.println("Number of Students writing at venue: " + noOfStudents.size());
            p.println();
            p.println();
        }
        System.out.println("File was created and it is named: " + saveName);
    }
    
    public static void showExamStatus()
    {
        Student studentChosen = null;
        System.out.println("Students managed by the system:");
        for (Student s: eb.getAllStudents())
        {
            System.out.println("Student Name: " + s.getName() + "  Student Number: " + s.getID());
        }
        boolean repeat = true;
        do
        {
            System.out.println("Select a student number to see their exam status:");
            String choice = scan.nextLine();
            for (Student s: eb.getAllStudents())
            {
                if (s.getID().equals(choice))
                {
                    studentChosen = s;
                    System.out.println("\nStudent Name: " + s.getName());
                    ArrayList<Venue> v = eb.getAllVenues();                 
                    System.out.println("Exam Venue for student: " + v.get(s.getExamVenueCode()).getName() + "\n");
                    System.out.println("Units: " );
                    for (Unit u: eb.getAllUnits())
                    {
                        int i = 1;
                        for (Unit u2: s.getUnits())
                        {
                            if (u.getName().equals(u2.getName()))
                            {
                                System.out.println(u2);
                                System.out.println("Unit Exam Status: " + s.getExamStatus(i-1));
                            }
                            i++;
                        }
                    }
                    repeat = false;
                }
            }
            if (studentChosen == null)
                System.out.println("\nPlease enter a valid student number - e.g 15000001");
        } while (repeat);
        System.out.println();
    }
    
    public static void changeExamStatus()
    {
        Student chosenStudent = null;
        Unit chosenUnit = null;
        String choice = null;
        System.out.println("Students managed by the system:");
        for (Student s: eb.getAllStudents())
        {
            System.out.println("Student Name: " + s.getName() + "  Student Number: " + s.getID());
        }        
        boolean repeat = true;
        do
        {
            System.out.println("Select a student number to change their exam status:");
            choice = scan.nextLine();
            for (Student s: eb.getAllStudents())
            {
                if (s.getID().equals(choice))
                {
                    chosenStudent = s;
                    System.out.println("\nStudent Name: " + s.getName());
                    ArrayList<Venue> v = eb.getAllVenues();                 
                    System.out.println("Exam Venue for student: " + v.get(s.getExamVenueCode()).getName());
                    System.out.println("Units: " );
                    for (Unit u: eb.getAllUnits())
                    {
                        int i = 1;
                        for (Unit u2: s.getUnits())
                        {
                            if (u.getName().equals(u2.getName()))
                            {
                                System.out.println(u2);
                                System.out.println("Unit Exam Status: " + s.getExamStatus(i-1));
                            }
                            i++;
                        }
                    }
                    repeat = false;
                }
            }
            if (chosenStudent == null)
                System.out.println("\nPlease select a valid student number e.g 15000001");
        } while (repeat);
        boolean repeat2 = true;
        int index = 0;
        
        int currentIndex = 0;
        do
        {
            int i = 1;
            System.out.println("\nSelect the Unit you want to manipulate by entering their code: ");
            choice = scan.nextLine();
            for (Unit u: chosenStudent.getUnits())
            {
                if (u.getCode().equals(choice))
                {
                    chosenUnit = u;
                    System.out.println("Unit: " + u);
                    System.out.println("Unit Exam Status: " + chosenStudent.getExamStatus(i-1));
                    currentIndex = index;
                    repeat2 = false;
                }
                index++;
                i++;
            }
            if (chosenUnit == null)
                System.out.println("\nPlease select a valid unit - e.g FIT1001");
        } while (repeat2);
        boolean repeat3 = true;
        int choiceOfStatus = 0;
        do
        {
            System.out.println("What status would you like to assign to this unit?");
            System.out.println("1. Waiting to sit the exam");
            System.out.println("2. Was present at the exam, exam is ready for collection by lecturer");
            System.out.println("3. Was absent from the exam");
            System.out.println("4. Exam has been collected by lecturer");
            choice = scan.nextLine();
            choice = removeWhiteSpace(choice);
            if (choice.length() > 0)
            {
                if (isADigitString(choice))
                    choiceOfStatus = Integer.parseInt(choice);
                else
                    choice = null;
            }
            if (choice != null)
            {
                switch (choiceOfStatus)
                {
                    case 1:
                        chosenStudent.setExamStatus(currentIndex, choiceOfStatus);
                        repeat3 = false;
                        break;
                    case 2:
                        chosenStudent.setExamStatus(currentIndex, choiceOfStatus);
                        repeat3 = false;
                        break;
                    case 3:
                        chosenStudent.setExamStatus(currentIndex, choiceOfStatus);
                        repeat3 = false;
                        break;
                    case 4:
                        chosenStudent.setExamStatus(currentIndex, choiceOfStatus);
                        repeat3 = false;
                        break;
                    default:
                        System.out.println("\nPlease pick a valid number between 1 and 4");
                        break;
                }
            }
            else
                System.out.println("\nPlease pick a valid number e.g 15000001");
        } while (repeat3);
    }
    
    public static void saveAllAndExit()
    {
        eb.saveData();
    }
    
    public static String removeWhiteSpace(String input)
    {
        return input.trim();
    }
    
    public static boolean isADigitString(String text) //Checks if string entered is fully comprised of digits
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