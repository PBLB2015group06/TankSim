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

}
