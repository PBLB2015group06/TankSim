package group06zero;

import robocode.TeamRobot;
import java.util.List;
import robocode.*;


public class EvadePattern1 extends EvadePattern {  // this pattern evade point along with shot orbit
	
	public static double EVADE_CONSTANT = 30.0;
	public static final double AVOID_RANGE = 50.0;
	
	AntiGravity antiGravity;

	public EvadePattern1(TeamRobot owner, AntiGravity antiGravity) {
		super(owner);
		this.antiGravity = antiGravity;
	}

	public void execute(BulletInfoContainer bulletInfoContainer) {
		//Vector2D steeringForce = new Vector2D();
		List<BulletInfo> bulletInfoList = bulletInfoContainer.getBulletList();
		Vector2D robotPosition = new Vector2D(owner.getX(), owner.getY());
		Vector2D averagePosition = new Vector2D();
		int count = 0; // this counts the number of bullets to be avoided
	
		for (BulletInfo bulletInfo : bulletInfoList) {
		// this calculate the time bullet will arrive on hypothesized with both of them has the same direction
			double time = bulletInfo.calArrivingTime(robotPosition);System.out.println(time);
			Vector2D bulletFuturePosition = bulletInfo.calFuturePosition(time);
			//robotLocalPosition = Vector2D.GetRotatedVector2D(robotPosition.sub(bulletFuturePosition),
			//												 bulletInfo.getHeading());
			
			if (!isPointWithinAvoidRange(bulletFuturePosition)) continue;

			count += 1;
			averagePosition.add(bulletFuturePosition);
		}
		
		if (count == 0) return;
		System.out.println(averagePosition.x);
		averagePosition.mul(1/count);
		antiGravity.addFpoint(1000, averagePosition.x, averagePosition.y);
	}

	private boolean isPointWithinAvoidRange(Vector2D point) {
		return Math.abs(owner.getX() * owner.getX() + owner.getY() * owner.getY()) <= AVOID_RANGE * AVOID_RANGE;
	}
}