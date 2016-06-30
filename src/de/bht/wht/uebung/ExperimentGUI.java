package de.bht.wht.uebung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ExperimentGUI {

	public JFrame frame;
	private JTextField txtCounter;
	public Color colors[] = new Color[24];
	public String colorsRedGreenBlue[] = new String[24];
	public float[] randomBrightness= new float[24];
	public float[] myBrightness= new float[24];
	int counter = 0;
	public Experiment panel_1;
	public Experiment panel_2;
	public String user;
	
	//Set up animation parameters.
    static final int FPS_MIN = 0;
    static final int FPS_MAX = 100;
    static final int FPS_INIT = 10;

	/**
	 * Create the application.
	 */
	public ExperimentGUI(String user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("WHTSS16 Uebung/Experiment");
		frame.setBounds(100, 100, 816, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Random rand = new Random();
		
		for(int i = 0; i < colors.length; i++) {
			
			if(i < 8) {

				float myRandomBrightness = rand.nextFloat();
		        String s =  String.format("%.2f", myRandomBrightness);
		        s = s.replace(",", ".");
		        myRandomBrightness = Float.parseFloat(s);
				float[] hsb = Color.RGBtoHSB(255, 0, 0, null);
			    float brightness =  Math.min(myRandomBrightness, Math.max(0.0f, hsb[0] + 1f));
			    Color retVal = new Color(Color.HSBtoRGB(hsb[0], hsb[1], brightness));
				colors[i] = retVal;
				colorsRedGreenBlue[i] = "RED";
				randomBrightness[i] = brightness;
				
			} else if(i < 16) {
				
				float myRandomBrightness = rand.nextFloat() ;
		        String s =  String.format("%.2f", myRandomBrightness);
		        s = s.replace(",", ".");
		        myRandomBrightness = Float.parseFloat(s);
				float[] hsb = Color.RGBtoHSB(0, 255, 0, null);
			    float brightness =  Math.min(myRandomBrightness, Math.max(0.0f, hsb[0] + 1f));
			    Color retVal = new Color(Color.HSBtoRGB(hsb[0], hsb[1], brightness));
			    colors[i] = retVal;
			    colorsRedGreenBlue[i] = "GREEN";
			    randomBrightness[i] = brightness;
			    
			} else {
				
				float myRandomBrightness = rand.nextFloat();
		        String s =  String.format("%.2f", myRandomBrightness);
		        s = s.replace(",", ".");
		        myRandomBrightness = Float.parseFloat(s);
				float[] hsb = Color.RGBtoHSB(0, 0, 255, null);
			    float brightness =  Math.min(myRandomBrightness, Math.max(0.0f, hsb[0] + 1f));
			    Color retVal = new Color(Color.HSBtoRGB(hsb[0], hsb[1], brightness));
				colors[i] = retVal;
				colorsRedGreenBlue[i] = "BLUE";
				randomBrightness[i] = brightness;
				
			}
			
		}
		
		// Farbfeld 1
	    panel_2 = new Experiment(colors[0]);
		panel_2.setBounds(18, 18, 366, 321);
		frame.getContentPane().add(panel_2);
		
		// Farbfeld2
	    panel_1 = new Experiment(Color.red);
		panel_1.setBounds(419, 18, 366, 321);
		frame.getContentPane().add(panel_1);
		
		txtCounter = new JTextField();
		txtCounter.setText((counter + 1) + " / " + colors.length);
		txtCounter.setEditable(false);
		txtCounter.setBounds(0, 357, 64, 37);
		frame.getContentPane().add(txtCounter);
		txtCounter.setColumns(10);
		
		JButton btnNewButton = new JButton("Weiter");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if((counter + 1) < colors.length) {
					
					// bis 8 ist die Farbe Rot, bis 16 ist die Farbe Grün,und ab 16 Blau.
			        if(counter < 8) {
			        	panel_1.setColor(Color.red);
				        panel_1.repaint();
			        } else if(counter < 16) {
			        	panel_1.setColor(Color.green);
				        panel_1.repaint();
			        } else {
			        	panel_1.setColor(Color.blue);
				        panel_1.repaint();
			        }
			        
					panel_2.setColor(colors[counter++]);
					txtCounter.setText((counter + 1) + " / " + colors.length);
			        panel_2.repaint();
			        
				} else {
					
					JOptionPane.showMessageDialog(null, "Vielen Dank f\u00FCr die Zusammenarbeit", "Meldung", JOptionPane.INFORMATION_MESSAGE);
					CSVWriter file = new CSVWriter();
					file.writeCsvFile(user, user, getColor(), getRandomBrightness(), getmyBrightness());
					System.exit(0);
					
				}
				
			}
			
		});
		
		btnNewButton.setBounds(725, 357, 75, 37);
		frame.getContentPane().add(btnNewButton);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL, FPS_MIN, FPS_MAX, FPS_INIT);
		slider.setBounds(62, 357, 665, 37);
		frame.getContentPane().add(slider);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		slider.addChangeListener(new JSliderChangeListener());
	}
	
	public String[] getColor() {
		return colorsRedGreenBlue;
	}
	
	public float[] getRandomBrightness() {
		return randomBrightness;
	}
	
	public float[] getmyBrightness() {
		return this.myBrightness;
	}
	
	public void setmyBrightness(float brightness, int counter) {
		this.myBrightness[counter] = brightness;
	}
	
	class JSliderChangeListener implements ChangeListener {
		
		  @Override
		  public void stateChanged(ChangeEvent changeEvent) {
			
			Object source = changeEvent.getSource();
			
			if (source instanceof JSlider) {
				
				JSlider theJSlider = (JSlider) source;
				  
				System.out.println("Slider changed: " + (float) theJSlider.getValue()/100);
				System.out.println("Random brightness: " + getRandomBrightness()[0]);
				float[] hsb = Color.RGBtoHSB(255, 0, 0, null);
				
				if(counter <= 8) {
					
					hsb = Color.RGBtoHSB(255, 0, 0, null);
					
				} else if(counter <= 16) {
					
					hsb = Color.RGBtoHSB(0, 255, 0, null);
					
				} else {
					
					hsb = Color.RGBtoHSB(0, 0, 255, null);
					
				}
				
				// get value of slider
				float brightness =  Math.min((float) theJSlider.getValue() / 100, Math.max(0.0f, hsb[0] + 1f));
				
				setmyBrightness(brightness, counter);
				Color retVal = new Color(Color.HSBtoRGB(hsb[0], hsb[1], brightness));
				panel_1.setColor(retVal);
				panel_1.repaint();
				
			}
		}
	}
}
