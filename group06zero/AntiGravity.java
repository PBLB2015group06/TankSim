package group06zero;

import robocode.ScannedRobotEvent;

public class AntiGravity{
	static private double width  = 0;
	static private double height = 0;
	private double selfX = 0;
	private double selfY = 0;
	private double dis = 0;
	private double F = 0;
	private int G =     3 *(int)Math.pow(10,6);//Gravitational constant of Enemy
	private int allyG = 3 *(int)Math.pow(10,7);//Gravitational constant of Ally
	private int wallG = 3 *(int)Math.pow(10,7);//Gravitational constant of Wall
	
	private double xF = 0;
	private double yF = 0;
	private double ang = 0;
	
	private String ally1 = "";
	private double ally1xF = 0;
	private double ally1yF = 0;
	private String ally2 = "";
	private double ally2xF = 0;
	private double ally2yF = 0;

	private Group06zerogouki self;

	AntiGravity(Group06zerogouki me){
		self = me;
		//Field info
		width  = self.getBattleFieldWidth();
		height = self.getBattleFieldHeight();
	}

	public void getMyPosition(){
		//My position(absolute)
		selfX = self.getX();
		selfY = self.getY();
	}

	public void addFrobot(EnemyRobot e){
		double x = 0;
		double y = 0;
		//Enemy's position(relative)
		x = e.getEnemyX() - selfX;
		y = e.getEnemyY() - selfY;
		//Distance
		dis = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		//Force
		F = G/(Math.pow(x,2)+Math.pow(y,2));
		xF -= F*x/dis;
		yF -= F*y/dis;
		
		System.out.println("ENEMY F : " + F +" (x..."+ F*x/dis + ", y..." + F*y/dis + ")");
	}

	public void addFally(ScannedRobotEvent e){
		double x = 0;
		double y = 0;
		double ang = e.getBearingRadians()+self.getHeadingRadians();
		double dis = e.getDistance();
		String name = e.getName();

		if(!ally1.equals(name)){
			ally1 = e.getName();
		}else if(!ally2.equals(name)){
			ally2 = e.getName();
		}

		//Ally's position(relative)
		x = dis*Math.sin(ang);
		y = dis*Math.cos(ang);

		//Force
		F = allyG/Math.pow(dis,2);
		if(ally1.equals(name)){
			ally1xF = F*x/dis ;
			ally1yF = F*y/dis ;
		}else if(ally2.equals(name)){
			ally2xF = F*x/dis ;
			ally2yF = F*y/dis ;
		}
	}
	
	public void addFpoint(double exG, double x, double y){
		//relative position
		x = x - selfX;
		y = y - selfY;
		//Distance
		dis = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		//Force
		F = exG/Math.pow(dis,2);
		xF -= F*x/dis;
		yF -= F*y/dis;
	}

	public void move(){
		double F = 0;
		//distance which robots avoid from wall
		int avoid = 10;
		
		//add force from wall
		xF += wallG/Math.pow(selfX, 3);//from Left
		yF += wallG/Math.pow(selfY, 3);//from Bottom
		xF -= wallG/Math.pow(width - selfX, 3);//from Right
		yF -= wallG/Math.pow(height - selfY, 3);//from Top

		//add force from ally
		xF -= ally1xF + ally2xF;
		yF -= ally1yF + ally2yF;

		System.out.println("ALLY[1]:"+ally1+", xF="+ally1xF+", yF= "+ally1yF);
		System.out.println("ALLY[2]:"+ally2+", xF="+ally2xF+", yF= "+ally2yF);

		//check to attack wall
		//x
		if(0 < xF && width - selfX < xF){
			xF = width - selfX - avoid;
		}else if(xF < 0 && selfX < -xF){
			xF = -selfX + avoid;
		}
		//y
		if(0 < yF && height - selfY < yF){
			yF = height - selfY - avoid;
		}else if(yF < 0 && selfY < -yF){
			yF = -selfY + avoid;
		}

		//MARGE
		//Angle(absolute, -180 to 180)
		ang = Math.toDegrees(Math.atan2(xF,yF));
		System.out.println("ABSOLUTE: ang= "+ang);

		//Angle(relative, -180 to 180)
		ang -= self.getHeading();
		if(ang < -180){
			ang += 360;
		}
		System.out.println("RELATIVE: ang= "+ang);
		
		//Force(local)
		F = Math.sqrt(Math.pow(xF,2)+Math.pow(yF,2));

		System.out.println("MOVE:xF= "+xF+", yF= "+yF);
		System.out.println("MOVE:ang= " + ang + ", F= " + F);
		
		turn(ang, F);
		//reset
		xF = 0;
		yF = 0;
	}

	private void turn(double ang, double F){

		if(-90 < ang && ang < 90){
			if(0 < ang){
				self.setTurnRight(ang);
			}else{
				self.setTurnLeft(-ang);
			}
			self.setAhead(F);
		}else{//if -180 to -90 or 90 to 180
			if(0 < ang){
				self.setTurnLeft(180-ang);
			}else{
				self.setTurnRight(180+ang);
			}
			self.setBack(F);
		}
		self.setAdjustGunForRobotTurn(true);
		self.turnRadarRight(360);
	}
}