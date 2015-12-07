package group06zero;
import java.awt.*;
import robocode.*;
import java.util.List;
import java.util.ArrayList;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Group06zerogouki - a robot by (your name here)
 */
public class Group06zerogouki extends TeamRobot
{
	/**
	 * run: Group06zerogouki's default behavior
	 */
    private StatisticForEvade statsForEvede;
    
    private EvadePattern evadePattern;
    
    private boolean onEvade = false;

    private List<BulletInfo> bulletList = new ArrayList<BulletInfo>();
    
    private double pastVelocity = 0;
    private InertiaDeviationShooting inertiaDeviationShooting;
	private ShootingMethod shootingMethod;
    
    public Group06zerogouki(){
        this.inertiaDeviationShooting = new InertiaDeviationShooting(this);
        this.shootingMethod = inertiaDeviationShooting;
    }

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
            this.pastVelocity = this.getVelocity();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
        this.shootingMethod.fire(1,e);
		fire(1);
        //-------回避するべきかどうか---------
        EnemyRobot eemyRobot = RobotInfoResistry.getRobotInfo(e,this);
        
        double previousHp = enemyRobot.getEnemyHp();
        double currentHp  = e.getEnergy();
        
        if (currentHp != previousHp) {
            onEvade = true;
            double bulletHeading = getHeading() + e.getBearing();
            bulletList.add(new BulletInfo(enemyRobot.getEnemyX(), enemyRobot.getEnemyY(), bulletHeading));
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

    private double getAcceleration(){
        double acceleration = this.getVelocity() - this.pastVelocity;
        return acceleration;
    }

    //1tick後の自分の戦車の位置を取得する
    private Point getNextMyPoint(){
        double myX = this.getX();
        double myY = this.getY();
        double myHeading = this.getHeading();
        double myMoveDistance = this.getVelocity() + this.getAcceleration() / 2;
        return new Point(
				(int)(myX + myMoveDistance * Math.sin(Math.toRadians(myHeading))),
				(int)(myY + myMoveDistance * Math.cos(Math.toRadians(myHeading)))
        );
    }

    //1tick後に自分の戦車が移動した
    public void setTurnGunToTarget(Point targetPoint){
        Point nextMyPoint = this.getNextMyPoint();
        double myTankToEnemyRadian = Math.atan((nextMyPoint.x - targetPoint.x) / (nextMyPoint.y - targetPoint.y));
        double myTankGunToEnemyRadian = myTankToEnemyRadian - this.getGunHeadingRadians();
        this.setTurnGunRightRadians(myTankGunToEnemyRadian);
    }
}
