// tranferred to Robot pack
package pack;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
//import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class Gyro {
	
	EV3GyroSensor sensor;
	SampleProvider angle;
	
	public Gyro( Port sensor_port)
	{
		sensor = new EV3GyroSensor(sensor_port);
		sensor.reset();
		angle = sensor.getAngleMode();
	}
	
	public float getAngle()
	{
		float[] sample = new float[angle.sampleSize()];
		angle.fetchSample(sample, 0);
		return sample[0];
	}
	
	public void reset()
	{
		sensor.reset();
	}
}
