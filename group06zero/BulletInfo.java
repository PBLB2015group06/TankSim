package group06zero;


public class BulletInfo {
	
	private double bulletSpeed;
	private Vector2D position = new Vector2D();
	private double heading;
	private Vector2D headingVector;

	public BulletInfo(double x, double y, double heading, double bulletSpeed) {
		this.position = new Vector2D(x, y);
		this.heading = heading;
		this.bulletSpeed = bulletSpeed;
		this.headingVector = Vector2D.GetDirectionalVectorFromDegree(heading);
	}

	public BulletInfo(Vector2D position, double heading, double bulletSpeed) {
		this.position = position;
		this.heading = heading;
		this.bulletSpeed = bulletSpeed;
		this.headingVector = Vector2D.GetDirectionalVectorFromDegree(heading);
	}

	public Vector2D getPosition() {
		return this.position;
	}

	public double getHeading() {
		return this.heading;
	}

	public void update(double deltaTime) {
		position.add(headingVector.mul(deltaTime * bulletSpeed));
	}

	public Vector2D calFuturePosition(double time) {
		return Vector2D.Add(position, headingVector.mul(time * bulletSpeed));
	}

	public double calArrivingTime(Vector2D targetPosition) {
		Vector2D relativePosition = Vector2D.Sub(targetPosition, position);
		double distance = relativePosition.getMagnitude();
		return distance / bulletSpeed;
	}
	
	public boolean isBulletOutOfField() {
		if (position.x >= 1000 || position.x <= 0)
			if (position.y >= 1000 || position.y <=0)
				 return true;
		return false;
	}
}