
package org.usfirst.frc.team1111.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import modes.EncoderTester;
import modes.GeneralTester;
import modes.NavXTester;
import util.SDPrinter;
import variables.Motors;
import variables.Sensors;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String DEFAULT = "General Tester";
	public final static String ENCODER_TEST = "Encoder Tester";
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
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		switch(autoSelected) {
		case ENCODER_TEST:
			EncoderTester.run(ENCODER_TEST);
			break;

		case NAVX_TEST:
			NavXTester.run(NAVX_TEST);
			break;
			
		case DISP_TEST:
			testDisp();
			break;
			
		case ENC_RATIO_TEST:
			EncoderTester.run(ENC_RATIO_TEST);
			break;

		case DEFAULT:
			GeneralTester.run("General Test");
			break;
		}
	}
	
	/**
	 * Method that tests the displacement on the navX board
	 * 
	 * @deprecated
	 */
	public void testDisp() {
		double disp = Sensors.mxp.getDisplacementX();
		SDPrinter.printVariable("Displacement", disp);
		
		if (disp <= EncoderTester.TEST_DIST) {
			Motors.driveMotorFrontLeft.set(Motors.QUARTER_POWER);
		}
		
		if (disp >= EncoderTester.TEST_DIST) {
			Motors.driveMotorFrontLeft.set(Motors.NO_POWER);
		}
	}
	
	
}
