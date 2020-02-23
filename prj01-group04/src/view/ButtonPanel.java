package view;

import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import java.awt.Color;

/**
 * Displays button that update values and switch the graph values.
 * 
 * @author Group 3
 */
public class ButtonPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2869644701192452513L;
	
	private ArrayList<JButton> myButtons = new ArrayList<>();

	/**
	 * Create the panel.
	 * @param myFrame 
	 */
	public ButtonPanel(JFrame theFrame) {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.setBounds(10, 10, 207, 111);
		setLayout(null);
		
		JButton tempButton = new JButton("TEMP");
		tempButton.setBounds(10, 11, 89, 23);
		this.add(tempButton);
		myButtons.add(tempButton);
		
		JButton humidityButton = new JButton("HUM");
		humidityButton.setBounds(10, 41, 89, 23);
		this.add(humidityButton);
		myButtons.add(humidityButton);
		
		JButton rainButton = new JButton("RAIN");
		rainButton.setBounds(10, 75, 89, 23);
		this.add(rainButton);
		myButtons.add(rainButton);
		
		JButton windButton = new JButton("WIND");
		windButton.setBounds(109, 75, 89, 23);
		this.add(windButton);
		myButtons.add(windButton);
		
		JButton barometerButton = new JButton("BAR");
		barometerButton.setBounds(109, 41, 89, 23);
		this.add(barometerButton);
		myButtons.add(barometerButton);
		
		JButton alarmButton = new JButton("ALARM");
		alarmButton.setBounds(109, 11, 89, 23);
		this.add(alarmButton);
		alarmButton.addActionListener(e -> {
			SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final JFrame timeViewFrame = new JFrame("Alarm Setting");
				timeViewFrame.pack();
				timeViewFrame.setVisible(true);
				timeViewFrame.setBounds(100, 100, 325, 200);

				JTextField textField = new JTextField();
				JLabel lblNewLabel = new JLabel("Set Alarm");
				textField = new JTextField();
				textField.setColumns(10);
				JRadioButton rainRadioButton = new JRadioButton("Rainfall");
				JRadioButton windRadioButton = new JRadioButton("Wind Speed");
				JRadioButton tempRadioButton = new JRadioButton("Temperature");
				JButton okButton = new JButton("Ok");
				okButton.addActionListener(theEvent -> {
					timeViewFrame.dispose();
				});
				GroupLayout gl_contentPanel = new GroupLayout(timeViewFrame);
				gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
											  .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup()
											  .addContainerGap().addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
											  .addGroup(gl_contentPanel.createSequentialGroup().addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											  .addGap(68).addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addComponent(windRadioButton).addComponent(rainRadioButton)
											  .addComponent(tempRadioButton))))).addGroup(gl_contentPanel.createSequentialGroup().addGap(95).addComponent(okButton))).addContainerGap(141, Short.MAX_VALUE)));
				gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(rainRadioButton))
											.addPreferredGap(ComponentPlacement.RELATED).addComponent(windRadioButton).addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(tempRadioButton).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(okButton).addContainerGap(89, Short.MAX_VALUE)));
				timeViewFrame.setLayout(gl_contentPanel);
				}
			});
		});
		myButtons.add(alarmButton);
		
		theFrame.getContentPane().add(this);
	}
	
	public ArrayList<JButton> getButtons() {
		return myButtons;
	}

	

}
