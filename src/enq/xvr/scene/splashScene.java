package enq.xvr.scene;

import android.content.Context;
import enq.xvr.graphic.XvrResourceManager;
import enq.xvr.graphic.XvrSprite;
import enq.xvr.graphic.XvrTexture;

public class splashScene extends XvrScene {

	public splashScene(Context mContext, int modelHandle) {
		super(mContext, modelHandle);

		spr = new XvrSprite();
		
		rmgr = new XvrResourceManager(mContext);
		rmgr.addPool("splash",tex = new XvrTexture("img/splash.png"));
		rmgr.createAllTexrures();
	}

	@Override
	void draw() {
		spr.draw(tex, modelHandle, 0, 0, 1, 1, 0);
	}

	@Override
	void frameMove(float timeDelta) {
		time += timeDelta;
		
		if(time > 2.5){
			this.smgr.changeScene(new tempScene(mContext, modelHandle));
		}
	}
	
	private XvrResourceManager rmgr =null;
	private XvrTexture tex =null;
	private XvrSprite spr =null;
	
	private float time =0;

}
