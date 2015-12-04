package group06zero;

public class AntiGravity{
	private double width  = 0;
	private double height = 0;
	private double selfX = 0;
	private double selfY = 0;
	private double dis = 0;
	private double F = 0;
	private int G = 5000;//Gravitational constant
	private int wallG = 8000;//Gravitational constant of Wall
	private double pi = Math.PI;
	
	private double xF = 0;
	private double yF = 0;
	private double ang = 0;

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
		xF += F*x/dis;
		yF += F*y/dis;
	}
	
	public void addFpoint(double exG, double x, double y){
		//relative position
		x = x - selfX;
		y = y - selfY;
		//Distance
		dis = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		//Force
		F = exG/Math.pow(dis,2);
		xF += F*x/dis;
		yF += F*y/dis;
	}

	public void move(){
		double F = 0;
		double tan = 0;
		//add force from wall
		xF += wallG/Math.pow(selfX, 2);//from Left
		yF += wallG/Math.pow(selfY, 2);//from Bottom
		xF -= wallG/Math.pow(width - selfX, 2);//from Right
		yF -= wallG/Math.pow(height - selfY, 2);//from Top

		//check to attack wall
		//x
		if(0 < xF && width - selfX < xF){
			xF = width - selfX - 50;
		}else if(xF < 0 && selfX < -xF){
			xF = -selfX + 50;
		}
		//y
		if(0 < yF && height - selfY < yF){
			yF = height - selfY - 50;
		}else if(yF < 0 && selfY < -yF){
			yF = -selfY + 50;
		}
		//MARGE
		//Angle(relative, -pi to pi)
		tan = Math.atan2(xF,yF);
		//Angle(relative, -180 to 180)
		ang = 180*tan/pi;
		
		//Force(local)
		F = Math.sqrt(Math.pow(xF,2)+Math.pow(yF,2));
		
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