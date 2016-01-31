package profile_switch.operator_profiles;

import Variables.Joysticks;
import Variables.Motors;
import edu.wpi.first.wpilibj.CANTalon;

public class OperatorDuffy extends OperatorProfile {
	//*****START OPERATOR SHOOTER METHODS*****
    
    
    public void shoot() {
    	if (Joysticks.joyOp.getRawButton(Joysticks.LEFT_TRIGGER)) {
    		Motors.motorShooter.set(Motors.FULL_POWER);
    		
    		if (Joysticks.joyOp.getRawButton(Joysticks.RIGHT_TRIGGER)) {
    			Motors.motorChassisIntake.set(Motors.FULL_POWER);
    		}
    	}
    	
    	else {
    		Motors.motorShooter.set(Motors.NO_POWER);
    	}
    }
    
    
    //*****START OPERATOR ARM METHODS*****
    
    
    public void testArm() {
    	if (Joysticks.joyOp.getRawButton(Joysticks.X)) {
        	if (Joysticks.joyOp.getRawButton(Joysticks.X)) {
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
    
    public void moveArm() {    	
    	int[] dPadUpRange = {Joysticks.D_PAD_UP, Joysticks.D_PAD_STRAFE_FORWARD_LEFT, Joysticks.
    	if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_UP) { //Moves arm up
    		Motors.motorArm.set(Motors.FULL_POWER);
    	}
    	
    	else if (Joysticks.joyOp.getPOV() == Joysticks.D_PAD_DOWN) { //Moves arm down
    		Motors.motorArm.set(Motors.REVERSE_FULL_POWER);
    	}
    	
    	else { //Stops arm movement
    		Motors.motorArm.set(Motors.NO_POWER);
    	}
    }
}
