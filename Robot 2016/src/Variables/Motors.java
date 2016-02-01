package Variables;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

public class Motors {
	public static CANTalon motorDriveFrontRight, motorDriveFrontLeft, motorDriveBackRight, motorDriveBackLeft; //Drive motors
    public static CANTalon motorArm; //Arm manipulation motors
    public static CANTalon motorArmIntake, motorChassisIntake, motorShooter; //Arm/chassis intake and shooter motors
    
    public final static int MAF_PORT = 1; //Sets the port number of the arm manipulation motors. TODO Configure the arm manipulation motor ports.
    public final static int MCI_PORT = 1, MS_PORT = 1; //Sets the port number of the arm/chassis intake and shooter motors.
    																				   //TODO Configure the port numbers of the intake and shooter motors.
    public final static int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1, MDBL_PORT = 1; //Sets the port number of the drive motors. TODO Configure the drive motor ports.
    public final static double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, NO_POWER = 0.0; //Sets FORWARD motor powers
    public final static double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5, REVERSE_QUARTER_POWER = -.25; //Sets REVERSE motor powers
    public final static int FRONT = 1, BACK = 2;
    
    public final static int LS_PORT = 1; //Sets port for the limit switch TODO Set port for the limit switch
    
    public final static DigitalInput limitSwitch = new DigitalInput(LS_PORT);
    
    
}
