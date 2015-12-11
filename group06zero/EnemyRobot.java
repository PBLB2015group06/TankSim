package group06zero;
import java.awt.*;
import robocode.*;
import java.awt.geom.Point2D;

public class EnemyRobot {

	static private String name;
	static private double hp;
	static private double x,y;
	static private double heading;

	EnemyRobot(ScannedRobotEvent e,Group06zerogouki self){
		name = e.getName();
		hp = e.getEnergy();
		heading = e.getHeading();
		double absBearing=e.getBearingRadians()+self.getHeadingRadians();
		x = self.getX()+e.getDistance()*Math.sin(absBearing);
		y = self.getY()+e.getDistance()*Math.cos(absBearing);
		heading = e.getHeadingRadians();
	}

	public void UpdateEnemy(ScannedRobotEvent e,TeamRobot self){
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