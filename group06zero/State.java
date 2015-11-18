package group06zero;

public abstract class State
{
	public abstract void enter(Robot owner);
	
	public abstract void execute(Robot owner);
	
	public abstract void exit(Robot owner);
}