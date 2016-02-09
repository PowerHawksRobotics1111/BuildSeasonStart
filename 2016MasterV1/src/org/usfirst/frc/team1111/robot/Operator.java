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
	public static void operate()
	{
		intakeOutake();
		shoot();
		tapeArmExtension();
		tapeArmRotation();
		functionStopOverride();
		armControl();
	}

	//Check and Command methods.

	/**
	 * Method, checks the intake and outtake command buttons and runs the  intake/outake motor, else stops the motor.
	 */
	static void intakeOutake()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton))
		{
			Motors.motorIntake.set(Motors.INTAKE_POWER);
			intake = true;
		} else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
			Motors.motorIntake.set(Motors.INTAKE_POWER * -1);
		else if (!intake || (intake && Sensors.intakeLimitSwitch.get()))
			Motors.motorIntake.set(Motors.NO_POWER);
	}

	/**
	 * Method, runs or stops the shoot motor.
	 */
	static void shoot()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
		{
			Motors.motorShooter.set(Motors.SHOOTER_POWER);
		} else
		{
			Motors.motorIntake.set(Motors.NO_POWER);
		}
	}

	/**
	 * Method, controls the tape arm extension motor.
	 */
	static void tapeArmExtension()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend))
			Motors.motorTapeArmExt.set(Motors.TAPE_EXT_POWER);
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
			Motors.motorTapeArmExt.set(Motors.TAPE_EXT_POWER * -1);
		else
			Motors.motorTapeArmExt.set(Motors.NO_POWER);
	}

	/**
	 * Method, controls tape arm rotation motor.
	 */
	static void tapeArmRotation()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotUp))
			Motors.motorTapeArmRot.set(Motors.TAPE_ROT_POWER);
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotDown))
			Motors.motorTapeArmRot.set(Motors.TAPE_ROT_POWER * -1);
		else
			Motors.motorTapeArmRot.set(Motors.NO_POWER);
	}

	/**
	 * Intake arm control
	 */
	static void armControl()
	{
		if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_LEFT
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_UP
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_FORWARD_RIGHT)
			Motors.motorArm.set(Motors.ARM_POWER);
		else if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_DOWN
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_LEFT
				|| Joysticks.joyOp.getPOV() == Joysticks.D_PAD_BACKWARD_RIGHT)
			Motors.motorArm.set(Motors.ARM_POWER * -1);
		else
			Motors.motorArm.set(Motors.NO_POWER);
	}

	/**
	 * Function override implementation
	 */
	static void functionStopOverride()
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
