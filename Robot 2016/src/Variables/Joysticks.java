package Variables;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks {
	 public final static int JD_PORT = 1, JO_PORT = 2; //Sets the ports of the joysticks. TODO Configure the ports of the joysticks.
	 public final static int X = 1, A = 2, B = 3, Y = 4, LEFT_BUMPER = 5, RIGHT_BUMPER = 6, LEFT_TRIGGER = 7, RIGHT_TRIGGER = 8; //Maps the buttons on the controller.
	    																											  //TODO verify/configure LT and RT
	 public final static int D_PAD_UP = 0, D_PAD_STRAFE_FORWARD_RIGHT = 45, D_PAD_RIGHT = 90, D_PAD_STRAFE_BACKWARD_RIGHT = 135, D_PAD_DOWN = 180, D_PAD_STRAFE_BACKWARD_LEFT = 225,
	    		  D_PAD_LEFT = 270, D_PAD_STRAFE_FORWARD_LEFT = 315, D_PAD_OFF = -1; //Maps the controller's D-Pad.
	    
	 public final static Joystick joyDrive = new Joystick (0), joyOp = new Joystick(1); // Driver/operator joysticks
}
