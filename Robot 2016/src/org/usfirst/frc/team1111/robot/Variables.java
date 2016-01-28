package org.usfirst.frc.team1111.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;

public class Variables {
	
	public boolean forwardIntake = true;
	
	//*****START PROFILES*****
	
	
	public final static String DUFFY = "Duffy", KYLE = "Kyle", RICARDO = "Ricardo"; //Sets operator profile names
    public final static String MARK = "Mark", SMASHY = "Smashy", HANNAH = "Hannah"; //Sets driver profile names
    
    
    //*****END PROFILES*****
	
	
	//*****START MOTOR PORT VARIABLES*****
	
	
	public final static int MAUDF_PORT = 1, MAUDB_PORT = 1; //Sets the port number of the arm manipulation motors. TODO Configure the arm manipulation motor ports.
    public final static int MAIF_PORT = 1, MAIB_PORT = 1, MCIF_PORT = 1, MCIB_PORT = 1, MS_PORT = 1; //Sets the port number of the arm/chassis intake and shooter motors.
    																				   //TODO Configure the port numbers of the intake and shooter motors.
    public final static int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1, MDBL_PORT = 1; //Sets the port number of the drive motors. TODO Configure the drive motor ports.
    public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, NO_POWER = 0.0; //Sets FORWARD motor powers
    public final static double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5, REVERSE_QUARTER_POWER = -.25; //Sets REVERSE motor powers
    public final static int FRONT = 1, BACK = 2;
    
    
    //*****END MOTOR PORT VARIABLES*****
    
    
    //*****START MOTOR VARIABLES*****
    
    
    public static CANTalon motorDriveFrontRight, motorDriveFrontLeft, motorDriveBackRight, motorDriveBackLeft; //Drive motors
    public static CANTalon motorArmFront, motorArmBack; //Arm manipulation motors
    public static CANTalon motorArmIntakeFront, motorArmIntakeBack, motorChassisIntakeFront, motorChassisIntakeBack, motorShooter; //Arm/chassis intake and shooter motors
    
    
    //*****END MOTOR VARIABLES*****
    
    
    //*****START JOYSTICK VARIABLES*****
    
    
    public final static int JD_PORT = 1, JO_PORT = 2; //Sets the ports of the joysticks. TODO Configure the ports of the joysticks.
    public final static int X = 1, A = 2, B = 3, Y = 4, LEFT_BUMPER = 5, RIGHT_BUMPER = 6, LEFT_TRIGGER = 7, RIGHT_TRIGGER = 8; //Maps the buttons on the controller.
    																											  //TODO verify/configure LT and RT
    public final static int D_PAD_UP = 0, D_PAD_STRAFE_FORWARD_RIGHT = 45, D_PAD_RIGHT = 90, D_PAD_STRAFE_BACKWARD_RIGHT = 135, D_PAD_DOWN = 180, D_PAD_STRAFE_BACKWARD_LEFT = 225,
    		  D_PAD_LEFT = 270, D_PAD_STRAFE_FORWARD_LEFT = 315, D_PAD_OFF = -1; //Maps the controller's D-Pad.
    
    public final static Joystick joyDrive = new Joystick (0), joyOp = new Joystick(1); // Driver/operator joysticks
    
}
