import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StopWatchMaker extends JPanel implements ActionListener {
/*** */private static final long serialVersionUID = 1L;
	
	int hou = 0;
	int min = 0;
	int sec = 0; 
	
	// JTextField
	 JTextField name;
	 JTextField hours;
	 JTextField minutes;
	 JTextField seconds;

	// JButton
	  JButton submitButton;

	// label to display text
	  JLabel WTitel;
	  JLabel wHours;
	  JLabel wMinutes;
	  JLabel wSeconds;

	public  StopWatchMaker() {
		
		// create a label to display text
		WTitel = new JLabel("Title:");
		wHours = new JLabel("Hours:");
		wMinutes = new JLabel("Minutes:");
		wSeconds = new JLabel("Seconds:");

		name = new JTextField(10);
		hours = new JTextField(3);
		minutes = new JTextField(2);
		seconds = new JTextField(2);
		

		Border emptyBorder = BorderFactory.createEmptyBorder();
		
		submitButton = new JButton("submit");
		submitButton.setBackground(Color.WHITE);
		
		JPanel mainPanel = new JPanel();	
		
		// add buttons and textfield to panel
		mainPanel.add(WTitel);
		mainPanel.add(name);
		
		mainPanel.add(wHours);
		mainPanel.add(hours);
		
		mainPanel.add(wMinutes);
		mainPanel.add(minutes);
		
		mainPanel.add(wSeconds);
		mainPanel.add(seconds);
		
		mainPanel.add(submitButton);

		submitButton.setBorder(emptyBorder);
		submitButton.setFocusable(false);
		submitButton.addActionListener(this);
		
		this.add(mainPanel);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		
		if ("".equals(hours.getText())) {
			hours.setText("0"); 
		}
		
		if ("".equals(minutes.getText())) {
			minutes.setText("0");
		}
		
		if ("".equals(seconds.getText())) {
			seconds.setText("0"); 
		}
		
		JFrame stopWatch = new JFrame();
		stopWatch.setTitle(name.getText()); 
		stopWatch.setSize(500, 500);
		stopWatch.setContentPane(new StopWatch(Integer.parseInt(hours.getText()), Integer.parseInt(minutes.getText()), Integer.parseInt(seconds.getText()), name.getText()));
		stopWatch.setLocationRelativeTo(null);
		stopWatch.setVisible(true);

		// set the text of field to blank
		name.setText(" ");
	}

}


