
package org.usfirst.frc.team1111.robot;

import com.kauailabs.navx.frc.AHRS;

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
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    AHRS mxp;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        mxp = new AHRS(SerialPort.Port.kMXP);
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
        SmartDashboard.putNumber("Raw X Accel", mxp.getRawAccelX());
        SmartDashboard.putNumber("Raw Y Accel", mxp.getRawAccelY());
        SmartDashboard.putNumber("Raw Z Accel", mxp.getRawAccelZ());
        
        SmartDashboard.putNumber("Linear X Accel", mxp.getWorldLinearAccelX());
        SmartDashboard.putNumber("Linear Y Accel", mxp.getWorldLinearAccelY());
        SmartDashboard.putNumber("Linear Z Accel", mxp.getWorldLinearAccelZ());
        
        SmartDashboard.putNumber("Angle", mxp.getAngle());
        
        SmartDashboard.putNumber("X Velocity", mxp.getVelocityX());
        SmartDashboard.putNumber("Y Velocity", mxp.getVelocityY());
        SmartDashboard.putNumber("Z Velocity", mxp.getVelocityZ());
        
        SmartDashboard.putNumber("X Displacement", mxp.getDisplacementX());
        SmartDashboard.putNumber("Y Displacement", mxp.getDisplacementY());
        SmartDashboard.putNumber("Z Displacement", mxp.getDisplacementZ());
        
        SmartDashboard.putNumber("X Magnetic", mxp.getRawMagX());
        SmartDashboard.putNumber("Y Magnetic", mxp.getRawMagY());
        SmartDashboard.putNumber("Z Magnetic", mxp.getRawMagZ());
        
        SmartDashboard.putNumber("Horizontal Displcement", Math.sqrt(Math.pow((double)mxp.getDisplacementX(), 2) + Math.pow((double)mxp.getDisplacementZ(), 2)));
        SmartDashboard.putNumber("Horizantal Angle Radians", Math.tan((double)(mxp.getDisplacementZ())/(double)(mxp.getDisplacementX())));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
