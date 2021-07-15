import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyWindowListener extends WindowAdapter implements WindowListener
{
    public void windowClosing(WindowEvent w)
    {
        System.exit(0);
    }
}