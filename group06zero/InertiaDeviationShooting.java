package group06zero;
import java.awt.*;
import robocode.*;

//慣性偏差射撃
public class InertiaDeviationShooting extends ShootingMethod{
    private TeamRobot myTank;

    public InertiaDeviationShooting(TeamRobot myTank){
        this.myTank = myTank;
    }

    @Override
    public void fire(double bullet_power, ScannedRobotEvent event){
        EnemyRobot enemy = new EnemyRobot(event, myTank);
        Point currentEnemyPoint = new Point((int)enemy.getEnemyX(), (int)enemy.getEnemyY());
        double enemyHeadingRadian = event.getHeadingRadians();
        double enemyVelocity = event.getVelocity();
        //弾が当たるまでの時間を求める
        //時間はCt^2-Bt-A=0の解t
        double enemyVelocityX = enemyVelocity * Math.sin(enemyHeadingRadian);
        double enemyVelocityY = enemyVelocity * Math.cos(enemyHeadingRadian);
        double A = Math.pow(currentEnemyPoint.x, 2) + Math.pow(currentEnemyPoint.y, 2);
        double B = 2 * (enemyVelocityX * currentEnemyPoint.x + enemyVelocityY * currentEnemyPoint.y);
        double C = Math.pow((20 - 3 * bullet_power), 2) - Math.pow(enemyVelocityX, 2) - Math.pow(enemyVelocityY, 2);
        double t = (B + Math.sqrt(Math.pow(B, 2) + 4 * A * C)) / 2;
        Point nextEnemyPoint = new Point(currentEnemyPoint.x + (int)(enemyVelocityX * t),
               currentEnemyPoint.x + (int)(enemyVelocityY * t));
        this.myTank.setTurnGunToTarget(nextEnemyPoint);
        this.myTank.fire(bullet_power);
    }
}
