package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import view.RainPanel;

class RainPanelTest {
	private JFrame testFrame = new JFrame();
	private RainPanel testRain = new RainPanel(testFrame);
	
	@Test
	void groupGetterTest() {
		assertAll("Getter",
				() -> assertNotNull(testRain.getMyTotalRainDays()),
				() -> assertNotNull(testRain.getMyTotalRainHrs()),
				() -> assertNotNull(testRain.getMyTotalRainMths()),
				() -> assertNotNull(testRain.getMyRainRateDays()),
				() -> assertNotNull(testRain.getMyRainRateHrs()),
				() -> assertNotNull(testRain.getMyRainRateMths()));
	}
}
