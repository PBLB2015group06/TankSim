package group06zero;

import java.util.List;
import java.util.ArrayList;
/**
 * MyClass - a class by (your name here)
 */
public class BulletInfoContainer
{
	List<BulletInfo> bulletInfoList;

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
			//bulletInfo.update(deltaTime);
			
			if (bulletInfo.isBulletOutOfField())
				bulletInfoList.remove(bulletInfo);
		}
	}
	
	public void removeBulletInfo(int index) {
		bulletInfoList.remove(index);
	}
}
