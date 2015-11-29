package group06zero;

import java.util.List;
import java.util.ArrayList;


public class BulletInfoContainer {

	private Robot owner;
	private List<BulletInfo> bulletList;

	public BulletInfoContainer(Robot owner) {
		this.bulletList = new ArrayList<>();
		this.owner = owner;
	}

	public List<BulletInfo> getBulletList() {
		return bulletList;
	}

	public update(long deltaTime) {
		foreach(bullet : bulletList) {
			bullet.update(deltaTime);

			bulletPosition = bullet.getPosition();
			if (bulletPosition.x <= 0 || bulletPosition.x >= owner.getBattleFieldWidth()) {
				bulletList.remove(bullet);
			}
			if (bulletPosition.y <= 0 || bulletPosition.y >= owner.getBattleFieldHeight()) {
				bulletList.remove(bullet);
			}
		}
	}
}