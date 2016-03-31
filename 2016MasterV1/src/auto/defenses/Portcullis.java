package auto.defenses;

import auto.Autonomous;
import auto.Autonomous.Timings;

/**
 * Class that is designed to cross the FRC Stronghold Portcullis defense in Autonomous mode using FRC 1111's robot, Griffin
 * @author Braidan Duffy, FRC Team 1111 programmer
 *
 */
public class Portcullis extends Autonomous {
	static String name = "portcullis";
	
	/**
	 * Constructor for the Portcullis defense to cross it in autonomous
	 * @param p the position the portcullis is in
	 * @param e if PID control on the arm is enabled
	 */
	public Portcullis(int p, boolean e) {
		super(p, name, e);
	}
	
	/**
	 * Method that moves the robot into firing position based on where it starts
	 */
	public void moveToFiringPosition() {
		//Crossing the portcullis
		if (!hasCrossedDefense) { //Tests if the robot has crossed the defense yet
			crossDefense();
		}
		
		//Moving into firing position
		else {
			super.moveToFiringPosition();
		}
	}
	
	/**
	 * Method that crosses the Portcullis defense
	 */
	void crossDefense() {
		//Lowering arm
		if (!isArmLowered) { //Tests if the arm hasn't been lowered yet
			lowerArm();
		}
		
		//Moving to the portcullis
		else if (isArmLowered && !isAtDefense) { //Tests if the arm is lowered and the robot hasn't been to the defense yet
			isAtDefense = moveFor(Timings.timeToPortcullis, false);
		}
		
		//Raising arm
		else if (isAtDefense && !isArmRaised) { //Tests if the arm hasn't been raised
			raiseArm();
		}
		
		//Moving across portcullis
		else if (isArmRaised && !hasCrossedDefense) { //Tests if the arm has been raised
			hasCrossedDefense = moveFor(Timings.timeAcrossPortcullis, false);
		}
	}
}
