package group06zero;
import robocode.*;
import java.util.List;
import java.util.ArrayList;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Group06zerogouki - a robot by (your name here)
 */
public class Group06zerogouki extends Robot
{
	/**
	 * run: Group06zerogouki's default behavior
	 */
    private StatisticForEvade statsForEvede;
    private StatisticForAttack statsForAttack;
    
    private EvadePattern evadePattern;
    
    private boolean onEvade = false;

    private List<BulletInfo> bulletList = new ArrayList<>();
    
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(100);
			turnGunRight(360);
			back(100);
			turnGunRight(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(1);
        
        //-------回避するべきかどうか---------
        EmemyRobot ememyRobot = RobotInfoResistry.getRobotInfo(e.getName());
        
        int previousHp = ememyRobot.getHp();
        int currentHp  = e.geEnergy();
        
        if (currentHp != previousHp) {
            onEvade = true;
            double bulletHeading = getHeading() + e.getBearing();
            bulletList.Add(new BulletInfo(x, y, bulletHeading))
            if (statsForEvede.isInfoEnough()) {
                evadePattern = statsForEvede.getMostScoredPattern();
            }
        }
        //---------------------------------
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
        
        statsForEvede.estimateScore(evadePattern, -10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}	
}
