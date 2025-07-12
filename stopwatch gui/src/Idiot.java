import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

public class Idiot extends JPanel implements ActionListener{
	
	/** * */private static final long serialVersionUID = 1L;
	
	
	//attributes
	JButton idiot;
	JButton start;
	JButton stop;
	JButton reset;
	JButton split;
	JButton blank;
	
	JLabel clock;
	
	JPanel topPanel;
	JPanel bottomPanel;
	
	JSplitPane splitPane = new JSplitPane();
	
	public Idiot(){	
		// defining clock and setting it with a font and where it is 
		clock = new JLabel("YOU ARE AN IDIOT");
		clock.setFont(new Font("Serif", Font.PLAIN, 50));
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout()); 
		topPanel.add(clock);
		
		// makeing the bottom layout
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2,4, 10, 10)); 
				
		// setting colour of backround
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(new BorderLayout());
		
		// declaring the buttons
		start = new JButton("YOU ARE AN IDIOT");
		reset = new JButton("YOU ARE AN IDIOT");
		stop = new JButton("YOU ARE AN IDIOT");
		split = new JButton("YOU ARE AN IDIOT");
		blank = new  JButton("YOU ARE AN IDIOT");
		idiot = new JButton("YOU ARE AN IDIOT");		
		
		// makeing the buttons diffrent colours
		start.setBackground(Color.WHITE);
		reset.setBackground(Color.WHITE);
		stop.setBackground(Color.WHITE);
		split.setBackground(Color.WHITE);
		blank.setBackground(Color.WHITE); 
		idiot.setBackground(Color.WHITE);
		
		
		// add buttons to bottom panel
		bottomPanel.add(start );
		bottomPanel.add(reset);
		bottomPanel.add(stop);
		bottomPanel.add(split);
		bottomPanel.add(blank );
		bottomPanel.add(idiot );
		
		//make them have no border
		Border emptyBorder = BorderFactory.createEmptyBorder();
		start.setBorder(emptyBorder);
		reset.setBorder(emptyBorder);
		stop.setBorder(emptyBorder);
		split.setBorder(emptyBorder);
		blank.setBorder(emptyBorder);
		idiot.setBorder(emptyBorder);
		
		//make them not have the box around them when clicked
		start.setFocusPainted(false);
		reset.setFocusPainted(false);
		stop.setFocusPainted(false);
		split.setFocusPainted(false);
		blank.setFocusPainted(false);
		idiot.setFocusPainted(false);
		
		// see when button is pressed
        start.addActionListener(this);
        reset.addActionListener(this);
        stop.addActionListener(this);
        split.addActionListener(this);
        blank.addActionListener(this);
        idiot.addActionListener(this);
		
        
        // make panel to put on screen
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
		splitPane.setDividerLocation(150);   
		splitPane.setTopComponent(topPanel);                  
        splitPane.setBottomComponent(bottomPanel); 
		this.add(splitPane);
		
		
		
	}


	public void actionPerformed(ActionEvent e) { // do stuff of action 
		
		for (int i = 0; i < 1000; i++) { // make more windows (MAX = 999999999)   MAKE ALL BUTTONS YOU ARE IDIOT
			Random rand = new Random();
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setTitle("YOU ARE AN IDIOT");
			frame.setSize(500, 500);
			frame.setContentPane(new Idiot());
			frame.setLocation(rand.nextInt(1500), rand.nextInt(550)); // 1 to 1500 for x axsis           1 to  550  for y axsis
			frame.setVisible(true);
}}}
