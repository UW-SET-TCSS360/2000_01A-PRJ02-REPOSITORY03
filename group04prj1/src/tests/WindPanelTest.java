package tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import view.WindPanel;

class WindPanelTest {
	private JFrame testFrame = new JFrame();
	private WindPanel testWind = new WindPanel(testFrame);
	
	@Test
	void groupGetterTest() {
		assertAll("Getter",
				() -> assertNotNull(testWind.getMyHighWind()),
				() -> assertNotNull(testWind.getMyCurWind()),
				() -> assertNotNull(testWind.getMyWindShift()));
	}
}
