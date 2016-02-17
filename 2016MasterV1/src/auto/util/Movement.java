package auto.util;

import org.usfirst.frc.team1111.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import variables.Sensors.Encoders;
import variables.Motors;

public class Movement {

	/**
	 * Variable to specify the left side
	 */
	final static String LEFT = "left";

	/**
	 * Variable to specify the right side
	 */
	final static String RIGHT = "right";

	/**
	 * Variables to specify both sides
	 */
	final static String BOTH = "both";
	
	

	public final static double DEFAULT_SPEED = Motors.QUARTER_POWER;

	/**
	 * Method that orients the robot to the given angle
	 * 
	 * @Precondition angle must be >= -180 and <= 180 degrees.
	 * 
	 * @param angle the angle to which the robot needs to turn to
	 * @param navX the navX board on the robot
	 */
	public static void orient(double angle, double yaw, AHRS navX)
	{
		Robot.state = "Orienting...";
		/**
		 * Booleans if yaw is greater than or less than the needed angle in a +-
		 * 5 range
		 */
		boolean isTooHigh = yaw > angle + 5, isTooLow = yaw < angle - 5;

		SmartDashboard.putBoolean("Robot too High", isTooHigh);
		SmartDashboard.putBoolean("Robot too Low", isTooLow);

		if (isTooHigh) // Tests if robot angle is above needed angle
			turnInPlace(LEFT, DEFAULT_SPEED); // Turns to left
		else if (isTooLow) // Tests if robot angle is below needed angle
			turnInPlace(RIGHT, DEFAULT_SPEED); // Turns to right
		else
			stopDriveMotors(); // Stops motors
	}

	/**
	 * Method that drives the robot a given distance
	 * 
	 * @param dist the distance needed to travel by the robot
	 */
	public static void driveDistance(double dist)
	{
		Robot.state = "Driving Distance...";

		/**
		 * Test to see if encoders need to be reset
		 */
		boolean resetEncoders = Encoders.resetEncoders;

		if (resetEncoders)
		{ // Tests if encoders needs to be reset. Resets encoder if applicable
			Encoders.resetEncoders();
			Encoders.resetEncoders = false;
		}

		/**
		 * Current ticks the encoder has traveled
		 */
		double distTraveled = Encoders.encoderDriveLeft.getDistance();

		if (distTraveled < dist) // Tests if current traveled ticks is less than the needed ticks.
			activateDriveMotors(DEFAULT_SPEED); // Goes forward
		else
			stopDriveMotors(); // Stops motor
	}

	/**
	 * Method that activates all drive motors
	 * 
	 * @param speed the speed at which you want the motors to move
	 */
	public static void activateDriveMotors(double speed)
	{
		Robot.subState = "Driving Drive Motors...";

		Motors.motorDriveFrontLeft.set(speed);
		Motors.motorDriveFrontRight.set(speed);
		Motors.motorDriveBackLeft.set(speed);
		Motors.motorDriveBackRight.set(speed);
	}

	/**
	 * Method that cause the robot to turn in place
	 * 
	 * @param dir the direction the robot is to turn
	 * @param speed the speed at which the motors will run
	 * 
	 * @Postcondition the robot will spin in place with the designated side's motors moving forward and the other side's motors moving backwards.
	 */
	public static void turnInPlace(String dir, double speed)
	{
		if (dir.equals(LEFT))
		{ // Tests if robot needs to turn left
			Robot.subState = "Turning Left...";

			Motors.motorDriveFrontLeft.set(speed);
			Motors.motorDriveBackLeft.set(speed);
			Motors.motorDriveFrontRight.set(-speed);
			Motors.motorDriveBackRight.set(-speed);
		} else if (dir.equals(RIGHT))
		{ // Tests if robot needs to turn right
			Robot.subState = "Turning Right...";

			Motors.motorDriveFrontRight.set(speed);
			Motors.motorDriveBackRight.set(speed);
			Motors.motorDriveFrontLeft.set(-speed);
			Motors.motorDriveBackLeft.set(-speed);
		}
	}

	/**
	 * Method that stops the drive motors of the robot
	 */
	public static void stopDriveMotors()
	{
		Robot.subState = "Stopping Motors...";

		Motors.motorDriveFrontLeft.set(Motors.NO_POWER);
		Motors.motorDriveFrontRight.set(Motors.NO_POWER);
		Motors.motorDriveBackLeft.set(Motors.NO_POWER);
		Motors.motorDriveBackRight.set(Motors.NO_POWER);
	}
}