package org.usfirst.frc.team1111.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import variables.Joysticks;
import variables.Motors;
import variables.Sensors;

public class Operator {

	static final int TAPE_ARM_LOWER_LIMIT = 0;
	static final int TAPE_ARM_UPPER_LIMIT = 0;
	private static final int NUM_ARM_STATES = 5;
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
		tapeArm(); //TODO this is disabled cause it breaks if it goes in to far and the encoder isnt connected
		functionStopOverride();
		armControl();
//		armStates();
		hardStopToggle();

		//		SmartDashboard.putString("Arm State:", armState);
		//		SmartDashboard.putNumber("Shooter rate:", Motors.motorShooter.getEncVelocity()); //TODO Units.
	}



	/**
	 * Method, checks the intake and outtake command buttons and runs the
	 * intake/outake motor, else stops the motor.
	 */
	static void intakeOutake()
	{
		if( !shooting && Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton))
		{
			Motors.motorOuterIntake.set(Motors.OUTER_INTAKE_POWER);
			Motors.motorInnerIntake.set(Motors.INNER_INTAKE_POWER);
			intake = true;
		}else if ( !shooting && Joysticks.joyOp.getRawButton(Joysticks.Buttons.outtakeButton))
		{
			Motors.motorOuterIntake.set(Motors.OUTER_INTAKE_POWER * -1);
			Motors.motorInnerIntake.set(Motors.INNER_OUTAKE_POWER);
			intake = false;
		}else if(intake && (Sensors.intakeLimitSwitch.get() || Sensors.intakeLimitSwitch2.get()))
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			Motors.motorInnerIntake.set(Motors.NO_POWER);
			intake = false;
		}else if(!intake)
		{
			Motors.motorOuterIntake.set(Motors.NO_POWER);
			if(!shooting)
			Motors.motorInnerIntake.set(Motors.NO_POWER);
		}
	}

	static boolean shooting = false;
	static boolean spunUp = false;
	static double intakeToShooterStartTime = 0.0;
	private static final double SHOOTER_INTAKE_TIME = 1.0;//Seconds
	static boolean shootingIntake = false;

	/**
	 * Method, runs or stops the shoot motor.
	 */
	static void shoot()//TODO Button to spin up, then intake again to shoot.
	{
		if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.shootButton))
		{
			shooting = true;
		}
		
		if(shooting)
			Motors.motorShooter.set(Motors.SHOOTER_POWER);
		else
			shootingIntake = false;
		
		if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.intakeButton))
				shootingIntake = true;
		if(shooting && shootingIntake)
		{
			if(intakeToShooterStartTime == 0.0)
				intakeToShooterStartTime = Timer.getMatchTime();
			else if(Timer.getMatchTime() - intakeToShooterStartTime < SHOOTER_INTAKE_TIME)
			{
				Motors.motorInnerIntake.set(Motors.INNER_INTAKE_POWER);
				Motors.motorShooter.set(Motors.SHOOTER_POWER);
			}else
			{
				intakeToShooterStartTime = 0.0;
				shooting = false;
				shootingIntake = false;
				Motors.motorInnerIntake.set(Motors.NO_POWER);
				Motors.motorShooter.set(Motors.NO_POWER);
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
			armStates = false;
			Motors.motorArm.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			Motors.motorArm.set(-Motors.ARM_POWER);
		}
		else if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.armDown))
		{
			armStates = false;
			Motors.motorArm.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			Motors.motorArm.set(Motors.ARM_POWER);
		}
		else if(!armStates)
			Motors.motorArm.set(Motors.NO_POWER);
	}

	final static class ArmStates//Done from zero at the top to 90 at horizantal hopefully...
	{
		final static int UP = 0, SCALING = 45, DRAWBRIDGE = 45, INTAKE = 90, LOWBAR = 95;//TODO Angles for scale and drawbridge
	}
	
	static int armStateNumber;

	static void armStates()
	{
		if(Joysticks.joyOp.getPOV() == Joysticks.D_PAD_UP)
		{
			armStateNumber++;
			armStates = true;
		}else if(Joysticks.joyOp.getPOV() == Joysticks.D_PAD_DOWN)
		{
			armStateNumber++;
			armStates = true;
		}
		
		String armPosForDash = "Not Position Based";
		
		if(armStates)
		{
			Motors.motorArm.changeControlMode(CANTalon.TalonControlMode.Position);
			int state = armStateNumber % NUM_ARM_STATES;
			if(state == 0)
			{
				Motors.motorArm.set(ArmStates.UP);
				armPosForDash = "Up";
			}else if(state == 1)
			{
				Motors.motorArm.set(ArmStates.SCALING);
				armPosForDash = "Scaling";
			}else if(state == 2)
			{
				Motors.motorArm.set(ArmStates.DRAWBRIDGE);
				armPosForDash = "DrawBidge";
			}else if(state == 3)
			{
				Motors.motorArm.set(ArmStates.INTAKE);
				armPosForDash = "Intake";
			}else if(state == 4)
			{
				Motors.motorArm.set(ArmStates.LOWBAR);
				armPosForDash = "Low Bar";
			}
		}
		
		SmartDashboard.putString("Arm State: ", armPosForDash);
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
				intakeToShooterStartTime = 0.0;
				Motors.motorShooter.set(Motors.NO_POWER);
				shooting = false;
				//Motors.leftStop.set(Motors.LEFTSTOP_UP);
				//Motors.rightStop.set(Motors.RIGHTSTOP_UP); TODO These currently arent on the robot
			}
			if (Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmExtend) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.tapeArmRetract))
				Motors.motorTapeArm.set(Motors.NO_POWER);
			if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.armUp) || Joysticks.joyOp.getRawButton(Joysticks.Buttons.armDown))
			{
				armStates = false;
				Motors.motorArm.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
				Motors.motorArm.set(Motors.NO_POWER);
			}
		}
	}
	
	static void hardStopToggle()
	{
		if(Joysticks.joyOp.getRawButton(Joysticks.Buttons.reverseShooter))
		{
			if(Motors.hardBallStop.getAngle() == 0)
			{
				Motors.hardBallStop.setAngle(45);
			}
			else if(Motors.hardBallStop.getAngle() == 45)
			{
				Motors.hardBallStop.setAngle(0);
			}
		}
	}

}
