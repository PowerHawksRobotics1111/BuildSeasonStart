package profile_switch.operator_profiles;

import Variables.Joysticks;
import Variables.Motors;
import edu.wpi.first.wpilibj.CANTalon;

public class OperatorProfile {
	
	//Control Variables (Buttons)
	public int shootButton = Joysticks.X;
	public int intakeButton;
	public int outtakeButton;
	public int armUpButton = Joysticks.Y;
	public int armDownButton = Joysticks.A;
	public int tapeArmExtend;
	public int tapeArmRetract;
	public int tapeArmRotUp;
	public int tapeArmRotDown;
	
	public int overrideKillModifier;
	
	
	
	//Act Methods
	public void shoot()
	{
		//Normal shoot (buttons x and b)
		if(Joysticks.joyOp.getRawButton(Joysticks.X) || Joysticks.joyOp.getRawButton(Joysticks.B))
				System.out.print("");//code to shoot
	}
	
	public void arm()
	{
		//Up arm (button y)
		if(Joysticks.joyOp.getRawButton(Joysticks.Y))
				System.out.print("");//code to arm
		//Down arm (button a)
		if(Joysticks.joyOp.getRawButton(Joysticks.A))
				System.out.print("");//code to reverse arm
	}
	
	public void intake()
	{
		//intake (button lb)
		//Starts intake on left bumper
		if(Joysticks.joyOp.getRawButton(Joysticks.LEFT_BUMPER)) {
			startMotor(Motors.motorArmIntake, Motors.FULL_POWER);
			startMotor(Motors.motorChassisIntake, Motors.FULL_POWER);
		}
		
		//Stops intake on left/right bumper
		if (Joysticks.joyOp.getRawButton(Joysticks.LEFT_BUMPER) && Joysticks.joyOp.getRawButton(Joysticks.RIGHT_BUMPER)) {
			stopMotor(Motors.motorArmIntake);
			stopMotor(Motors.motorChassisIntake);
		}
		
		//Stops motor if intake switch is it
		else if (Motors.limitSwitch.get()) {
			stopMotor(Motors.motorArmIntake);
			stopMotor(Motors.motorChassisIntake);
		}
				
		//outtake (button rb)
		if(Joysticks.joyOp.getRawButton(Joysticks.RIGHT_BUMPER))
				System.out.print("");//code to reverse intake
	}
	
	public void intakeArm()
	{
		//Forward intakeArm (button lt)
		if(Joysticks.joyOp.getRawButton(Joysticks.LEFT_TRIGGER))
				System.out.print("");//code to intakeArm
		//Reverse intakeArm (button rt)
		if(Joysticks.joyOp.getRawButton(Joysticks.RIGHT_TRIGGER))
				System.out.print("");//code to reverse intakeArm
	}
	
	private void startMotor(CANTalon m, double speed) {
		m.set(speed);
	}
	
	private void stopMotor(CANTalon m) {
		m.set(Motors.NO_POWER);
	}
}