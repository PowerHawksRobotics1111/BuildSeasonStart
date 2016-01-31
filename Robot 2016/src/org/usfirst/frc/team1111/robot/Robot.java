
package org.usfirst.frc.team1111.robot;

import profile_switch.operator_profiles.OperatorProfile;
import profile_switch.profile_switch.driver_profiles.Driver;
import Variables.Joysticks;
import Variables.Motors;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
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
    final String PORTCULLIS = "Portcullis", CHANGE_DEFENSES = "Change Defenses";
    
    String autoSelected;
    String profileSelected;
    SendableChooser chooser;
    
    OperatorProfile operator;
    SendableChooser operatorChooser;
   
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("Break Portcullis", PORTCULLIS);
        chooser.addObject("Break 2 Defenses", CHANGE_DEFENSES);
        SmartDashboard.putData("Auto choices", chooser);
        
        chooser.addDefault("Default Profile", defaultAuto);
        chooser.addObject("Break Portcullis", PORTCULLIS);
        chooser.addObject("Break 2 Defenses", CHANGE_DEFENSES);
        SmartDashboard.putData("Auto choices", chooser);
        
        //Operator
//        operatorChooser = new SendableChooser();
//        operatorChooser.addDefault("Pesswagno", Variables.KYLE);
//        operatorChooser.addObject("Duffy", Variables.DUFFY);
//        operatorChooser.addObject("Ricky", Variables.RICARDO);
//        SmartDashboard.putData("Operator", operatorChooser);
        
        
        motorInit(); //Initializes motors
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
    	Motors.motorDriveFrontRight = new CANTalon(Motors.MDFR_PORT);
    	Motors.motorDriveFrontLeft = new CANTalon(Motors.MDFL_PORT);
    	Motors.motorDriveBackRight  = new CANTalon(Motors.MDBR_PORT);
    	Motors.motorDriveBackLeft  = new CANTalon(Motors.MDBL_PORT);
    }
    
    /**
     * Method that initializes the arm manipulation motors
     */
    private void armMotorInit() {
    	Motors.motorArm = new CANTalon(Motors.MAF_PORT);
    }
    
    /**
     * Method that initializes the shooter motor
     */
    private void shooterMotorInit() {
    	Motors.motorShooter = new CANTalon(Motors.MS_PORT);
    }
    
    /**
     * Method that initializes the arm/chassis intake motors
     */
    private void intakeMotorInit() {
    	Motors.motorChassisIntake = new CANTalon(Motors.MCI_PORT);
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
    public void autonomousInit() 
    {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() 
    {
    	switch(autoSelected) {
//    	case customAuto:
//        //Put custom auto code here   
//            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }
    
    public void autoDrive(double d) 
    {
    	Motors.motorDriveFrontRight.set(d);
    	Motors.motorDriveFrontLeft.set(d);
    	
    	Motors.motorDriveBackRight.set(d);
    	Motors.motorDriveBackLeft.set(d);
    }
    
    public void autoBreakPortcullis() {
    	autoMoveArm(Motors.motorArm, Motors.REVERSE_QUARTER_POWER); //Lowers front arm
    	// TODO Wait time or encoder
    	
    	autoMoveArm(Motors.motorArm, Motors.NO_POWER);     // stop front arm movement
    	autoDrive(Motors.QUARTER_POWER);   // move forward
    	// TODO implement locations stuffs with encoders
    	
    	autoMoveArm(Motors.motorArm, Motors.QUARTER_POWER); // lift front arm
    	// TODO implement wait or encoder
    	
    	autoDrive(Motors.QUARTER_POWER);  //move forward
    	// TODO implement encoder
    	
    	autoDrive(Motors.REVERSE_QUARTER_POWER);  //move backward after clearing defense
    	// TODO implement encoder
    	
    	autoMoveArm(Motors.motorArm, Motors.REVERSE_QUARTER_POWER);  //lower front arm
    	autoMoveArm(Motors.motorArm, Motors.QUARTER_POWER);		   //lift back arm
    	// TODO Wait time or encoder
    	
    	autoDrive(Motors.REVERSE_QUARTER_POWER);  //move backward back under defense
    }
    
    public void autoMoveArm(CANTalon a, double d) {
    	a.set(d);
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drive();
//        testArm();
//        testShooter();
        chooseProfile();
    }
    
    private void profileInit() {
    	profileSelected = (String) chooser.getSelected();
//		profileSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Profile selected: " + profileSelected);    }
    
    private void chooseProfile() {
    	profileInit();
    	
//    	switch(profileSelected) {
//    	case Variables.DUFFY:
//    		//Load Duffy's Op profile
//    		break;
//    	case Variables.KYLE:
//    		//Load Kyle's Op Profile
//    		break;
//    	case Variables.RICARDO:
//    		//Load Ricardo's Op Profile
//    		break;
//    	}
    	
    }
    
    
    //*****START DRIVER METHODS*****
    
    
    private void drive() {
    	Motors.motorDriveFrontLeft.set(Joysticks.joyDrive.getRawAxis(1) * -1);
    	Motors.motorDriveBackLeft.set(Joysticks.joyDrive.getRawAxis(1) * -1);
    	Motors.motorDriveFrontRight.set(Joysticks.joyDrive.getRawAxis(3) * -1);
    	Motors.motorDriveBackRight.set(Joysticks.joyDrive.getRawAxis(3) * -1);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
	
