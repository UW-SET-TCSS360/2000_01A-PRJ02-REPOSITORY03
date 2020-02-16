package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * Displays the time, date, high, low, moon phase, sunrise time, and sunset time values.
 * 
 * @author Ryan Donohue
 * @author Chinenye Stella Ezenwoye
 * @author Sukhraj Kaur
 * @author Minh Nguyen
 */
public class TimePanel extends JPanel {

	
	/**
	 * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = -7341235861935972980L;
	private JLabel myCurrentTimeLabel;
	private JLabel myMoonPhase;
    private JLabel mySunsetTime;
	private JLabel myLowTemp;
	private JLabel mySunriseTime;
	private JLabel myHiTemp;
	private String[] myMoonphases = {"New Moon", "Waxing Crescent", "First Quarter", "Waxing Gibbous Moon", "Full Moon", "Wanning Gibbous", "Last Quarter", "Waning Crescent"};
	
	/**
	 * Constructor for the panel and labels.
	 * 
	 * @param theFrame The frame the TimePanel is being added to.
	 */
	public TimePanel(JFrame theFrame) {
		super();
		this.setBounds(10, 10, 326, 103);
		theFrame.getContentPane().add(this);
		this.setLayout(null);
		
		final Random rand = new Random();
		
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		timeLabel.setBounds(113, 5, 48, 14);
		this.add(timeLabel);
		
		myCurrentTimeLabel = new JLabel(DateFormat.getDateTimeInstance().format(new Date()));
		myCurrentTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myCurrentTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myCurrentTimeLabel.setBounds(55, 23, 170, 17);
		this.add(myCurrentTimeLabel);
		startClock();
		
		JLabel moonLabel = new JLabel("Moon Phase");
		moonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moonLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		moonLabel.setBounds(236, 66, 68, 16);
		this.add(moonLabel);
		
		myMoonPhase = new JLabel(myMoonphases[rand.nextInt(myMoonphases.length - 1)]);
		myMoonPhase.setHorizontalAlignment(SwingConstants.CENTER);
		myMoonPhase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		myMoonPhase.setBounds(219, 82, 107, 14);
		this.add(myMoonPhase);
		
		mySunsetTime = new JLabel("16:" + (50 + (rand.nextInt(8) - 4)));
		mySunsetTime.setHorizontalAlignment(SwingConstants.CENTER);
		mySunsetTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mySunsetTime.setBounds(182, 48, 48, 14);
		this.add(mySunsetTime);
		
		JLabel mySunsetLabel = new JLabel("Sunset");
		mySunsetLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mySunsetLabel.setFont(new Font("Verdana", Font.BOLD, 10));
		mySunsetLabel.setBounds(136, 48, 48, 14);
		this.add(mySunsetLabel);
		
		JLabel myLowLabel = new JLabel("Low");
		myLowLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myLowLabel.setFont(new Font("Verdana", Font.BOLD, 10));
		myLowLabel.setBounds(133, 68, 48, 14);
		this.add(myLowLabel);
		
		myLowTemp = new JLabel((40 + (rand.nextInt(16) - 8)) + "F");
		myLowTemp.setHorizontalAlignment(SwingConstants.CENTER);
		myLowTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myLowTemp.setBounds(173, 68, 48, 17);
		this.add(myLowTemp);
		
		JLabel mySunriseLabel = new JLabel("Sunrise");
		mySunriseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mySunriseLabel.setFont(new Font("Verdana", Font.BOLD, 10));
		mySunriseLabel.setBounds(40, 48, 48, 14);
		this.add(mySunriseLabel);
		
		mySunriseTime = new JLabel("07:" + (45 + (rand.nextInt(6) - 3)));
		mySunriseTime.setHorizontalAlignment(SwingConstants.CENTER);
		mySunriseTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mySunriseTime.setBounds(90, 48, 45, 14);
		this.add(mySunriseTime);
		
		myHiTemp = new JLabel((48 + (rand.nextInt(5) - 2)) + "F");
		myHiTemp.setHorizontalAlignment(SwingConstants.CENTER);
		myHiTemp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		myHiTemp.setBounds(88, 68, 48, 14);
		this.add(myHiTemp);
		
		JLabel myHiLabel = new JLabel("Hi");
		myHiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myHiLabel.setFont(new Font("Verdana", Font.BOLD, 10));
		myHiLabel.setBounds(45, 68, 48, 14);
		this.add(myHiLabel);
	}
	
	/**
	 * Method to start a timer that updates the date and time every half second.
	 */
	private void startClock() {
		Timer timer = new Timer(500, new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent theEvent) {
				advanceTime();			
			}
		});
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
	}
	
	
	/**
	 * Helper method to advance the date & time based on the clock attached to it.
	 */
	private void advanceTime() {
        myCurrentTimeLabel.setText(DateFormat.getDateTimeInstance().format(new Date()));
    }

}
