package onofriLiptak;
import robocode.*;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/JuniorRobot.html


public class ArnoldSchwarzenegger extends JuniorRobot {
	private Strategist strategist = StrategistImpl.getInstance(); // Estratega por defecto

	public void setStrategist(Strategist strategist) {
		this.strategist = strategist;
	}

	@Override
	public void run() {
		setColors(blue, blue, blue, blue, blue);
		this.strategist.getStrategyForRunning(this).run(this);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	@Override
	public void onScannedRobot() {
		this.strategist.getStrategyForScannedRobot(this).onScannedRobot(this);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	@Override
	public void onHitByBullet() {
		this.strategist.getStrategyForHitByBullet(this).onHitByBullet(this);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	@Override
	public void onHitWall() {
		this.strategist.getStrategyForHitWall(this).onHitWall(this);
	}

	public Integer getEnergy(){
		return energy;
	}

	public Integer getScannedAngle(){
		return scannedAngle;
	}

	public Integer getScannedEnergy(){
		return scannedEnergy;
	}

	public Integer getScannedDistance(){
		return scannedDistance;
	}


}