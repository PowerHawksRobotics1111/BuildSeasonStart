package org.usfirst.frc.team1111.robot;

import variables.Motors;
import variables.Sensors;
import variables.Sensors.Encoders;

public class Auto {

	static class Movement {

		public static final double TEMP_DEFAULT_AUTO_SPEED = Motors.HALF_POWER;

		public static void orient(double targetAngle)
		{
			if (Sensors.navX.getYaw() > targetAngle + 5.0)
				turnInPlace("left", TEMP_DEFAULT_AUTO_SPEED);
			else if (Sensors.navX.getYaw() < targetAngle - 5.0)
				turnInPlace("right", TEMP_DEFAULT_AUTO_SPEED);
			else
				stopDriveMotors();
		}

		public static void driveForDistance(double distance)
		{
			if (Sensors.Encoders.resetEncoders)
			{
				Encoders.resetEncoders();
				Encoders.resetEncoders = false;
			}

			if (Encoders.encoderDriveLeft.getDistance() < distance)
				driveDriveMotors(TEMP_DEFAULT_AUTO_SPEED);
			else
				stopDriveMotors();
		}

		public static void turnInPlace(String direction, double speed)
		{
			if (direction.equals("left"))
			{
				Robot.subState = "Turning Left...";

				Motors.motorDriveFrontLeft.set(speed);
				Motors.motorDriveBackLeft.set(speed);
				Motors.motorDriveFrontRight.set(-speed);
				Motors.motorDriveBackRight.set(-speed);
			} else if (direction.equals("right"))
			{
				Robot.subState = "Turning Right...";

				Motors.motorDriveFrontRight.set(speed);
				Motors.motorDriveBackRight.set(speed);
				Motors.motorDriveFrontLeft.set(-speed);
				Motors.motorDriveBackLeft.set(-speed);
			}
		}

		public static void driveDriveMotors(double power)
		{
			Motors.motorDriveBackLeft.set(power);
			Motors.motorDriveBackRight.set(power);
			Motors.motorDriveFrontLeft.set(power);
			Motors.motorDriveFrontRight.set(power);
		}

		public static void stopDriveMotors()
		{
			Motors.motorDriveBackLeft.set(Motors.NO_POWER);
			Motors.motorDriveBackRight.set(Motors.NO_POWER);
			Motors.motorDriveFrontLeft.set(Motors.NO_POWER);
			Motors.motorDriveFrontRight.set(Motors.NO_POWER);
		}

	}
	
	//TODO Shoot subversions, what can we go over, what will mess with encoders, what is the same?
	public static void rockWall()
	{
		
	}
	
	public static void moat()
	{
		
	}
	
	public static void portcullis()
	{
		
	}
	
	public static void seesaws()
	{
		
	}
	
	public static void drawbridge()
	{
		
	}
	
	public static void roughTerrain()
	{
		
	}
	
	public static void ramparts()
	{
		
	}
	
	public static void lowBar()
	{
		
	}

}
