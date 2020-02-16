package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DecimalFormat;
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
    
    /**
     * Creates a weather object.
     */
    public Weather() {
    	super();
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

}
