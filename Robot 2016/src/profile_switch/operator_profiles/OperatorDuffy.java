package profile_switch.operator_profiles;

import edu.wpi.first.wpilibj.CANTalon;

public class OperatorDuffy {
	//*****START OPERATOR SHOOTER METHODS*****
    
    
    public void testShooter() {
    	if (joyOp.getRawButton(A)) {
    		shoot();
    	}
    }
    
    public void shoot() {
    	if (joyOp.getRawButton(RIGHT_TRIGGER)) {
    		motorShooter.set(FULL_POWER);
    	}
    	
    	else {
    		motorShooter.set(NO_POWER);
    	}
    }
    
    
    //*****START OPERATOR ARM METHODS*****
    
    
    public void testArm() {
    	if (joyOp.getRawButton(X)) {
        	if (joyOp.getRawButton(X)) {
        		moveArm(FRONT);
        		testIntake(FRONT);
        	}
        	
        	else if (joyOp.getRawButton(B)) {
        		moveArm(BACK);
        		testIntake(BACK);
        	}
        }
    }
    
    public void testIntake(int side) {
    	CANTalon motorArm;
    	CANTalon motorChassis;
    	
    	if (side == FRONT) {
    		motorArm = motorChassisIntakeFront;
    		motorChassis = motorArmIntakeFront;
    	}
    	
    	else {
    		motorArm = motorArmIntakeBack;
    		motorChassis = motorChassisIntakeBack;
    	}
    	
    	if (joyOp.getRawButton(Y)) {
    		motorArm.set(FULL_POWER);
    		motorChassis.set(FULL_POWER);
    	}
    	
    	else {
    		motorArm.set(NO_POWER);
    		motorChassis.set(NO_POWER);
    	}
    }
    
    public void moveArm(int side) {
    	CANTalon motor;
    	
    	if (side == FRONT) { //Tests which arm is being controlled
    		motor = motorArmFront;
    	}
    	
    	else {
    		motor = motorArmBack;
    	}
    	
    	if (joyOp.getPOV() == D_PAD_UP) { //Moves arm based on D-Pad input
    		motor.set(FULL_POWER);
    	}
    	
    	else if (joyOp.getPOV() == D_PAD_DOWN) {
    		motor.set(REVERSE_FULL_POWER);
    	}
    	
    	else {
    		motor.set(NO_POWER);
    	}
    }
}
