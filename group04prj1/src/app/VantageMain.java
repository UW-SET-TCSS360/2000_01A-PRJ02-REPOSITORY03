package app;

import java.awt.EventQueue;

import controller.WeatherController;

/**
 * Class that starts the Vantage Vue application
 * 
 * @author Ryan Donohue
 * @author Chinenye Stella Ezenwoye
 * @author Sukhraj Kaur
 * @author Minh Nguyen
 */
public final class VantageMain {
	
	
	/**
     * Private empty constructor to avoid accidental instantiation. 
     */
	private VantageMain() { };
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherController window = new WeatherController();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
