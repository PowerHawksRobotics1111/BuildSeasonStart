package variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Motors {

	//motor variables for drive, arm, intake, outer intake, and tape arm
	public static CANTalon motorDriveFrontRight, motorDriveFrontLeft, motorDriveBackRight, motorDriveBackLeft; 
	public static CANTalon motorArm; 
	public static CANTalon motorIntake, motorOuterIntake, motorShooter, motorTapeArm;

	//motor ports for drive, arm, intake, outer intake, and tape arm
	final static int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1, MDBL_PORT = 1; 
	final static int MA_PORT = 1; 
	final static int MI_PORT = 1, MOI_PORT = 1, MS_PORT = 1, MTA_PORT = 1; 
	
	//Motor power variables for forward, reverse, intake, outer intake, arm, shooter, and tape arm
	public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, NO_POWER = 0.0;
	public final static double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5, REVERSE_QUARTER_POWER = -.25;
	public static final double INTAKE_POWER = 0.0, OUTER_INTAKE_POWER = 0.0, ARM_POWER = 0.0, SHOOTER_POWER = 0.0, TAPE_ARM_POWER = 0.0;
	
	public static final double SHOOTER_SPIN_TIME = 0; //TODO find shooter spin up time
	
	/** Motor initialization*/
	public static void motorInit()
	{
		motorDriveFrontRight = new CANTalon(MDFR_PORT);
		motorDriveFrontLeft = new CANTalon(MDFL_PORT);
		motorDriveBackRight = new CANTalon(MDBR_PORT);
		motorDriveBackLeft = new CANTalon(MDBL_PORT);
		
		motorArm = new CANTalon(MA_PORT);
		
		motorTapeArm = new CANTalon(MTA_PORT);
		
		motorShooter = new CANTalon(MS_PORT);
		
		motorIntake = new CANTalon(MI_PORT);
		motorOuterIntake = new CANTalon(MOI_PORT);
	}
} 