import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.util.Random;
// make the tick work on a different thread
public  class StopWatch extends JPanel implements ActionListener{
	//attributes
	
	/*** */private static final long serialVersionUID = 1L;
	
	
	JButton start;
	JButton stop;
	JButton reset;
	JButton lap;
	JButton blank;
	JButton idiot;
	JButton cake;
	JButton secondGuess;
	JButton secondBlank;
	
	JLabel jClock;
	JLabel title;
	
	JPanel topPanel;
	JPanel bottomPanel;
	
	JSplitPane splitPane = new JSplitPane();
	
	JTextField userText = new JTextField(20);
	
	Timer tick;

	Clock daved = new Clock();
	
	int oldHours = 0; 
	int oldMinutes = 0;
	int oldSeconds = 0;
	int newHours = 0;
	int newMinutes = 0;
	int newSeconds = 0; 
	int playerTime = 0; 
	int originalHours = 0;
	int originalMinutes = 0;
	int originalSeconds = 0;
	
	boolean pressed = false;
	boolean red = false;
	boolean finished = true;
	boolean done = true;
	boolean idiotFlag = true; // true to enable idiot mode
	
	public StopWatch(int h, int m , int s, String Toptitle){	
		
		daved.setTime(h , m, s);
		
		originalHours = h;
		originalMinutes = m;
		originalSeconds = s;
		
		// defining clock and setting it with a font and where it is 		
		jClock = new JLabel(daved.getTime());
		jClock.setFont(new Font("Serif", Font.PLAIN, 50));
		jClock.setHorizontalAlignment(JLabel.CENTER); // MAKE IT CENTER
		
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout()); 
		topPanel.add(jClock);
		
		// makeing the bottom layout
		bottomPanel = new JPanel();
		
		bottomPanel.setLayout(new GridLayout(2,4, 10, 10)); 
				
		// setting colour of backround
		this.setBackground(new Color(255, 255, 255));
		this.setLayout(new BorderLayout());
		
		// declaring the buttons
		start = new JButton("start");
		reset = new JButton("reset");
		stop = new JButton("stop");
		cake = new JButton();
		idiot = new JButton("YOU ARE AN IDIOT");	
		lap = new JButton("lap");
		blank = new  JButton();
		secondGuess = new JButton ("press to guess");
		secondBlank = new  JButton();
		
		// makeing the buttons diffrent colours
		start.setBackground(Color.GREEN);
		reset.setBackground(Color.PINK);
		stop.setBackground(Color.RED);
		idiot.setBackground(Color.WHITE);
		cake.setBackground(new Color(237, 237, 237));
		lap.setBackground(Color.ORANGE);
		blank.setBackground(new Color(237, 237, 237)); // background colour
		secondGuess.setBackground(Color.magenta);
		secondBlank.setBackground(new Color(237, 237, 237)); // background colour
		
		// add buttons to bottom panel
		bottomPanel.add(start );
		bottomPanel.add(reset);
		bottomPanel.add(stop);
		
		if (idiotFlag) {
			bottomPanel.add(idiot );
			bottomPanel.add(cake);
			bottomPanel.add(lap);
			bottomPanel.add(blank);
			bottomPanel.add(secondGuess);
			bottomPanel.add(secondBlank);
			
			}
		
		else {
			
			title = new JLabel(Toptitle);
			title.setFont(new Font("Serif", Font.PLAIN, 50));
			title.setHorizontalAlignment(JLabel.CENTER);
			bottomPanel.add(title);
			}
		

		
		
		//make them have no border
		Border emptyBorder = BorderFactory.createEmptyBorder();
		start.setBorder(emptyBorder);
		reset.setBorder(emptyBorder);
		stop.setBorder(emptyBorder);
		idiot.setBorder(emptyBorder);
		cake.setBorder(emptyBorder);
		lap.setBorder(emptyBorder);
		blank.setBorder(emptyBorder);
		secondGuess.setBorder(emptyBorder);
		secondBlank.setBorder(emptyBorder);
		
		//make them not have the box around them when clicked
		start.setFocusPainted(false);
		reset.setFocusPainted(false);
		stop.setFocusPainted(false);
		idiot.setFocusPainted(false);
		cake.setFocusPainted(false);
		lap.setFocusPainted(false);
		blank.setFocusPainted(false);
		secondGuess.setFocusPainted(false);
		secondBlank.setFocusPainted(false);
		
		// see when button is pressed
        start.addActionListener(this);
        reset.addActionListener(this);
        stop.addActionListener(this);
        idiot.addActionListener(this);
        cake.addActionListener(this);
        lap.addActionListener(this);
        blank.addActionListener(this);
        secondGuess.addActionListener(this);
        
        // make panel to put on screen
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  
		splitPane.setDividerLocation(150);   
		splitPane.setTopComponent(topPanel);                  
        splitPane.setBottomComponent(bottomPanel); 
		this.add(splitPane);
				
		tick = new Timer (1000, this);
	}


	public void actionPerformed(ActionEvent z) { // do stuff of action 

		if(daved.hours() == 0 && daved.minutes() == 0 && daved.seconds() == 0){
			
			if (red) {
				topPanel.setBackground(Color.WHITE);
				red = false;
			} else { 
				topPanel.setBackground(Color.RED);
				red = true;
			}
			
			daved.increment(0);
			finished = false;
	}
		
		if (z.getSource() == tick) {
			if (done) {
			done = false;
			}
			
			if (finished) {
			daved.increment(-1);
			}
			
			jClock.setText(daved.getTime());     
			
			if (pressed) {
				playerTime += 1;
				jClock.setText("wait 10 seconds");
			}
			

			}
		
		if (z.getSource() == start) {tick.start(); } // make a while loop dependent on the started variable 
		if (z.getSource() == reset) {daved.setTime(originalHours, originalMinutes, originalSeconds);} 
		if (z.getSource() == stop)  {tick.stop(); }  // stops clock
		
		if (z.getSource() == cake) {
			Image myPicture = null;
			try {
				myPicture = ImageIO.read(new File("cake.jpg"));
				myPicture = myPicture.getScaledInstance(175, 150, Image.SCALE_DEFAULT);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			cake.add(picLabel, BorderLayout.CENTER); 
		}
		
		if (z.getSource() == lap) {

				newHours = daved.hours();
				newMinutes = daved.minutes();
				newSeconds = daved.seconds();
				
				daved.setTime((newHours - oldHours), (newMinutes-oldMinutes), (newSeconds-oldSeconds));
				blank.setText(daved.getTime());
				
				oldHours = newHours;
				oldMinutes = newMinutes;
				oldSeconds = newSeconds;
				
				daved.setTime(newHours , newMinutes, newSeconds);
			}
		
		
		if (z.getSource() == secondGuess) {
			if (pressed) {
				pressed = false;
				secondBlank.setText(Integer.toString((10 - playerTime))+ "  out");
				playerTime = 0;
			}
			else {
				pressed = true; 
				}

		}
		
		if (z.getSource() == idiot) {
			for (int i = 0; i < 1000; i++) { // make more windows (MAX = 999999999)   MAKE ALL BUTTONS YOU ARE IDIOT
				Random rand = new Random();
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setTitle("YOU ARE AN IDIOT");
				frame.setSize(500, 500);
				frame.setContentPane(new Idiot());
				frame.setLocation(rand.nextInt(1500), rand.nextInt(550)); // 1 to 1500 for x axsis           1 to  550  for y axsis
				frame.setVisible(true);
				}
			}
	}
}

