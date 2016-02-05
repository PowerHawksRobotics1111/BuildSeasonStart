package variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Motors {

	/**
	 * TODO These need better labels and Javadocs for the weird ones.
	 */

	public static CANTalon motorDriveFrontRight, motorDriveFrontLeft,
			motorDriveBackRight, motorDriveBackLeft; // Drive motors
	public static CANTalon motorArm; // Arm manipulation motors
	public static CANTalon motorIntake, motorShooter; // Arm/chassis intake and
														// shooter motors

	public final static int MAF_PORT = 1; // Sets the port number of the arm
											// manipulation motors. TODO
											// Configure the arm manipulation
											// motor ports.
	public final static int MCI_PORT = 1, MS_PORT = 1; // Sets the port number
														// of the arm/chassis
														// intake and shooter
														// motors.
														// TODO Configure the
														// port numbers of the
														// intake and shooter
														// motors.
	public final static int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1,
			MDBL_PORT = 1; // Sets the port number of the drive motors. TODO
							// Configure the drive motor ports.
	public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75,
			HALF_POWER = .5, QUARTER_POWER = .25, NO_POWER = 0.0; // Sets
																	// FORWARD
																	// motor
																	// powers
	public final static double REVERSE_FULL_POWER = -1.0,
			REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5,
			REVERSE_QUARTER_POWER = -.25; // Sets REVERSE motor powers
	public final static int FRONT = 1, BACK = 2;

	public final static int LS_PORT = 1; // Sets port for the limit switch TODO
											// Set port for the limit switch

	// public final static DigitalInput limitSwitch = new DigitalInput(LS_PORT);
	// MOVED TO SENSORS

	/**
	 * Method that initializes the motors on the robot
	 */
	// TODO NOOO!!!! PLEASE!!! THERE DOESNT NEED TO BE DIFFERENT METHODS FOR
	// THIS!!!!
	public static void motorInit()
	{
		driveMotorInit();
		armMotorInit();
		intakeMotorInit();
		shooterMotorInit();
	}

	/**
	 * Method that initializes the drive motors
	 */
	private static void driveMotorInit()
	{
		motorDriveFrontRight = new CANTalon(MDFR_PORT);
		motorDriveFrontLeft = new CANTalon(MDFL_PORT);
		motorDriveBackRight = new CANTalon(MDBR_PORT);
		motorDriveBackLeft = new CANTalon(MDBL_PORT);
	}

	/**
	 * Method that initializes the arm manipulation motors
	 */
	private static void armMotorInit()
	{
		motorArm = new CANTalon(MAF_PORT);
	}

	/**
	 * Method that initializes the shooter motor
	 */
	private static void shooterMotorInit()
	{
		motorShooter = new CANTalon(MS_PORT);
	}

	/**
	 * Method that initializes the arm/chassis intake motors
	 */
	private static void intakeMotorInit()
	{
		motorIntake = new CANTalon(MCI_PORT);
	}
}
