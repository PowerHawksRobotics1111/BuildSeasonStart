package variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Servo;

public class Motors {

	//motor variables for drive, arm, intake, outer intake, tape arm, and servo
	public static CANTalon motorDriveFrontRight, motorDriveFrontLeft, motorDriveBackRight, motorDriveBackLeft; 
	public static CANTalon motorArm; 
	public static CANTalon motorInnerIntake, motorOuterIntake, motorShooter, motorTapeArm;
	public static Servo brake;
	public static Servo leftStop, rightStop;

	//motor ports for drive, arm, intake, outer intake, tape arm, and servo
	final static int FRONT_RIGHT_DRIVE = 47, FRONT_LEFT_DRIVE = 52, BACK_RIGHT_DRIVE = 55, BACK_LEFT_DRIVE = 60;  //TODO MDBL Port possibly wrong mapped.
	final static int ARM = 53; 
	final static int INNER_INTAKE = 44, OUTER_INTAKE = 62, SHOOTER = 61, TAPE_ARM = 45; 
	final static int TAPE_BRAKE_SERVO = 0;
	final static int BALL_STOP_LEFT = 1;
	final static int BALL_STOP_RIGHT = 2;
	
	//Motor power variables for forward, reverse, intake, outer intake, arm, shooter, and tape arm
	public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, NO_POWER = 0.0;
	public final static double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5, REVERSE_QUARTER_POWER = -.25;
	public static final double INNER_INTAKE_POWER = .75, OUTER_INTAKE_POWER = .50, ARM_POWER = .55, SHOOTER_POWER = .90, TAPE_ARM_POWER = .75, SHOOTER_REVERSE_POWER = -.5;//TODO sHOOTER REVERSE NOT CALIBRATED
	
	public static final double SHOOTER_SPIN_TIME = 0; //TODO find shooter spin up timea
	public static final double SHOOTER_INTAKE_TIME = 0.0;
	
	public static final double BRAKE_ANGLE = 120; //TODO find accurate breaking angle
	private static final double ARM_VOLTAGE_RAMPRATE = 3;// Volts per second TODO Have to figure the utility of this.
	
	public static final double LEFTSTOP_UP = 0.0;//TODO VALUES!!!
	public static final double RIGHTSTOP_UP = 0.0;
	
	public static final double LEFTSTOP_RETRACTED = 0.0;
	public static final double RIGHTSTOP_RETRACTED = 0.0;
	
	public static final double NEW_ENC_RESOLUTION = 0.0; //TODO
	
	
	public static double calcArmRot()
	{
		return .5 * (motorArm.getEncPosition() / NEW_ENC_RESOLUTION);
	}
	
	/** Motor initialization*/
	public static void motorInit()
	{
		motorDriveFrontRight = new CANTalon(FRONT_RIGHT_DRIVE);
		motorDriveFrontLeft = new CANTalon(FRONT_LEFT_DRIVE);
		motorDriveBackRight = new CANTalon(BACK_RIGHT_DRIVE);
		motorDriveBackLeft = new CANTalon(BACK_LEFT_DRIVE);
		
		motorArm = new CANTalon(ARM);
		//motorArm.setVoltageRampRate(ARM_VOLTAGE_RAMPRATE); TODO this stopped the arm entirely
		
		motorTapeArm = new CANTalon(TAPE_ARM);
		motorTapeArm.setInverted(true);
		
		motorShooter = new CANTalon(SHOOTER);
		
		motorInnerIntake = new CANTalon(INNER_INTAKE);
		motorOuterIntake = new CANTalon(OUTER_INTAKE);
		
		motorOuterIntake.setInverted(true);
		
		brake = new Servo(TAPE_BRAKE_SERVO);
		
		leftStop = new Servo(BALL_STOP_LEFT);
		rightStop = new Servo(BALL_STOP_RIGHT);
		
	}
} 