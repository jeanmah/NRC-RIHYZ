package Robot;
/* claw must be able to...
 *  be in a default position to be able move up and make sure it doesnt get stuck
 *  open and close
 * 	use color sensor to detect stuff 
 * determine what something is 
 * lift up to store things in cavity 
 * Note : Angles used for motor servo assumes that the servo start ed out at the correct angle: all the way down and rubber just touching
 */

import lejos.hardware.motor.*;
import lejos.hardware.port.Port;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.*;
import lejos.utility.*;

public class Claw{
	//declaring the motors for the claw	
	/*static Port LiftClawMotor_Port = LocalEV3.get().getPort("C");
	static NXTRegulatedMotor LiftClawMotor = new NXTRegulatedMotor(LiftClawMotor_Port);
	static Port OpenClawMotor_Port = LocalEV3.get().getPort("D");
	static EV3MediumRegulatedMotor OpenClawMotor = new EV3MediumRegulatedMotor(OpenClawMotor_Port);
	*/	  

	final static int defaultHeight = 0; // default height should also be grabbing height
	final static int defaultCloseAngle = 0;
	final static int defaultOpenAngle = 0;
	final static int defaultUpHeight = 0;
	final static int angleThreshold = 5;

	int neededHeight = 0; //changes according to what it is holding
	final int depositDeciderHeight = 0;
	final int depositBaseHeight = 0;
	final int depositTurbineHeight = 0;	
	final int depositWallHeight = 0;
	
	int neededAngle = 0; // changes according to what it is holding
	final int deciderAngle = 0;
	final int baseAngle = 0;
	
	int secondBranchDist;
	final int baseDist = 0;
	final int deciderDist = 0;
	
	String firstPiece;  
	 NXTRegulatedMotor LiftClawMotor;
	 EV3MediumRegulatedMotor OpenClawMotor;
	 
	public void claw_setup(NXTRegulatedMotor liftClawMotor,EV3MediumRegulatedMotor openClawMotor) {
		LiftClawMotor = liftClawMotor;
		OpenClawMotor = openClawMotor;
	 }


	// Goes to the default claw position
	public void defaultPosition(){
		if(LiftClawMotor.getTachoCount() >= (defaultHeight + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultHeight - angleThreshold) ){ 
			LiftClawMotor.rotateTo(defaultHeight,true);
			LiftClawMotor.waitComplete();
		}
		if (LiftClawMotor.getTachoCount() >= (defaultCloseAngle + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultCloseAngle - angleThreshold) ){
			OpenClawMotor.rotateTo(defaultCloseAngle,true);
			OpenClawMotor.waitComplete();	
		}
		
	}
	
	//when robot needs to move with claw up 
	public void defaultUpMovingPosition(){
		if((LiftClawMotor.getTachoCount() >= (defaultUpHeight + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultUpHeight - angleThreshold) )){ 
			LiftClawMotor.rotateTo(defaultUpHeight,true);
			LiftClawMotor.waitComplete();
		}	
	}
	public void upMovingPosition(){
		if((LiftClawMotor.getTachoCount() >= (neededHeight + angleThreshold) || LiftClawMotor.getTachoCount() <= (neededHeight - angleThreshold) )){ 
			LiftClawMotor.rotateTo(neededHeight,true);
			LiftClawMotor.waitComplete();
		}	
	}
	
	public void pickup(){
		if(LiftClawMotor.getTachoCount() >= (defaultUpHeight + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultUpHeight - angleThreshold) ){ 
			LiftClawMotor.rotateTo(defaultUpHeight,true);
			LiftClawMotor.waitComplete();
		}
		if (LiftClawMotor.getTachoCount() >= (defaultOpenAngle + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultOpenAngle - angleThreshold)){
			OpenClawMotor.rotateTo(defaultOpenAngle,true);
			OpenClawMotor.waitComplete();	
		}
			LiftClawMotor.rotateTo(defaultHeight,true); // goes down
			OpenClawMotor.rotateTo(neededAngle,true);	//grabs
	}
	
	public void putdown(){
		if(LiftClawMotor.getTachoCount() >= (neededHeight + angleThreshold) || LiftClawMotor.getTachoCount() <= (neededHeight - angleThreshold) ){ 
			LiftClawMotor.rotateTo(neededHeight,true);
			LiftClawMotor.waitComplete();
		}
		OpenClawMotor.rotateTo(defaultOpenAngle,true);
		OpenClawMotor.waitComplete();
		OpenClawMotor.rotateTo(defaultCloseAngle,true);
		OpenClawMotor.waitComplete();
	}
	public boolean IsBase(){
		if(OpenClawMotor.getTachoCount() >= (baseAngle - angleThreshold) && OpenClawMotor.getTachoCount() <= (baseAngle + angleThreshold) ){
			return true;
		}
		else{ 
			return false; // if false it should be the decider but there might be a bug with this if angle is completely off
		}
	}
	
	public void isBasePickUp(){	
		double startTime = 0;
		double endTime = 0;
		if(LiftClawMotor.getTachoCount() >= (defaultUpHeight + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultUpHeight - angleThreshold) ){ 
			LiftClawMotor.rotateTo(defaultUpHeight,true);
			LiftClawMotor.waitComplete();
		}
		if (LiftClawMotor.getTachoCount() >= (defaultOpenAngle + angleThreshold) || LiftClawMotor.getTachoCount() <= (defaultOpenAngle - angleThreshold)){
			OpenClawMotor.rotateTo(defaultOpenAngle,true);
			OpenClawMotor.waitComplete();	
		}
		LiftClawMotor.rotateTo(defaultHeight,true); //goes down
		startTime = System.currentTimeMillis(); //start timer
		
		// counts to 2s while trying to close
		while (endTime != startTime + 2000){
			endTime = System.currentTimeMillis();
			OpenClawMotor.forward(); 
		}
		OpenClawMotor.stop(true);
	}
	
}