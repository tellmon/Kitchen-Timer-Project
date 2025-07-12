import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Errors {
	JTextField errorsDisplay;
	
	
	public Errors(String errors )  {
		JFrame frame = new JFrame("Errors");
         		
        errorsDisplay = new JTextField(errors);
       
        
        
        errorsDisplay.setSize(500, 500);
        errorsDisplay.setLayout(new BorderLayout());
        errorsDisplay.setFocusable(false);
        
        frame.add(errorsDisplay, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
}
