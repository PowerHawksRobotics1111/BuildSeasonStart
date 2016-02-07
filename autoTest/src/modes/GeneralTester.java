package modes;

import util.Movement;
import variables.Sensors;

public class GeneralTester {
	
	
	//*****START GENERAL TESTER CLASS FIELDS*****
	
	
	final static String GENERAL_TEST = "General Tester";
	//TODO Determine and map class fields and place here
	
	
	//*****END GENERAL TESTER CLASS FIELDS*****
	
	
	//*****START GENERAL TESTER METHODS*****
	
	
	/**
	 * Determines what method needs to be run
	 * @param method The method to be run
	 */
	public static void run(String method) {
		switch (method) {
		
		case GENERAL_TEST:
			generalTest();
			break;
		}
	}
	
	/**
	 * General tester for the test chassis. Tests both the navX and the encoder. 
	 * 
	 * @Note this method will orient the robot to pre-determined orientation and then run the encoder until the robot goes a set distance. 
	 * The robot will re-orient itself if it goes off-angle
	 */
	public static void generalTest() {
		NavXTester.testNavX(Sensors.orientation);
		double yaw = Sensors.mxp.getYaw();
		boolean tooHigh = yaw > Sensors.orientation + 1, tooLow = yaw < Sensors.orientation - 1;
		
		Movement.orient(Sensors.orientation);

		if (!(tooHigh || tooLow)) {
			Movement.driveDistance();
		}
	}
}
