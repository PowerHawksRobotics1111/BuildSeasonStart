package org.usfirst.frc.team1111.robot;

import variables.Joysticks;
import variables.Motors;

public class Operator {
	
	/**
	 * Runs through test methods to determine what operation is requested
	 */
	public void operate() {
		testIntake(); testOuttake(); testShoot(); testTapeArmRetract(); testTapeArmExtend(); testTapeArmRotateUp();
		testTapeArmRotateDown(); testKillSwitch(); testArmUp(); testArmDown();
	}
	
	
	//*****START OPERATION TEST METHODS*****
	
	
	/**
	 * Method that tests if intake needs to be run
	 */
	public void testIntake() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton)) {
			intake();
		}
		
		else {
			Motors.motorIntake.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that tests if outtake needs to be run
	 */
	public void testOuttake() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton)) {
			outtake();
		}
		
		else {
			Motors.motorIntake.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that tests if shoot needs to be run
	 */
	public void testShoot() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton)) {
			shoot();
		}
		
		else {
			Motors.motorIntake.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that tests if tap arm needs to be extended
	 */
	public void testTapeArmExtend()
	{
		if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend))
		{
			tapeArmExtend();
		}
		else
		{
			Motors.motorTapeArmExt.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that tests if the tape arm needs to be extracted
	 */
	public void testTapeArmRetract() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract)) {
			tapeArmRetract();
		}
		
		else {
			Motors.motorTapeArmExt.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that tests if the tape arm needs to rotate up
	 */
	public void testTapeArmRotateUp() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotUp)) {
			tapeArmRotateUp();
		}
		
		else {
			Motors.motorTapeArmRot.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that tests if the tape arm needs to rotate down
	 */
	public void testTapeArmRotateDown() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotDown)) {
			tapeArmRotateDown();
		}
		
		else {
			Motors.motorTapeArmRot.set(Motors.NO_POWER);
		}
	}
	
	public void testArmUp() {
		if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_LEFT || Joysticks.joyOp.getPOV() == Joysticks.D_PAD_UP ||
			Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_RIGHT) {
			
			armUp();
		}
		
		else {
			Motors.motorArm.set(Motors.NO_POWER);
		}
	}
	
	public void testArmDown() {
		if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_DOWN ||
			Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_LEFT || 
			Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_RIGHT) {
			
			armDown();
		}
		
		else {
			Motors.motorArm.set(Motors.NO_POWER);
		}
	}
	
	/**
	 * Method that activates motor overrides
	 */
	public void testKillSwitch() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier)) {
			killMotors();
		}
	}
	
	
	//*****END OPERATION TEST METHODS*****
	
	
	//*****START OPERATION METHODS*****
	
	
	/**
	 * Method that operates the intake motor
	 */
	public void intake() {
		Motors.motorIntake.set(Motors.QUARTER_POWER);
	}
	
	/**
	 * Method that operates the outtake motor
	 */
	public void outtake() {
		Motors.motorIntake.set(Motors.REVERSE_QUARTER_POWER);
	}
	
	/**
	 * Method that shoots the ball
	 */
	public void shoot() {
		Motors.motorShooter.set(Motors.FULL_POWER);
	}
	
	/**
	 * Method which extends the scaling/tape measure arm by runnning a motor
	 */
	public void tapeArmExtend()
	{
		Motors.motorTapeArmExt.set(Motors.QUARTER_POWER);
	}
	
	/**
	 * Method that retracts the tape arm
	 */
	public void tapeArmRetract() {
		Motors.motorTapeArmExt.set(Motors.REVERSE_QUARTER_POWER);
	}
	
	/**
	 * Method that rotates the tape arm up
	 */
	public void tapeArmRotateUp() {
		Motors.motorTapeArmRot.set(Motors.QUARTER_POWER);
	}
	
	/**
	 * Method that rotates the tape arm down
	 */
	public void tapeArmRotateDown() {
		Motors.motorTapeArmRot.set(Motors.REVERSE_QUARTER_POWER);
	}
	
	/**
	 * Method that rotates the arm up
	 */
	public void armUp() {
		Motors.motorArm.set(Motors.QUARTER_POWER);
	}
	
	/**
	 * Method that rotates the arm down
	 */
	public void armDown() {
		Motors.motorArm.set(Motors.REVERSE_QUARTER_POWER);
	}
	
	/**
	 * Method that kills corresponding motors
	 */
	public void killMotors() {
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton) ||
			Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton)) {
			
			Motors.motorIntake.set(Motors.NO_POWER);
		}
		
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton)) {
			Motors.motorShooter.set(Motors.NO_POWER);
		}
		
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend) ||
				 Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract)) {
			
			Motors.motorTapeArmExt.set(Motors.NO_POWER);
		}
		
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotUp) ||
				 Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotDown)) {
			
			Motors.motorTapeArmRot.set(Motors.NO_POWER);
		}
		
		else if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_LEFT || Joysticks.joyOp.getPOV() == Joysticks.D_PAD_UP ||
				 Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_RIGHT || Joysticks.joyOp.getPOV() == Joysticks.D_PAD_DOWN ||
				 Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_LEFT || 
				 Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_RIGHT) {
			
			Motors.motorArm.set(Motors.NO_POWER);
		}
	}
	
	
	//*****END OPERATIONS METHODS*****
}
