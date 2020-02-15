package view;

import static model.WeatherProperties.PROPERTY_WINDDIR;
import static model.WeatherProperties.PROPERTY_WINDSPD;
import static model.WeatherProperties.PROPERTY_OUT_TEMP;
import model.Weather;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Displays the current, average, high wind, forecast, windchill, and heat index values.
 * 
 * @author Ryan Donohue
 */
public class WindPanel extends JPanel implements PropertyChangeListener {
	
	/**
	 *A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html 
	 */
	private static final long serialVersionUID = 720298670523283096L;
	
	private String[] myHeadings = {"N", "NNE", "NE", "ENE", "E", "ESE",  "SE",  "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
	private String[] myForecasts = {"Foggy", "Rainy", "Cloudy", "Sunny", "Windy"};
	private double myHighWind = 0.0;
	private double myCurWind = 6.0;
	private int myWindShift;
	private JLabel myWindSpd;
	private JLabel myAvgWindSpd;
	private JLabel myWindDir;
	private JLabel myHighWindSpd;
	private JLabel myWindchillLabel;
	private JLabel myHeatindexLabel;
	private JLabel myWeatherPrediction;
	private Random myRandom;
	
	public WindPanel(JFrame theFrame) {
		super();
		this.setBounds(12, 114, 324, 143);
		theFrame.getContentPane().add(this);
		this.setLayout(null);
		myRandom = new Random();

		JLabel windLabel = new JLabel("Wind Speed");
		windLabel.setBounds(75, 8, 81, 16);
		this.add(windLabel);
		windLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		JLabel currwindLabel = new JLabel("current");
		currwindLabel.setBounds(12, 32, 48, 14);
		this.add(currwindLabel);
		currwindLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		myWindSpd = new JLabel("windspd");
		myWindSpd.setBounds(22, 50, 89, 28);
		this.add(myWindSpd);
		myWindSpd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		myWindSpd.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel averageWindLabel = new JLabel("average");
		averageWindLabel.setBounds(12, 92, 48, 14);
		this.add(averageWindLabel);
		averageWindLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		myAvgWindSpd = new JLabel("windspd");
		myAvgWindSpd.setBounds(22, 108, 89, 28);
		this.add(myAvgWindSpd);
		myAvgWindSpd.setHorizontalAlignment(SwingConstants.CENTER);
		myAvgWindSpd.setFont(new Font("Tahoma", Font.PLAIN, 16));

		myWindDir = new JLabel("winddir");
		myWindDir.setBounds(123, 50, 89, 28);
		this.add(myWindDir);
		myWindDir.setHorizontalAlignment(SwingConstants.CENTER);
		myWindDir.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel hiWindLabel = new JLabel("high");
		hiWindLabel.setBounds(123, 92, 48, 14);
		this.add(hiWindLabel);
		hiWindLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		myHighWindSpd = new JLabel("windspd");
		myHighWindSpd.setBounds(123, 108, 89, 28);
		this.add(myHighWindSpd);
		myHighWindSpd.setHorizontalAlignment(SwingConstants.CENTER);
		myHighWindSpd.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel expectedWeather = new JLabel("Weather Today");
		expectedWeather.setBounds(224, 8, 89, 16);
		this.add(expectedWeather);
		expectedWeather.setHorizontalAlignment(SwingConstants.CENTER);
		expectedWeather.setFont(new Font("Tahoma", Font.BOLD, 11));

		myWeatherPrediction = new JLabel(myForecasts[myRandom.nextInt(myForecasts.length - 1)]);
		myWeatherPrediction.setBounds(224, 31, 89, 17);
		this.add(myWeatherPrediction);
		myWeatherPrediction.setFont(new Font("Tahoma", Font.PLAIN, 12));
		myWeatherPrediction.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel chillLabel = new JLabel("chill");
		chillLabel.setBounds(209, 50, 48, 14);
		this.add(chillLabel);
		chillLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chillLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		myWindchillLabel = new JLabel("44.6F");
		myWindchillLabel.setBounds(223, 66, 89, 28);
		this.add(myWindchillLabel);
		myWindchillLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myWindchillLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel heatLabel = new JLabel("heat index");
		heatLabel.setBounds(212, 92, 89, 15);
		this.add(heatLabel);
		heatLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heatLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		myHeatindexLabel = new JLabel("60.3 HI");
		myHeatindexLabel.setBounds(233, 112, 68, 20);
		this.add(myHeatindexLabel);
		myHeatindexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myHeatindexLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
	
	/**
	 * Helper method to set wind speed
	 * 
	 * @param value new wind value
	 */
	protected void setWindSpd(double theValue) {
		myCurWind += theValue;
		if (myCurWind > myHighWind) {
			myHighWind = myCurWind;
		}
		if (myCurWind > 0) {
			myWindSpd.setText(Weather.formatValue(myCurWind) + " MPH");
		}
		myHighWindSpd.setText(Weather.formatValue(myHighWind) + " MPH");
		myAvgWindSpd.setText(Weather.formatValue(myHighWind / 2) + " MPH");
	}
	
	/**
	 * Helper method to set wind direction.
	 */
	protected void setWindDir() {
		myWindShift += (myRandom.nextInt(3) - 1);
		if (myWindShift < 0) {
			myWindShift = myHeadings.length - 1;
		} else if (myWindShift > myHeadings.length - 1) {
			myWindShift = 0;
		}
		myWindDir.setText(myHeadings[myWindShift]);
	}
	
	/**
	 * Calculates the wind chill.
	 * 
	 * Utilizes the formula provided from:
	 * https://www.mentalfloss.com/article/26730/how-wind-chill-calculated
	 * 
	 * @param theWindSpeed the wind speed.
	 * @param theOutsideTemp the outside temperature.
	 */
	private void calculateWindChill(double theWindSpeed, double theOutsideTemp) {
        double windChill = (0.6215*theOutsideTemp) + 35.74 + (0.4275*theOutsideTemp - 35.75) * Math.pow(theWindSpeed, 0.16);
        myWindchillLabel.setText(Weather.formatValue(windChill) + "F");
	}

	public double getMyHighWind() {
		return myHighWind;
	}

	public double getMyCurWind() {
		return myCurWind;
	}

	public int getMyWindShift() {
		return myWindShift;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		double value = (double) theEvent.getNewValue();
		if (theEvent.getPropertyName().equals(PROPERTY_WINDSPD)) {
			setWindSpd(value);
		} else if (theEvent.getPropertyName().equals(PROPERTY_WINDDIR)) {
			setWindDir();
		} else if (theEvent.getPropertyName().equals(PROPERTY_OUT_TEMP)) {
			calculateWindChill(myCurWind , (double) theEvent.getNewValue());
		}
		
	}

}
