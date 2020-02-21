package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The panel that shows the wind speed and direction graphically.
 * 
 * @author  Group 6
 * @version February 20, 2020
 */
public class WindDirectionPanel extends JPanel {
	
	/**
	 * The unique ID for the Serializable interface
	 */
	private static final long serialVersionUID = -7614555071611756523L;
	
	private static final String[] DIRECTIONS = {"N", "NNE", "NE", "ENE", "E", "ESE",  "SE",  
			"SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
	
	/**
	 * The wind speed sensor data
	 */
	private String speed;
	/**
	 * The wind direction sensor data
	 */
	// {
	private String dir;
	// }
	/**
	 * The Stroke that will be used to draw the WindPanel
	 */
	private Stroke myStroke;
	
	/**
	 * The constructor for the WindPanel
	 * 
	 * @param diam the diameter of the circular wind panel
	 */
	// {
	public WindDirectionPanel(int diam, JFrame frame) {
		super();
		this.setBounds(100, 265, 150, 150);
		frame.getContentPane().add(this);
		speed = "0.0";
		dir = DIRECTIONS[0];
		myStroke = new BasicStroke(1);
		this.setPreferredSize(new Dimension(diam, diam));
		this.setMinimumSize(new Dimension(diam, diam));
	}
	// }
	
	/**
	 * Updates the value of the wind speed sensor data
	 * 
	 * @param speed the wind speed sensor data
	 */
	// {
	public void updateWindSpeed(String speed) {
		this.speed = speed;
		repaint();
	}
	// }
	
	/**
	 * Updates the indicator of the wind direction sensor
	 * @param myDir the wind direction indicator
	 */
	// {
	public void updateWindDirection(String myDir) {
		this.dir = myDir;
		repaint();
	}
	// }
	
	/**
	 * Retrieves the last wind speed sensor data
	 * 
	 * @return the last wind speed sensor data
	 */
	// {
	public String getSpeed() {
		return speed;
	}
	// }
	
	/**
	 * Retrieves the last wind direction sensor data
	 * 
	 * @return the last wind speed sensor data
	 */
	// {
	public String getDir() {
		return dir;
	}
	// }
	
	/**
	 * Paints the wind panel
	 * 
	 * @param theGraphics the graphics device onto which to paint the wind panel
	 */
	@Override
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g2d = (Graphics2D)theGraphics;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                 RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                 RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                 RenderingHints.VALUE_STROKE_PURE);
		
		g2d.setColor(Color.BLACK);
		g2d.setStroke(myStroke);
		
		// only use 90% of the bounding rectangle in each dimension
		double innerDiam = getWidth()*0.9;
		
		// draw the background circle
		g2d.draw(new Ellipse2D.Double((getWidth()-innerDiam)/2, (getHeight()-innerDiam)/2, innerDiam, innerDiam));
		
		//////////// {
		// draw the speed text string
		g2d.drawString(speed, getWidth()*(0.33f), getHeight()/1.9f);
		
		// draw the compass direction acronyms
		g2d.drawString("N", getWidth()*(0.47f), getHeight()/6.5f);
		g2d.drawString("E", getWidth()*(0.85f), getHeight()/1.9f);
		g2d.drawString("S", getWidth()*(0.47f), getHeight()/1.1f);
		g2d.drawString("W", getWidth()*(0.09f), getHeight()/1.9f);
		
		// draw the direction triangle
		g2d.translate(getWidth()/2.0, getHeight()/2.0);
		
		double angle = 0;
		for (int i = 0; i < DIRECTIONS.length; i++) {
			String direction = DIRECTIONS[i];
			String realDirection = dir.toString();
			if (DIRECTIONS[i].equals(this.dir.toString())) {
				angle = (i * 22.5) % 360;
			}
		}
		
		g2d.rotate(angle * Math.PI/180);
		g2d.fillPolygon(new int[] {-getWidth()/10, 0, getWidth()/10},
						new int[] {(int) (getHeight()*(-0.85)/2) , (int) (-getHeight()/2.0), (int) (getHeight()*(-0.85)/2)}, 3);
		g2d.rotate(angle * -Math.PI/180);
		g2d.translate(-getWidth()/2.0, -getHeight()/2.0);
		//////////// }
		
		g2d.dispose();
	}
}