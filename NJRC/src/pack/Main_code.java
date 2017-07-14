package pack;

import Robot.BasicClaw;
import lejos.hardware.BrickFinder;
//import lejos.hardware.Brick;
//import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.LCD;
//import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.utility.Delay;

public class Main_code
{

	public static void main(String[] args)
	{
//		LightSensor sensor = new LightSensor(BrickFinder.getDefault().getPort("S2"));
//	
//		while(true)
//		{
//			LCD.clear();
//			LCD.drawInt((int) sensor.getIntensity(), 0, 0);
//			Delay.msDelay(100);
//		}
		Our_robot Robot = new Our_robot();
		
		//Gyro gyro = new Gyro(BrickFinder.getDefault().getPort("S1"));
		//gyro.reset();
//		while(true)
//		{
//			LCD.clear();
//			LCD.drawInt((int) gyro.getAngle(), 0, 0);
//			Delay.msDelay(100);
//		}
		//Robot.pilot.travel(50.0);
		//LCD.drawString("50", 1, 1);

		//return;
	} 
}
