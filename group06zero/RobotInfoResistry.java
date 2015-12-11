package group06zero;
import java.util.ArrayList;

import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

// 敵ロボットの情報をまとめて保持し、ロボットの情報を返すメソッドを持つクラス

static public class RobotInfoResistry {

	private static ArrayList<EnemyRobot> EnemyList = new ArrayList<EnemyRobot>();

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
}