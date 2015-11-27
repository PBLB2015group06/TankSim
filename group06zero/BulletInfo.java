package group06zero;


public class BulletInfo {
	
	private double bulletSpeed;
	private Vector2D position = new Vector2D();
	private double heading;
	private Vector2D headingVector;
	private long lastUpdateTime = 0;

	public BulletInfo(double x, double y, double heading) {
		this.position = new Vector2D(x, y);
		this.heading = heading;
		this.headingVector = Vector2D.GetDirectionalVectorFromDegree(heading);
	}

	public Vector2D getPosition() {
		return this.position;
	}

	public double getHeading() {
		return this.heading;
	}

	public void update(long deltaTime) {
		position.add(headingVector.mul(bulletSpeed * deltaTime / 1000));
	}

	public Vector2D calFuturePosition(double time) {
		Vector2D headingVector = Vector2D.GetDirectionalVectorFromDegree(this.heading);
		return Vector2D.Add(position, headingVector.mul(time * bulletSpeed));
	}

	public double calArrivingTime(Vector2D targetPosition) {
		Vector2D relativePosition = Vector2D.Sub(targetPosition, position);
		double distance = relativePosition.magnitude();
		return distance / bulletSpeed;
	}
}