package variables;

import edu.wpi.first.wpilibj.DigitalInput;

public class Sensors {

	/**
	 * Sets the limit switch port
	 */
	public final static int LS_PORT = 1, LS2_PORT = 2;

	/**
	 * intake Limit Switch init
	 */
	public static final DigitalInput intakeLimitSwitch = new DigitalInput(LS_PORT);
	public static final DigitalInput intakeLimitSwitch2 = new DigitalInput(LS2_PORT);
	
	/**
	 * Motor arm PID set points.
	 */
	public static class MOTOR_ARM_STATES
	{
		public static final double UP = 0, UNDER_PORT = 0, SEESAW = 0, SEESAW_DOWN = 0, INTAKE_LEVEL = 0, PORT_RAISE = 0;
	}
}
