package group06zero;

import java.util.List;

import robocode.TeamRobot;

public abstract class EvadePattern {

	protected TeamRobot owner;

	public EvadePattern(TeamRobot owner) {
		this.owner = owner;
	}

	public abstract void execute(BulletInfoContainer bulleInfoList);
}