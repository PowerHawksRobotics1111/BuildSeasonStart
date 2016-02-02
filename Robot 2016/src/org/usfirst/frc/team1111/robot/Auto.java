package org.usfirst.frc.team1111.robot;

import Variables.Motors;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.*;

public class Auto 
{
	//Put contrustor and init in robot class
	AHRS mxp = new AHRS(SerialPort.Port.kMXP);
	double encoderTickRatio;
	final double DIAMETER = 8;
	

	//Catergories B & D Rock Wall, Rough Terrarin, Ramparts, Lowbar
	    public void autoBreakDefault()
	    {
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER); //Moves arm down. TODO add distance to travel parameter
	    	autoDrive(Motors.QUARTER_POWER); //Drives forward across defense. //TODO add distance to travel parameter
	    	autoDrive(Motors.REVERSE_QUARTER_POWER); //Drives backward to neutral zone. TODO add distance to travel parameter
	    }
	    
	    public void autoBreakPortcullis() 
	    {
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER); //Lowers arm. TODO add distance to travel argument
	    	autoDrive(Motors.QUARTER_POWER); //move forward to portcullis. TODO add distance to travel argument	    	
	    	autoMoveArm(Motors.QUARTER_POWER); //Moves arm up to raise portcullis. TODO add distance to travel argument	    	
	    	autoDrive(Motors.QUARTER_POWER);  //move forward under and across portcullis. TODO add distance to travel argument	 
	    	
	    	//TODO  implement way to get back to neutral zone
	    }
	    
	    public void autoBreakSeesaws()
	    {
	    	autoDrive(Motors.QUARTER_POWER); //TODO add distance to travel argument
	    	
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER); //TODO add distance to travel argument
	    	autoDrive(Motors.QUARTER_POWER); //TODO add distance to travel argument
	    	
	    	autoMoveArm(Motors.QUARTER_POWER); //TODO add distance to travel argument
	    	autoDrive(Motors.QUARTER_POWER); //TODO add distance to travel argument
	    }
	    
	    
	    
	    public void autoDrive(double d) 
	    {
	    	double yaw = mxp.getYaw();
	    	mxp.reset();
	    	
		    if (yaw < 5 && yaw > -5)
		    {
		    	autoActivateDriveMotors(d);
		    }
		    
		    else if (yaw >= 5 || yaw <= -5) {
		    	orientStraight(0);
		    }
		    
		    else {
		    	autoActivateDriveMotors(Motors.NO_POWER);
		    }
		}
	    
	    public void autoDrive(double d, double dist) {
	    	double encRatio = calcEncoderRatio(dist), encDist = encRatio * dist, yaw = mxp.getYaw();
	    	
	    	mxp.reset();
	    	
		    if (yaw < 5 && yaw > -5 && Motors.motorDriveFrontLeft.getEncPosition() < encDist)
		    {
		    	autoActivateDriveMotors(d);
		    }
		    
		    else if (yaw >= 5 || yaw <= -5) {
		    	orientStraight(0);
		    }
		    
		    else {
		    	autoActivateDriveMotors(Motors.NO_POWER);
		    }
	    	
	    }
	    
	    protected void autoActivateDriveMotors(double d) {
	    	Motors.motorDriveFrontRight.set(d);
	    	Motors.motorDriveFrontLeft.set(d);
	    	Motors.motorDriveBackRight.set(d);
	    	Motors.motorDriveBackLeft.set(d);
	    }
	    
	    protected void autoDeactivateDriveMotors() {
	    	Motors.motorDriveFrontRight.set(Motors.NO_POWER);
	    	Motors.motorDriveFrontLeft.set(Motors.NO_POWER);
	    	Motors.motorDriveBackRight.set(Motors.NO_POWER);
	    	Motors.motorDriveBackLeft.set(Motors.NO_POWER);
	    }
	    
	    public void autoMoveArm(double d, double dist) 
	    {
	    	double encRatio = calcEncoderRatio(dist);
	    	double distTicks = encRatio * dist;
	    	
	    	if (Motors.motorArm.getEncPosition() < distTicks) {
	    		Motors.motorArm.set(d);
	    	}
	    	
	    	else {
	    		Motors.motorArm.set(Motors.NO_POWER);
	    	}
	    }
	    
	    public void autoMoveArm(double d) {
	    	Motors.motorArm.set(d);
	    }
	    
	    public void autoRotate180()
	    {
	    	mxp.reset();
	    	orientStraight(180);
	    }
	    
	    public void orientStraight(int z) {
	    	double yaw = mxp.getYaw();
	    	
	    	if (yaw > z + 5) {
	    		Motors.motorDriveFrontLeft.set(Motors.QUARTER_POWER);
	    		Motors.motorDriveBackLeft.set(Motors.QUARTER_POWER);
	    	}
	    	
	    	else if (yaw < z - 5){
	    		Motors.motorDriveFrontRight.set(Motors.QUARTER_POWER);
	    		Motors.motorDriveBackRight.set(Motors.QUARTER_POWER);
	    	}
	    	
	    	else {
	    		autoDeactivateDriveMotors();
	    	}
	    }
	    
	    
	    
	    public double calcEncoderRatio(double dist) {
	    	double circumference = Math.PI * DIAMETER;
	    	return (360 / circumference) * dist;
	    }
}
