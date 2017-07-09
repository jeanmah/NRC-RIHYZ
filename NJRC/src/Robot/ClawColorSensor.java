package Robot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.utility.Delay;
import SensorLib.HiTechColorSensor;

// this is an HiTech color sensor
public class ClawColorSensor 
{
	HiTechColorSensor sensor;
	
	public ClawColorSensor(Port port)
	{
		sensor = new HiTechColorSensor(port);
		LCD.clear();
		LCD.drawString("HiTech Sensor setup", 0, 0);
		Delay.msDelay(200);
	}
	
	public int getIntensity()
	{
		return sensor.getIntensity();
	}
	
	public int getColor()
	{
		int HiTechID = sensor.getColorID();
		// Not ready yet
		// Need to convert from HiTech ID to Lego ID
		return HiTechID;
	}
	
}

