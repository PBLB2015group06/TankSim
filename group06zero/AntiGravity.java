package group06zero;

public class AntiGravity{
	private double width  = 0;
	private double height = 0;
	private double selfX = 0;
	private double selfY = 0;
	private double x = 0;
	private double y = 0;
	private double dis = 0;
	private double F = 0;
	private int G = 5000;//Gravitational constant
	private int wallG = 50000;//Gravitational constant of Wall
	
	static private double xF = 0;
	static private double yF = 0;
	static private double ang = 0;

	private Group06zerogouki self = new Group06zerogouki();
	
	AntiGravity(Group06zerogouki me){
		self = me;
		//Field info
		width  = self.getBattleFieldWidth();
		height = self.getBattleFieldHeight();
		//My position(absolute)
		selfX = self.getX();
		selfY = self.getY();
	}
	
	public void addFrobot(EnemyRobot e){
		//Enemy's position(relative)
		x = e.getEnemyX() - self.getX();
		y = e.getEnemyY() - self.getY();
		//Distance
		dis = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		//Force
		F = G/(Math.pow(x,2)+Math.pow(y,2));
		xF += F*x/dis;
		yF += F*y/dis;
	}
	
	public void addFpoint(double exG, double x, double y){
		//Enemy's position(relative)
		x -= self.getX();
		y -= self.getY();
		//Distance
		dis = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		//Force
		F = exG/(Math.pow(x,2)+Math.pow(y,2));
		xF += F*x/dis;
		yF += F*y/dis;
	}

	public void move(){
		double F = 0;
		double tan = 0;
		double pi = Math.PI;
		//add force from wall
		xF += wallG/Math.pow(selfX, 2);//from Left
		yF += wallG/Math.pow(selfY, 2);//from Bottom
		xF -= wallG/Math.pow(width - selfX, 2);//from Right
		yF -= wallG/Math.pow(height - selfY, 2);//from Top

		//MARGE
		//Angle(relative, 0-360)
		tan = Math.atan(yF/xF);
		
		if(xF > 0){
			ang = 90 - 180*tan/pi;
		}else{
			ang = 270 - 180*tan/pi;
		}
		ang = ((ang - self.getHeading())%360 + 360)%360;
		
		//Force(local)
		F = Math.sqrt(Math.pow(xF,2)+Math.pow(yF,2));
		//if(F > 5){
			turn(ang, F);
		//}
	}

	private void turn(double ang, double F){
		if(ang < 90 || 270 < ang){
			if(ang < 90){
				self.setTurnRight(ang);
				self.setTurnGunLeft(ang);
			}else if(270 < ang){
				self.setTurnLeft(360-ang);
				self.setTurnGunRight(360-ang);
			}
			self.setAhead(F);
		}else{//if 90<ang<270
			if(ang < 180){
				self.setTurnLeft(180-ang);
				self.setTurnGunRight(180-ang);
			}else if(180 <= ang){
				self.setTurnRight(ang-180);
				self.setTurnGunLeft(ang-180);
			}
			self.setBack(F);
		}
		self.execute();
	}
}