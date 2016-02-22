package variables;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;

public class Sensors {

	//Limit switch ports
	final static int LS_PORT = 0, LS2_PORT = 1;

	public static final DigitalInput intakeLimitSwitch = new DigitalInput(LS_PORT);
	public static final DigitalInput intakeLimitSwitch2 = new DigitalInput(LS2_PORT);

	public static AHRS navX = new AHRS(SerialPort.Port.kMXP);

	public static class Encoders {

		public static Encoder encoderDriveLeft = new Encoder(2, 3);
		public static Encoder encoderDriveRight = new Encoder(4, 5);
		
		/** Boolean to determine if encoders need to be reset */
		public static boolean resetEncoders = true;

		static double distanceTraveledPerTick;
		static int DRIVE_WHEEL_DIAMETER = 6;
		static final int TICKS_PER_ENCODER_REVOLUTION = 48;
		
		/**	encoder revolutions per wheel revolution */
		static final double GEARING_RATIO = 2.84;

		public static void resetEncoders()
		{
			encoderDriveLeft.reset();
			encoderDriveRight.reset();
		}

		public static void initEncoders()
		{
			resetEncoders();
			distanceTraveledPerTick = calcDistTraveledPerTick();
			encoderDriveLeft.setDistancePerPulse(distanceTraveledPerTick);
		}

		/**
		 * Method that calculates the distance the wheel travels per encoder
		 * tick using the circumference, gear ratio, and ticks per revolution
		 * 
		 * @return the distance the wheel travels per encoder tick in inches
		 */
		static double calcDistTraveledPerTick()
		{
			return (Math.PI * (double) DRIVE_WHEEL_DIAMETER) / ((double) TICKS_PER_ENCODER_REVOLUTION * GEARING_RATIO);
		}
	}
}