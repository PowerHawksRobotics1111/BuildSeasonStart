package org.usfirst.frc.team1111.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDebug extends Auto {
	final double DIAMETER = 8;
	double encoderRatio, encoderTickRate, encoderDist;
	double orientation;
	
	
	public void printVariables() {
		SmartDashboard.putNumber("Encoder Ratio", encoderRatio);
		SmartDashboard.putNumber("Encoder Tick Rate", encoderTickRate);
		SmartDashboard.putNumber("Encoder Distance", encoderDist);
		SmartDashboard.putNumber("Orientation", orientation);
	}
}
