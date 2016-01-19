package group06zero;

public class ShootingMethodSelector {
	public enum shootingMethods {
		PIN_POINT,
		INERTIA_DEVIATION,
		ACCELERATION_DEVIATION;
		
		private double CHANGE_RATE = 0.1;
		
		public double probability;
		
		private shootingMethods(){
			this.probability = 1.0;
		}
		
		public void changeProbability(boolean hasHit){
			if(hasHit)
				this.probability *= 1 + this.CHANGE_RATE;
			else
				this.probability *= 1 - this.CHANGE_RATE;
		}
	}
	
	private Group06zerogouki myTank;
	public PinpointShooting pinpoint;
	public InertiaDeviationShooting inertiaDeviation;
	public AccelerationDeviationShooting accelerationDeviation;
	public shootingMethods pastMethod = null;
	
	public double bullet_power;
	
	public ShootingMethodSelector(Group06zerogouki myTank){
		this.myTank = myTank;
		this.pinpoint = new PinpointShooting(myTank);
		this.inertiaDeviation = new InertiaDeviationShooting(myTank);
		this.accelerationDeviation = new AccelerationDeviationShooting(myTank);
		this.bullet_power = 0;
	}
	
	public ShootingMethod selectMethod(){
		shootingMethods best = null;
		for(shootingMethods method: shootingMethods.values()){
			if(best == null)
				best = method;
			else if(best.probability < method.probability)
				best = method;
		}
		this.pastMethod = best;
		this.bullet_power = best.probability;
		switch (best){
		case PIN_POINT:
			return this.pinpoint;
		case INERTIA_DEVIATION:
			return this.inertiaDeviation;
		case ACCELERATION_DEVIATION:
			return this.accelerationDeviation;
		default:
			return null;
		}
	}
	
	public void reflectProbability(boolean hasHit){
		if(this.pastMethod == null)
			return;
		this.pastMethod.changeProbability(hasHit);
	}
}
