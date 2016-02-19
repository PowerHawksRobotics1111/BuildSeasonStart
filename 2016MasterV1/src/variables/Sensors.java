package variables;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors {

	/**
	 * Limit switch ports
	 */
	final static int LS_PORT = 1, LS2_PORT = 2;

	/**
	 * intake Limit Switch inits
	 */
	public static final DigitalInput intakeLimitSwitch = new DigitalInput(LS_PORT);
	public static final DigitalInput intakeLimitSwitch2 = new DigitalInput(LS2_PORT);

	public static AHRS navX = new AHRS(SerialPort.Port.kMXP);

	public static class Encoders {

		/**
		 * Encoders on the drive motors
		 */
		public static Encoder encoderDriveLeft = new Encoder(1, 2);
		public static Encoder encoderDriveRight = new Encoder(3, 4);

		
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

		
		/**
		 * Method that resets the encoders
		 */
		public static void resetEncoders()
		{
			encoderDriveLeft.reset();
			encoderDriveRight.reset();
		}

		/**
		 * Method that initializes aspects of the encoders
		 */
		public static void initEncoders()
		{
			resetEncoders();
			distanceTraveledPerTick = calcDistTraveledPerTick();
			encoderDriveLeft.setDistancePerPulse(distanceTraveledPerTick);
		}

		/**
		 * Method that calculates the distance the wheel travels per encoder
		 * tick
		 * 
		 * @return the distance the wheel travels per encoder tick
		 */
		static double calcDistTraveledPerTick()
		{
			double circumference = Math.PI * DIAMETER;
			return circumference / ENCODER_RESOLUTION;
			//TODO Incorporate gear ratio (TBD)
		}
	}
}
