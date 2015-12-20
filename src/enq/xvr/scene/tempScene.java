package enq.xvr.scene;

import enq.xvr.global;
import enq.xvr.graphic.XvrImage;
import enq.xvr.graphic.XvrRect;

public class tempScene extends XvrScene {
	
	private XvrImage bg =null;
	private XvrImage player =null;
	private XvrImage pSpr =null;
	
	private float alpha =0;
	private float x =0;
	private float y =0;
	
	
	@Override
	void initialize() {

		// 이미지 등록
		bg = rmgr.addImage("bg","img/BG.png");
		player = rmgr.addImage("player","img/player.png");
		pSpr = rmgr.addImage("pSpr", "img/playerSpr.png");
		
		// 이미지 생성
		rmgr.create();
		
		x = global.x;
		y = global.y;
	}

	@Override
	void draw() {
		bg.draw(0, 0);
		player.draw(x, y, 1, 1, 32, 32, alpha*180);
		player.draw(x+100, y, 2, 2, 32, 32, alpha*180*3);
		pSpr.draw(0, 0);
		pSpr.draw(100, 100, new XvrRect(0,0,64,64));
	}

	@Override
	void frameMove(float timeDelta) {
		
		alpha += timeDelta;
		
		if( Math.abs(global.x - x) > 2 ){
			x += ( global.x - x ) / 5.0f * 60 * timeDelta;
		}
		
		if( Math.abs(global.y - y) > 2 ){
			y += ( global.y - y ) / 5.0f * 60 * timeDelta;
		}
		//Log.d("FRAME", ""+(1/timeDelta));
	}

	

}
