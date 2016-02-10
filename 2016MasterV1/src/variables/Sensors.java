package variables;

import edu.wpi.first.wpilibj.DigitalInput;

public class Sensors {

	/**
	 * Sets the limit switch port
	 */
	public final static int LS_PORT = 1;

	/**
	 * intake Limit Switch init
	 */
	public static final DigitalInput intakeLimitSwitch = new DigitalInput(LS_PORT);
}
