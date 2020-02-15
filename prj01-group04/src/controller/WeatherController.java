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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;

import view.*;
import model.Weather;

/**
 * A controller for the Weather model.
 * 
 * @author Ryan Donohue
 * @author Chinenye Stella Ezenwoye
 * @author Sukhraj Kaur
 * @author Minh Nguyen
 * @author Ahmed A Hassan
 */
public class WeatherController {
	
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
		myTempPanel = new TempPanel(myFrame);
		myWindPanel = new WindPanel(myFrame);
		myRainPanel = new RainPanel(myFrame);
		
		myFrame.setBounds(100, 100, 606, 456);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.getContentPane().setLayout(null);
		
		createButtons(myFrame);	
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 0, 1, 2);
		myFrame.getContentPane().add(separator);

		myFrame.getContentPane().add(myTimePanel);
		myFrame.getContentPane().add(myTempPanel);
		myFrame.getContentPane().add(myWindPanel);
		myFrame.getContentPane().add(myRainPanel);
		
		//Can't get the Graph picture to show.
		
		JLabel graphPanel = new JLabel();
		graphPanel.setIcon(new ImageIcon("/Weather Station2/images/line_graph_small.gif"));
		graphPanel.setBounds(49, 269, 233, 140);
		myFrame.getContentPane().add(graphPanel);
		
		myWeather.addPropertyChangeListener(myTempPanel);
		myWeather.addPropertyChangeListener(myWindPanel);
		myWeather.addPropertyChangeListener(myRainPanel);
		
		myFrame.setVisible(true);
		myFrame.setResizable(false);		
		timerStart();
	}
	
	
	/**
	 * Creates and attaches buttons and their associated actions.
	 * 
	 * @param myFrame the frame.
	 */
	private void createButtons(JFrame myFrame) {
		JButton tempButton = new JButton("TEMP");
		tempButton.addActionListener(theEvent -> {
			myWeather.updateValue(PROPERTY_IN_TEMP);
			myWeather.updateValue(PROPERTY_OUT_TEMP);
		});
		tempButton.setBounds(370, 318, 89, 23);
		myFrame.getContentPane().add(tempButton);
		
		JButton humidityButton = new JButton("HUM");
		humidityButton.addActionListener(theEvent -> {
			myWeather.updateValue(PROPERTY_IN_HUM);
			myWeather.updateValue(PROPERTY_OUT_HUM);
		});
		humidityButton.setBounds(370, 352, 89, 23);
		myFrame.getContentPane().add(humidityButton);
		
		JButton windButton = new JButton("WIND");
		windButton.addActionListener(theEvent -> {
			myWeather.updateValue(PROPERTY_WINDSPD);
			myWeather.updateValue(PROPERTY_WINDDIR);
		});
		windButton.setBounds(370, 386, 89, 23);
		myFrame.getContentPane().add(windButton);
		
		JButton rainButton = new JButton("RAIN");
		rainButton.addActionListener(theEvent -> {
			myWeather.updateValue(PROPERTY_RAIN);
		});
		rainButton.setBounds(491, 318, 89, 23);
		myFrame.getContentPane().add(rainButton);
		
		JButton barometerButton = new JButton("BAR");
		barometerButton.addActionListener(theEvent -> {
			myWeather.updateValue(PROPERTY_BAR);
		});
		barometerButton.setBounds(491, 352, 89, 23);
		myFrame.getContentPane().add(barometerButton);
		
		JButton alarmButton = new JButton("ALARM");
		alarmButton.setBounds(491, 386, 89, 23);
		myFrame.getContentPane().add(alarmButton);
		alarmButton.addActionListener(e -> {
			SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final JFrame timeViewFrame = new JFrame("Alarm Setting");
				timeViewFrame.pack();
				timeViewFrame.setVisible(true);
				timeViewFrame.setBounds(100, 100, 325, 200);

				JTextField textField = new JTextField();
				JLabel lblNewLabel = new JLabel("Set Alarm");
				textField = new JTextField();
				textField.setColumns(10);
				JRadioButton rainRadioButton = new JRadioButton("Rainfall");
				JRadioButton windRadioButton = new JRadioButton("Wind Speed");
				JRadioButton tempRadioButton = new JRadioButton("Temperature");
				JButton okButton = new JButton("Ok");
				okButton.addActionListener(theEvent -> {
					timeViewFrame.dispose();
				});
				GroupLayout gl_contentPanel = new GroupLayout(timeViewFrame);
				gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
											  .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
											  .addContainerGap().addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
											  .addGroup(gl_contentPanel.createSequentialGroup().addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											  .addGap(68).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(windRadioButton).addComponent(rainRadioButton)
											  .addComponent(tempRadioButton))))).addGroup(gl_contentPanel.createSequentialGroup().addGap(95).addComponent(okButton))).addContainerGap(141, Short.MAX_VALUE)));
				gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(rainRadioButton))
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(windRadioButton).addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tempRadioButton).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(okButton).addContainerGap(89, Short.MAX_VALUE)));
				timeViewFrame.setLayout(gl_contentPanel);
				}
			});
		});
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
	
}