package variables;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors {

	/**
	 * Sets the limit switch port
	 */
	public final static int LS_PORT = 1, LS2_PORT = 2;

	/**
	 * intake Limit Switch init
	 */
	public static final DigitalInput intakeLimitSwitch = new DigitalInput(LS_PORT);
	public static final DigitalInput intakeLimitSwitch2 = new DigitalInput(LS2_PORT);
	
	public static AHRS navX = new AHRS(SerialPort.Port.kMXP);
	
	/**
	 * Motor arm PID set points.
	 */
	public static class MOTOR_ARM_STATES
	{
		public static final double UP = 0, UNDER_PORT = 0, SEESAW = 0, SEESAW_DOWN = 0, INTAKE_LEVEL = 0, PORT_RAISE = 0;
	}
	
	public static class Encoders {

		
		//*****START ENCODER INITIALIZERS*****
		
		
		/**
		 * Encoder on the left drive motors
		 */
		public static Encoder encoderDriveLeft = new Encoder(1, 2);
		
		
		//*****END ENCODER INITIALIZERS*****
		
		
		//*****START ENCODER VARIABLES*****
		
		
		/**
		 * Boolean to determine if encoders need to be reset
		 */
		public static boolean resetEncoders = true;

		/**
		 * The distance the wheel travels per encoder tick
		 */
		static double distanceTraveledPerTick;
		
		/**
		 * The diameter of the drive wheels
		 */
		static int DIAMETER = 6;
		
		/**
		 * The number of ticks per encoder revolution
		 */
		static final int ENCODER_RESOLUTION = 48;
			
		
		//*****END ENCODER VARIABLES*****
		
		
		//*****START ENCODER METHODS*****
		
		/**
		 * Method that resets the encoders to 0
		 */
		public static void resetEncoders() {
			encoderDriveLeft.reset();
		}
		
		/**
		 * Method that initializes aspects of the encoders
		 */
		public static void initEncoders() {
			resetEncoders();
			distanceTraveledPerTick = calcDistTraveledPerTick();
			encoderDriveLeft.setDistancePerPulse(distanceTraveledPerTick);
		}
		
		/**
		 * Method that calculates the distance the wheel travels per encoder tick
		 * @return the distance the wheel travels per encoder tick
		 */
		static double calcDistTraveledPerTick() {
			double circumference = Math.PI * DIAMETER;
			return circumference / ENCODER_RESOLUTION;
		}
		
		
		//*****END ENCODER METHODS*****
	}
}
