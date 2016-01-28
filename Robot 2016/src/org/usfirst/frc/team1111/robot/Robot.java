
package org.usfirst.frc.team1111.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    String profileSelected;
    SendableChooser chooser;
    
    //***Start profile choices***
    
    final String DUFFY = "Duffy", KYLE = "Kyle", RICARDO = "Ricardo"; //Sets operator profile names
    
    //***Start CANTalon Motor Variables***
    
    final int MDFR_PORT = 1, MDFL_PORT = 1, MDBR_PORT = 1, MDBL_PORT = 1; //Sets the port number of the drive motors. TODO Configure the drive motor ports.
    final int MAUDF_PORT = 1, MAUDB_PORT = 1; //Sets the port number of the arm manipulation motors. TODO Configure the arm manipulation motor ports.
    final int MAIF_PORT = 1, MAIB_PORT = 1, MCIF_PORT = 1, MCIB_PORT = 1, MS_PORT = 1; //Sets the port number of the arm/chassis intake and shooter motors.
    																				   //TODO Configure the port numbers of the intake and shooter motors.
    final double FULL_POWER = 1.0, THREE_QUARTERS_POWER = .75, HALF_POWER = .5, QUARTER_POWER = .25, NO_POWER = 0.0; //Sets FORWARD motor powers
    final double REVERSE_FULL_POWER = -1.0, REVERSE_THREE_QUARTERS_POWER = -.75, REVERSE_HALF_POWER = -.5, REVERSE_QUARTER_POWER = -.25; //Sets REVERSE motor powers
    final int FRONT = 1, BACK = 2;
    
    CANTalon motorDriveFrontRight, motorDriveFrontLeft, motorDriveBackRight, motorDriveBackLeft; //Drive motors
    CANTalon motorArmFront, motorArmBack; //Arm manipulation motors
    CANTalon motorArmIntakeFront, motorArmIntakeBack, motorChassisIntakeFront, motorChassisIntakeBack, motorShooter; //Arm/chassis intake and shooter motors
    
    //***Start Joystick Variables***
    
    final int JD_PORT = 1, JO_PORT = 2; //Sets the ports of the joysticks. TODO Configure the ports of the joysticks.
    final int X = 1, A = 2, B = 3, Y = 4, LEFT_BUMPER = 5, RIGHT_BUMPER = 6, LEFT_TRIGGER = 7, RIGHT_TRIGGER = 8; //Maps the buttons on the controller.
    																											  //TODO verify/configure LT and RT
    final int D_PAD_UP = 0, D_PAD_STRAFE_FORWARD_RIGHT = 45, D_PAD_RIGHT = 90, D_PAD_STRAFE_BACKWARD_RIGHT = 135, D_PAD_DOWN = 180, D_PAD_STRAFE_BACKWARD_LEFT = 225,
    		  D_PAD_LEFT = 270, D_PAD_STRAFE_FORWARD_LEFT = 315, D_PAD_OFF = -1; //Maps the controller's D-Pad.
    
    Joystick joyDrive, joyOp; // Driver/operator joysticks
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        motorInit(); //Initializes motors
        joystickInit(); //Initializes joysticks
    }
    
    /**
     * Method that initializes the motors on the robot
     */
    private void motorInit() {
    	driveMotorInit();
    	armMotorInit();
    	intakeMotorInit();
    	shooterMotorInit();
    }
    /**
     * Method that initializes the drive motors
     */
    private void driveMotorInit() {
    	motorDriveFrontRight = new CANTalon(MDFR_PORT);
    	motorDriveFrontLeft = new CANTalon(MDFL_PORT);
    	motorDriveBackRight  = new CANTalon(MDBR_PORT);
    	motorDriveBackLeft  = new CANTalon(MDBL_PORT);
    }
    
    /**
     * Method that initializes the arm manipulation motors
     */
    private void armMotorInit() {
    	motorArmFront = new CANTalon(MAUDF_PORT);
    	motorArmBack = new CANTalon(MAUDB_PORT);
    }
    
    /**
     * Method that initializes the shooter motor
     */
    private void shooterMotorInit() {
    	motorShooter = new CANTalon(MS_PORT);
    }
    
    /**
     * Method that initializes the arm/chassis intake motors
     */
    private void intakeMotorInit() {
    	motorArmIntakeFront = new CANTalon(MAIF_PORT);
    	motorArmIntakeBack = new CANTalon(MAIB_PORT);
    	motorChassisIntakeFront = new CANTalon(MCIF_PORT);
    	motorChassisIntakeBack = new CANTalon(MCIB_PORT);
    }
    
    /**
     * Method that initializes the joysticks
     */
    private void joystickInit() {
    	joyDrive = new Joystick(JD_PORT);
    	joyOp = new Joystick(JO_PORT);
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drive();
        testArm();
        testShooter();
        chooseProfile();
    }
    
    private void profileInit() {
    	profileSelected = (String) chooser.getSelected();
//		profileSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Profile selected: " + profileSelected);    }
    
    private void chooseProfile() {
    	profileInit();
    	
    	switch(autoSelected) {
    	case DUFFY:
    		//Load Duffy's Op profile
    		break;
    	case KYLE:
    		//Load Kyle's Op Profile
    		break;
    	case RICARDO:
    		//Load Ricardo's Op Profile
    		break;
    	}
    	
    }
    
    
    //*****START DRIVER METHODS*****
    
    
    private void drive() {
    	motorDriveFrontLeft.set(joyDrive.getRawAxis(1) * -1);
    	motorDriveBackLeft.set(joyDrive.getRawAxis(1) * -1);
    	motorDriveFrontRight.set(joyDrive.getRawAxis(3) * -1);
    	motorDriveBackRight.set(joyDrive.getRawAxis(3) * -1);
    }
    
    
    //*****START OPERATOR SHOOTER METHODS*****
    
    
    private void testShooter() {
    	if (joyOp.getRawButton(A)) {
    		shoot();
    	}
    }
    
    private void shoot() {
    	if (joyOp.getRawButton(RIGHT_TRIGGER)) {
    		motorShooter.set(FULL_POWER);
    	}
    	
    	else {
    		motorShooter.set(NO_POWER);
    	}
    }
    
    
    //*****START OPERATOR ARM METHODS*****
    
    
    private void testArm() {
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
    
    private void testIntake(int side) {
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
    
    private void moveArm(int side) {
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
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
