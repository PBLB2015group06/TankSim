package group06zero;

import java.util.List;
import robocode.*;

public class EvadePattern2 extends EvadePattern {
	
	public static final double AVOID_RANGE = 100.0;
	private AntiGravity antiGravity;

	public EvadePattern2(TeamRobot owner, AntiGravity antiGravity) {
		super(owner);
		this.antiGravity = antiGravity;
	}

	public void execute(BulletInfoContainer bulletInfoContainer) {
		List<BulletInfo> bulletInfoList = bulletInfoContainer.getBulletList();
		Vector2D robotPosition = new Vector2D(owner.getX(), owner.getY());
		for (BulletInfo bulletInfo : bulletInfoList) {
		// this calculate the time bullet will arrive on hypothesized with both of them has the same direction
			double time = bulletInfo.calArrivingTime(robotPosition);
			Vector2D bulletFuturePosition = bulletInfo.calFuturePosition(time/100);
			//robotLocalPosition = Vector2D.GetRotatedVector2D(robotPosition.sub(bulletFuturePosition),
			//												 bulletInfo.getHeading());
			System.out.println(time);
			System.out.println(bulletFuturePosition.x);
			System.out.println(robotPosition.x);
			//if (!isPointWithinAvoidRange(bulletFuturePosition)) continue;
			
			antiGravity.addFpoint(30000000, bulletFuturePosition.x, bulletFuturePosition.y);
			//antiGravity.addFpoint(100, robotPosition.x ,robotPosition.y);
		}
	}

	private boolean isPointWithinAvoidRange(Vector2D point) {
		double deltaX = point.x - owner.getX();
		double deltaY = point.y - owner.getY();
		return deltaX * deltaX + deltaY * deltaY <= AVOID_RANGE * AVOID_RANGE;
	}
}