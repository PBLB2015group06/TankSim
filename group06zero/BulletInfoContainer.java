package group06zero;

import java.util.List;
import java.util.ArrayList;
/**
 * MyClass - a class by (your name here)
 */
public class BulletInfoContainer
{
	private List<BulletInfo> bulletInfoList;
	private long removeInterval = 300;

	public BulletInfoContainer() {
		this.bulletInfoList = new ArrayList<BulletInfo>();
	}

	public void add(BulletInfo bulletInfo) {
		bulletInfoList.add(bulletInfo);
	}

	public List<BulletInfo> getBulletList() {
		return bulletInfoList;
	}
	
	public void updateBullets(double deltaTime) {
		for (BulletInfo bulletInfo : bulletInfoList) {
			bulletInfo.update(deltaTime);
			if (bulletInfo.isBulletOutOfField())
				bulletInfoList.remove(bulletInfo);
		}
	}
	
	public void removeBullet(long noFrame) {
		
		if (bulletInfoList.size() == 0)
			return;		

		if (noFrame % removeInterval == 0) {
			bulletInfoList.remove(0);
		}
	}

	public void removeBulletInfo(int index) {
		bulletInfoList.remove(index);
	}
}
