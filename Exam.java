import java.io.*;
public class Exam implements Serializable
{
    private String examStatus;
    private String[] status = new String[4];
    
    public Exam()
    {
        status[0] = "Waiting to sit the exam";
        status[1] = "Was present at the exam, exam is ready for collection by lecturer";
        status[2] = "Was absent from the exam";
        status[3] = "Exam has been collected by lecturer";
        
        examStatus = status[0];
    }
    
    public void changeStatus(int choice)
    {
        switch (choice)
        {
            case 1:
                examStatus = status[0];
                break;
            case 2:
                examStatus = status[1];
                break;
            case 3:
                examStatus = status[2];
                break;
            case 4:
                examStatus = status[3];
                break;
            default:
                System.out.println("Please select a valid status");
                break;
        }
    }
    
    public String getStatus()
    {
        return examStatus;
    }
}
