package variables;

import edu.wpi.first.wpilibj.CANTalon;

public class Motors {
	
	
	//*****START MOTOR POWER VARAIBLES*****
	
	
	/**
	 * Sets motor to full power in respective direction
	 */
	public final int FULL_POWER = 1, REVERSE_FULL_POWER = -1;
	
	/**
	 * Sets motor speed to three quarters power in respective direction
	 */
	public final double THREE_Q_POWER = .75, REVERSE_THREE_Q_POWER = -.75;
	
	/**
	 * Sets motor speed to half power in respective direction
	 */
	public final double HALF_POWER = .5, REVERSE_HALF_POWER = -.5;
	
	/**
	 * Sets motor speed to quarter power in respective direction
	 */
	public final double QUARTER_POWER = .25, REVERSE_QUARTER_POWER = -.25; 
	
	/**
	 * Sets motor to no power
	 */
	public final int NO_POWER = 0;
	
	
	//*****END MOTOR POWER VARIABLES*****
	
	
	//*****START DRIVE MOTORS*****
	
	/**
	 * Front left drive motor
	 */
	public CANTalon driveMotorFrontLeft = new  CANTalon(47); 
	
	/**
	 * Front right drive motor
	 */
	public CANTalon driveMotorFrontRight = new CANTalon(46);
	
	/**
	 * Back left drive motor
	 */
	public CANTalon driveMotorBackLeft = new CANTalon(57);
	
	/**
	 * Back right drive motor
	 */
	public CANTalon driveMotorBackRight = new CANTalon(51);
	
	
	//*****END DRIVE MOTORS*****
	
	
	//*****START AUXILLARY MOTORS*****
	
	
	//TODO Determine and map in auxillary motors
	
	
	//*****END AUXILLARY MOTORS*****
}
