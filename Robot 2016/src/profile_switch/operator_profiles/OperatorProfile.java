package profile_switch.operator_profiles;

import org.usfirst.frc.team1111.robot.Variables;

public class OperatorProfile {

	//Act Methods
	public void shoot()
	{
		//Normal shoot
		if(Variables.joyOp.getRawButton(Variables.X))
				System.out.print("");//code to shoot
		//Reverse shoot
		if(Variables.joyOp.getRawButton(Variables.B))
				System.out.print("");//code to reverse shoot
	}
	
	public void arm()
	{
		//Up arm
		if(Variables.joyOp.getRawButton(Variables.Y))
				System.out.print("");//code to arm
		//Down arm
		if(Variables.joyOp.getRawButton(Variables.A))
				System.out.print("");//code to reverse arm
	}
	
	public void intake()
	{
		//intake
		if(Variables.joyOp.getRawButton(Variables.LEFT_BUMPER))
				System.out.print("");//code to intake
		//outtake
		if(Variables.joyOp.getRawButton(Variables.RIGHT_BUMPER))
				System.out.print("");//code to reverse intake
	}
	
	public void intakeArm()
	{
		//Forward intakeArm
		if(Variables.joyOp.getRawButton(Variables.LEFT_TRIGGER))
				System.out.print("");//code to intakeArm
		//Reverse intakeArm
		if(Variables.joyOp.getRawButton(Variables.RIGHT_TRIGGER))
				System.out.print("");//code to reverse intakeArm
	}
}
