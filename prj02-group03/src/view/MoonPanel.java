package view;

import java.awt.Font;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Displays the moon phase.
 * 
 * @author Group 3
 */
public class MoonPanel extends JPanel {

	/**
	 * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = 4294636548019710446L;
	private JLabel myMoonPhase;
	// {
	private JLabel myMoonIcon;
	// }
	private String[] myMoonphases = {"New Moon", "Waxing Crescent", "First Quarter", "Waxing Gibbous", "Full Moon", "Wanning Gibbous", "Last Quarter", "Waning Crescent"};
	
	/**
	 * Create the panel.
	 */
	public MoonPanel() {
		super();
		setLayout(null);
		
		final Random rand = new Random();
		this.setBounds(10, 10, 125, 107);
		
		JLabel moonLabel = new JLabel("Moon Phase");
		moonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moonLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		// {
		moonLabel.setBounds(29, 0, 68, 16);
		// }
		this.add(moonLabel);
		
		// {
		int moonPhaseNumber = rand.nextInt(myMoonphases.length - 1);
		myMoonPhase = new JLabel(myMoonphases[moonPhaseNumber]);
		// }
		myMoonPhase.setHorizontalAlignment(SwingConstants.CENTER);
		myMoonPhase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		myMoonPhase.setBounds(10, 82, 107, 14);
		this.add(myMoonPhase);
		
		// {
		myMoonIcon = new JLabel();
		myMoonIcon.setHorizontalAlignment(SwingConstants.CENTER);
		myMoonIcon.setBounds(40, 27, 50, 50);
		ImageIcon moonPhaseIcon = new ImageIcon("src/Lunar_Phases/image" + moonPhaseNumber + ".png");
		Image scaledIcon = moonPhaseIcon.getImage();
		scaledIcon = scaledIcon.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		myMoonIcon.setIcon(new ImageIcon(scaledIcon));
		this.add(myMoonIcon);
		// }

	}

}
