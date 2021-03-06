import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*; 
import java.io.*;
public class ExamGUI extends JFrame implements ActionListener
{
    private JLabel unitLabel;
    private JLabel venueLabel;
    private JButton generateReportBtn;
    private JComboBox unitEntry;
    private JComboBox venueEntry;
    private JLabel reportArea;
    private JTextArea reportResults;
    private JScrollPane reportScroll;
    private JPanel first;
    private JPanel second;
    private JPanel group1;
    private JPanel group2;
    private JPanel group3;
    private ExamDatabase eb;
    private Scanner scan;
    
    public ExamGUI() throws FileNotFoundException
    {
        setTitle("Exam Report Generator");
        unitLabel = new JLabel("Select Unit: ");
        venueLabel = new JLabel("Select Venue: " );
        generateReportBtn = new JButton("Generate Report");
        unitEntry = new JComboBox();
        venueEntry = new JComboBox();
        reportArea = new JLabel("Report Output: ");
        reportResults = new JTextArea(28,50);
        reportScroll = new JScrollPane(reportResults,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eb = new ExamDatabase();
        scan = new Scanner(System.in);
        
        setLayout(new GridLayout(1,1));
        setSize(800,600);
        
        first = new JPanel();
        second = new JPanel();
        group1 = new JPanel();
        group2 = new JPanel();
        group3 = new JPanel();
        
        first.setLayout(new FlowLayout());
        second.setLayout(new FlowLayout());
        group1.setLayout(new FlowLayout());
        group2.setLayout(new FlowLayout());
        group3.setLayout(new FlowLayout());

        unitEntry.addItem(new String("FIT1001 - Computer Systems"));
        unitEntry.addItem(new String("FIT1002 - Computer Programming 1"));
        unitEntry.addItem(new String("FIT1003 - IT in Organisations"));
        unitEntry.addItem(new String("FIT1004 - Database"));
        unitEntry.addItem(new String("FIT1005 - Network and Data Communications"));
        unitEntry.addItem(new String("FIT1007 - Computer Programming 2"));
        unitEntry.addItem(new String("FIT1009 - e-Commerce Systems"));
        unitEntry.addItem(new String("FIT1011 - Web Systems 1"));
        unitEntry.addItem(new String("FIT1012 - Website Authoring"));
        unitEntry.addItem(new String("FIT1020 - Fundamentals of Information Systems"));
        unitEntry.addItem(new String("FIT2001 - Systems Analysis and Design"));
        unitEntry.addItem(new String("FIT2002 - IT Project Management"));
        unitEntry.addItem(new String("FIT2005 - Systems Analysis and Design 2"));
        
        venueEntry.addItem(new String("Clayton Campus"));
        venueEntry.addItem(new String("Caulfield Campus"));
        venueEntry.addItem(new String("Gippsland Campus"));
        venueEntry.addItem(new String("Berwick Campus"));
        venueEntry.addItem(new String("Peninsula Campus"));
        venueEntry.addItem(new String("Malaysia Campus"));
        venueEntry.addItem(new String("South Africa Campus"));
        venueEntry.addItem(new String("Melbourne Exam Venue"));
        venueEntry.addItem(new String("Sydney Exam Venue"));
        venueEntry.addItem(new String("Brisbane Exam Venue"));
        venueEntry.addItem(new String("Adelaide Exam Venue"));
        venueEntry.addItem(new String("Perth Exam Venue"));
        venueEntry.addItem(new String("Singapore Exam Venue"));
        venueEntry.addItem(new String("Hong Kong Exam Venue"));
        venueEntry.addItem(new String("Thailand Exam Venue"));
        venueEntry.addItem(new String("London Exam Venue (UK)"));
        venueEntry.addItem(new String("Prato Exam Venue (Italy)"));
        venueEntry.addItem(new String("Guangzhou Venue"));
        
        
        group1.add(unitLabel);
        group1.add(unitEntry);
        group2.add(venueLabel);
        group2.add(venueEntry);
        group2.add(generateReportBtn);
        group3.add(reportArea);
        group3.add(reportScroll);
        
        first.add(group1);
        first.add(group2);
        
        first.add(group3);
        
        generateReportBtn.addActionListener(this);
        
        add(first);
        
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
            setVisible(true);
    }
    
    public void actionPerformed(ActionEvent a)
    {
        if(a.getSource() == generateReportBtn)
        {
            unitLabel.setForeground(Color.BLACK);
            venueLabel.setForeground(Color.BLACK);
            try
            {
                printReport();
            }
            catch (Exception e){}
        }
    }
    
    public void printReport()
    {
        ArrayList<Student> noOfStudents;
        Unit unitOfChoice = null;
        String choice = unitEntry.getSelectedItem().toString();
        int choice2 = venueEntry.getSelectedIndex();
        ArrayList<Venue> venues = eb.getAllVenues();
        Venue v = venues.get(choice2);
        for (Unit u: eb.getAllUnits())
        {
            if (u.toString().equals(choice))
            {
                reportResults.append("\n" + "Report contains the list of students sitting the exam for " + "\n" + "the unit " + u + "\n" + 
                "at the venue " + v.getName() + "\n");
                unitOfChoice = u;
            }
        }
        noOfStudents = new ArrayList<Student>(); 
        reportResults.append("\nVenue name: " + v.getName() + "\n"); 
        reportResults.append("\nStudents sitting the exam: \n");
        for (Student s: eb.getAllStudents())
        {
            for (Unit us: s.getUnits())
            {
                if (us.toString().equals(unitOfChoice.toString()) && s.getExamVenueCode() == (choice2 + 1))
                {
                    noOfStudents.add(s);
                    reportResults.append(s.getName() + " - " + s.getID() + "\n");
                }
            }
        }
        reportResults.append("\n");
        reportResults.append("Number of Students writing at venue: " + noOfStudents.size() + "\n");
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
    
    public static void main(String[] args) throws FileNotFoundException
    {
        ExamGUI myFrame = new ExamGUI();
        MyWindowListener myWindow = new MyWindowListener();        
        myFrame.addWindowListener(myWindow);
    }
} 