package auto.util;

import variables.Sensors;
import variables.Motors;

public class Movement {
	/**
	 * Method that drives forward at a set power for a set time
	 * @param pow the power the drive motors have
	 */
	public static void driveForwards(double pow) {
		activateDriveMotors(pow);
	}
	
	/**
	 * Method that drives backwards at a set power for a set time
	 * @param pow the power the drive motors have
	 */
	public static void driveBackwards(double pow) {
		activateDriveMotors(-pow);
	}
	
	/**
	 * Method that activates the drive motors at a set power
	 * @param pow the power the drive motors have
	 */
	public static void activateDriveMotors(double pow) {
		activateLeftDriveMotors(pow);
		activateRightDriveMotors(pow);
	}

	/**
	 * Method that stops the drive motors
	 */
	public static void stopDriveMotors() {
		Motors.motorDriveBackLeft.set(0);
		Motors.motorDriveBackRight.set(0);
		Motors.motorDriveFrontLeft.set(0);
		Motors.motorDriveFrontRight.set(0);
	}
	
	/**
	 * Method that orients the robot to a give direction in a zero-point turn
	 * @postcondition the robot will orient to +/- a certain number of degrees to that orientation
	 * @param dir the direction the robot is to turn to
	 * @note handles orienting to within +/- the navX range of the given direction
	 */
	public static void orient(double dir, double pow) {
		double currentDir = Sensors.navX.getYaw();
		
		if (currentDir < dir - Sensors.navXRange) {
			activateLeftDriveMotors(-pow);
			activateRightDriveMotors(pow);
		}
		
		else if (currentDir > dir + Sensors.navXRange) {
			activateLeftDriveMotors(pow);
			activateRightDriveMotors(-pow);
		}
		
		else {
			stopDriveMotors();
		}
	}

	/**
	 * Method that turns the robot left in a zero-point turn
	 * @Precondition robot must already be orientated in the 0 position otherwise it will not work properly
	 * @note handles orienting to within +/- the navX range of -90 degrees
	 */
	public static void turnLeft(double pow) {	
		double currentDir = Sensors.navX.getYaw();
		
		if (currentDir - 90 <= -180) {
			currentDir = 180 + ((currentDir - 90) + 180);
		}
		
		orient(currentDir - 90, pow);
	}
	
	/**
	 * Method that turns the robot right in a zero-point turn
	 * @Precondition robot must already be orientated in the 0 position otherwise it will not work properly
	 * @note handles orienting to within +/- the navX range of 90 degrees
	 */
	public static void turnRight(double pow) {
		double currentDir = Sensors.navX.getYaw();
		
		if (currentDir + 90 >= -180) {
			currentDir = -180 + ((currentDir + 90) - 180);
			orient(currentDir, pow);
		}
		
		else {
			orient(currentDir + 90, pow);
		}
	}

	/**
	 * Method that turns the robot around in a zero-point turn
	 * @param pow the speed at which the robot turns
	 * @note handles orienting to within +/- the navX range of 180 degrees
	 */
	public static void turnAround(double pow) {
		double currentDir = Sensors.navX.getYaw();
		
		if (currentDir > 0) {
			orient(currentDir - 180, pow);
		}
		
		else {
			orient(currentDir + 180, pow);	
		}
	}
	
	/**
	 * Method that activates the left drive motors a set power
	 * @param pow the power the left drive motors are given
	 */
	public static void activateLeftDriveMotors(double pow) {
		Motors.motorDriveFrontLeft.set(-pow);
		Motors.motorDriveBackLeft.set(Motors.BACK_WHEEL_DRIVE_RATIO * -1.0 * pow);
	}
	
	/**
	 * Method that activates the left drive motors a set power
	 * @param pow the power the left drive motors are given
	 */
	public static void activateRightDriveMotors(double pow) {
		Motors.motorDriveFrontRight.set(pow);
		Motors.motorDriveBackRight.set(Motors.BACK_WHEEL_DRIVE_RATIO * pow);
	}
}
