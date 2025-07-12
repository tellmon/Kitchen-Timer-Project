import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class ImageStopWatchMaker extends JPanel implements ActionListener{
	
	int hou = 0;
	int min = 0;
	int sec = 0; 
	
	// JTextField
	 JTextField name;
	 JTextField hours;
	 JTextField minutes;
	 JTextField seconds;
	 
	 JLabel blank;
	 JLabel nameName; 
	 JLabel nameHours; 
	 JLabel blank1; 
	 JLabel nameMinutes; 
	 JLabel nameSeconds; 

	// JButton
	  JButton submitButton;
	  
	  String error = "      Errors: ";


	
    public ImageStopWatchMaker()  {

                try {
                    BackgroundPane background = new BackgroundPane();
                    background.setBackground(ImageIO.read(new File("kitchen.jpg")));

                    JFrame frame = new JFrame("Kitchen Timer Maker");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setContentPane(background);
                     		
            		name = new JTextField(8);
            		hours = new JTextField(8);
            		minutes = new JTextField(8);
            		seconds = new JTextField(8);

            		Border emptyBorder = BorderFactory.createEmptyBorder();
            		
            		submitButton = new JButton("submit"); 
            		submitButton.setBackground(Color.WHITE);
            		
            		blank = new JLabel("              ");
            		nameName = new JLabel("  Title  ");
            		nameHours = new JLabel("  Hours  ");
            		blank1 = new JLabel("                                         ");
            		nameMinutes = new JLabel("  Minutes  ");
            		nameSeconds = new JLabel("  Seconds  ");
            		
            		nameName.setOpaque(true);
            		nameHours.setOpaque(true);
            		nameMinutes.setOpaque(true);
            		nameSeconds.setOpaque(true);
            		
            		nameName.setBackground(Color.WHITE);
            		nameHours.setBackground(Color.WHITE);
            		nameMinutes.setBackground(Color.WHITE);
            		nameSeconds.setBackground(Color.WHITE);
            		
            		
            		// add buttons and textfield to panel
            		frame.add(blank);
            		frame.add(nameName);
            		frame.add(name);
            		frame.add(nameHours);
            		frame.add(hours);
            		frame.add(blank1);
            		frame.add(nameMinutes);
            		frame.add(minutes);
            		frame.add(nameSeconds);
            		frame.add(seconds);
            		frame.add(submitButton);

            		submitButton.setBorder(emptyBorder);
            		submitButton.setFocusable(false);
            		
            		submitButton.addActionListener(this);
            		
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    
                } catch (IOException exp) {
                    exp.printStackTrace();
                }
            }



    public class BackgroundPane extends JPanel {

        private BufferedImage img;
        private BufferedImage scaled;

        public BackgroundPane() {
        }

        @Override
        public Dimension getPreferredSize() {
            return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(), img.getHeight());
        }

        public void setBackground(BufferedImage value) {
            if (value != img) {
                this.img = value;
                repaint();
            }
        }

        @Override
        public void invalidate() {
            super.invalidate();
            if (getWidth() > img.getWidth() || getHeight() > img.getHeight()) {
                scaled = getScaledInstanceToFill(img, getSize());
            } else {
                scaled = img;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (scaled != null) {
                int x = (getWidth() - scaled.getWidth()) / 2;
                int y = (getHeight() - scaled.getHeight()) / 2;
                g.drawImage(scaled, x, y, this);
            }
        }

    }

    public static BufferedImage getScaledInstanceToFill(BufferedImage img, Dimension size) {

        double scaleFactor = getScaleFactorToFill(img, size);

        return getScaledInstance(img, scaleFactor);

    }

    public static double getScaleFactorToFill(BufferedImage img, Dimension size) {

        double dScale = 1;

        if (img != null) {

            int imageWidth = img.getWidth();
            int imageHeight = img.getHeight();

            double dScaleWidth = getScaleFactor(imageWidth, size.width);
            double dScaleHeight = getScaleFactor(imageHeight, size.height);

            dScale = Math.max(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = (double) iTargetSize / (double) iMasterSize;

        return dScale;

    }

    public static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor) {

        return getScaledInstance(img, dScaleFactor, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);

    }

    protected static BufferedImage getScaledInstance(BufferedImage img, double dScaleFactor, Object hint, boolean bHighQuality) {

        BufferedImage imgScale = img;

        int iImageWidth = (int) Math.round(img.getWidth() * dScaleFactor);
        int iImageHeight = (int) Math.round(img.getHeight() * dScaleFactor);

//        System.out.println("Scale Size = " + iImageWidth + "x" + iImageHeight);
        if (dScaleFactor <= 1.0d) {

            imgScale = getScaledDownInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        } else {

            imgScale = getScaledUpInstance(img, iImageWidth, iImageHeight, hint, bHighQuality);

        }

        return imgScale;

    }

    protected static BufferedImage getScaledDownInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {

        int type = (img.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        if (targetHeight > 0 || targetWidth > 0) {
            int w, h;
            if (higherQuality) {
                // Use multi-step technique: start with original size, then
                // scale down in multiple passes with drawImage()
                // until the target size is reached
                w = img.getWidth();
                h = img.getHeight();
            } else {
                // Use one-step technique: scale directly from original
                // size to target size with a single drawImage() call
                w = targetWidth;
                h = targetHeight;
            }

            do {
                if (higherQuality && w > targetWidth) {
                    w /= 2;
                    if (w < targetWidth) {
                        w = targetWidth;
                    }
                }

                if (higherQuality && h > targetHeight) {
                    h /= 2;
                    if (h < targetHeight) {
                        h = targetHeight;
                    }
                }

                BufferedImage tmp = new BufferedImage(Math.max(w, 1), Math.max(h, 1), type);
                Graphics2D g2 = tmp.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
                g2.drawImage(ret, 0, 0, w, h, null);
                g2.dispose();

                ret = tmp;
            } while (w != targetWidth || h != targetHeight);
        } else {
            ret = new BufferedImage(1, 1, type);
        }
        return ret;
    }

    protected static BufferedImage getScaledUpInstance(BufferedImage img,
            int targetWidth,
            int targetHeight,
            Object hint,
            boolean higherQuality) {

        int type = BufferedImage.TYPE_INT_ARGB;

        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w < targetWidth) {
                w *= 2;
                if (w > targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h < targetHeight) {
                h *= 2;
                if (h > targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
            tmp = null;

        } while (w != targetWidth || h != targetHeight);
        return ret;
    }	
    
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == submitButton) {
			try {
			
				if (sec >= 60) { 
		            min += sec / 60; 
		            sec = sec % 60;
		        }
		        
		        if (min >= 60) { 
		            hou += min / 60; 
		            min = min % 60; 
		        }
		        
				JFrame stopWatch = new JFrame();
				stopWatch.setTitle(name.getText()); 
				stopWatch.setSize(500, 500);
				stopWatch.setContentPane(new StopWatch(Integer.parseInt(hours.getText()), Integer.parseInt(minutes.getText()), Integer.parseInt(seconds.getText()), name.getText()));
				stopWatch.setLocationRelativeTo(null);
				stopWatch.setVisible(true);

			}
			
			catch(Exception f){
				
				try {
					hou = Integer.parseInt(hours.getText());
				}
				
				catch (Exception g) {
					hou = 0; 
					error += "  hours not inputted";
				}
				
				try {
					min = Integer.parseInt(minutes.getText());
				}
				
				catch (Exception h) {
					min = 0; 
					error += "   minutes not inputted";
				}
				
				try {
					sec = Integer.parseInt(seconds.getText());
				}
				
				catch (Exception i) {
					sec = 0; 
					error += "   seconds not inputted";
				}
				
				if ("Name".equals(name.getText())) {
					error += "        No name inputed";
					name.setText("");
				}
				

			        if (sec >= 60) { 
			            min += sec / 60; 
			            sec = sec % 60;
			        }
			        
			        if (min >= 60) { 
			            hou += min / 60; 
			            min = min % 60; 
			        }
			        
				
				
				
				JFrame stopWatch = new JFrame();
				stopWatch.setTitle(name.getText()); 
				stopWatch.setSize(500, 500);
				stopWatch.setContentPane(new StopWatch(hou, min, sec, name.getText())); 
				stopWatch.setLocationRelativeTo(null);
				stopWatch.setVisible(true);
				
				if ("Errors: ".equals(error)) {}
				
				else {
				new Errors("  "+error+ "  ");
				}
				
				error = "      Errors: ";
			}
			// set the text of field to blank
			name.setText(" ");
			hours.setText("");
			minutes.setText("");
			seconds.setText("");
			
		}
	}

}