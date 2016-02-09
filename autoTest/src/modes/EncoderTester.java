package modes;

import org.usfirst.frc.team1111.robot.Robot;

import util.Movement;
import util.SDPrinter;
import variables.Motors;
import variables.Sensors;

public class EncoderTester {
	
	
	//*****START ENCODER TESTER CLASS FIELDS*****
	
	
	final static String ENCODER_TEST = "Encoder Test";
	
	public final static int TEST_DIST = 36;
	//TODO determine and map class fields
	
	
	//*****END ENCODER TESTER CLASS FIELDS*****
	
	
	//*****START ENCODER TESTER METHODS*****
	
	
	public static void run(String method) {
		switch (method) {
			
		case ENCODER_TEST:
			testEncoder();
			break;
		}
	}
	
	public static void testEncoder() {
		final int neededTicks = (int) (TEST_DIST / Sensors.encoderDistPerPulse);
		int encoderPosLeft = Sensors.encoderL.get();
		int encoderPosRight = Sensors.encoderR.get();
		
		boolean encoderLStopped = Sensors.encoderL.getStopped(), encoderRStopped = Sensors.encoderR.getStopped();

		SDPrinter.printVariable("Ticks Traveled Left", encoderPosLeft);
		SDPrinter.printVariable("Ticks Traveled Right", encoderPosRight);
		SDPrinter.printVariable("Left Encoder Stopped", encoderLStopped);
		SDPrinter.printVariable("Right Encoder Stopped", encoderRStopped);

		if (encoderPosLeft < neededTicks) {
			Movement.activateDriveMotors(Motors.QUARTER_POWER);
		}

		else {
			Movement.stopDriveMotors();
		}
	}
	
	
	//*****END ENCODER TESTER METHODS*****
}
