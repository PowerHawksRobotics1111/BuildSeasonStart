package variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;

public class Motors {
	
	/**
	 * Drive Motor Variables
	 */
	public static CANTalon motorDriveFrontRight, motorDriveFrontLeft,
						   motorDriveBackRight, motorDriveBackLeft; // Drive motors
	
	/**
	 * Arm Motor variables
	 */
	public static CANTalon motorArm; // Arm manipulation motor
	
	/**
	 * Intake, shooter, and tape extender and rotator motors
	 */
	public static CANTalon motorIntake, motorShooter, motorTapeArmExt, motorTapeArmRot; // Arm intake, shooter, and tape motors
	
	/**
	 * Sets the port for the arm motor
	 */
	public final static int MA_PORT = 1; // Sets the port number of the arm manipulation motors. 
										// TODO Configure the arm manipulation motor ports.
	
	/**
	 * Sets the ports for the intake, shooter, and tape extender/rotator motors
	 */
	public final static int MI_PORT = 1, MS_PORT = 1, MTAE_PORT = 1, 
						    MTAR_PORT = 1; // Sets the port number of the intake and shooter motors. 
										  // TODO Configure the port numbers of the intake and shooter motors.
	
	/**
	 * Sets the ports for drive motors
	 */
	public final static int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1,
						    MDBL_PORT = 1; // Sets the port number of the drive motors. TODO Configure the drive motor ports.
	
	/**
	 * Sets the forward power levels
	 */
	public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, 
							   NO_POWER = 0.0; // Sets FORWARD motor powers
	
	/**
	 * Sets the reverse power levels
	 */
	public final static double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5,
							   REVERSE_QUARTER_POWER = -.25; // Sets REVERSE motor powers

	/**
	 * Sets the limit switch port
	 */
	public final static int LS_PORT = 1; // Sets port for the limit switch TODO Set port for the limit switch

	/**
	 * Method that initializes the motors on the robot
	 */
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
		motorArm = new CANTalon(MA_PORT);
		motorTapeArmExt = new CANTalon(MTAE_PORT);
		motorTapeArmRot = new CANTalon(MTAR_PORT);
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
		motorIntake = new CANTalon(MI_PORT);
	}
}
