package group06zero;

public abstract class EvadePattern {

	protected Robot owner;

	public EvadePattern(Robot owner) {
		this.owner = owner;
	}

	public abstract void execute(List<BulletInfo> bulleInfoList);
}