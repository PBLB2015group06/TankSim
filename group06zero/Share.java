package group06zero;
import robocode.*;
import robocode.Droid;
import robocode.MessageEvent;
import robocode.TeamRobot;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import robocode.util.Utils;
import java.awt.geom.Point2D;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import java.io.IOException;

public class Share extends TeamRobot{



    
    public void shareinformation(ScannedRobotEvent e,TeamRobot self){
	EnemyRobot ref = new EnemyRobot(e,self);
        try{
        broadcastMessage(ref);
                         }catch(IOException ex){
                             out.println("Unable to send order: ");
                             ex.printStackTrace(out);
                         }
    
                         }
                         
                         
    public void onMessageReceived(MessageEvent e){
            if(e.getMessage() instanceof EnemyRobot){
            EnemyRobot inf = (EnemyRobot) e.getMessage();
            
            
        }
                         
                         
                         }
                         
                        }
