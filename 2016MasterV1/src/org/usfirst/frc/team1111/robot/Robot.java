package org.usfirst.frc.team1111.robot;

import variables.Motors;
import variables.Sensors;
import auto.Autonomous;
import auto.defenses.ChevalDeFrise;
import auto.defenses.Portcullis;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
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

//	CameraServer server;//TODO Temp

	final String lowBarShoot = "Low Bar | Shoot";
	final String lowBarPos = "Low Bar | Position";
	
	//Category A Defenses
	final String portcullisShoot = "Portcullis | Shoot";
	final String portcullisPos = "Portcullis | Position";
	final String chevalShoot = "Cheval de Frise | Shoot";
	final String chevalPos = "Cheval de Frise | Pos";
	
	//Category B Defenses
	final String moatShoot = "Moat | Shoot";
	final String moatPos = "Moat | Position";
	final String moatDefault = "Moat | Default";
	final String rampartsShoot = "Ramparts | Shoot";
	final String rampartsPos = "Ramparts | Position";
	final String rampartsDefault = "Ramparts | Default";
	
	//Category D defenses
	final String roughTerrainShoot = "Rough Terrain | Shoot";
	final String roughTerrainPos = "Rough Terrain | Pos";
	final String roughTerrainDefault = "Rough Terrain | Default";
	final String rockWallShoot = "Rock Wall | Shoot";
	final String rockWallPos = "Rock Wall | Position";
	final String rockWallDefault = "Rock Wall | Default";
	
	final String spyBoxShoot = "Spy Box | Shoot";
	final String spyBoxPos = "Spy Box | Position";
	final String reach = "Reach";
		
	//Category A Defenses
	Portcullis p;
	ChevalDeFrise cdf;
	
	Autonomous a;

	String autoSelected;
	SendableChooser chooser;

	Double startTime = 0.0;
	
	int pos;
	
	public static Timer timer = new Timer();
	
	boolean pidEnabled = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
//		server = CameraServer.getInstance();
//		server.setQuality(100);
//		//the camera name (ex "cam0") can be found through the roborio web interface TODO TEMP CAMERA CODE
//		server.startAutonomousmaticCapture("cam0");
//		Sensors.Cameras.shootCam.

		chooser = new SendableChooser();
		
		chooser.addObject(lowBarShoot, lowBarShoot);
		chooser.addObject(lowBarPos, lowBarPos);
		
		//Category A Choices
		chooser.addObject(portcullisShoot, portcullisShoot);
		chooser.addObject(portcullisPos, portcullisPos);
		chooser.addObject(chevalShoot, chevalShoot);
		chooser.addObject(chevalPos, chevalPos);
		
		//Category B Choices
		chooser.addObject(moatShoot, moatShoot);
		chooser.addObject(moatPos, moatPos);
		chooser.addObject(rampartsShoot, rampartsShoot);
		chooser.addObject(rampartsPos, rampartsPos);
		
		//Category D Choices
		chooser.addObject(roughTerrainShoot, roughTerrainShoot);
		chooser.addObject(roughTerrainPos, roughTerrainPos);
		chooser.addObject(rockWallShoot, rockWallShoot);
		chooser.addObject(rockWallPos, rockWallPos);
		
		chooser.addObject(spyBoxShoot, spyBoxShoot);
		chooser.addObject(spyBoxPos, spyBoxPos);
		chooser.addObject(reach, reach);
		
		SmartDashboard.putData("Autonomous choices", chooser);

		Motors.motorInit();
//		Motors.lightingControlSpike.set(Relay.Value.kOn);
//		
		Sensors.initUltras();
