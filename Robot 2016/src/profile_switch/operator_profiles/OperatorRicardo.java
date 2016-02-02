package profile_switch.operator_profiles;

import Variables.Joysticks;

public class OperatorRicardo extends OperatorProfile {

	public OperatorRicardo()
	{
		super.shootButton = Joysticks.RIGHT_TRIGGER;
		super.intakeButton = Joysticks.A;
		super.outtakeButton = Joysticks.Y;
		//Move arm up same as super
		//move arm down same as super
//		super.tapeArmExtend; //TODO override the tape arm extention control to operate on R stick
//		super.tapeArmRetract;
		super.tapeArmRotUp = Joysticks.LEFT_BUMPER;
		super.tapeArmRotDown = Joysticks.LEFT_TRIGGER;
		//Override modifier same as super.
	}
}
