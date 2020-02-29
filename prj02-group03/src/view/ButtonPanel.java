package view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * Displays button that update values and switch the graph values.
 * 
 * @author Group 3
 */
public class ButtonPanel extends JPanel {
	
	/**
	 * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = -2869644701192452513L;
	
	/**
	 * A list of all the buttons on the main GUI.
	 */
	private ArrayList<JButton> myButtons = new ArrayList<>();

	/**
	 * Create the panel.
	 * @param myFrame 
	 */
	public ButtonPanel(JFrame theFrame) {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBounds(10, 10, 207, 111);
		setLayout(null);
		
		JButton tempButton = new JButton("TEMP");
		tempButton.setBounds(20, 11, 100, 23);
		this.add(tempButton);
		myButtons.add(tempButton);
		
		JButton humidityButton = new JButton("HUM");
		humidityButton.setBounds(80, 41, 100, 23);
		this.add(humidityButton);
		myButtons.add(humidityButton);
		
		JButton rainButton = new JButton("RAIN");
		rainButton.setBounds(260, 11, 100, 23);
		this.add(rainButton);
		myButtons.add(rainButton);
		
		JButton windButton = new JButton("WIND");
		windButton.setBounds(380, 11, 100, 23);
		this.add(windButton);
		myButtons.add(windButton);
		
		JButton barometerButton = new JButton("BAR");
		barometerButton.setBounds(310, 41, 100, 23);
		this.add(barometerButton);
		myButtons.add(barometerButton);
		
		JButton alarmButton = new JButton("ALARM");
		alarmButton.setBounds(140, 11, 100, 23);
		this.add(alarmButton);
		myButtons.add(alarmButton);
		
		theFrame.getContentPane().add(this);
	}
	
	public ArrayList<JButton> getButtons() {
		return myButtons;
	}

	

}
