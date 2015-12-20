package enq.xvr;

import android.util.Log;
import enq.xvr.core.XvrColour;
import enq.xvr.core.XvrInputManager;
import enq.xvr.graphic.XvrAnimatedImage;
import enq.xvr.graphic.XvrImage;
import enq.xvr.scene.XvrScene;

public class tempScene extends XvrScene {
	
	private XvrImage bg =null;
	private XvrImage fade =null;
	private XvrAnimatedImage pSpr =null;
	
	private XvrImage tile = null;
	
	private float alpha =0;
	private float x =0;
	private float y =0;
	
	@Override
	public void initialize() {

		// 이미지 등록
		fade = rmgr.addImage("fade","img/fade.png");
		bg = rmgr.addImage("bg","img/BG.png");
		rmgr.addAnimatedImage("pSpr", "img/playerSpr.png", 64, 64);
		pSpr = rmgr.getAnimatedImage("pSpr");
		
		tile = rmgr.addImage("tile", "img/tile_block1.png");
		
		// 이미지 생성
		rmgr.create();
		
		x = this.getThisIntent().getInteger(0);
		y = this.getThisIntent().getInteger(0);
		
		pSpr.play();
	}

	@Override
	public void draw() {
		
		bg.draw(0, 0);
		
		tile.draw(0, 0);
		tile.draw(99, 0);
		

		tile.draw(250, 0);
		tile.draw(250 + 100, 0);
		

		tile.draw(500, 0);
		tile.draw(500 + 101, 0);
		
		pSpr.draw(5, y, 1, 1, 32, 32 , 3.1415f / 2.0f );
		pSpr.draw(x, (465.0f - 64.0f), 1, 1, 32, 32);
		pSpr.draw(x, y, 1, 1, 32, 32, alpha * 5);
		//pSpr.draw(0, 0);
		//pSpr.draw(0, 70 , new XvrRect(0,0,320,64));
		 
	
		pSpr.draw(100,100);
		
		fade.draw(x, y, 400, 240, new XvrColour(255,255,255,128));
		fade.draw(x+200, y, 400, 240, new XvrColour(255,255,255,25));
		//fade.draw(0, 0, new XvrColour((int) (Math.cos(alpha)*128 + 128),(int) (Math.sin(alpha)*128 + 128),255,(int) (Math.sin(alpha)*128 + 128)));
	}
	
	@Override
	public void frameMove(float timeDelta) {
		
		pSpr.update(timeDelta);
		
		alpha += timeDelta;
		
		/*
		if(inputMgr.isTouched()){
			if((100 < inputMgr.getX() && inputMgr.getX() < 164)&&(100 < inputMgr.getY() && inputMgr.getY() < 164)){
				mActivity.finish();
			}
		}*/
		
		
		if(inputMgr.getState() == XvrInputManager.ACTION_DOWN){
			if((100 < inputMgr.getX() && inputMgr.getX() < 164)&&(100 < inputMgr.getY() && inputMgr.getY() < 164)){
				mActivity.finish();
			}
		}
		
		if( Math.abs(inputMgr.getX() - x) > 2 ){
			x += ( inputMgr.getX() - x ) / 5.0f * 60 * timeDelta;
		}
		
		if( Math.abs(inputMgr.getY() - y) > 2 ){
			y += ( inputMgr.getY() - y ) / 5.0f * 60 * timeDelta;
		}
		
		Log.d("timeDelta", "FPS ="+ 1/timeDelta +" acTime = "+ alpha);
		
	}

	

}
