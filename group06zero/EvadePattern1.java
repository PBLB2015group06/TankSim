package groupt06zero;

public class EvadePattern1 extends EvadePattern {  // this pattern evade point along with shot orbit
	
	public static float EVADE_CONSTANT = 30.0;
	public static double AVOID_RANGE = 5.0;
	AntiGravity antiGravity;

	public EvadePattern1(Robot owner, AntiGravity antiGravity) {
		super(owner);
		this.antiGravity = antiGravity;
	}

	public void execute(List<BulletInfo> bulletInfoList) {
		//Vector2D steeringForce = new Vector2D();
		Vector2D robotPosition = new Vector2D(owner.getX(), owner.getY());
		Vector2D averagePosition = new Vector2D();
		int count = 0; // this counts the number of bullets to be avoided
		foreach(bulletInfo : bulletInfoList) {
		// this calculate the time bullet will arrive on hypothesized with both of them has the same direction
			double time = bulletInfo.calArrivingTime(robotPosition);
			Vector2D bulletFuturePosition = bulletInfo.calFuturePosition(time);
			//robotLocalPosition = Vector2D.GetRotatedVector2D(robotPosition.sub(bulletFuturePosition),
			//												 bulletInfo.getHeading());

			if (!isPointWithinAvoidRange(bulletFuturePosition)) continue;

			count += 1;
			averagePosition.add(bulletFuturePosition);
		}
		averagePosition.mul(1/count);
		antiGravity.addF(averagePosition.x, averagePosition.y);
	}

	private Vector2 calForce() {

	}

	private boolean isPointWithinAvoidRange(Vector2D point) {
		return Math.abs(owner.getX() * owner.getX() + owner.getY() * owner.getY()) <= AVOID_RANGE * AVOID_RANGE;
	}
}