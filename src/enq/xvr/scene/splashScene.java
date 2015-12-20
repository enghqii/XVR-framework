package enq.xvr.scene;

import enq.xvr.graphic.XvrTexture;
import android.content.Context;

public class splashScene extends XvrScene {

	private float time =0;
	private XvrTexture tex =null;
	private XvrTexture xvr =null;
	
	public splashScene(Context mContext) {
		super(mContext);

		rmgr.addPool("splash",tex = new XvrTexture("img/BG.png"));
		rmgr.addPool("xvr",xvr = new XvrTexture("img/xvr.png"));
		rmgr.createAllTexrures();
	}

	@Override
	void draw() {
		spr.draw(tex, 0, 0, 1, 1, 0);
		spr.draw(xvr, 284, 175, 1, 1, 0);
	}

	@Override
	void frameMove(float timeDelta) {
		time += timeDelta;
		
		if(time > 2.5){
			this.smgr.changeScene(new tempScene(mContext));
		}
	}
}
