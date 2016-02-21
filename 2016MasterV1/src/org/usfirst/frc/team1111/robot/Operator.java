package org.usfirst.frc.team1111.robot;

import edu.wpi.first.wpilibj.Timer;
import variables.Joysticks;
import variables.Motors;
import variables.Sensors;

public class Operator {

	static final int TAPE_ARM_LOWER_LIMIT = 0;
	static final int TAPE_ARM_UPPER_LIMIT = 0;
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
//		shoot();
		tapeArm(); //TODO this is disabled cause it breaks if it goes in to far and the encoder isnt connected
		functionStopOverride();
		armControl();

		//		SmartDashboard.putString("Arm State:", armState);
		//		SmartDashboard.putNumber("Shooter rate:", Motors.motorShooter.getEncVelocity()); //TODO Units.
	}



	/**
	 * Method, checks the intake and outtake command buttons and runs the
	 * intake/outake motor, else stops the motor.
	 */
	static void intakeOutake()
	{
		if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton))
		{
			Motors.motorOuterIntake.set(Motors.OUTER_INTAKE_POWER);
			Motors.motorInnerIntake.set(Motors.INNER_INTAKE_POWER);
			intake = true;
		}else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
		{
			Motors.motorOuterIntake.set(Motors.OUTER_INTAKE_POWER * -1);
			Motors.motorInnerIntake.set(Motors.INNER_INTAKE_POWER * -1);
			intake = false;
		}else if(intake && (Sensors.intakeLimitSwitch.get() || Sensors.intakeLimitSwitch2.get()))
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			Motors.motorInnerIntake.set(Motors.NO_POWER);
			intake = false;
		}else if(!intake)
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			//if(!shooting || (shooting && !spunUp))
				Motors.motorInnerIntake.set(Motors.NO_POWER);
		}
	}

	static boolean shooting = false;
	static boolean spunUp = false;
	static double shooterSpinupStartTime = 0.0;

	/**
	 * Method, runs or stops the shoot motor.
	 */
	static void shoot()
	{
		if(!shooting)
		{
			if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
			{
				shooting = true;
				spunUp = false;
			}
			else
				Motors.motorShooter.set(Motors.NO_POWER);
		}

		if(shooting)
		{
			//spin up shooter
			if(shooterSpinupStartTime == 0.0)
			{
				shooterSpinupStartTime = Timer.getMatchTime();
				Motors.leftStop.set(Motors.LEFTSTOP_RETRACTED);
				Motors.rightStop.set(Motors.RIGHTSTOP_RETRACTED);
			}else if(Timer.getMatchTime() - shooterSpinupStartTime < Motors.SHOOTER_SPIN_TIME)
				Motors.motorShooter.set(Motors.SHOOTER_POWER);
			else if(Timer.getMatchTime() -shooterSpinupStartTime < Motors.SHOOTER_SPIN_TIME + Motors.SHOOTER_INTAKE_TIME)
			{
				spunUp = true;
				Motors.motorInnerIntake.set(Motors.INNER_INTAKE_POWER);
			}else
			{
				Motors.leftStop.set(Motors.LEFTSTOP_UP);
				Motors.rightStop.set(Motors.RIGHTSTOP_UP);
				Motors.motorShooter.set(Motors.NO_POWER);
				Motors.motorInnerIntake.set(Motors.NO_POWER);
				spunUp = false;
				shooting = false;
			}
		}
	}

	/**
	 * Method, controls the tape arm extension motor.
	 */
	static void tapeArm()
	{
		//if(!(Motors.motorTapeArm.getEncPosition() <= TAPE_ARM_LOWER_LIMIT) && !(Motors.motorTapeArm.getEncPosition() >= TAPE_ARM_UPPER_LIMIT)) TODO Enable after limits are calibrated.
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend))
			Motors.motorTapeArm.set(Motors.TAPE_ARM_POWER);
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
			Motors.motorTapeArm.set(-Motors.TAPE_ARM_POWER);
		else
			Motors.motorTapeArm.set(Motors.NO_POWER);


	}
	
	static boolean armStates = false;

	/**
	 * Intake arm control
	 */
	static void armControl()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.armUp))
		{
			//armStates = false;
			Motors.motorArm.set(-Motors.ARM_POWER);
		}
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.armDown))
			Motors.motorArm.set(Motors.ARM_POWER);
		else
			Motors.motorArm.set(Motors.NO_POWER);
	}
	
	static void armState()
	{
		
	}

	/**
	 * Function override implementation
	 */
	static void functionStopOverride()
	{
		if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.overrideKillModifier2))
		{
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
			{
				Motors.motorInnerIntake.set(Motors.NO_POWER);
				Motors.motorOuterIntake.set(Motors.NO_POWER);
				intake = false;
			}
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
			{
				Motors.motorShooter.set(Motors.NO_POWER);
				shooting = false;
				spunUp = false;
				Motors.leftStop.set(Motors.LEFTSTOP_UP);
				Motors.rightStop.set(Motors.RIGHTSTOP_UP);
			}
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
				Motors.motorTapeArm.set(Motors.NO_POWER);
			if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.armUp) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.armDown))
				Motors.motorArm.set(Motors.NO_POWER);
		}
	}

}
