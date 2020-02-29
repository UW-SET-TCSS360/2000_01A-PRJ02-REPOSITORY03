package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlarmFrame extends JFrame {

	/**
	 * A generated serial version UID for object Serialization. 
     * http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html
	 */
	private static final long serialVersionUID = -5933053350919014361L;
	
	private JPanel contentPane;
	private JTextField alarmValueInput;
	private JLabel valueTypeLabel;
	private JRadioButton rainfallRadioBtn;
	private JRadioButton windspdRadioBtn;
	private JRadioButton tempRadioBtn;
	private JButton acceptButton;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public AlarmFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 324, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		alarmValueInput = new JTextField();
		alarmValueInput.setText("");
		alarmValueInput.setBounds(10, 32, 96, 20);
		contentPane.add(alarmValueInput);
		alarmValueInput.setColumns(10);
		
		JLabel inputLabel = new JLabel("Set an Alarm:");
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setBounds(10, 7, 90, 14);
		contentPane.add(inputLabel);
		
		valueTypeLabel = new JLabel("Choose a Type:");
		valueTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		valueTypeLabel.setBounds(180, 7, 96, 14);
		contentPane.add(valueTypeLabel);
		
		rainfallRadioBtn = new JRadioButton("Rainfall Rate");
		buttonGroup.add(rainfallRadioBtn);
		rainfallRadioBtn.setBounds(168, 59, 109, 23);
		contentPane.add(rainfallRadioBtn);
		
		windspdRadioBtn = new JRadioButton("Wind Speed");
		buttonGroup.add(windspdRadioBtn);
		windspdRadioBtn.setBounds(168, 85, 109, 23);
		contentPane.add(windspdRadioBtn);
		
		tempRadioBtn = new JRadioButton("Temperature");
		buttonGroup.add(tempRadioBtn);
		tempRadioBtn.setBounds(168, 31, 109, 23);
		contentPane.add(tempRadioBtn);
		
		acceptButton = new JButton("OK");
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		acceptButton.setBounds(10, 63, 89, 23);
		contentPane.add(acceptButton);
	}
	
	/**
	 * Returns the value of the text field as a string
	 * 
	 * @return the value entered.
	 */
	public String getAlarmValue() {
		String alarmVal = alarmValueInput.getText();
		buttonGroup.getSelection();
		return alarmVal;
	}
	
	/**
	 * Returns the OK button on the frame for use with action listeners.
	 * 
	 * @return the Ok JButton
	 */
	public JButton getOkButton() {
		return acceptButton;
	}
}
