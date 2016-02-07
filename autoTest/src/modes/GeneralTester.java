package modes;

import variables.Sensors;

public class GeneralTester {
	
	
	//*****START GENERAL TESTER CLASS FIELDS*****
	
	
	final String GENERAL_TEST = "General Tester";
	//TODO Determine and map class fields and place here
	
	
	//*****END GENERAL TESTER CLASS FIELDS*****
	
	
	//*****START GENERAL TESTER METHODS*****
	
	
	public void run(String method) {
		switch (method) {
		
		case GENERAL_TEST:
			generalTest();
			break;
		}
	}
	public void generalTest() {
		NavXTester.testNavX(Sensors.orientation);
		double yaw = Sensors.mxp.getYaw();
		boolean tooHigh = yaw > Sensors.orientation + 1, tooLow = yaw < Sensors.orientation - 1;
		
		if (Sensors.orientation < 0) {
			orient(Sensors.orientation);

			if (!(tooHigh || tooLow)) {
				driveDistance();
			}
		}

		else if (Sensors.orientation > 0) {
			orient(Sensors.orientation);

			if (!(tooHigh || tooLow)) {
				driveDistance();
			}
		}
	}
}
