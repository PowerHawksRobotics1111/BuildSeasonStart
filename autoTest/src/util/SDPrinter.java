package util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Util class that has methods for printing to the SmartDashboard
 * @author arewy_000
 *
 */
public class SDPrinter {
	
	
	//*****START SD PRINT METHODS*****
	
	
	/**
	 * Method that prints a number to the SmartDashboard
	 * @param str name of the variable
	 * @param val that value of the variable
	 */
	public static void printVariable(String key, double val) {
		SmartDashboard.putNumber(key, val);
	}
	
	/**
	 * Method that prints a boolean to the SmartDashboard
	 * @param str name of the variable
	 * @param val that value of the variable
	 */
	public void printVariable(String key, boolean val) {
		SmartDashboard.putBoolean(key, val);
	}
	
	
	//*****END SP PRINT METHODS*****
}
