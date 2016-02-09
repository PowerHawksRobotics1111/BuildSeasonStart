package modes;

import util.SDPrinter;
import variables.Sensors;

public class NavXTester {
	
	
	//*****START NAVX CLASS FIELDS*****
	
	
	/**
	 * Default test navX test String
	 */
	final static String DEFAULT_TEST = "Test NavX";
	//TODO Determine and map any necessary class fields as necessary
	
	
	//*****END NAVX CLASS FIELDS*****
	
	
	//*****START NAVX TEST METHODS*****
	
	
	/**
	 * Runs the requested test method
	 */
	public static void run(String method) {
		switch (method) {
		
		case DEFAULT_TEST:
			testNavX(Sensors.orientation);
			break;
		}
	}
	
	/**
	 * Method that tests and prints different aspects of the navX board to tbe SmartDashboard
	 * @param The requested orientation
	 */
	public static void testNavX(double o) {
		double yaw = Sensors.mxp.getYaw();
		SDPrinter.printVariable("Orientation", yaw);
		SDPrinter.printVariable("Target orientation", o);
	}
}
