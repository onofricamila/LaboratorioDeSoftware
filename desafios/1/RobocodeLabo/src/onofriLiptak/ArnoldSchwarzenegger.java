package onofriLiptak;
import robocode.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html


public class ArnoldSchwarzenegger extends JuniorRobot {
	private LaboRobotStrategy LaboRobotStrategyImpl = new LaboRobotStrategyImpl();

	public LaboRobotStrategy getLaboRobotStrategyImpl() {
		return LaboRobotStrategyImpl;
	}

	public void setLaboRobotStrategyImpl(LaboRobotStrategy laboRobotStrategyImpl) {
		LaboRobotStrategyImpl = laboRobotStrategyImpl;
	}

	@Override
	public void run() {
		setColors(blue, blue, blue, blue, blue);
		this.getLaboRobotStrategyImpl().run(this);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		this.getLaboRobotStrategyImpl().onScannedRobot(this);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
		this.getLaboRobotStrategyImpl().onHitByBullet(this);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		this.getLaboRobotStrategyImpl().onHitWall(this);
	}

	public Integer getEnergy(){
		return getEnergy();
	}

	public Integer getScannedBearing(){
		return scannedAngle;
	}

	public Integer getScannedEnergy(){
		return scannedEnergy;
	}

	public Integer getScannedDistance(){
		return scannedDistance;
	}


}