package Robot;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

// This is a EV3 color sensor
public class SideColorSensor {
	
	EV3ColorSensor sensor;
	SampleProvider RGB, color_ID, Red;
	
	public SideColorSensor (Port sensor_port)
	{
		this.sensor = new EV3ColorSensor(sensor_port);
		RGB = sensor.getRGBMode();
		color_ID = sensor.getColorIDMode();
		Red = sensor.getRedMode();
	}
	
	public float getIntensity()
	{
		float[] sample = new float[Red.sampleSize()];
		Red.fetchSample(sample, 0);
		return sample[0];
	}
	
	public int getColorID()
	{
		float[] sample = new float[color_ID.sampleSize()];
		color_ID.fetchSample(sample, 0);
		return Math.round(sample[0]);
	}

}

////get a port instance
//Port port = LocalEV3.get().getPort("S2");
//
////Get an instance of the Ultrasonic EV3 sensor
//SensorModes sensor = new EV3UltrasonicSensor(port);
//
////get an instance of this sensor in measurement mode
//SampleProvider distance= sensor.getMode("Distance");
//
////initialize an array of floats for fetching samples. 
////Ask the SampleProvider how long the array should be
//float[] sample = new float[distance.sampleSize()];
//
////fetch a sample
//while(true) 
//distance.fetchSample(sample, 0);