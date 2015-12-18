package group06zero;
import robocode.*;
import java.util.*;//for list
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Group06zerogouki - a robot by (your name here)
 */
public class Group06zerogouki extends TeamRobot
{
	List<EnemyRobot> EnemyList = new ArrayList<EnemyRobot>();
	AntiGravity g;

	/**
	 * run: Group06zerogouki's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		g = new AntiGravity(this);
		turnRadarRight(360);
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			g.getMyPosition();
			for (int i = 0; i < EnemyList.size(); i++) {
				g.addFrobot(EnemyList.get(i));
			}
			g.move();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		int i;
		// Replace the next line with any behavior you would like
		if(isTeammate(e.getName())){
			g.addFally(e);
		}else{
			for (i = 0; i < EnemyList.size(); i++) {
				EnemyRobot tmp = EnemyList.get(i);
				if(tmp.getEnemyName().equals(e.getName())){
					tmp.UpdateEnemy(e, this);
					out.println("UPDATE" + e.getName());
					break;
				}
				out.println("LIST:[" + i + "] " + tmp.getEnemyName());
			}
			if(i == EnemyList.size()){
				EnemyList.add((EnemyRobot)new EnemyRobot(e, this));
				out.println("INSERT" + e.getName());
			}
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		turnRadarRight(360);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		turnRadarRight(360);
	}
}