//		Sensors.armEncoderInit();

		updateDashboard();		
	}

	/**
	 * Method that initializes/updates the dashboard
	 */
	public void updateDashboard()
	{
		SmartDashboard.putBoolean("Ball In", Sensors.intakeLimitSwitch.get() || Sensors.intakeLimitSwitch2.get());
		SmartDashboard.putBoolean("Inner Intake Running", Motors.motorInnerIntake.get() >= 0.2 || Motors.motorInnerIntake.get() <= -0.2);
		SmartDashboard.putBoolean("Outer Intake Running", Motors.motorOuterIntake.get() >= 0.2 || Motors.motorOuterIntake.get() <= -0.2);
		SmartDashboard.putBoolean("Shooting", Operate.shooting);
		SmartDashboard.putBoolean("BallStop Down", Motors.hardBallStop.getAngle() == 45.0);
//		SmartDashboard.putNumber("Arm revolutions", Sensors.getArmRev());
		
		SmartDashboard.putNumber("Left Front Amperage", Motors.motorDriveFrontLeft.getOutputCurrent());
		SmartDashboard.putNumber("Left Back Amperage", Motors.motorDriveBackLeft.getOutputCurrent());
		SmartDashboard.putNumber("Right Front Amperage", Motors.motorDriveFrontRight.getOutputCurrent());
		SmartDashboard.putNumber("Right Back Amperage", Motors.motorDriveBackRight.getOutputCurrent());
		
		SmartDashboard.putNumber("Right Ultra", Sensors.rightUltra.getRangeInches());
		SmartDashboard.putNumber("Left Ultra", Sensors.leftUltra.getRangeInches());
		
		SmartDashboard.putNumber("Starting Position", pos);
		SmartDashboard.putBoolean("PID Control Enabled:", pidEnabled);
	}

	/**
	 * Method that initializes things required for autonomous mode
	 */
	public void autonomousInit()
	{
		autoSelected = (String) chooser.getSelected();
		Motors.initArmPID();
		createDefense();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		switch (autoSelected) {
			//Low Bar tests
			case lowBarShoot:
				a.shoot();
				break;
				
			case lowBarPos:
				a.moveToFiringPosition();
				break;
			
			//Category A tests
			case portcullisShoot:
				p.shoot();
				break;
				
			case portcullisPos:
				p.moveToFiringPosition();
				break;
				
			case chevalShoot:
				cdf.shoot();
				break;
				
			case chevalPos:
				cdf.moveToFiringPosition();
				break;
				
			//Category B tests
			case moatShoot:
				a.shoot();
				break;
				
			case moatPos:
				a.moveToFiringPosition();
				break;
				
			case moatDefault:
				Autonomous.moat();
				break;
				
			case rampartsShoot:
				a.shoot();
				break;
			
			case rampartsPos:
				a.moveToFiringPosition();
				break;
			
			case rampartsDefault:
				Autonomous.ramparts();
				break;
				
			//Category D tests
			case roughTerrainShoot:
				a.shoot();
				break;
				
			case roughTerrainPos:
				a.moveToFiringPosition();
				break;
				
			case roughTerrainDefault:
				Autonomous.roughTerrainRockwall();
				
			case rockWallShoot:
				a.shoot();
				break;
				
			case rockWallPos:
				a.moveToFiringPosition();
				break;
				
			case rockWallDefault:
				Autonomous.roughTerrainRockwall();
				break;
				
			//Spy box tests
			case spyBoxShoot:
				a.lowerArm();
				a.moveTo(a.towerEdge);
				a.shoot();
				break;
				
			case spyBoxPos:
				a.lowerArm();
				a.moveTo(a.towerEdge);
				break;
			
			//Reach tests
			default:
				a.reach();
				break;
		}
	
//		Motors.lightingControlSpike.set(Relay.Value.kOn);
		
		updateDashboard();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		if(!Motors.motorArm.getBrakeEnableDuringNeutral())
			Motors.motorArm.enableBrakeMode(true);
		drive();
		Operate.operate();

//		Motors.lightingControlSpike.set(Relay.Value.kOn);

		//		cameraControl();

		updateDashboard();
	}

	void drive()
	{
		double right = variables.Joysticks.joyDrive.getRawAxis(3);
		double left = variables.Joysticks.joyDrive.getRawAxis(1);

		Motors.motorDriveFrontLeft.set(left);
		Motors.motorDriveBackLeft.set(Motors.BACK_WHEEL_DRIVE_RATIO * left);

		Motors.motorDriveFrontRight.set(-right);
		Motors.motorDriveBackRight.set(Motors.BACK_WHEEL_DRIVE_RATIO * -right);
	}

	public void disabledPeriodic()
	{
		//		Motors.brake.setAngle(Motors.BRAKE_ANGLE); TODO implement?!?!?
		Motors.motorInnerIntake.set(0.0);
		Motors.motorOuterIntake.set(0.0);
		Motors.motorShooter.set(0.0);
		Motors.motorArm.set(0.0);
		Operate.disable();
		updateDashboard();
	}
	
	public void createDefense() {
		pos = (int) SmartDashboard.getNumber("Starting Position");
		pidEnabled = SmartDashboard.getBoolean("PID Control Enabled:");
		
		//Category A tests
		switch (autoSelected) {
		
		case "portcullis":
			p = new Portcullis(pos, pidEnabled);
			break;
		
		case "cheval":
			cdf = new ChevalDeFrise(pos, pidEnabled);
			break;
			
		default:
			a = new Autonomous(pos, pidEnabled);
			break;
		}
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