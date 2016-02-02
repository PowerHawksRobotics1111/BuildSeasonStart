package profile_switch.operator_profiles;

import Variables.Joysticks;
import Variables.Motors;

public class OperatorDuffy extends OperatorProfile {
	
	public OperatorDuffy()
	{
		super.shootButton = Joysticks.LEFT_TRIGGER;
		super.intakeButton = Joysticks.RIGHT_BUMPER;
		super.outtakeButton = Joysticks.LEFT_BUMPER;
		//Move arm up same as super
		//move arm down same as super
		super.tapeArmExtend = Joysticks.Y;
		super.tapeArmRetract = Joysticks.A;
		//TODO Override the control for the tape arm rotate to operate on stick.
		//Override modifier same as super.
	}
}
