package auto.variables;

public class Timings {
	/**
	 * Variables that correspond to the time it takes the robot to cross a defense
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeAcrossLowBar = 1, timeAcrossCheval = 1, timeAcrossMoat = 2.5, timeAcrossPortcullis = 1, timeAcrossRamparts = 3.25, 
						   	   timeAcrossRockWall = 1, timeAcrossRoughTerrain = 2;
	
	/**
	 * Variables that correspond to the time it takes the robot to get into shooting position from the respective position
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToShootPosFromCenterLine = 1, timeToShootPosFromEdge = 1;
	
	/**
	 * Variables that correspond to the time it takes the robot to get to the centerline from different defensive positions
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToCenterLineFromPos3 = 1, timeToCenterLineFromPos5 = 1;
	
	/**
	 * Time it takes for the shooter motors to rev up to full speed
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double shooterRevUpTime = 1; 
	
	/**
	 * Time it takes for the arm to be lowered
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToLowerArm = 2;
	
	/**
	 * Time it takes for the robot to reach a defense
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToDefense = 1; 
	
	/**
	 * Time it takes for the robot to raise its arm
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToRaiseArm = 1; 
	
	/**
	 * Time it takes for the robot to reach the door of the portcullis
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToPortcullis = 1;
	
	/**
	 * Time it takes for the robot to eject the ball from the robot
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToEjectBall = 1;
	
	/**
	 * Time it takes for the robot to eject the ball from the robot
	 * @TODO Must be calibrated
	 * @Note time must be in seconds and less than 15
	 */
	public static final double timeToDropPos = 1;
}
