// transferred to Robot pack
package pack;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.SampleProvider;

public class LightSensor {
	
	NXTLightSensor sensor;
	SampleProvider Red;
	
	public LightSensor(Port sensor_port)
	{
		sensor = new NXTLightSensor(sensor_port);
		Red = sensor.getRedMode();
	}
	
	public float getIntensity()
	{
		float[] sample = new float[Red.sampleSize()];
		Red.fetchSample(sample, 0);
		return sample[0]*255;
	}

}
