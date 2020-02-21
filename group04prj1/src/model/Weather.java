package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Model that provides values for various labels.
 * 
 * @author Ryan Donohue
 *
 */
public class Weather implements WeatherProperties {
	
	/**
	 * Number generator for feasible values.
	 */
	private Random myRandom;
	
	/**
     * Manager for Property Change Listeners. 
     */
    private final PropertyChangeSupport myPcs;
    
    private static final int MAX_WEATHER_AMOUNT = 25;
    
    /**
     * Array holding 25 values for hourly rain.
     */
    private final double[] rainHours;
    
    /**
     * Array holding 25 values for daily rain.
     */
    private final double[] rainDays;
    
    /**
     * Array holding 25 values for montly rain.
     */
    private final double[] rainMonths;
    
    /**
     * Array holding 25 values for hourly humidity.
     */
    private final double[] humHours;
    
    /**
     * Array holding 25 values for daily humidity.
     */
    private final double[] humDays;
    
    /**
     * Array holding 25 values for monthly humidity.
     */
    private final double[] humMonths;
    
    /**
     * Array holding 25 values for hourly wind.
     */
    private final double[] windHours;
    
    /**
     * Array holding 25 values for daily wind.
     */
    private final double[] windDays;
    
    /**
     * Array holding 25 values for monthly wind.
     */
    private final double[] windMonths;
    
    /**
     * Array holding 25 values for hourly barometric pressure.
     */
    private final double[] pressureHours;
    
    /**
     * Array holding 25 values for daily barometric pressure.
     */
    private final double[] pressureDays;
    
    /**
     * Array holding 25 values for monthly barometric pressure.
     */
    private final double[] pressureMonths;
    
    /**
     * Creates a weather object.
     */
    public Weather() {
    	super();
    	rainHours = new double[MAX_WEATHER_AMOUNT];
    	rainDays = new double[MAX_WEATHER_AMOUNT];
    	rainMonths = new double[MAX_WEATHER_AMOUNT];
    	humHours = new double[MAX_WEATHER_AMOUNT];
        humDays = new double[MAX_WEATHER_AMOUNT];       
        humMonths = new double[MAX_WEATHER_AMOUNT];        
        windHours = new double[MAX_WEATHER_AMOUNT];        
        windDays = new double[MAX_WEATHER_AMOUNT];       
        windMonths = new double[MAX_WEATHER_AMOUNT];  
        pressureHours = new double[MAX_WEATHER_AMOUNT];        
        pressureDays = new double[MAX_WEATHER_AMOUNT];        
        pressureMonths = new double[MAX_WEATHER_AMOUNT];
        
    	generateRainVals();
    	generateHumVals();
    	generateWindVals();
    	generatePressureVals();
        
        myRandom = new Random();
    	myPcs = new PropertyChangeSupport(this);
    }
    
    /**
     * Fires off a property change event that is dependent on ValueType to property change listeners.
     * 
     * @param theValueType the value type
     */
    public void updateValue(final String theValueType) {
    	Double newValue = newValue(theValueType);
    	myPcs.firePropertyChange(theValueType, null, newValue);
    }
    
    /**
     * Formats numbers to a string of a number rounded to the tens decimal place.
     * 
     * @param theValue to be formatted.
     * @return a String representing the rounded value.
     */
    public static String formatValue(final Double theValue) {
    	DecimalFormat myFormatter = new DecimalFormat("#0.0");
    	return myFormatter.format(theValue);
    }
	
