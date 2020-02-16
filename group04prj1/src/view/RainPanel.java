package view;

import static model.WeatherProperties.PROPERTY_BAR;
import static model.WeatherProperties.PROPERTY_RAIN;
import static model.Weather.barTrend;
import static model.Weather.formatValue;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



/**
 * Displays the rain rate and totals for the past 25 hours/days/months, the past 24 hour
 * temperature and barometric pressure difference, and the barometric pressure trend.
 * 
 * @author Minh Nguyen, Ryan Donohue
 */
public class RainPanel extends JPanel implements PropertyChangeListener {
	
	private static final long serialVersionUID = 6263424022946499487L;
	private JLabel myTotalRainNum;
	private JLabel myRainRateNum;
	private JLabel myBaroTrend;
	private JLabel myYesterdayBar;
	private JLabel myYesterdayTemp;
	
	private double myTotalRainDays;
	private double myTotalRainHrs;
	private double myTotalRainMths;
	private double myRainRateDays;
	private double myRainRateHrs;
	private double myRainRateMths;
	private int myRainCycler;

	public RainPanel(JFrame theFrame) {
		super();
		this.setBounds(360, 114, 220, 193);
		theFrame.getContentPane().add(this);
		this.setLayout(null);
		
		myYesterdayTemp = new JLabel(formatValue(Math.random() * 10) + "F");
		myYesterdayTemp.setBounds(36, 171, 29, 16);
		this.add(myYesterdayTemp);
		myYesterdayTemp.setHorizontalAlignment(SwingConstants.CENTER);
		myYesterdayTemp.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel tempChangeLabel = new JLabel("temp");
		tempChangeLabel.setBounds(28, 150, 48, 14);
		this.add(tempChangeLabel);
		tempChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tempChangeLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		JLabel barChangeLabel = new JLabel("bar");
		barChangeLabel.setBounds(109, 150, 49, 15);
		this.add(barChangeLabel);
		barChangeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		barChangeLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		myYesterdayBar = new JLabel(formatValue(Math.random() * 2) + "inHg");
		myYesterdayBar.setBounds(109, 171, 50, 16);
		this.add(myYesterdayBar);
		myYesterdayBar.setHorizontalAlignment(SwingConstants.CENTER);
		myYesterdayBar.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JLabel twentyFourHrLbl = new JLabel("24 Hour Difference");
		twentyFourHrLbl.setBounds(29, 131, 129, 16);
		this.add(twentyFourHrLbl);
		twentyFourHrLbl.setHorizontalAlignment(SwingConstants.CENTER);
		twentyFourHrLbl.setFont(new Font("Verdana", Font.BOLD, 12));

		JLabel barTrendLabel = new JLabel("Bar Trend");
		barTrendLabel.setBounds(128, 13, 80, 16);
		this.add(barTrendLabel);
		barTrendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		barTrendLabel.setFont(new Font("Verdana", Font.BOLD, 12));		

		myBaroTrend = new JLabel(barTrend());
		myBaroTrend.setBounds(138, 55, 58, 20);
		this.add(myBaroTrend);
		myBaroTrend.setHorizontalAlignment(SwingConstants.CENTER);
		myBaroTrend.setFont(new Font("Tahoma", Font.PLAIN, 16));

		myRainRateNum = new JLabel("totalrain");
		myRainRateNum.setBounds(36, 91, 58, 20);
		this.add(myRainRateNum);
		myRainRateNum.setHorizontalAlignment(SwingConstants.CENTER);
		myRainRateNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		myRainRateNum.setText(formatValue(myRainRateHrs) + "in");

		JLabel rainRateLabel = new JLabel("rate");
		rainRateLabel.setBounds(11, 76, 48, 14);
		this.add(rainRateLabel);
		rainRateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rainRateLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));
		
		myTotalRainNum = new JLabel("totalrain");
		myTotalRainNum.setBounds(38, 55, 58, 20);
		this.add(myTotalRainNum);
		myTotalRainNum.setHorizontalAlignment(SwingConstants.CENTER);
		myTotalRainNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		myTotalRainNum.setText(formatValue(myTotalRainHrs) + "in");

		JLabel totalRainLabel = new JLabel("total");
		totalRainLabel.setBounds(11, 39, 48, 14);
		this.add(totalRainLabel);
		totalRainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalRainLabel.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 11));

		JLabel myRainfallLabel = new JLabel("Rainfall");
		myRainfallLabel.setBounds(36, 13, 80, 16);
		this.add(myRainfallLabel);
		myRainfallLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myRainfallLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		
		setRainValues();
	}
	
	
	/**
	 * Sets the rain values when the panel is initialized.
	 */
	protected void setRainValues() {
		myTotalRainDays = (Math.random() * 10) + 2;
		myTotalRainHrs = (Math.random() * 3);
		myTotalRainMths = myTotalRainDays + 1.2;
		myRainRateDays = myTotalRainDays / 25;
		myRainRateHrs = myTotalRainHrs / 25;
		myRainRateMths = myTotalRainMths / 25;
		myRainCycler = 0;
		cycleRainValues();
	}
	
	/**
	 * Cycles through the pre-generated rain values.
	 */
	public void cycleRainValues() {
			if (myRainCycler == 0) {
			myTotalRainNum.setText(formatValue(myTotalRainHrs) + "in");
			myRainRateNum.setText(formatValue(myRainRateHrs) + "in");	
			myRainCycler++;
		} else if (myRainCycler == 1) {
			myTotalRainNum.setText(formatValue(myTotalRainDays) + "in");
			myRainRateNum.setText(formatValue(myRainRateDays) + "in");	
			myRainCycler++;
		}else if (myRainCycler == 2) {
			myTotalRainNum.setText(formatValue(myTotalRainMths) + "in");
			myRainRateNum.setText(formatValue(myRainRateMths) + "in");		
			myRainCycler = 0;
		}
	}
	
	public double getMyTotalRainDays() {
		return myTotalRainDays;
	}


	public double getMyTotalRainHrs() {
		return myTotalRainHrs;
	}


	public double getMyTotalRainMths() {
		return myTotalRainMths;
	}


	public double getMyRainRateDays() {
		return myRainRateDays;
	}


	public double getMyRainRateHrs() {
		return myRainRateHrs;
	}


	public double getMyRainRateMths() {
		return myRainRateMths;
	}


	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		if (theEvent.getPropertyName().equals(PROPERTY_BAR)) {
			myBaroTrend.setText(barTrend());
		} else if (theEvent.getPropertyName().equals(PROPERTY_RAIN)) {
			cycleRainValues();
		}
	}
	
}
