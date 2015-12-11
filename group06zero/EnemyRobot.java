package group06zero;
<<<<<<< HEAD
import java.awt.*;
import robocode.*;
import java.awt.geom.Point2D;

public class EnemyRobot {

	static private String name;
	static private double hp;
	static private double x,y;
	static private double heading;

	EnemyRobot(ScannedRobotEvent e,TeamRobot self){
=======
import robocode.*;
//import java.awt.geom.Point2D;

public class EnemyRobot{

	private String name;
	private double hp;
	private double x,y;
	private double heading;

	EnemyRobot(ScannedRobotEvent e,Group06zerogouki self){
>>>>>>> origin/#12ogura
		name = e.getName();
		hp = e.getEnergy();
		heading = e.getHeading();
		double absBearing=e.getBearingRadians()+self.getHeadingRadians();
		x = self.getX()+e.getDistance()*Math.sin(absBearing);
		y = self.getY()+e.getDistance()*Math.cos(absBearing);
		heading = e.getHeadingRadians();
	}

<<<<<<< HEAD
	public void UpdateEnemy(ScannedRobotEvent e,TeamRobot self){
=======
	public void UpdateEnemy(ScannedRobotEvent e,Group06zerogouki self){
>>>>>>> origin/#12ogura
		hp = e.getEnergy();
		heading = e.getHeading();
		double absBearing=e.getBearingRadians()+self.getHeadingRadians();
		x = self.getX()+e.getDistance()*Math.sin(absBearing);
		y = self.getY()+e.getDistance()*Math.cos(absBearing);
		heading = e.getHeadingRadians();
	}

	public String getEnemyName(){
		return name;
	}
	public double getEnemyHp(){
		return hp;
	}
	public double getEnemyX(){
		return x;
	}
	public double getEnemyY(){
		return y;
	}
	public double getEnemyHeading(){
		return heading;
	}
}