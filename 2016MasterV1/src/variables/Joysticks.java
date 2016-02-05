package variables;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {

	/**
	 * Joystick port numbers (Duffy, these are zero based)
	 */
	private final static int DRIVER_PORT = 0, OPERATOR_PORT = 1;
	// TODO Should we remove these and just have them in the constructors? Not
	// really worth the code.

	/**
	 * Button number references.
	 */
	public final static int X = 1, A = 2, B = 3, Y = 4, LEFT_BUMPER = 5,
			RIGHT_BUMPER = 6, LEFT_TRIGGER = 7, RIGHT_TRIGGER = 8, START = 10;

	// TODO verify/configure LT and RT (Duffy, what does this mean? Do you want
	// named references for those numbers?

	/**
	 * Named references for the POV directions.
	 */
	public final static int D_PAD_UP = 0, D_PAD_FORWARD_RIGHT = 1,
			D_PAD_RIGHT = 2, D_PAD_BACKWARD_RIGHT = 3, D_PAD_DOWN = 4,
			D_PAD_BACKWARD_LEFT = 5, D_PAD_LEFT = 6, D_PAD_FORWARD_LEFT = 7,
			D_PAD_OFF = -1;

	public final static Joystick joyDrive = new Joystick(0),
			joyOp = new Joystick(1);

	/**
	 * Named references based on the driver profile. TODO add the mapping here
	 * too (in the javadocs).
	 */
	static class Buttons {

		public static final int shootButton = X;
		public static final int intakeButton = Y;
		public static final int outtakeButton = A;
		/** @deprecated */
		public int armUpButton;
		/** @deprecated */
		public int armDownButton;
		public static final int tapeArmExtend = RIGHT_BUMPER;
		public static final int tapeArmRetract = LEFT_BUMPER;
		public static final int tapeArmRotUp = RIGHT_TRIGGER;
		public static final int tapeArmRotDown = LEFT_TRIGGER;
		public static final int overrideKillModifier = START;
	}
}
