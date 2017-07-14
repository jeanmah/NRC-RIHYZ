// work in progress

package Robot;

import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;


public class OurRobot {
	
	//this is the brick we are currently using
	Brick brick;
	
	// this section contains the information about the dimension of the robot
	double WheelDiameter = 82;
	double InterWheelDistance = 165;
	double WheelOffset = InterWheelDistance/2; // distance from the wheel to the center of rotation
	
	// this section declare the relevant robot parts (motors, sensors, etc)
	
	// Motors
	NXTRegulatedMotor LiftClawMotor;    // might as well just use NXT for compatibility with other functions
	EV3MediumRegulatedMotor OpenClawMotor;
	String LeftMotor_Port = "A", RightMotor_Port = "B", LiftClawMotor_Port = "C", OpenClawMotor_Port = "D";
	MovePilot pilot;
	
	BasicClaw Claw = new BasicClaw();

	// sensors
	String ClawColorSensor_Port = "S4", SideColorSensor_Port = "S3", Gyro_Port = "S1", LightSensor_Port = "S2";
	ClawColorSensor CCS;
	SideColorSensor SCS;
	Gyro GyroSensor;
	LightSensor LS;
	
	public OurRobot()
	{
		brick = BrickFinder.getDefault();
		setupMotors();
		setupSensors();
	}
	
	private void setupMotors()
	{
		//Move Pilot Setup
		NXTRegulatedMotor LeftMotor = new NXTRegulatedMotor(brick.getPort(LeftMotor_Port));
		NXTRegulatedMotor RightMotor = new NXTRegulatedMotor(brick.getPort(RightMotor_Port));
		LCD.drawString("BaseMotor declared", 0, 0);
		Wheel LeftWheel = WheeledChassis.modelWheel(LeftMotor, WheelDiameter).offset(-WheelOffset);
		Wheel RightWheel = WheeledChassis.modelWheel(RightMotor, WheelDiameter).offset(WheelOffset);
		LCD.drawString("Wheel setup", 0, 1);
		Chassis chassis = new WheeledChassis(new Wheel[] {LeftWheel, RightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = new MovePilot(chassis);
		pilot.setLinearAcceleration(1000); // reduce the braking acceleration so that the robot will not jerk forward
		LCD.drawString("MovePilot setup", 0, 2);
		Delay.msDelay(3000);
		//End of MovePilot Setup
		
		//ClawSetup
		LiftClawMotor = new NXTRegulatedMotor(brick.getPort(LiftClawMotor_Port));
		OpenClawMotor = new EV3MediumRegulatedMotor(brick.getPort(OpenClawMotor_Port));
		Claw.setupClaw(LiftClawMotor, OpenClawMotor);

		
		// prepare the pilot we use for navigation

	}
	
	private void setupSensors()
	{
		CCS = new ClawColorSensor(brick.getPort("S3"));
		//SCS = new SideColorSensor(brick.getPort(SideColorSensor_Port));
		//GyroSensor = new Gyro (brick.getPort(Gyro_Port));
		//LS = new LightSensor(brick.getPort(LightSensor_Port));
	}
	//will delete later 
	public void testClaw(){
		Claw.OpenCloseMotion(20);
	}
}
