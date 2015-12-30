package group06zero;

import java.awt.Point;

import robocode.ScannedRobotEvent;

//ピンポイント射撃
public class PinpointShooting extends ShootingMethod {

	private Group06zerogouki myTank;
	
	public PinpointShooting(Group06zerogouki myTank){
		this.myTank = myTank;
	}
	
	@Override
	public void fire(double bullet_power, ScannedRobotEvent event) {
		EnemyRobot enemy = new EnemyRobot(event, this.myTank);
		Point currentEnemyPoint = new Point((int)enemy.getEnemyX(), (int)enemy.getEnemyY());
		this.myTank.setTurnGunToTarget(currentEnemyPoint);
		this.myTank.fire(bullet_power);
	}

}
