
package org.usfirst.frc.team1111.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
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
	final String DEFAULT = "General Tester";
	final String ENCODER_TEST = "Encoder Tester";
	final String NAVX_TEST = "NavX Tester";
	final String DISP_TEST = "Displacement Tester";
	final String ENC_RATIO_TEST = "Encoder Ratio Test";
	String autoSelected;
	SendableChooser chooser;


	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		chooser = new SendableChooser();
		chooser.addDefault("General Tester", DEFAULT);
		chooser.addObject("Encoder Tester", ENCODER_TEST);
		chooser.addObject("NavX Tester", NAVX_TEST);
		chooser.addObject("Displacement Tester", DISP_TEST);
		chooser.addObject("Encoder Ratio Test", ENC_RATIO_TEST);
		SmartDashboard.putData("Auto choices", chooser);
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
		mxp.reset(); testMotorFrontRight.setEncPosition(0);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch(autoSelected) {
		case ENCODER_TEST:
			testEncoder();
			break;

		case NAVX_TEST:
			testNavX(TEST_ORIENTATION);
			break;
			
		case DISP_TEST:
			testDisp();
			break;
			
		case ENC_RATIO_TEST:
			getEncoderRatio();
			break;

		default:
			generalTest();
			break;
		}
	}

	

	public void generalTest() {
		testNavX(TEST_ORIENTATION);
		double yaw = mxp.getYaw();
		boolean tooHigh = yaw > TEST_ORIENTATION + 1, tooLow = yaw < TEST_ORIENTATION - 1;
		
		if (TEST_ORIENTATION < 0) {
			orient(TEST_ORIENTATION);

			if (!(tooHigh || tooLow)) {
				driveDistance();
			}
		}

		else if (TEST_ORIENTATION > 0) {
			orient(TEST_ORIENTATION);

			if (!(tooHigh || tooLow)) {
				driveDistance();
			}
		}
	}

	public void testEncoder() {
		int encoderPosLeft = encoderL.get();
		int encoderPosRight = encoderR.get();
		
		boolean encoderLStopped = encoderL.getStopped(), encoderRStopped = encoderR.getStopped();

		printVariable("Ticks Traveled Left", encoderPosLeft);
		printVariable("Ticks Traveled Right", encoderPosRight);
		printVariable("Left Encoder Stopped", encoderLStopped);
		printVariable("Right Encoder Stopped", encoderRStopped);

		if (encoderPosLeft < encoderDist) {
			activateDriveMotors(QUARTER_POWER);
		}

		else {
			stopDriveMotors();
		}
	}
	
	public void getEncoderRatio() {
		if (resetEncoder) {
			encoderL.reset(); encoderR.reset();
			resetEncoder = false;
		}
		
		double encoderLeft = encoderL.get();
		double encoderRight = encoderR.get();
		
		printVariable("EncoderL Value", encoderLeft);
		printVariable("EncoderR Value", encoderRight);
	}

	

	public void autoRotate180()
	{
		mxp.reset();
		orient(180);
	}

	public void orient(int z) {
		double yaw = mxp.getYaw();

		if (yaw > z) {
			turnInPlace("right");
		}

		else if (yaw < z){
			turnInPlace("left");
		}

		else {
			stopDriveMotors();
		}
	}

	public double calcEncoderRatio(double dist) {
		double circumference = Math.PI * DIAMETER;
		return (360 / circumference) * dist;
	}
	
	public void testDisp() {
		double disp = mxp.getDisplacementX();
		printVariable("Displacement", disp);
		
		if (disp <= TEST_DIST) {
			testMotorFrontLeft.set(QUARTER_POWER);
		}
		
		if (disp >= TEST_DIST) {
			testMotorFrontLeft.set(NO_POWER);
		}
	}
	
	public void activateDriveMotors(double speed) {
		testMotorFrontLeft.set(speed); testMotorFrontRight.set(speed); 
		testMotorBackLeft.set(speed); testMotorBackRight.set(speed);
	}
	
	public void stopDriveMotors()
	{
		testMotorFrontRight.set(NO_POWER);
		testMotorBackRight.set(NO_POWER);
		testMotorFrontLeft.set(NO_POWER);
		testMotorBackLeft.set(NO_POWER);
	}
	
	public void driveDistance() {
		double encoderTicks = encoderL.get();
		
		if (resetEncoder) {
			encoderL.reset();
			resetEncoder = false;
		}
		
		if (encoderTicks < encoderDist) {
			activateDriveMotors(QUARTER_POWER);
		}
		else{
			stopDriveMotors();
			resetEncoder = true;
		}
	}
	
	public void turnInPlace(String direction) {
		if (direction.equals("left")) {
			testMotorFrontRight.set(QUARTER_POWER);
			testMotorBackRight.set(QUARTER_POWER);
			testMotorBackRight.set(REVERSE_QUARTER_POWER);
			testMotorBackLeft.set(REVERSE_QUARTER_POWER);
		}
		
		else {
			testMotorFrontRight.set(REVERSE_QUARTER_POWER);
			testMotorBackRight.set(REVERSE_QUARTER_POWER);
			testMotorBackRight.set(QUARTER_POWER);
			testMotorBackLeft.set(QUARTER_POWER);
		}
	}
}
