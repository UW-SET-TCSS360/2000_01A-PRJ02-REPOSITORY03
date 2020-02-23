package controller;

import static model.WeatherProperties.PROPERTY_OUT_TEMP;
import static model.WeatherProperties.PROPERTY_IN_HUM;
import static model.WeatherProperties.PROPERTY_IN_TEMP;
import static model.WeatherProperties.PROPERTY_OUT_HUM;
import static model.WeatherProperties.PROPERTY_BAR;
import static model.WeatherProperties.PROPERTY_WINDDIR;
import static model.WeatherProperties.PROPERTY_WINDSPD;
import static model.WeatherProperties.PROPERTY_RAIN;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JSeparator;

import view.*;
import model.Weather;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A controller for the Weather model.
 * 
 * @author Ryan Donohue
 * @author Chinenye Stella Ezenwoye
 * @author Sukhraj Kaur
 * @author Minh Nguyen
 * @author Ahmed A Hassan
 */
public class WeatherController implements ActionListener {
	
	/**
	 * Panel that holds the time, date, high, low, moon phase, sunrise time, and sunset time values.
	 */
	private TimePanel myTimePanel;
	
	/**
	 * Panel that holds the inside temperature, outside temperature, inside humidity, outside humidity values.
	 */
	private TempPanel myTempPanel;
	
	/**
	 * Panel that holds current, average, high wind, forecast, windchill, and heat index values.
	 */
	private WindPanel myWindPanel;
	
	/**
	 * Panel that holds rain, barometer information.
	 */
	private RainPanel myRainPanel;
	
	/**
	 * Panel that holds all the buttons;
	 */
	private ButtonPanel myButtonPanel;
	
	/**
	 * Frame that houses all the weather panels.
	 */
	private JFrame myFrame;
	
	/**
	 * Model object that defines property changes and their behaviors.
	 */
	private Weather myWeather;

	/**
	 * Create the application.
	 */
	public WeatherController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		myFrame = new JFrame("Vantage Vue");
		myWeather = new Weather();
		myTimePanel = new TimePanel(myFrame);
		myTimePanel.setLocation(216, 10);
		myTempPanel = new TempPanel(myFrame);
		myTempPanel.setLocation(553, 10);
		myWindPanel = new WindPanel(myFrame);
		myWindPanel.setLocation(192, 124);
		myRainPanel = new RainPanel(myFrame);
		myRainPanel.setLocation(526, 124);
		myButtonPanel = new ButtonPanel(myFrame);
		
		myFrame.setBounds(100, 100, 760, 593);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		
		addActionListeners(myFrame);	
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 1, 2);
		myFrame.getContentPane().add(separator);

		myFrame.getContentPane().add(myTimePanel);
		myFrame.getContentPane().add(myTempPanel);
		myFrame.getContentPane().add(myWindPanel);
		myFrame.getContentPane().add(myRainPanel);
		myFrame.getContentPane().add(myButtonPanel);
		
		myWeather.addPropertyChangeListener(myTempPanel);
		myWeather.addPropertyChangeListener(myWindPanel);
		myWeather.addPropertyChangeListener(myRainPanel);
	
		myFrame.setVisible(true);
		myFrame.setResizable(false);		
		timerStart();
	}
	
	
	/**
	 * Helper method to attach action listeners to buttons from the ButtonPanel.
	 * 
	 * @param myFrame the frame.
	 */
	private void addActionListeners(JFrame theFrame) {
		for (JButton button : myButtonPanel.getButtons()) {
			button.addActionListener(this);
		}
	}

	/**
	 * Starts the timers that trigger property changes for the various values of each panel.
	 */
	private void timerStart() {
		Timer sixtyTimer = new Timer();
		Timer fiftyTimer = new Timer();
		Timer tenTimer = new Timer();
		Timer twoFiveTimer = new Timer();
		
		TimerTask sixtyTask = new TimerTask() {
			public void run() {
				myWeather.updateValue(PROPERTY_IN_HUM);
				myWeather.updateValue(PROPERTY_IN_TEMP);
			}
		};
		sixtyTimer.schedule(sixtyTask, 0, 60000);
		
		TimerTask fiftyTask = new TimerTask() {
			public void run() {
				myWeather.updateValue(PROPERTY_OUT_HUM);
				myWeather.updateValue(PROPERTY_WINDDIR);
			}
		};
		fiftyTimer.schedule(fiftyTask, 0, 50000);
		
		TimerTask tenTask = new TimerTask() {
			public void run() {
				myWeather.updateValue(PROPERTY_BAR);
				myWeather.updateValue(PROPERTY_OUT_TEMP);
			}
		};
		tenTimer.schedule(tenTask, 0, 10000);
		
		TimerTask twoFiveTask = new TimerTask() {
			public void run() {
				myWeather.updateValue(PROPERTY_WINDSPD);
			}
		};
		twoFiveTimer.schedule(twoFiveTask, 0, 2500);		
	}

	
	/**
	 * Will update the values of the button pressed.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent theEvent) {
		
		JButton button = (JButton) theEvent.getSource();
		String name = button.getText();
		
		if (name.equalsIgnoreCase("temp")) {
			myWeather.updateValue(PROPERTY_IN_TEMP);
			myWeather.updateValue(PROPERTY_OUT_TEMP);
		} else if (name.equalsIgnoreCase("hum")) {
			myWeather.updateValue(PROPERTY_IN_HUM);
			myWeather.updateValue(PROPERTY_OUT_HUM);
		} else if (name.equalsIgnoreCase("rain")) {
			myWeather.updateValue(PROPERTY_RAIN);
		} else if (name.equalsIgnoreCase("wind")) {
			myWeather.updateValue(PROPERTY_WINDSPD);
		} else if (name.equalsIgnoreCase("bar")) {
			myWeather.updateValue(PROPERTY_BAR);
		}
		
	}	
}