package code5_1001850424;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class TextAreaFrame extends JFrame
{
 public JTextArea textArea;
 
 public TextAreaFrame()
 {
 super("TrickOrTreat");
 textArea = new JTextArea(50, 10);
 textArea.setEditable(false);
 textArea.setFont(new Font("monospaced", Font.PLAIN, 20));
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 setSize(1100,600);
 setLocationRelativeTo(null);
 setLayout(new GridBagLayout());
 GridBagConstraints constraints = new GridBagConstraints();
 constraints.insets = new Insets(10, 10, 10, 10);
 constraints.anchor = GridBagConstraints.WEST;
 constraints.gridx = 0;
 constraints.gridy = 1;
 constraints.gridwidth = 2;
 constraints.fill = GridBagConstraints.BOTH;
 constraints.weightx = 1.0;
 constraints.weighty = 1.0;
 
 add(new JScrollPane(textArea), constraints);
 }
}
