package model;

import java.beans.PropertyChangeListener;

/**
 * Defines property change support implementation and event properties
 * 
 * @author Ryan Donohue
 */
public interface WeatherProperties {
	
	/**
	 * A property name for shifting between rain rate of 25 hours, days, or months.
	 */
	String PROPERTY_RAIN = "rain";
	
	/**
     * A property name for the wind speed timer intervals.
     */
    String PROPERTY_WINDSPD = "windspd";
    
    /**
     * A property name for the barometer timer intervals.
     */
    String PROPERTY_BAR = "bar";
      
    /**
     * A property name for the outside temperature timer intervals.
     */
    String PROPERTY_OUT_TEMP = "oTemp";
    
    /**
     * A property name for the outside humidity.
     */
    String PROPERTY_OUT_HUM = "oHum";
    
    /**
     * A property name for the wind direction.
     */
    String PROPERTY_WINDDIR = "winddir";
    
    /**
     * A property name for the inside humidity.
     */
    String PROPERTY_IN_HUM = "iHum";
    
    /**
     * A property name for the inside temp.
     */
    String PROPERTY_IN_TEMP = "iTemp";
    
    /**
     * Add a PropertyChangeListener to the listener list. The listener is registered for 
     * all properties. The same listener object may be added more than once, and will be 
     * called as many times as it is added. If listener is null, no exception is thrown and 
     * no action is taken.
     * 
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(PropertyChangeListener theListener);
    
    
    /**
     * Add a PropertyChangeListener for a specific property. The listener will be invoked only 
     * when a call on firePropertyChange names that specific property. The same listener object
     * may be added more than once. For each property, the listener will be invoked the number 
     * of times it was added for that property. If propertyName or listener is null, no 
     * exception is thrown and no action is taken.
     * 
     * @param thePropertyName The name of the property to listen on.
     * @param theListener The PropertyChangeListener to be added
     */
    void addPropertyChangeListener(String thePropertyName, PropertyChangeListener theListener);

    /**
     * Remove a PropertyChangeListener from the listener list. This removes a 
     * PropertyChangeListener that was registered for all properties. If listener was added 
     * more than once to the same event source, it will be notified one less time after being 
     * removed. If listener is null, or was never added, no exception is thrown and no action 
     * is taken.
     * 
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(PropertyChangeListener theListener);
    
    /**
     * Remove a PropertyChangeListener for a specific property. If listener was added more than
     * once to the same event source for the specified property, it will be notified one less 
     * time after being removed. If propertyName is null, no exception is thrown and no action 
     * is taken. If listener is null, or was never added for the specified property, no 
     * exception is thrown and no action is taken.
     * 
     * @param thePropertyName The name of the property that was listened on.
     * @param theListener The PropertyChangeListener to be removed
     */
    void removePropertyChangeListener(String thePropertyName, 
                                      PropertyChangeListener theListener);
}
