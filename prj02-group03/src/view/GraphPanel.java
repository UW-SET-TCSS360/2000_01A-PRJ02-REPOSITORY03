package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The JPanel on which graph data can be displayed.
 * 
 * @author  Group 3
 * @version February 20, 2020
 */
public class GraphPanel extends JPanel {
	/**
	 * The unique ID for the Serializable interface
	 */
	private static final long serialVersionUID = -819909597063406070L;
	
	/**
	 * The number of graph divisions
	 */
	private static final int NUM_DIVS = 10;
	
	/**
	 * Temperature ID
	 */
	public static final int TEMP_SENSOR = 0;
	/**
	 * Pressure ID
	 */
	public static final int PRESSURE_SENSOR = 1;
	/**
	 * Humidity ID
	 */
	public static final int HUMIDITY_SENSOR = 2;
	/**
	 * Rainfall ID
	 */
	public static final int RAINFALL_SENSOR = 3;
	/**
	 * Wind Speed ID
	 */
	public static final int WIND_SENSOR = 4;
	/**
	 * Number of sensors able to be graphed
	 */
	public static final int NUM_SENSORS = 5;
	
	/**
	 * The minimum values of the respective sensors
	 */
	private static final int[] SENSOR_MINS = {0, 29000, 0, 0, 0};
	/**
	 * The maximum values of the respective sensors
	 */
	private static final int[] SENSOR_MAXES = {1000, 31000, 1000, 40, 600};
	/**
	 * The minimum values to be displayed on the graph
	 */
	private static final double[] READING_MINS = {0.0, 29.000, 0.0, 0.0, 0.0};
	/**
	 * The maximum values to be displayed on the graph
	 */
	private static final double[] READING_MAXES = {100.0, 31.000, 100.0, 4.0, 60.0};
	
	/**
	 * The names of the respective sensors
	 */
	private static final String[] SENSOR_NAMES = {"Temp (°F)", "Pressure (in.)", "Humidity (%)", 
							"Rainfall (in./hr.)", "Wind Speed (mph)"};
	
	/**
	 * The Stroke with which the graph can be drawn
	 */
	private Stroke myStroke;
	/**
	 * The lines making up the background of the graph
	 */
	private List<double[]> lines;
	/**
	 * A circular buffer of the previous sensor values of selected condition
	 */
	private List<Integer> prevVals;
	/**
	 * A circular buffer of time ticks of previously recorded values.
	 */
	private List<String> timeTicks;
	/**
	 * A circular buffer of the previous temperature values
	 */
	private List<Integer> tempVals;
	/**
	 * A circular buffer of the previous humidity values
	 */
	private List<Integer> humidVals;
	/**
	 * A circular buffer of the previous rainfall values
	 */
	private List<Integer> rainVals;
	/**
	 * A circular buffer of the previous wind speed values
	 */
	private List<Integer> windVals;
	/**
	 * A circular buffer of the previous pressure values
	 */
	private List<Integer> baroVals;
	
	/**
	 * The index of the first element of the prevVals circular buffer
	 */
	private int index;
	/**
	 * The type of the sensor currently being graphed
	 */
	private int sensorType;
	
