package Robot;

import lejos.hardware.motor.*;

public class BasicClaw {
	//initialise variables
	// for the angles I'll need a formula to set it but i need to find out the angle range we need and the angles we will use most to figure this out
	public int OpenCloseAngle =0; // need to test what this angle is but its when the claw is all the way down
	public int UpDownAngle = 0; // closed 
	private int ResetOpenCloseAngle = 0;
	private int ResetUpDownAngle = 0;
	private int OpenMinLiftAngle = 5; //need to test
	private int AngleThreshold = 2;
	private int TriggerAngle = 20;
	private int LiftLineTrackAngle = 2;
	private int OpenLineTrackAngle = 10; // may just be closed
	//to find out if it is a base or decider
	private int BaseAngle = 10; // need to test
	private int DeciderAngle = 15;	//need to test
	
	
	//calling motors
	
	BaseRegulatedMotor LiftClawMotor;
	BaseRegulatedMotor OpenClawMotor; 
	
	
	public void setupClaw(BaseRegulatedMotor liftclawmotor, BaseRegulatedMotor openclawmotor){
		LiftClawMotor = liftclawmotor;
		OpenClawMotor = openclawmotor;
		LiftClawMotor.setSpeed(50); // need to test speed
		OpenClawMotor.setSpeed(50); // need to test speed
		LiftClawMotor.setAcceleration(1000);
		OpenClawMotor.setAcceleration(1000);
		LiftClawMotor.setStallThreshold(5, 2000);
		OpenClawMotor.setStallThreshold(5, 2000);

	}
	
	//changes input raw angle into relative one
	//may or may not use
	/*
	private int setRelativeAngle(BaseRegulatedMotor motor){
		return motor.getTachoCount(); // insert equation here
	}
	 */
	//basic motions
	//up-down motion 
	@SuppressWarnings("deprecation")
	public void UpDownMotion(int newAngle ){
		int ChangeInAngle = newAngle - UpDownAngle;
		LiftClawMotor.rotate(ChangeInAngle);
		LiftClawMotor.lock(50); //may not need need try out
		UpDownAngle = newAngle;
		//UpDownAngle = setRelativeAngle(LiftClawMotor);
	}
	
	@SuppressWarnings("deprecation")
	public void OpenCloseMotion(int newAngle){
		int ChangeInAngle = newAngle - OpenCloseAngle;
		if (UpDownAngle < OpenMinLiftAngle + AngleThreshold ){
			UpDownMotion(OpenMinLiftAngle-UpDownAngle);
		}
		OpenClawMotor.rotate(ChangeInAngle);
		OpenClawMotor.lock(50); // may not need need try out
		OpenCloseAngle = newAngle;
		//OpenCloseAngle = setRelativeAngle(LiftClawMotor);
	}
	
	// default position for line track
	public void ClawLineTrack(){
		 UpDownMotion(LiftLineTrackAngle);
		 OpenCloseMotion(OpenLineTrackAngle);
	}
	
	
	//Reset (might only be in the beginning)
	//resets the opening and closing of the claw by going all the way up
	@SuppressWarnings("deprecation")
	public void resetOpenClaw(){
		if (UpDownAngle < OpenMinLiftAngle + AngleThreshold ){
			UpDownMotion(OpenMinLiftAngle-UpDownAngle);
		}
		while(!OpenClawMotor.isStalled()){
			OpenClawMotor.forward(); // may need to use backward();
		}	
		OpenClawMotor.stop();
		OpenClawMotor.lock(50); // may not need need try out
		OpenCloseAngle = ResetOpenCloseAngle;
	}
	
	// resets the up and down claw part
	@SuppressWarnings("deprecation")
	public void resetLiftClaw(){
		while(!LiftClawMotor.isStalled()){
			LiftClawMotor.forward(); // may need to use backward();
		}	
		LiftClawMotor.stop();
		LiftClawMotor.lock(50); //may not need need try out
		UpDownAngle = ResetUpDownAngle;
	}
	
	//Identifying if it is a block or a decider
	public int indentifyBaseDecider(){
		if (OpenClawMotor.isStalled()){
			if((OpenClawMotor.getTachoCount()> (BaseAngle- AngleThreshold)) && OpenClawMotor.getTachoCount()< (BaseAngle + AngleThreshold)) {
				return 1; // this is a base
			}
			if ((OpenClawMotor.getTachoCount()> (DeciderAngle- AngleThreshold)) && OpenClawMotor.getTachoCount()< (DeciderAngle + AngleThreshold)){
				return 2; // this is a decider
			}
			else return -1; // neither
		}
		else return -1; // not even stalled
	}
	
	// controls trigger 
	public void trigger(){
		UpDownMotion(TriggerAngle);
	}

}
