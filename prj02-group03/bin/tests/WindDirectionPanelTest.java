package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import view.WindDirectionPanel;

/**
 * JUnit class for testing the WindDirectionPanel class
 * 
 * @author brandk94
 * @version February 24, 2020
 */
public class WindDirectionPanelTest {

	/**
	 * Set up the JFrame for the wind direction panel.
	 */
	JFrame frame = new JFrame();
	
	/**
	 * The WindDirectionPanel object that will be used in the test.
	 */
	WindDirectionPanel myWindDirectionPanel;
	
	/**
	 * Initialize the WindDirectionPanel object.
	 */
	@Before
	public void setup() {
		myWindDirectionPanel = new WindDirectionPanel(100, frame);
	}
	
	/**
	 * Test the getter methods of the WindDirectionPanel.
	 */
	@Test
	public void testNotNullGetterMethods() {
		assertNotNull(myWindDirectionPanel.getSpeed());
		assertNotNull(myWindDirectionPanel.getDir());
	}
	
	/**
	 * Test the setter method that updates the wind speed.
	 */
	@Test
	public void testUpdateWindSpeed() {
		myWindDirectionPanel.updateWindSpeed("50.0");
		assertEquals("Speed number does not match.", "50.0", myWindDirectionPanel.getSpeed());
	}
	
	/**
	 * Test the setter method that updates the wind direction.
	 */
	@Test
	public void testUpdateWindDirection() {
		myWindDirectionPanel.updateWindDirection("ESE");
		assertEquals( "Wind direction acronymn does not match.", "ESE", myWindDirectionPanel.getDir());
	}
	
}
