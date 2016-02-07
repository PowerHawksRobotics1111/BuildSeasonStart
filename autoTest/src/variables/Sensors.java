package variables;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

/**
 * Variables class that contains sensor variables necessary for function and declaration of necessary sensors
 * @author Duffy
 *
 */
public class Sensors {
	
	
	//*****START GENERAL VARIABLES*****
	
	
	/**
	 * Diameter of the wheels on the test chassis
	 */
	public final double DIAMETER = 6; 
	
	/**
	 * Orientation  goal of the robot test
	 */
	public final int TEST_ORIENTATION = -45;
	
	
	//*****END GENERAL VARIABLES
	
	
	 //*****START ENCODER VARIABLES*****
	/**
	 * Encoders created based on DIO ports they're plugged into
	 */
	public Encoder encoderL = new Encoder(4, 3), encoderR = new Encoder(2, 1); 
	
	/**
	 * Boolean for reseting encoders 
	 * @TODO determine if this is necessary here
	 */
	public boolean resetEncoder = true; 
	
	/**
	 * The number of ticks the encoder makes in one rotation (resolution) 
	 * @TODO set encoder resolution
	 */
	public int encoderResolution = 1; 
	
	/**
	 * The distance the wheel travels per tick of the encoder
	 */
	public double encoderDistPerPulse = calcDistancePerPulse(); 
	
	
	//*****END ENCODER VARIABLES*****
	
	
	//*****START NAVX VARIABLES*****
	
	
	/**
	 * navX board for autonomous function/testing
	 */
	public static AHRS mxp = new AHRS(SerialPort.Port.kMXP); 
	
	
	//*****END NAVX VARIABLES*****
	
	
	//*****START SENSOR METHODS*****
	
	
	/**
	 * Method that initializes the encoders i.e. sets them to zero and sets their ratios
	 */
	public void initEncoders() {
		resetEncoders(); //Sets encoder values to 0
		
//		encoderL.setDistancePerPulse(encoderDistPerPulse); encoderR.setDistancePerPulse(encoderDistPerPulse) //Sets encoder ratio for getDistance()
	}
	
	/**
	 * Method that resets the encoders to a value of 0
	 */
	public void resetEncoders() {
		encoderL.reset(); encoderR.reset();
	}
	
	/**
	 * Method that calculates the wheels distance of travel per encoder tick/pulse (encoder ratio)
	 * @return the encoder ratio
	 */
	private double calcDistancePerPulse() {
		double circumference = Math.PI * DIAMETER;
		return circumference / encoderResolution;
	}
	
	
	//*****END SENSOR METHODS*****
}
