package SensorLib;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.I2CPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.I2CSensor;
import lejos.robotics.ColorIdentifier;


public class HiTechColorSensor extends I2CSensor implements ColorIdentifier {

  private byte[] buf = new byte[3];


  public HiTechColorSensor(I2CPort port) 
  {
    super(port);
  }

  public HiTechColorSensor(Port port) 
  {
    super(port);
  }

  
  @Override
  public int getColorID() {
    getData(0x42, buf, 1);
    int HT_val = (0xFF & buf[0]);
    return HT_val;
  }
  
  public int getIntensity()
  {
	  getData(0x43,buf,3);
	  int R= (0xFF & buf[0]);
	  int G= (0xFF & buf[1]);
	  int B= (0xFF & buf[2]);
	  return (R+B+G)/3;
  }
  
  public void debug()
  {
	  getData(0x43,buf,3);
	  int R= (0xFF & buf[0]);
	  int G= (0xFF & buf[1]);
	  int B= (0xFF & buf[2]);
	  getData(0x42, buf, 1);
	  int HT_val = (0xFF & buf[0]);
	  LCD.drawInt((R+G+B)/3, 0, 0);
	  LCD.drawInt(HT_val, 0, 1);
  }

}