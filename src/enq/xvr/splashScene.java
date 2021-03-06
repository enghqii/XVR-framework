package enq.xvr;

import enq.xvr.graphic.XvrImage;
import enq.xvr.scene.XvrIntent;
import enq.xvr.scene.XvrScene;

public class splashScene extends XvrScene {

	private float time =0;
	private XvrImage bg =null;
	private XvrImage xvr =null;
	
	@Override
	public void initialize() {
		bg = rmgr.addImage("background", "img/BG.png");
		xvr = rmgr.addImage("xvr", "img/xvr.png");
		
		rmgr.create();
	}

	@Override
	public void draw() {
		bg.draw(0, 0);
		xvr.draw(285, 175);
	}

	@Override
	public void frameMove(float timeDelta) {
		time += timeDelta;
		
		if(time > 2.5){
			XvrIntent intent = new XvrIntent(100);
			this.smgr.changeSceneWithIntent("tempScene",intent);
		}
	}
}
