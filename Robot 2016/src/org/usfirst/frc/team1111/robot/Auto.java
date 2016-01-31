package org.usfirst.frc.team1111.robot;

import Variables.Motors;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.*;

public class Auto 
{
//Put contrustor and init in robot class
	AHRS mxp = new AHRS(SerialPort.Port.kMXP);
	
	  public void autoDrive(double d) 
	    {
		  	mxp.reset();
		  	
	    	if(mxp.getAngle() < 0)
	    	{
	    		Motors.motorDriveFrontRight.set(d);
	    		Motors.motorDriveBackRight.set(d);
	    	}
	    	else if(mxp.getAngle() > 0)
	    	{
	    		Motors.motorDriveFrontLeft.set(d);
	    		Motors.motorDriveBackLeft.set(d);
	    	}
	    	else
	    	{
	    		Motors.motorDriveFrontRight.set(d);
	    		Motors.motorDriveFrontLeft.set(d);
	    		Motors.motorDriveBackRight.set(d);
	    		Motors.motorDriveBackLeft.set(d);
	    	}
	    }
	    
	    public void autoBreakPortcullis() 
	    {
	    	autoMoveArm(Motors.REVERSE_QUARTER_POWER); //Lowers front arm
	    	// TODO Wait time or encoder
	    	
	    	autoMoveArm(Motors.NO_POWER);     // stop front arm movement
	    	autoDrive(Motors.QUARTER_POWER);   // move forward
	    	// TODO implement locations stuffs with encoders
	    	
	    	autoMoveArm(Motors.QUARTER_POWER); // lift front arm
	    	// TODO implement wait or encoder
	    	
	    	autoDrive(Motors.QUARTER_POWER);  //move forward
	    	// TODO implement encoder
	    	
	    	//TODO  implement way to get back to neutral zone
	    }
	    
	    //Catergories B & D Rock Wall, Rough Terrarin, Moat, Ramparts, Lowbar
	    public void autoBreakDefault()
	    {
	    	
	    }
	    
	    public void autoMoveArm(double d) {
	    	Motors.motorArm.set(d);
	    }

}
