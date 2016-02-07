package modes;

import util.SDPrinter;
import variables.Sensors;

public class NavXTester {
	
	
	//*****START NAVX CLASS FIELDS*****
	
	
	/**
	 * Wanted orientation for testing.
	 * @Precondtion orientation must be >= -180 and <= 180;
	 */
	public int orientation = 45;
	
	/**
	 * Default test navX test String
	 */
	final String DEFAULT_TEST = "Test NavX";
	//TODO Determine and map any necessary class fields as necessary
	
	
	//*****END NAVX CLASS FIELDS*****
	
	
	//*****START NAVX TEST METHODS*****
	
	
	/**
	 * Runs the requested test method
	 */
	public void run(String method) {
		switch (method) {
		
		case DEFAULT_TEST:
			testNavX(orientation);
			break;
		}
	}
	
	/**
	 * Method that tests and prints different aspects of the navX board to tbe SmartDashboard
	 * @param The requested orientation
	 */
	public void testNavX(double o) {
		double yaw = Sensors.mxp.getYaw();
		SDPrinter.printVariable("Orientation", yaw);
		SDPrinter.printVariable("Target orientation", o);
	}
}
