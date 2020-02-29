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
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JSeparator;
import view.*;
import model.Weather;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

/**
 * A controller for the Weather model.
 * 
 * @author Group 3
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
	 * Panel that shows the current wind direction.
	 */
	private WindDirectionPanel myWindDirectionPanel;
	
	/**
	 * Panel that shows the graph display of a sensor.
	 */
	private GraphPanel myGraphPanel;
	
	/**
	 * Panel that holds all the buttons;
	 */
	private ButtonPanel myButtonPanel;
	
	/**
	 * Panel that displays the moonphase
	 */
	private MoonPanel myMoonPanel;
	
	/**
	 * Frame that houses all the weather panels.
	 */
	private JFrame myFrame;
	
	/**
	 * Model object that defines property changes and their behaviors.
	 */
	private Weather myWeather;

	/**
	 * Company logo.
	 */
	private JLabel DavisLogo;
	
	/**
	 * Frame for entering Alarm numbers.
	 */
	private AlarmFrame myAlarmGUI;
	

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
		myFrame.getContentPane().setLayout(null);
//		DavisLogo = new JLabel("New label");
//		DavisLogo.setBounds(10, 26, 239, 92);
//		DavisLogo.setForeground(Color.WHITE);
//		DavisLogo.setBackground(Color.WHITE);
//		DavisLogo.setIcon(new ImageIcon(getClass().getResource("Davis.PNG")));
//		myFrame.getContentPane().add(DavisLogo);
//		myFrame.getContentPane().setBackground(Color.ORANGE);
//		myFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Davis company logo.PNG")));
		myWeather = new Weather();
		myTimePanel = new TimePanel(myFrame);
		myTimePanel.setBounds(240, 215, 332, 103);
		myTempPanel = new TempPanel(myFrame);
		myTempPanel.setBounds(10, 128, 220, 141);
		myRainPanel = new RainPanel(myFrame);
		myRainPanel.setBounds(10, 279, 220, 193);
		myMoonPanel = new MoonPanel();
		myMoonPanel.setBounds(594, 216, 158, 103);

		//////////////// {
		myFrame.setBounds(100, 100, 788, 800);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myGraphPanel = new GraphPanel(myFrame.getWidth(), (int) myFrame.getHeight(), myFrame);
		myGraphPanel.setBounds(20, 482, 732, 280);
		//////////////// }
		
		myButtonPanel = new ButtonPanel(myFrame);
		myButtonPanel.setBounds(240, 128, 505, 77);
		addActionListeners(myFrame);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 1, 2);
		myFrame.getContentPane().add(separator);
		
		myAlarmGUI = new AlarmFrame();
		myAlarmGUI.getOkButton().addActionListener(this);
		myAlarmGUI.setVisible(false);

		myFrame.getContentPane().add(myTimePanel);
		myFrame.getContentPane().add(myTempPanel);
		myFrame.getContentPane().add(myRainPanel);
		myFrame.getContentPane().add(myButtonPanel);
		myFrame.getContentPane().add(myMoonPanel);
		myFrame.getContentPane().add(myGraphPanel);
		//
		
		myWeather.addPropertyChangeListener(myTempPanel);
		myWeather.addPropertyChangeListener(myRainPanel);
		//////////////// {
		
		//////////////// {
		myWindDirectionPanel = new WindDirectionPanel(myFrame.getHeight(), myFrame);
		myWindDirectionPanel.setBounds(240, 328, 158, 144);
		myFrame.getContentPane().add(myWindDirectionPanel);
		myWindPanel = new WindPanel(myFrame);
		myWindPanel.setBounds(418, 329, 332, 143);
		myFrame.getContentPane().add(myWindPanel);
		myWeather.addPropertyChangeListener(myWindPanel);
		
		JLabel lblNewLabel = new JLabel("Vantage Vue Console");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 31));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(331, 44, 357, 40);
		myFrame.getContentPane().add(lblNewLabel);
		
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
	 * 
	 * Every 60 seconds interior humidity and temperature is updated, every 50 seconds the 
	 * outside humidity and the wind direction updates, every 10 seconds the barometric pressure
	 * trend and outside temperature updates, every 2.5 seconds the wind speed updates, and finally
	 * every 2 seconds the graph data updates.
	 */
	private void timerStart() {
		Timer sixtyTimer = new Timer();
		Timer fiftyTimer = new Timer();
		Timer tenTimer = new Timer();
		Timer twoFiveTimer = new Timer();
		////////////{
		Timer twoSecondTimer = new Timer();
		////////////}
		
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
				myWindDirectionPanel.updateWindDirection(myWindPanel.getMyWindDir());
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
				myWindDirectionPanel.updateWindSpeed(myWindPanel.getMyWindSpd());
			}
		};
		twoFiveTimer.schedule(twoFiveTask, 0, 2500);	
		
		//////////// {
		TimerTask twoSecondTask = new TimerTask() {
			public void run() {
				myGraphPanel.updateSensorValue(GraphPanel.TEMP_SENSOR, (int) (myTempPanel.getOutsideTemp()*10));
				myGraphPanel.updateSensorValue(GraphPanel.HUMIDITY_SENSOR, (int) (myTempPanel.getOutsideHumid()*10));
//				myGraphPanel.updateSensorValue(GraphPanel.PRESSURE_SENSOR, myTempPanel.getOutsideHumid());
				myGraphPanel.updateSensorValue(GraphPanel.RAINFALL_SENSOR, (int) (myRainPanel.getMyRainRate()*10));
				myGraphPanel.updateSensorValue(GraphPanel.WIND_SENSOR, (int) (myWindPanel.getMyWindSpdValue()*10));
				Date date = new Date();
				myGraphPanel.updateTimeRecords(date.toString().substring(11, 19));
				myGraphPanel.updateDisplay();

			}
		};
		twoSecondTimer.schedule(twoSecondTask, 1000, 2000);
		//////////// }
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
			myGraphPanel.setSensorType(GraphPanel.TEMP_SENSOR);
			myWeather.updateValue(PROPERTY_IN_TEMP);
			myWeather.updateValue(PROPERTY_OUT_TEMP);
		} else if (name.equalsIgnoreCase("hum")) {
			myGraphPanel.setSensorType(GraphPanel.HUMIDITY_SENSOR);
			myWeather.updateValue(PROPERTY_IN_HUM);
			myWeather.updateValue(PROPERTY_OUT_HUM);
		} else if (name.equalsIgnoreCase("rain")) {
			myGraphPanel.setSensorType(GraphPanel.RAINFALL_SENSOR);
			myWeather.updateValue(PROPERTY_RAIN);
		} else if (name.equalsIgnoreCase("wind")) {
			myGraphPanel.setSensorType(GraphPanel.WIND_SENSOR);
			myWeather.updateValue(PROPERTY_WINDSPD);
		} else if (name.equalsIgnoreCase("bar")) {
			myWeather.updateValue(PROPERTY_BAR);
		} else if (name.equalsIgnoreCase("alarm")) {
			myAlarmGUI.setVisible(true);
		} else if (name.equalsIgnoreCase("ok")) {
			myAlarmGUI.setVisible(false);
		}
			
	}	
}
