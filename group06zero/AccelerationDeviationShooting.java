package group06zero;

import java.awt.Point;

import robocode.ScannedRobotEvent;

//加速度偏差射撃
//与えた威力通りには射出されない
public class AccelerationDeviationShooting extends ShootingMethod{
	private Group06zerogouki myTank;
	private double pastVelocity;
	
	public AccelerationDeviationShooting(Group06zerogouki myTank){
		this.myTank = myTank;
		this.pastVelocity = 0;
	}

	@Override
	public void fire(double bullet_power, ScannedRobotEvent event) {
		EnemyRobot enemy = new EnemyRobot(event, myTank);
        Point currentEnemyPoint = new Point((int)enemy.getEnemyX(), (int)enemy.getEnemyY());
        double enemyHeadingRadian = event.getHeadingRadians();
        double enemyVelocity = event.getVelocity();
        double enemyAcceleration = enemyVelocity - this.pastVelocity;
        this.pastVelocity = enemyVelocity;
        //照準を合わせたあと1秒tickで当たると予測できれば発射、そうでないなら発射しない
        double bulletX = (enemyAcceleration / 2 + enemyVelocity + currentEnemyPoint.getX()) * Math.sin(enemyHeadingRadian)
        		- this.myTank.getNextMyPoint().getX();
        double bulletY = (enemyAcceleration / 2 + enemyVelocity + currentEnemyPoint.getY()) * Math.cos(enemyHeadingRadian)
        		- this.myTank.getNextMyPoint().getY();
        double bulletVelocity = Math.sqrt(bulletX * bulletX + bulletY * bulletY);
        if(bulletVelocity < 11.0 || bulletVelocity > 19.7)
        	return;
        bullet_power = (20 - bulletVelocity) / 3;
        Point nextEnemyPoint = new Point(currentEnemyPoint.x
        		+ (int)((enemyVelocity + enemyAcceleration / 2) * Math.sin(enemyHeadingRadian)),
        		currentEnemyPoint.y + (int)((enemyVelocity + enemyAcceleration / 2) * Math.cos(enemyHeadingRadian)));
        this.myTank.setTurnGunToTarget(nextEnemyPoint);
        this.myTank.fire(bullet_power);
	}
}
