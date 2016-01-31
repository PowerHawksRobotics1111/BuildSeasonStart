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
	

	//Catergories B & D Rock Wall, Rough Terrarin, Moat, Ramparts, Lowbar
	    public void autoBreakDefault()
	    {
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER);
	    	autoDrive(Motors.QUARTER_POWER);
	    	//TODO encoder stuff to end of obstacle
	    	autoDrive(Motors.REVERSE_QUARTER_POWER);
	    	//TODO encoder stuff to neutral zone
	    }
	    
	    public void autoBreakPortcullis() 
	    {
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER); //Lowers front arm
	    	
	    	
	    	autoMoveArm(Motors.NO_POWER);     // stop front arm movement
	    	autoDrive(Motors.QUARTER_POWER);   // move forward
	    	// TODO implement locations stuffs with encoders
	    	
	    	autoMoveArm(Motors.QUARTER_POWER); // lift front arm
	    	// TODO implement wait or encoder
	    	
	    	autoDrive(Motors.QUARTER_POWER);  //move forward
	    	// TODO implement encoder
	    	
	    	//TODO  implement way to get back to neutral zone
	    }
	    
	    public void autoBreakSeesaws()
	    {
	    	autoDrive(Motors.QUARTER_POWER);
	    	//TODO encoder stuff to stop at obstacle
	    	
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER);
	    	autoDrive(Motors.QUARTER_POWER);
	    	//TODO encoder stuff to kinda on the obstacle
	    	
	    	autoMoveArm(Motors.QUARTER_POWER);
	    	autoDrive(Motors.QUARTER_POWER);
	    	//TODO encoder stuff to end of obstacle
	    }
	    
	    
	    
	    public void autoDrive(double d) 
	    {
	    	mxp.reset();
			  	
	    	orientStraight(0);
	    	
		    if (mxp.getYaw() < 1 && mxp.getYaw() > -1)
		    {
		    	Motors.motorDriveFrontRight.set(d);
		    	Motors.motorDriveFrontLeft.set(d);
		    	Motors.motorDriveBackRight.set(d);
		    	Motors.motorDriveBackLeft.set(d);
		    }
		}
	    
	    public void autoMoveArm(double d, double dist) 
	    {
	    	calcEncoderRatio(dist);
	    	encoder.
	    	
	    	while(armEncoder)
	    	Motors.motorArm.set(d);
	    }
	    
	    public void autoRotate180()
	    {
	    	mxp.reset();
	    	
	    	while (mxp.getYaw() != 180 || mxp.getYaw() != -180) {
	    		Motors.motorDriveFrontRight.set(Motors.QUARTER_POWER);
	    		Motors.motorDriveBackRight.set(Motors.QUARTER_POWER);
	    	}
	    	
	    	orientStraight(180);
	    }
	    
	    public void orientStraight(int z) {
	    	if (mxp.getYaw() > z) {
	    		while (mxp.getYaw() > z) {
	    			Motors.motorDriveFrontLeft.set(Motors.QUARTER_POWER);
	    			Motors.motorDriveBackLeft.set(Motors.QUARTER_POWER);
	    		}
	    	}
	    	
	    	else {
	    		while (mxp.getYaw() < z) {
	    			Motors.motorDriveFrontRight.set(Motors.QUARTER_POWER);
	    			Motors.motorDriveBackRight.set(Motors.QUARTER_POWER);
	    		}
	    	}
	    }
	    
	    
	    
	    public void calcEncoderRatio(double dist) {
	    	double circumference = Math.PI * DIAMETER;
	    	encoderTickRatio = (360 / circumference) * dist;
	    }
	    
	    

}
