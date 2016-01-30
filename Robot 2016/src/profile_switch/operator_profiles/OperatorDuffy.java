package profile_switch.operator_profiles;

import org.usfirst.frc.team1111.robot.Variables;

import edu.wpi.first.wpilibj.CANTalon;

public class OperatorDuffy extends OperatorProfile {
	//*****START OPERATOR SHOOTER METHODS*****
    
    
    public void testShooter() {
    	if (Variables.joyOp.getRawButton(Variables.A)) {
    		shoot();
    	}
    }
    
    public void shoot() {
    	if (Variables.joyOp.getRawButton(Variables.RIGHT_TRIGGER)) {
    		Variables.motorShooter.set(Variables.FULL_POWER);
    	}
    	
    	else {
    		Variables.motorShooter.set(Variables.NO_POWER);
    	}
    }
    
    
    //*****START OPERATOR ARM METHODS*****
    
    
    public void testArm() {
    	if (Variables.joyOp.getRawButton(Variables.X)) {
        	if (Variables.joyOp.getRawButton(Variables.X)) {
        		moveArm(Variables.FRONT);
        		testIntake(Variables.FRONT);
        	}
        	
        	else if (Variables.joyOp.getRawButton(Variables.B)) {
        		moveArm(Variables.BACK);
        		testIntake(Variables.BACK);
        	}
        }
    }
    
    public void testIntake(int side) {
    	CANTalon motorArm;
    	CANTalon motorChassis;
    	
    	if (side == Variables.FRONT) {
    		motorArm = Variables.motorChassisIntakeFront;
    		motorChassis = Variables.motorArmIntakeFront;
    	}
    	
    	else {
    		motorArm = Variables.motorArmIntakeBack;
    		motorChassis = Variables.motorChassisIntakeBack;
    	}
    	
    	if (Variables.joyOp.getRawButton(Variables.Y)) {
    		motorArm.set(Variables.FULL_POWER);
    		motorChassis.set(Variables.FULL_POWER);
    	}
    	
    	else {
    		motorArm.set(Variables.NO_POWER);
    		motorChassis.set(Variables.NO_POWER);
    	}
    }
    
    public void moveArm(int side) {
    	CANTalon motor;
    	
    	if (side == Variables.FRONT) { //Tests which arm is being controlled
    		motor = Variables.motorArmFront;
    	}
    	
    	else {
    		motor = Variables.motorArmBack;
    	}
    	
    	if (Variables.joyOp.getPOV() == Variables.D_PAD_UP) { //Moves arm based on D-Pad input
    		motor.set(Variables.FULL_POWER);
    	}
    	
    	else if (Variables.joyOp.getPOV() == Variables.D_PAD_DOWN) {
    		motor.set(Variables.REVERSE_FULL_POWER);
    	}
    	
    	else {
    		motor.set(Variables.NO_POWER);
    	}
    }
}
