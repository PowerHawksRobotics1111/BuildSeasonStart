package util;

import variables.Motors;
import variables.Sensors;

/**
 * Util class that defines movement-based methods for use by robot in autonomous mode
 * @author Duffy
 *
 */
public class Movements {
	
	
	//*****START MOVEMENT CLASS FIELDS*****
	
	
	//TODO Determine and map movement-based class fields
	
	
	//*****END MOVEMENT CLASS FIELDS*****
	
	
	//*****START MOVEMENT METHODS*****
	
	
	/**
	 * Method that orients the robot to a given direction
	 * @Precondition d must be >= -180 and <= 180
	 * 
	 * @param d the direction the robot is supposed to turn to
	 */
	public void orient(int d) {
		double yaw = Sensors.mxp.getYaw();
		
		/**
		 * Variables that test the range that the yaw is at. 
		 */
		boolean isTooHigh = yaw > d + 1, isTooLow = yaw < d - 1;

		if (isTooHigh) { //Tests if robot needs to turn right
			turnInPlace("right");
		}

		else if (isTooLow){ //Tests if robot needs to turn left
			turnInPlace("left");
		}

		else { 
			stopDriveMotors();
		}
	}
	
	public void autoRotate180() {
		Sensors.mxp.reset();
		orient(180);
	}
	
	public void activateDriveMotors(double speed) {
		Motors.driveMotorFrontLeft.set(speed); Motors.driveMotorFrontRight.set(speed); 
		Motors.driveMotorBackLeft.set(speed); Motors.driveMotorBackRight.set(speed);
	}
	
	public void stopDriveMotors()
	{
		Motors.driveMotorFrontRight.set(Motors.NO_POWER);
		Motors.driveMotorBackRight.set(Motors.NO_POWER);
		Motors.driveMotorFrontLeft.set(Motors.NO_POWER);
		Motors.driveMotorBackLeft.set(Motors.NO_POWER);
	}
	
	public void driveDistance() {
		double encoderTicks = Sensors.encoderL.get();
		
		if (Sensors.resetEncoder) {
			Sensors.encoderL.reset();
			Sensors.resetEncoder = false;
		}
		
		if (encoderTicks < Sensors.encoderL.getDistance()) {
			activateDriveMotors(Motors.QUARTER_POWER);
		}
		else{
			stopDriveMotors();
			Sensors.resetEncoder = true;
		}
	}
	
	public void turnInPlace(String direction) {
		if (direction.equals("left")) {
			Motors.driveMotorFrontRight.set(Motors.QUARTER_POWER);
			Motors.driveMotorBackRight.set(Motors.QUARTER_POWER);
			Motors.driveMotorBackRight.set(Motors.REVERSE_QUARTER_POWER);
			Motors.driveMotorBackLeft.set(Motors.REVERSE_QUARTER_POWER);
		}
		
		else {
			Motors.driveMotorFrontRight.set(Motors.REVERSE_QUARTER_POWER);
			Motors.driveMotorBackRight.set(Motors.REVERSE_QUARTER_POWER);
			Motors.driveMotorBackRight.set(Motors.QUARTER_POWER);
			Motors.driveMotorBackLeft.set(Motors.QUARTER_POWER);
		}
	}
}
