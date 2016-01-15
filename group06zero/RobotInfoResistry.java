package group06zero;
import java.util.ArrayList;

import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

// 敵ロボットの情報をまとめて保持し、ロボットの情報を返すメソッドを持つクラス

public class RobotInfoResistry {

	private static ArrayList<EnemyRobot> EnemyList = new ArrayList<EnemyRobot>();

	public RobotInfoResistry() {}

	public static EnemyRobot getRobotInfo(ScannedRobotEvent e,TeamRobot me){

		EnemyRobot enemy = new EnemyRobot(e,me);

		if(EnemyList.contains(enemy)){
			EnemyRobot oldEnemy = EnemyList.get(EnemyList.indexOf(enemy));
			EnemyList.get(EnemyList.indexOf(enemy)).UpdateEnemy(e, me);
			return oldEnemy;
		} else {
			EnemyList.add(enemy);
			return enemy;
		}
	}
	
	public ArrayList<EnemyRobot> getEnemyRobotInfoList() {
			return EnemyList;
	}
	
	public EnemyRobot getEnemyRobotInfo(ScannedRobotEvent e) {
		for (EnemyRobot enemy : EnemyList) {
			if (enemy.getEnemyName() == e.getName()) {
				return enemy;
			}
		}
		return null;
	}

	public void addEnemyRobotInfo(ScannedRobotEvent e, TeamRobot me) {
		
		for (EnemyRobot enemy : EnemyList) {
			if (enemy.getEnemyName() == e.getName()) {
				enemy.UpdateEnemy(e, me);
				return;
			}
		}

		EnemyList.add(new EnemyRobot(e, me));
	}
}