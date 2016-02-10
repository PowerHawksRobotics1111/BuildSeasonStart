package variables;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {

	/**
	 * Joystick port numbers
	 */
	private final static int DRIVER_PORT = 0, OPERATOR_PORT = 1;

	/**
	 * Button number references.
	 */
	final static int X = 1, A = 2, B = 3, Y = 4, LEFT_BUMPER = 5,
			RIGHT_BUMPER = 6, LEFT_TRIGGER = 7, RIGHT_TRIGGER = 8, START = 10;

	/**
	 * Named references for the POV directions.
	 */
	public final static int D_PAD_UP = 0, D_PAD_FORWARD_RIGHT = 1,
			D_PAD_RIGHT = 2, D_PAD_BACKWARD_RIGHT = 3, D_PAD_DOWN = 4,
			D_PAD_BACKWARD_LEFT = 5, D_PAD_LEFT = 6, D_PAD_FORWARD_LEFT = 7,
			D_PAD_OFF = -1;

	/**
	 * References for joysticks
	 */
	public final static Joystick joyDrive = new Joystick(DRIVER_PORT),
			joyOp = new Joystick(OPERATOR_PORT);

	/**
	 * Named references based on the driver profile.
	 */
	public static class Buttons {

		public static final int shootButton = X;
		public static final int intakeButton = Y;
		public static final int outtakeButton = A;
		public static final int tapeArmExtend = RIGHT_BUMPER;
		public static final int tapeArmRetract = LEFT_BUMPER;
		public static final int tapeArmRotUp = RIGHT_TRIGGER;
		public static final int tapeArmRotDown = LEFT_TRIGGER;
		public static final int overrideKillModifier = START;
	}
}
