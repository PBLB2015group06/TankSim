package group06zero;
import java.awt.Point;
//for list
//import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

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

    private EvadePattern evadePattern;
	private double bulletSpeed = 17.0;
	private RobotInfoResistry robotInfoResistry;

    private boolean onEvade = false;

	private BulletInfoContainer bulletInfoContainer;
    
    private double pastVelocity = 0;
    private InertiaDeviationShooting inertiaDeviationShooting;
	private ShootingMethod shootingMethod;
    
    public Group06zerogouki(){
        this.inertiaDeviationShooting = new InertiaDeviationShooting(this);
        this.shootingMethod = inertiaDeviationShooting;
		this.robotInfoResistry = new RobotInfoResistry();
		this.bulletInfoContainer = new BulletInfoContainer();
    }

	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		g = new AntiGravity(this);
		evadePattern = new EvadePattern2(this, g);
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			//this.pastVelocity = this.getVelocity();
			g.getMyPosition();
			//for (int i = 0; i < EnemyList.size(); i++) {
			//	g.addFrobot(EnemyList.get(i));
			//}
			g.move();
			//g.addFpoint(100, getX(), getY());
			//bulletInfoContainer.updateBullets(1);
			evadePattern.execute(bulletInfoContainer);
			bulletInfoContainer.removeBullet(getTime());
			setTurnRadarRight(60);
			execute();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		int i;
        //this.shootingMethod.fire(1,e);
		//fire(1);

        setAvoid(e);
		
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
			out.println("INDERT" + e.getName());
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		
		bulletSpeed = e.getVelocity();
		//bulletInfoContainer.removeBulletInfo(0);
        //statsForEvede.estimateScore(evadePattern, -10);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
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
	
	private void setAvoid(ScannedRobotEvent e) {
		EnemyRobot enemyRobot = robotInfoResistry.getEnemyRobotInfo(e);
		System.out.println(e.getName());
		if (enemyRobot == null) {
			robotInfoResistry.addEnemyRobotInfo(e, this);
			return;
		} else {
			double previousHP = enemyRobot.getEnemyHp();
			double currentHP = e.getEnergy();
			
			if (previousHP != currentHP) {
				onEvade = true;
            	double bulletHeading = getHeading() + e.getBearing();
				double x = getX() + e.getDistance() * Math.sin(bulletHeading);
				double y = getY() + e.getDistance() * Math.cos(bulletHeading);
				
            	bulletInfoContainer.add(new BulletInfo(x, y, bulletHeading, bulletSpeed));
            	if (false) {
                	//evadePattern = statsForEvede.getMostScoredPattern();
            	}
				//bulletInfoContainer.removeBullet();
			}
			robotInfoResistry.addEnemyRobotInfo(e, this);
		}
	}
}
