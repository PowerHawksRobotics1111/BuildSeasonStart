package Variables;

import profile_switch.operator_profiles.OperatorDuffy;
import profile_switch.operator_profiles.OperatorKyle;
import profile_switch.operator_profiles.OperatorProfile;
import profile_switch.operator_profiles.OperatorRicardo;
import profile_switch.profile_switch.driver_profiles.Driver;

public class Profiles {
	public final static OperatorProfile DUFFY = new OperatorDuffy(), KYLE = new OperatorKyle(), RICARDO = new OperatorRicardo(); //Sets operator profile names
	public final static Driver DRIVER = new Driver();
}
