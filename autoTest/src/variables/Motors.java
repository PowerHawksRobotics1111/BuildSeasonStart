package variables;

import edu.wpi.first.wpilibj.CANTalon;

public class Motors {
	public final double QUARTER_POWER = .25, REVERSE_QUARTER_POWER = -.25, NO_POWER = 0;
	
	public CANTalon testMotorFrontLeft = new  CANTalon(47), testMotorFrontRight = new CANTalon(46), testMotorBackLeft = new CANTalon(57),
			 		testMotorBackRight = new CANTalon(51);
}
