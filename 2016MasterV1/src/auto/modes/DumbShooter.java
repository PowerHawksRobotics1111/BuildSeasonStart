package auto.modes;

import org.usfirst.frc.team1111.robot.Robot;

import auto.util.Movement;
import edu.wpi.first.wpilibj.Ultrasonic;
import variables.Sensors;

public class DumbShooter {

	/**
	 * Range finder for robot
	 */
	static Ultrasonic rangeFinder = new Ultrasonic(4, 3);

	/**
	 * Distance needed for shooting
	 */
	static int maxRange = 12;

	/**
	 * Distance robot has to travel from start position to station position
	 */
	static final int DIST_TO_STATION = 36; // TODO calibrate distance to station

	/**
	 * Distance robot has to travel from station position to shoot position
	 */
	static final int DIST_TO_SHOOT_POS = 36; // TODO calibrate distance to shoot
												// pos

	/**
	 * Angle robot needs to orient to to get to shooting position
	 */
	public static final int TARGET_ORIENTATION = 45; // TODO calibrate needed
														// orientation

	/**
	 * Boolean to see if robot is on station
	 */
	static boolean isOnStation = false;

	/**
	 * Boolean to see if robot is in shooting position
	 */
	static boolean isInPosition = false;

	public static void dumbShoot()
	{
		Movement.driveDistance(DIST_TO_STATION);
		if (Robot.subState.equals("Stopping Motors..."))
			isOnStation = true;

		if (isOnStation)
			Movement.orient(TARGET_ORIENTATION);

		if (Sensors.navX.getYaw() <= TARGET_ORIENTATION + 5 && Sensors.navX.getYaw() >= TARGET_ORIENTATION - 5)
		{
			Movement.driveDistance(DIST_TO_SHOOT_POS);
			if (Robot.subState.equals("Stopping Motors..."))
				isInPosition = true;
		}

		if (isInPosition)
			shoot();
	}

	static void shoot()
	{

	}
}