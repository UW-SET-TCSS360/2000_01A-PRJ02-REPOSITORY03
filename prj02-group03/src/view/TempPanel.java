/**
 * 
 */
package view;

import static model.WeatherProperties.PROPERTY_OUT_TEMP;
import static model.WeatherProperties.PROPERTY_IN_HUM;
import static model.WeatherProperties.PROPERTY_IN_TEMP;
import static model.WeatherProperties.PROPERTY_OUT_HUM;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Displays the inside temperature, outside temperature, inside humidity, outside humidity, and associated labels for reference.
 * 
 * @author Ryan Donohue
 * @author Minh Nguyen
 */
public class TempPanel extends JPanel implements PropertyChangeListener {
	
	/**
	 * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 7302786842042190587L;
	private JLabel myTempLabel;
	private JLabel myInsideTemp;
	private JLabel myOutsideTemp;
	private JLabel myInsideLabel;
	private JLabel myOutsideLabel;
	private JLabel myInsideHum;
	private JLabel myOutsideHum;
	private DecimalFormat myNumFormat;
	
	/**
	 * Constructs the Temperature Panel and attaches it to the main frame.
	 * 
	 * @param theFrame the frame.
	 */
	public TempPanel(JFrame theFrame) {
		super();
		myNumFormat = new DecimalFormat("#0.0");
		this.setBounds(371, 10, 193, 103);
		theFrame.getContentPane().add(this);
		this.setLayout(null);
		
		myTempLabel = new JLabel("Temperature");
		myTempLabel.setBounds(51, 5, 87, 16);
		this.add(myTempLabel);
		myTempLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myTempLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		myInsideTemp = new JLabel("insideTemp");
		myInsideTemp.setBounds(10, 29, 89, 17);
		this.add(myInsideTemp);
		myInsideTemp.setHorizontalAlignment(SwingConstants.CENTER);
		myInsideTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		myOutsideTemp = new JLabel("outsideTemp");
		myOutsideTemp.setBounds(94, 29, 89, 17);
		this.add(myOutsideTemp);
		myOutsideTemp.setHorizontalAlignment(SwingConstants.CENTER);
		myOutsideTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		myInsideLabel = new JLabel("inside");
		myInsideLabel.setBounds(20, 48, 79, 14);
		this.add(myInsideLabel);
		myInsideLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));
		myInsideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		myOutsideLabel = new JLabel("outside");
		myOutsideLabel.setBounds(94, 48, 79, 14);
		this.add(myOutsideLabel);
		myOutsideLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));
		myOutsideLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		myInsideHum = new JLabel("insideHum");
		myInsideHum.setBounds(10, 65, 89, 17);
		this.add(myInsideHum);
		myInsideHum.setHorizontalAlignment(SwingConstants.CENTER);
		myInsideHum.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		myOutsideHum = new JLabel("outsideHum");
		myOutsideHum.setBounds(94, 65, 89, 17);
		this.add(myOutsideHum);
		myOutsideHum.setHorizontalAlignment(SwingConstants.CENTER);
		myOutsideHum.setFont(new Font("Tahoma", Font.PLAIN, 14));
	}
	
	/**
	 * Receives a specific value through a timed property event that is tied to a specific label.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		Double value = (Double) theEvent.getNewValue();
		if (theEvent.getPropertyName().equals(PROPERTY_IN_HUM)) {
			myInsideHum.setText(myNumFormat.format(value) + "%");
		} else if (theEvent.getPropertyName().equals(PROPERTY_IN_TEMP)) {
			myInsideTemp.setText(myNumFormat.format(value) + "F");
		} else if (theEvent.getPropertyName().equals(PROPERTY_OUT_TEMP)) {
			myOutsideTemp.setText(myNumFormat.format(value) + "F");
		} else if (theEvent.getPropertyName().equals(PROPERTY_OUT_HUM)) {
			myOutsideHum.setText(myNumFormat.format(value) + "%");
		}
		
	}
	
	//////////// {
	public double getOutsideTemp() {
		String tempReading = myOutsideTemp.getText();
		double tempValue = (Integer.parseUnsignedInt(tempReading.substring(0, tempReading.length()-3))*10)
				+ Integer.parseUnsignedInt(tempReading.substring(tempReading.length()-2, tempReading.length()-1));
		return tempValue / 10;
	}
	
	public double getOutsideHumid() {
		String humidReading = myOutsideHum.getText();
		double humidValue = (Integer.parseUnsignedInt(humidReading.substring(0, humidReading.length()-3))*10)
				+ Integer.parseUnsignedInt(humidReading.substring(humidReading.length()-2, humidReading.length()-1));
		return humidValue / 10;
	}
	//////////// }

}
