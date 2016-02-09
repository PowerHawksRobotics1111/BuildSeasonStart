package org.usfirst.frc.team1111.robot;

import variables.Joysticks;
import variables.Motors;
import variables.Sensors;

public class Operator {

	/*
	 * The following are booleans to carry states through iterations of the
	 * code.
	 * 
	 * bool intake determines if the intake is set to run, as to wait for a
	 * ball.
	 */
	static boolean intake = false;

	/**
	 * Runs through test methods to determine what operation is requested
	 */
	public void operate()
	{
		this.intakeOutake();
		this.shoot();
		this.tapeArmExtension();
		this.tapeArmRotation();
		this.functionStopOverride();
		this.armControl();
	}

	//Check and Command methods.

	/**
	 * Method, checks the intake and outtake command buttons and runs the  intake/outake motor, else stops the motor.
	 */
	public void intakeOutake()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton))
		{
			Motors.motorIntake.set(Motors.QUARTER_POWER);
			intake = true;
		} else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
			Motors.motorIntake.set(Motors.REVERSE_QUARTER_POWER);
		else if (!intake || (intake && Sensors.intakeLimitSwitch.get()))
			Motors.motorIntake.set(Motors.NO_POWER);
	}

	/**
	 * Method, runs or stops the shoot motor.
	 */
	public void shoot()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
		{
			Motors.motorShooter.set(Motors.FULL_POWER);
		} else
		{
			Motors.motorIntake.set(Motors.NO_POWER);
		}
	}

	/**
	 * Method, controls the tape arm extension motor.
	 */
	public void tapeArmExtension()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend))
			Motors.motorTapeArmExt.set(Motors.QUARTER_POWER);
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
			Motors.motorTapeArmExt.set(Motors.REVERSE_QUARTER_POWER);
		else
			Motors.motorTapeArmExt.set(Motors.NO_POWER);
	}

	/**
	 * Method, controls tape arm rotation motor.
	 */
	public void tapeArmRotation()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotUp))
			Motors.motorTapeArmRot.set(Motors.QUARTER_POWER);
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotDown))
			Motors.motorTapeArmRot.set(Motors.REVERSE_QUARTER_POWER);
		else
			Motors.motorTapeArmRot.set(Motors.NO_POWER);
	}

	/**
	 * Intake arm control
	 */
	public void armControl()
	{
		if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_LEFT
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_UP
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_RIGHT)
			Motors.motorArm.set(Motors.QUARTER_POWER);
		else if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_DOWN
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_LEFT
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_RIGHT)
			Motors.motorArm.set(Motors.REVERSE_QUARTER_POWER);
		else
			Motors.motorArm.set(Motors.NO_POWER);
	}

	/**
	 * Function override implementation
	 */
	public void functionStopOverride()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier))
		{
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton)
					|| Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
				Motors.motorIntake.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
				Motors.motorShooter.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend)
					|| Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
				Motors.motorTapeArmExt.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotUp)
					|| Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotDown))
				Motors.motorTapeArmRot.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getPOV() != Joysticks.D_PAD_OFF)
				Motors.motorArm.set(Motors.NO_POWER);
		}
	}

}