	/**
	 * The constructor, initializes the panel with everything needed for it to draw itself
	 * 
	 * @param width the width of the graph
	 * @param height the height of the graph
	 */
	public GraphPanel(int width, int height, JFrame frame) {
		super();
		this.setBounds(0, (int) (height*0.6), (int) (width*0.98), (int) (height*0.35));
		myStroke = new BasicStroke(1);
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		// lines is initialized with coordinates from 0 to 1 on each dimension
		lines = new ArrayList<>(Arrays.asList(
				new double[]{0, 1-1.0/(NUM_DIVS+1), 1-1.0/(NUM_DIVS+1), 1-1.0/(NUM_DIVS+1)},
				new double[]{1.0/(NUM_DIVS+1), 0, 1.0/(NUM_DIVS+1), 1-1.0/(NUM_DIVS+1)}));
		
		timeTicks = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			timeTicks.add("");
		}
		tempVals = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			tempVals.add(null);
		}
		humidVals = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			humidVals.add(null);
		}
		rainVals = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			rainVals.add(null);
		}
		windVals = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			windVals.add(null);
		}
		baroVals = new ArrayList<>();
		for (int i=0; i<NUM_DIVS; i++) {
			baroVals.add(null);
		}
		prevVals = tempVals;
		
		sensorType = TEMP_SENSOR;
		
		index = 0;
	}
	
	/**
	 * Updates the sensor value of a selected type.
	 * 
	 * @param type the type of the sensor data
	 * @param value the value of the sensor reading
	 */
	public void updateSensorValue(int type, int value) {		
		if (type == TEMP_SENSOR) {
			tempVals.set(index, value);
		}
		if (type == PRESSURE_SENSOR) {
			baroVals.set(index, value);
		}
		if (type == HUMIDITY_SENSOR) {
			humidVals.set(index, value);
		}
		if (type == RAINFALL_SENSOR) {
			rainVals.set(index, value);
		}
		if (type == WIND_SENSOR) {
			windVals.set(index, value);
		}
	}
	
	/**
	 * Updates the time ticks on the graph
	 * @param time The time tick
	 */
	public void updateTimeRecords(String time) {
		timeTicks.set(index, time);
	}
	
	/**
	 * Update graph of display panel with value from the latest LOOP packet.
	 */
	public void updateDisplay() {
		index++;
		index %= NUM_DIVS;
		repaint();
	}
	
	/**
	 * Retrieves the most recent sensor value
	 * 
	 * @return the most recent sensor value
	 */
	public int getCurrentSensorValue() {
		return prevVals.get(index);
	}
	
	/**
	 * Defines what is needed to draw every aspect of the graph
	 * @param theGraphics the Graphics device on which to draw
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
		
		// draw the background of the graph
		for (double[] line : lines) {
			g2d.draw(new Line2D.Double(line[0]*getWidth(), line[1]*getHeight(), line[2]*getWidth(), line[3]*getHeight()));
		}
		
		double divW = getWidth()/(double)(NUM_DIVS+1);
		double divH = getHeight()/(double)(NUM_DIVS+1);
		
		// draw the name of the sensor data type
		g2d.translate(0, getHeight());
		g2d.rotate(-Math.PI/2);
		g2d.drawString(SENSOR_NAMES[sensorType], (float)(5*divH), (float)(divW/3)-5);
		g2d.rotate(Math.PI/2);
		g2d.translate(0, -getHeight());
		
		// draw the label "Time" on the x-axis of graph
		g2d.drawString("Time", (int) (getWidth()*0.03), (int) (divH*10.5));
		
		// draw the bars of the graph
		int min = SENSOR_MINS[sensorType];
		int max = SENSOR_MAXES[sensorType];		
		double readMin = READING_MINS[sensorType];
		double readMax = READING_MAXES[sensorType];
				
		g2d.drawString(Double.toString(readMin), (float) divW - 40, (float) getHeight() - 35);
		g2d.drawString(Double.toString(readMin + (readMax - readMin)/4), (float) divW - 40, (float) (divH-1)*8);
		g2d.drawString("__", (float) divW-5, (float) ((divH-1)*8) - 10);
		g2d.drawString(Double.toString(readMin + (readMax - readMin)/2), (float) divW - 40, (float) (getHeight()-10)/2);
		g2d.drawString("__", (float) divW-5, (float) ((getHeight()-10)/2) - 10);
		g2d.drawString(Double.toString(readMax - (readMax - readMin)/4), (float) divW - 40, (float) (divH*3)-5);
		g2d.drawString("__", (float) divW-5, (float) (divH*3) - 15);
		g2d.drawString(Double.toString(readMax), (float) divW - 40, (float) (divH/3) + 2);
		g2d.drawString("__", (float) divW-5, (float) (divH/3) - 10);		
		
		for (int i=0; i<NUM_DIVS; i++) {
			Integer val = prevVals.get((index+i)%NUM_DIVS);
			if (val != null) {
				double yOffset = ((val-min)/(double)(max-min)) * (getHeight() - divH);
				g2d.draw(new Rectangle.Double(divW + i*divW,
						getHeight()-divH-yOffset,
						divW,
						yOffset)
					);
				g2d.drawString(timeTicks.get((index+i)%NUM_DIVS), (int) (divW + i*divW), 
						(int) (divH*10.5)); 
			}
		}
		
		g2d.dispose();
	}

	/**
	 * Sets which kind of sensor data to display on the graph
	 * 
	 * @param type the type of the sensor data to display on the graph
	 */
	public void setSensorType(int type) {
		if (type != sensorType) {
			sensorType = type;
			repaintGraph(sensorType);
		}
	}
	
	/**
	 * Redraw the graph display with the selected type of sensor data.
	 * 
	 * @param type The sensor type
	 */
	public void repaintGraph(int type) {
		if (type == TEMP_SENSOR) {
			prevVals = tempVals;	
		} else if (type == PRESSURE_SENSOR) {
			prevVals = baroVals;
		} else if (type == HUMIDITY_SENSOR) {
			prevVals = humidVals;
		} else if (type == RAINFALL_SENSOR) {
			prevVals = rainVals;
		} else {
			if (type == WIND_SENSOR) {
				prevVals = windVals;
			}
		}
		repaint();
	}
	
	/**
	 * Retrieves the type of the sensor data being displayed on the graph
	 * 
	 * @return the type of the sensor data being displayed on the graph
	 */
	public int getSensorType() {
		return sensorType;
	}
}
