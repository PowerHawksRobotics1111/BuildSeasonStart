package auto.defenses;

import auto.Autonomous;
import auto.variables.Timings;

/**
 * Class that is designed to cross the FRC Stronghold Cheval De Frise defense in Autonomous mode using FRC 1111's robot, Griffin
 * @author Braidan Duffy, FRC Team 1111 programmer
 *
 */
public class ChevalDeFrise extends Autonomous{
	static String name = "cheval de frise";
			
	/**
	 * Constructor for the ChevalDeFrise defense
	 * @param p the position the defense is in
	 * @param e if PID control is enabled
	 */
	public ChevalDeFrise(int p, boolean e) {
		super(p, name, e); 
	}
	
	/**
	 * Method that moves the robot into firing position from the given position
	 */
	public void moveToFiringPosition() {		
		//Crossing defense
		if (!crossedDefense) { //Tests if hasn't yet crossed the defense
			crossDefense();
		}
		
		else {
			super.moveToFiringPosition();
		}
	}
	
	/**
	 * Method that crosses the CDF defense
	 */
	void crossDefense() {		
		//Going to edge of the defense
		if (!isAtDefense) { //Tests if the robot has not been to the defense
			reach();	
		}
		
		//Lowering arm
		if (isAtDefense && !armIsLowered) { //Tests if the robot has been to the defense and the arm has not been lowered
			lowerArm();
		}
		
		//Moving across defense
		if (armIsLowered && !crossedDefense) { //Tests if the arm has been lowered and the defense has not been crossed yet
			crossedDefense = moveFor(Timings.timeAcrossCheval, false);
		}
	}	
}
