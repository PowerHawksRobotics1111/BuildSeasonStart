package variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Motors {

	/**
	 * Drive Motor Variables
	 */
	public static CANTalon motorDriveFrontRight, motorDriveFrontLeft,
	motorDriveBackRight, motorDriveBackLeft;

	/**
	 * Arm Motor variables
	 */
	public static CANTalon motorArm; 

	/**
	 * Intake, outer intake, shooter, and tape extender and rotator motors
	 */
	public static CANTalon motorIntake, motorOuterIntake, motorShooter, motorTapeArmExt, motorTapeArmRot;

	/**
	 * Sets the port for the arm motor
	 */
	final static int MA_PORT = 1; 

	/**
	 * Ports for the intake, outer intake, shooter, and tape extender/rotator motors
	 */
	final static int MI_PORT = 1, MOI_PORT=1, MS_PORT = 1, MTAE_PORT = 1, 
			MTAR_PORT = 1; 	

	/**
	 * Ports for drive motors
	 */
	final static int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1,
			MDBL_PORT = 1; 	

	/**
	 * Forward power levels
	 */
	public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, 
			NO_POWER = 0.0; 	

	/**
	 * Reverse power levels
	 */
	public final static double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5,
			REVERSE_QUARTER_POWER = -.25; 

	public static final double INTAKE_POWER = 0.0, OUTER_INTAKE_POWER = 0.0, ARM_POWER = 0.0, SHOOTER_POWER = 0.0, TAPE_EXT_POWER = 0.0, TAPE_ROT_POWER = 0.0;

	/**
	 * The PID constants for the Arm PID
	 */
	private static final double ARM_P = 0, ARM_I = 0, ARM_D = 0;
	

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
		
		motorArm.changeControlMode(TalonControlMode.Position);
		motorArm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		motorArm.setPID(ARM_P, ARM_I, ARM_D);
		
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
		
		motorOuterIntake = new CANTalon(MOI_PORT);
	}
}