    /**
	 * Generates a random barometric pressure trend symbol.
	 * 
	 * @return the barometric pressure trend.
	 */
	public static String barTrend() {
		double check = Math.random();
		String barTrend;
		if (check < 0.33) {
			barTrend = "/\\";
		} else if (check > 0.66) {
			barTrend = "\\/";
		} else {
			barTrend = "--";
		}
		return barTrend;
	}
    
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
    

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }
    
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
        
    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
        
    }
    
    /**
	 * @return the rainHours
	 */
	public double[] getRainHours() {
		return rainHours;
	}

	/**
	 * @return the rainDays
	 */
	public double[] getRainDays() {
		return rainDays;
	}

	/**
	 * @return the rainMonths
	 */
	public double[] getRainMonths() {
		return rainMonths;
	}

	/**
	 * @return the humHours
	 */
	public double[] getHumHours() {
		return humHours;
	}

	/**
	 * @return the humDays
	 */
	public double[] getHumDays() {
		return humDays;
	}

	/**
	 * @return the humMonths
	 */
	public double[] getHumMonths() {
		return humMonths;
	}

	/**
	 * @return the windHours
	 */
	public double[] getWindHours() {
		return windHours;
	}

	/**
	 * @return the windDays
	 */
	public double[] getWindDays() {
		return windDays;
	}

	/**
	 * @return the windMonths
	 */
	public double[] getWindMonths() {
		return windMonths;
	}

	/**
	 * @return the pressureHours
	 */
	public double[] getPressureHours() {
		return pressureHours;
	}

	/**
	 * @return the pressureDays
	 */
	public double[] getPressureDays() {
		return pressureDays;
	}

	/**
	 * @return the pressureMonths
	 */
	public double[] getPressureMonths() {
		return pressureMonths;
	}

	/**
     * Generates a new value for each type of parameter.
     * 
     * @param theValueType
     * @return a new, appropriate value
     */
    private Double newValue(final String theValueType) {
    	Double newValue = 0.0;
    	if (theValueType.equals(PROPERTY_IN_HUM)) {
    		newValue = (25 + (myRandom.nextInt(4) - 2) + (Math.random() - 0.5));
    	} else if (theValueType.equals(PROPERTY_IN_TEMP)) {
    		newValue = (70 + (myRandom.nextInt(8) - 4) + (Math.random() - 0.5));
    	} else if (theValueType.equals(PROPERTY_OUT_HUM)) {
    		newValue = Math.random() * 100;
    	} else if (theValueType.equals(PROPERTY_OUT_TEMP)) {
    		newValue = (51 + (myRandom.nextInt(8) - 4) + (Math.random() - 0.5));
    	} else if (theValueType.equals(PROPERTY_WINDSPD)) {
    		newValue = myRandom.nextInt(4) - (myRandom.nextDouble() * 3);
    	}
    	return newValue;
    }
    
    /**
     * Generates rain values for 25 hours, days, and months for rain to be used in the GraphPanel.
     */
    private void generateRainVals() {
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		rainHours[i] = (Math.random() * 3);
    		rainDays[i] = (Math.random() * 10) + 2;
    		rainMonths[i] = rainDays[i] + 1.2;
    	}
    }
    
    /**
     * Generates humidity values for 25 hours, days, and months for rain to be used in the GraphPanel.
     */
    private void generateHumVals() {
    	double humDayAvg = 0;
    	double humMthAvg = 0;
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		humHours[i] = Math.random() * 100;
    		humDayAvg += humHours[i];
    	}
    	
    	humDayAvg = humDayAvg / MAX_WEATHER_AMOUNT;
    	
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		humDays[i] = humDayAvg + (myRandom.nextInt(20) - 10);
    		humMthAvg += humDays[i];
    	}
    	
    	humMthAvg = humMthAvg / MAX_WEATHER_AMOUNT;
    	
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		humMonths[i] = humMthAvg + (myRandom.nextInt(10) - 5);
    	}
    }
    
    /**
     * Generates wind values for 25 hours, days, and months for rain to be used in the GraphPanel.
     */
    private void generateWindVals() {
    	double windDayAvg = 0;
    	double windMthAvg = 0;
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		windHours[i] = myRandom.nextInt(4) - (myRandom.nextDouble() * 3);
    		windDayAvg += windHours[i];
    	}
    	
    	windDayAvg = windDayAvg / MAX_WEATHER_AMOUNT;
    	
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		windDays[i] = windDayAvg + (myRandom.nextInt(4) - 2);
    		windMthAvg += windDays[i];
    	}
    	
    	windMthAvg = windMthAvg / MAX_WEATHER_AMOUNT;
    	
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		windMonths[i] = windMthAvg + (myRandom.nextInt(10) - 5);
    	}
    }
    
    /**
     * Generates barometric pressure values for 25 hours, days, and months for rain to be used in the GraphPanel.
     */
    private void generatePressureVals() {
    	for (int i = 0; i < MAX_WEATHER_AMOUNT; i++) {
    		pressureHours[i] = (Math.random() * 2);
    		pressureDays[i] = pressureHours[i] + (myRandom.nextInt(2) + (Math.random() - 0.5));
    		pressureMonths[i] = pressureDays[i] + (Math.random() - 0.5);
    	}
    }

}
