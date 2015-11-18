package group06zero;

/**
 * MyClass - a class by (your name here)
 */
public class StateMachine
{
	private Robot owner;

	private State globalState;
	private State currentState;
	private State previousState;
	
	public StateMachine(Robot owner)
	{
		this.owner = owner;
		
	}

	public void changeState(State newState)
	{
		currentState.exit(owner);
		
        currentState = newState;
        
        currentState.enter(owner);
	}
    
    public void Update()
    {
        if (globalState != null) globalState.execute(owner);
        
        if (currentState != null) currentState.execute(owner);
    }
}
