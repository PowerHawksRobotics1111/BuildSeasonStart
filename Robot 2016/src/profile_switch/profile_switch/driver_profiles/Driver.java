package profile_switch.profile_switch.driver_profiles;

import Variables.Joysticks;
import Variables.Motors;

public class Driver {
	public void drive() {
    	Motors.motorDriveFrontLeft.set(Joysticks.joyDrive.getRawAxis(1) * -1);
    	Motors.motorDriveBackLeft.set(Joysticks.joyDrive.getRawAxis(1) * -1);
    	Motors.motorDriveFrontRight.set(Joysticks.joyDrive.getRawAxis(3) * -1);
    	Motors.motorDriveBackRight.set(Joysticks.joyDrive.getRawAxis(3) * -1);
    }
}
