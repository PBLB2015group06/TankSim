package group06zero;


public class BulletInfo {
	
	private double bulletSpeed;
	private Vector2D position = new Vector2D();
	private double heading;

	public BulletInfo(double x, double y, double heading) {
		this.position = new Vector2D(x, y);
		this.heading = heading;
	}

	public BulletInfo(Vector2D position, double heading) {
		this.position = position;
		this.heading = heading;
	}

	public Vector2D getPosition() {
		return this.position;
	}

	public double getHeading() {
		return this.heading;
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