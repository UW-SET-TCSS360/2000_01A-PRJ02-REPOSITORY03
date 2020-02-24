package tests;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import view.TempPanel;

public class TempPanelTest {
	private JFrame testFrame = new JFrame();
	private TempPanel testTemp = new TempPanel(testFrame);
	@Test
	void groupGetterTest() {
		assertAll("Getter",
				() -> assertNotNull(testTemp.getOutsideTemp()),
				() -> assertNotNull(testTemp.getOutsideHumid()));
				
	}
}
