package profile_switch.operator_profiles;

import org.usfirst.frc.team1111.robot.Variables;

public class OperatorProfile {

	//Act Methods
	public void shoot()
	{
		//Normal shoot (buttons x and b)
		if(Variables.joyOp.getRawButton(Variables.X) || Variables.joyOp.getRawButton(Variables.B))
				System.out.print("");//code to shoot
	}
	
	public void arm()
	{
		//Up arm (button y)
		if(Variables.joyOp.getRawButton(Variables.Y))
				System.out.print("");//code to arm
		//Down arm (button a)
		if(Variables.joyOp.getRawButton(Variables.A))
				System.out.print("");//code to reverse arm
	}
	
	public void intake()
	{
		//intake (button lb)
		if(Variables.joyOp.getRawButton(Variables.LEFT_BUMPER))
				System.out.print("");//code to intake
		//outtake (button rb)
		if(Variables.joyOp.getRawButton(Variables.RIGHT_BUMPER))
				System.out.print("");//code to reverse intake
	}
	
	public void intakeArm()
	{
		//Forward intakeArm (button lt)
		if(Variables.joyOp.getRawButton(Variables.LEFT_TRIGGER))
				System.out.print("");//code to intakeArm
		//Reverse intakeArm (button rt)
		if(Variables.joyOp.getRawButton(Variables.RIGHT_TRIGGER))
				System.out.print("");//code to reverse intakeArm
	}
	
	public void intakeSwitcher()
	{
		//switches to forward or backwards intake based on which button on the dpad is pressed (up or down)
		if((Variables.forwardIntake && Variables.joyOp.getPOV() == 4) || (!Variables.forwardIntake && Variables.joyOp.getPOV() == 0))
			Variables.forwardIntake = !Variables.forwardIntake;
	}
}