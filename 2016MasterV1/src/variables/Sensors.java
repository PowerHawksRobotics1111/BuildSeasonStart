package variables;

import com.kauailabs.navx.frc.AHRS;

//import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Ultrasonic;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Sensors {

	//Limit switch ports
	final static int LS_PORT = 0, LS2_PORT = 1;

	public static final DigitalInput intakeLimitSwitch = new DigitalInput(LS_PORT);
	public static final DigitalInput intakeLimitSwitch2 = new DigitalInput(LS2_PORT);
	
	public static final Ultrasonic leftUltra = new Ultrasonic(2,3), rightUltra = new Ultrasonic(4,5);
	
	public static void initUltras()
	{
		leftUltra.setEnabled(true);
		rightUltra.setEnabled(true);
		
		leftUltra.setAutomaticMode(true);
	}
	
	public static double getUltraAverage()
	{
		return (leftUltra.getRangeInches() + rightUltra.getRangeInches())/2.0;
	}

	public static AHRS navX = new AHRS(SerialPort.Port.kMXP);
	public static int navXRange = 5;

//	public static class Cameras {
//		final static String axisCameraAdress = "";
//		
//			public static final USBCamera shootCam = new USBCamera("cam0");
//			public static final AxisCamera driveCam = new AxisCamera(axisCameraAdress);
//	}
}