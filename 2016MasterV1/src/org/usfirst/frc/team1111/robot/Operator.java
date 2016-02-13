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
	}

	// Check and Command methods.

	/**
	 * Method, checks the intake and outtake command buttons and runs the
	 * intake/outake motor, else stops the motor.
	 */
	// TODO Rewrite for new intake system.
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
	 * Intake arm control
	 */
	static void armControl()
	{
		if (armPos <= 99 && Joysticks.joyOp.getRawButton(Joysticks.Buttons.ArmUp))
			armPos++;
		else if (armPos > 0 && Joysticks.joyOp.getRawButton(Joysticks.Buttons.ArmDown))
			armPos--;

		switch (armPos) {
		case 0:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.UNDER_PORT);
			break;
		case 1:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.UNDER_PORT);
			break;
		case 2:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.INTAKE_LEVEL);
			break;
		case 3:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.SEESAW);
			break;
		case 4:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.PORT_RAISE);
			break;
		case 5:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.UP);
			break;
		default:
			Motors.motorArm.setPosition(Sensors.MOTOR_ARM_STATES.UP);
		}
	}

	/**
	 * Function override implementation
	 */
	static void functionStopOverride()// TODO :Either make these not ELSE or
										// inform OP that they have to only
										// press one button in conjunction with
										// the override.
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier2))
		{
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
				Motors.motorOuterIntake.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton)
					|| Joysticks.joyOp.getRawButton(Joysticks.Buttons.innerIntakeButton))
				Motors.motorIntake.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
				Motors.motorShooter.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
				Motors.motorTapeArmExt.set(Motors.NO_POWER);
//			else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotUp)
//					|| Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRotDown))
//				Motors.motorTapeArmRot.set(Motors.NO_POWER);
			else if (Joysticks.joyOp.getPOV() != Joysticks.D_PAD_OFF)
				Motors.motorArm.set(Motors.NO_POWER);
		}
	}

}
