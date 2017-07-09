package Robot;

import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

public class Claw {
	RemoteEV3 ev3;
	static RMIRegulatedMotor motorA;
	static RMIRegulatedMotor motorB;
	static RMIRegulatedMotor motorC;
	static RMIRegulatedMotor motorD;
	static boolean stop = false;
}
