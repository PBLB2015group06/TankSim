package group06zero;


public class Vector2D {

	public double x;
	public double y;

	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getMagnitude() {
<<<<<<< Updated upstream
		return math.sqrt(this.x * this.x + this.y * this.y);
=======
		return Math.sqrt(this.x * this.x + this.y * this.y);
>>>>>>> Stashed changes
	}

	public Vector2D normalized() {
		double magnitude = this.getMagnitude();
		this.x /= magnitude;
		this.y /= magnitude;
		return this;
	}

	public Vector2D add(Vector2D vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

<<<<<<< Updated upstream
=======
	public Vector2D sub(Vector2D vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

>>>>>>> Stashed changes
	public Vector2D mul(double num) {
		this.x *= num;
		this.y *= num;
		return this;
	}

	public static Vector2D Add(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x + v2.x, v1.y + v2.y);
	}

<<<<<<< Updated upstream
=======
	public static Vector2D Sub(Vector2D v1, Vector2D v2) {
		return new Vector2D(v1.x - v2.x, v1.y - v2.y);
	}

>>>>>>> Stashed changes
	public static Vector2D Normalize(Vector2D vector) {
		double magnitude = vector.getMagnitude();
		double x = vector.x / magnitude;
		double y = vector.y / magnitude;
		return new Vector2D(x, y);
	}

<<<<<<< Updated upstream
	public static Vector2D GetDirectionalVectorFromDegree(double degree) {
		double x = math.sin(degree);
		double y = math.cos(degree);
=======
	public static Vector2D GetDirectionalVectorFromDegree(double angle) {
		double x = Math.sin(Math.toRadians(angle));
		double y = Math.cos(Math.toRadians(angle));
		return new Vector2D(x, y);
	}

	public static Vector2D GetRotatedVector2D(Vector2D vector, double angle) {
		double x = vector.x * Math.cos(Math.toRadians(angle)) + vector.y * Math.sin(Math.toRadians(angle));
		double y = -vector.x * Math.sin(Math.toRadians(angle)) + vector.y * Math.cos(Math.toRadians(angle));
>>>>>>> Stashed changes
		return new Vector2D(x, y);
	}
}