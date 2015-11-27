package group06zero;
import robocode.*;

// 敵ロボットの情報をまとめて保持し、ロボットの情報を返すメソッドを持つクラス

public static class RobotInfoResistry　{

	private static List<EnemyRobot> EnemyList = new ArrayList<EnemyRobot>;

	public static EnemyRobot getEnemy(ScannedRobotEvent e,Robot me){

		EnemyRobot enemy = new EnemyRobot(e,me);

		if(EnemyList.contains(enemy)){
			return EnemyList.get(EnemyList.IndexOf(enemy));			
		} else {
			EnemyList.add(enemy);
			return enemy;
		}
	}
}	