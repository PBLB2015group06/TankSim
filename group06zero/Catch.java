package group06zero;
import java.awt.geom.Point2D;

import robocode.AdvancedRobot;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 * Catch - a robot by (your name here)
 */
public class Catch extends AdvancedRobot
{

final static double BULLET_POWER=3;//Our bulletpower.
	final static double BULLET_DAMAGE=BULLET_POWER*4;//Formula for bullet damage.
	final static double BULLET_SPEED=20-3*BULLET_POWER;//Formula for bullet speed.

	//Variables
	static double dir=1;
	static double oldEnemyHeading;
	static double enemyEnergy;
	/**
	 * run: Catch's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like

			turnRadarRight(360);


			}
			}


/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double absBearing=e.getBearingRadians()+getHeadingRadians();

		//This makes the amount we want to turn be perpendicular to the enemy.
        //ここは敵機と垂直をなすためのターンの統計をもとめる




		//Finding the heading and heading change.
        //向きを見つける、または向きの変更を見つける
		double enemyHeading = e.getHeadingRadians();
		double enemyHeadingChange = enemyHeading - oldEnemyHeading;
		oldEnemyHeading = enemyHeading;

		/*This method of targeting is know as circular targeting; you assume your enemy will
         このターゲットのメソッドは回るターゲットとして知られる；あなたが敵がどうなるかを想定する
		 *keep moving with the same speed and turn rate that he is using at fire time.The

		 *base code comes from the wiki.
         */
		double deltaTime = 0;
		double predictedX = getX()+e.getDistance()*Math.sin(absBearing);
		double predictedY = getY()+e.getDistance()*Math.cos(absBearing);
		while((++deltaTime) * BULLET_SPEED <  Point2D.Double.distance(getX(), getY(), predictedX, predictedY)){

			//Add the movement we think our enemy will make to our enemy's current X and Y
			predictedX += Math.sin(enemyHeading) * e.getVelocity();
			predictedY += Math.cos(enemyHeading) * e.getVelocity();


			//Find our enemy's heading changes.
			enemyHeading += enemyHeadingChange;

			//If our predicted coordinates are outside the walls, put them 18 distance units away from the walls as we know
			//that that is the closest they can get to the wall (Bots are non-rotating 36*36 squares).
			predictedX=Math.max(Math.min(predictedX,getBattleFieldWidth()-18),18);
			predictedY=Math.max(Math.min(predictedY,getBattleFieldHeight()-18),18);

		}
		//Find the bearing of our predicted coordinates from us.
		double aim = Utils.normalAbsoluteAngle(Math.atan2(  predictedX - getX(), predictedY - getY()));

		//Aim and fire.
		/*setTurnGunRightRadians(Utils.normalRelativeAngle(aim - getGunHeadingRadians()));*/
		/*setFire(BULLET_POWER);
        */




        setAdjustRadarForGunTurn(true);
		setTurnRadarRightRadians(Utils.normalRelativeAngle(absBearing-getRadarHeadingRadians())*2);
		setTurnGunRight(240);


	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like

	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		dir=-dir;
	}
}
