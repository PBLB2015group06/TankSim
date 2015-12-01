package Group06zero;
import java.awt.*;
import robocode.*;

//慣性偏差射撃
public class InertiaDeviationShooting extends ShootingMethod{
    private Group06zerogouki myTank;

    public InertiaDeviationShooting(Robot myTank){
        this.myTank = myTank;
    }

    public void fire(double bullet_power, ScannedRobotEvent event){
        EnemyRobot enemy = new EnemyRobot(event, myTank);
        Point currentMyPoint = new Point(this.myTank.getX(), this.myTank.getY());
        Point currentEnemyPoint = new Point(enemy.getEnemyX(), enemy.getEnemyY());
        double enemyHeadingRadian = event.getHeadingRadians();
        double enemyVelocity = event.getVelocity();
        Point nextEnemyPoint = new Point(currentEnemyPoint.x + (int)(enemyVelocity * Math.sin(enemyHeadingRadian)),
               currentEnemyPoint.x + (int)(enemyVelocity * Math.cos(enemyHeadingRadian)));
        double myMoveDistance = this.getVelocity + this.getAcceleration() / 2;
        Point nextMyPoint = new Point(currentMyPoint.x + (int)(myMoveDistance * Math.sin(Math.toRadians(this.myTank.getHeading()))),
                currentMyPoint.y + (int)(myMoveDistance * Math.cos(Math.toRadians(this.myTank.getHeading()))));
        double myTankToEnemyRadian = Math.atan((nextMyPoint.x - nextEnemyPoint.x) / (nextMyPoint.y - nextEnemyPoint.y));    // arctan(x/y)
        double myTankGunToEnemyRadian = myTankToEnemyRadian - this.myTank.getGunHeadingRadian;
        myTank.setTurnGunRightRadians(myTankToEnemyRadian);
        this.myTank.fire(bullet_power);
    }
}
