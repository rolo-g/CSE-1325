/*
 * Rolando Rosales 1001850424 Coding Assignment 6
 */
package code6_1001850424;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GameFrame extends JFrame
{
    private final JLabel label1;
    private final JButton OKButton;
    private final JButton CancelButton;
    private final JTextField textField1;
    public String MapName;
    
    public GameFrame()
    {
        super("Guess Game");
        
        setLayout(new FlowLayout());
        Random rn = new Random();
        switch(rn.nextInt(4))
        {
            case 0 :
            {
                MapName = "Guardian";
                break;
            }
            case 1 :
            {
                MapName = "Sandtrap";
                break;
            }
            case 2 :
            {
                MapName = "Narrows";
                break;
            }
            case 3 :
            {
                MapName = "Valhalla";
                break;
            }
        }
        Icon Map = new ImageIcon(getClass().getResource(MapName + ".png"));
        
        label1 = new JLabel();
        label1.setText("Guess the map!");
        label1.setIcon(Map);
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setVerticalTextPosition(SwingConstants.BOTTOM);
        label1.setToolTipText("A map from the video game Halo 3");
        add(label1);
        
        EventHandler handle = new EventHandler();
        
        textField1 = new JTextField("Type your answer here");
        textField1.selectAll();
        textField1.addActionListener(handle);
        add(textField1);
        
        OKButton = new JButton("Enter");
        add(OKButton);
        CancelButton = new JButton("Quit");
        add(CancelButton);
        
        OKButton.addActionListener(handle);
        CancelButton.addActionListener(handle);
    }
    private class EventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            boolean guessCorrect = false;
            
            if(MapName.toLowerCase().equals(textField1.getText().toLowerCase()))
            {
                string = String.format("Nice one, %s is the correct answer!", MapName);
                guessCorrect = true;
            }
            else
            {
                string = String.format("Sorry, %s is incorrect.", textField1.getText());
            }
            if(event.getSource() == OKButton || event.getSource() == textField1)
            {
                JOptionPane.showMessageDialog(null, string, null, JOptionPane.PLAIN_MESSAGE);
                if(guessCorrect == true)
                {
                    System.exit(0);
                }
            }
            if(event.getSource() == CancelButton)
            {
                System.exit(0);
            }
        }
    }
}
