
package org.usfirst.frc.team1111.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
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
    final String DEFAULT = "General Tester";
    final String ENCODER_TEST = "Encoder Tester";
    final String NAVX_TEST = "NavX Tester";
    String autoSelected;
    SendableChooser chooser;
    
    final double TEST_DIST = 36, DIAMETER = 8;
    final double QUARTER_POWER = .25, REVERSE_QUARTER_POWER = -.25;
	double encoderRatio, encoderTickRate, encoderDist;
	double orientation;
	CANTalon testMotor = new CANTalon(42);
	AHRS mxp = new AHRS(SerialPort.Port.kMXP);
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("General Tester", DEFAULT);
        chooser.addObject("Encoder Tester", ENCODER_TEST);
        chooser.addObject("NavX Tester", NAVX_TEST);
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
    		testEncoder();
    		break;
    		
    	case NAVX_TEST:
    		testNavX();
    		break;
    	default:
    		generalTest();
    		break;
    	}
    }

    public void printVariable(String str, double var) {
		SmartDashboard.putNumber(str, var);
	}
	
	public void generalTest() {
		testEncoder(); testNavX();
		double yaw = mxp.getYaw();
		double neededEncoderTicks = encoderRatio * TEST_DIST;
		double encoderTicks = testMotor.getEncPosition();
		
		if (yaw != 0) {
			orientStraight(0);
		}
		
		else if (encoderTicks < neededEncoder){
			testMotor.set(QUARTER_POWER);
		}
	}
	
	public void testEncoder() {
		calcEncoderRatio(TEST_DIST);
		printVariable("Ticks", testMotor.getEncPosition());
	}
	
	public void testNavX() {
		printVariable("Orientation", mxp.getYaw());
	}
    
    public void autoRotate180()
    {
    	mxp.reset();
    	orientStraight(180);
    }
    
    public void orientStraight(int z) {
    	double yaw = mxp.getYaw();
    	
    	if (yaw > z + 5) {
    		testMotor.set(QUARTER_POWER);
    	}
    	
    	else if (yaw < z - 5){
    		testMotor.set(REVERSE_QUARTER_POWER);
    	}
    	
    	else {
    		testMotor.set(0);
    	}
    }
    
    public double calcEncoderRatio(double dist) {
    	double circumference = Math.PI * DIAMETER;
    	return (360 / circumference) * dist;
    }
    
}
