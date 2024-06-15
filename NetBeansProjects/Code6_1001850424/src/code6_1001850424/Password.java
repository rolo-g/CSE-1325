/*
 * Rolando Rosales 1001850424 Coding Assignment 6
 */
package code6_1001850424;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class Password extends JFrame
{
    public String userInput;
    private final JPasswordField passwordField;
    private final String ActualPassword = "1001850424";
    
    public Password()
    {
        super("Enter your password");
        setLayout(new FlowLayout());
        passwordField = new JPasswordField("Hidden text");
        passwordField.setEchoChar('x');
        passwordField.selectAll();
        add(passwordField);
        
        TextFieldHandler handler = new TextFieldHandler();
        passwordField.addActionListener(handler);   
    }
    
    private class TextFieldHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == passwordField)
            {
                userInput = event.getActionCommand();
            }
            if(userInput.equals(ActualPassword))
            {
                setVisible(false);
                GameFrame gameFrame = new GameFrame();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setSize(600, 500);
                gameFrame.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect password, try again", null, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
