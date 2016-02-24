package org.usfirst.frc.team1111.robot;

import variables.Motors;
import variables.Sensors;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
    CameraServer server;//TODO Temp

	final String lowbarShoot = "LowShoot", lowbar = "lowbar", rockwall = "Rockwall", moat = "Moat", ramparts = "Ramp", roughTerrain = "Rough";

	String autoSelected;
	SendableChooser chooser;

	public static String state;//TODO are we using this?
	public static String subState;

	Double startTime = 0.0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		server = CameraServer.getInstance();
        server.setQuality(100);
        //the camera name (ex "cam0") can be found through the roborio web interface TODO TEMP CAMERA CODE
        server.startAutomaticCapture("cam0");
		
		chooser = new SendableChooser();
		chooser.addDefault("Low Bar", lowbar);
//		chooser.addObject("Lowbar Shoot", lowbarShoot);
		chooser.addObject("Moat Auto", moat);
		chooser.addObject("Ramparts Auto", ramparts);
		chooser.addObject("Rough Terrain Auto", roughTerrain);
		chooser.addObject(rockwall, rockwall);
		SmartDashboard.putData("Auto choices", chooser);
		
		Motors.motorInit();

		initDashboard();
	}

	/**
	 * Yes. This is for initializing the Dashboard. Put stuff in here.
	 */
	private void initDashboard()
	{
		SmartDashboard.putBoolean("Ball In", Sensors.intakeLimitSwitch.get() || Sensors.intakeLimitSwitch2.get());
		//		SmartDashboard.putBoolean("Spun Up?", false);
		//SmartDashboard.putString("Arm Position", "We need to make this state machine thing");
		//		SmartDashboard.putNumber("Drive Speed (inches/second)", (Sensors.Encoders.encoderDriveLeft.getRate() + Sensors.Encoders.encoderDriveRight.getRate())/2.0);
		//SmartDashboard.putNumber("Tape Arm Distance", Motors.motorTapeArm.getEncPosition());//TODO UNIT COnversion for this...
		
		//SmartDashboard.putNumber("Arm ENC", Motors.motorArm.getEncPosition());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	public void autonomousInit()
	{
		autoSelected = (String) chooser.getSelected();
//		Motors.motorArm.changeControlMode(CANTalon.TalonControlMode.Position);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		switch (autoSelected) {
		case moat:
			Auto.moat();
			break;
		case ramparts:
			Auto.ramparts();
			break;
		case roughTerrain:
		case rockwall:
			Auto.roughTerrainRockwall();
			break;
//		case lowbarShoot:
//			Auto.lowBarShoot();
//			break;
		default:
			case lowbar:
			Auto.lowBar();
			break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		drive();
		Operator.operate();
//		cameraControl();

		SmartDashboard.putBoolean("Ball In", Sensors.intakeLimitSwitch.get() || Sensors.intakeLimitSwitch2.get());
		//SmartDashboard.putString("Arm Position", "We need to make this state machine thing");
		//SmartDashboard.putNumber("Arm ENC", Motors.motorArm.getEncPosition());
		//		SmartDashboard.putNumber("Drive Speed (inches/second)", (Sensors.Encoders.encoderDriveLeft.getRate() + Sensors.Encoders.encoderDriveRight.getRate())/2.0);

		//		if(Motors.motorShooter.get() > .5)
		//			if(startTime == 0.0)
		//				startTime = Timer.getMatchTime();
		//			else if(Timer.getMatchTime()-startTime <= Motors.SHOOTER_SPIN_TIME)
		//				SmartDashboard.putBoolean("Spun Up?", false);
		//			else
		//				SmartDashboard.putBoolean("Spun Up?", true);
		//		else
		//			startTime = 0.0;
	}

	void drive()
	{
		double right = variables.Joysticks.joyDrive.getRawAxis(3);
		double left = variables.Joysticks.joyDrive.getRawAxis(1);

		Motors.motorDriveFrontLeft.set(left);
		Motors.motorDriveBackLeft.set(left);

		Motors.motorDriveFrontRight.set(-right);
		Motors.motorDriveBackRight.set(-right);
	}

	public void disabledPeriodic()
	{
		Auto.Movement.stopDriveMotors();
//		Motors.brake.setAngle(Motors.BRAKE_ANGLE);
//		Motors.motorArm.set(0.0);//TODO We need to test that this holds.
		Motors.motorInnerIntake.set(0.0);
		Motors.motorOuterIntake.set(0.0);
		Motors.motorShooter.set(0.0);
		Operator.disable();
	}
	
//	static void cameraControl()
//	{
//		if(Operator.shooting)
//		{
//			Sensors.Cameras.driveCam.writeResolution(edu.wpi.first.wpilibj.vision.AxisCamera.Resolution.k160x120);
//			Sensors.Cameras.shootCam.startCapture();
//		}else
//		{
//			Sensors.Cameras.driveCam.writeResolution(edu.wpi.first.wpilibj.vision.AxisCamera.Resolution.k640x480);
//			Sensors.Cameras.shootCam.stopCapture();
//		}
//	}

}