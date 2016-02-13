package org.usfirst.frc.team1111.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import variables.*;

public class Operator {

	/*
	 * The following are booleans to carry states through iterations of the
	 * code.
	 * 
	 * bool intake determines if the intake is set to run, as to wait for a
	 * ball.
	 * int armPos holds arm state.
	 * String armState translate state to the dashboard
	 */
	static boolean intake = false;
	static int armPos = 0;
	static String armState = "";

	
	/**
	 * Runs through test methods to determine what operation is requested
	 */
	public static void operate()
	{
		intakeOutake();
		shoot();
		tapeArmExtension();
		functionStopOverride();
		armControl();
		
		SmartDashboard.putString("Arm State:", armState);
		SmartDashboard.putNumber("Shooter rate:", Motors.motorShooter.getEncVelocity()); //TODO Units.
	}


	
	/**
	 * Method, checks the intake and outtake command buttons and runs the
	 * intake/outake motor, else stops the motor.
	 */
	static void intakeOutake()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton))
		{
			Motors.motorOuterIntake.set(Motors.OUTER_INTAKE_POWER);
			Motors.motorIntake.set(Motors.INTAKE_POWER);
			intake = true;
		} else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.innerIntakeButton))
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			Motors.motorIntake.set(Motors.INTAKE_POWER);
			intake = true;
		} else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
		{
			Motors.motorOuterIntake.set(Motors.OUTER_INTAKE_POWER * -1);
			Motors.motorIntake.set(Motors.INTAKE_POWER * -1);
		} else if (intake && (Sensors.intakeLimitSwitch.get() || Sensors.intakeLimitSwitch2.get()))
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			Motors.motorIntake.set(Motors.NO_POWER);
		} else
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			Motors.motorIntake.set(Motors.NO_POWER);
		}
	}

	/**
	 * Method, runs or stops the shoot motor.
	 */
	static void shoot()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
			Motors.motorShooter.set(Motors.SHOOTER_POWER);
		else
			Motors.motorIntake.set(Motors.NO_POWER);
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
	 * Intake arm control
	 */
	static void armControl()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.armUp))
			Motors.motorArm.set(Motors.ARM_POWER);
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.armDown))
			Motors.motorArm.set(Motors.ARM_POWER * -1);
		else
			Motors.motorArm.set(Motors.NO_POWER);
	}

	/**
	 * Function override implementation
	 */
	static void functionStopOverride()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier2))
		{
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
				Motors.motorOuterIntake.set(Motors.NO_POWER);
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.innerIntakeButton))
				Motors.motorIntake.set(Motors.NO_POWER);
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
				Motors.motorShooter.set(Motors.NO_POWER);
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
				Motors.motorTapeArmExt.set(Motors.NO_POWER);
			if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.armUp) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.armDown))
				Motors.motorArm.set(Motors.NO_POWER);
		}
	}

}
