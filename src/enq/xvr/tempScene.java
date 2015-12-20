package enq.xvr;

import android.util.Log;
import enq.xvr.core.XvrInputManager;
import enq.xvr.graphic.XvrAnimatedImage;
import enq.xvr.graphic.XvrImage;
import enq.xvr.scene.XvrScene;

public class tempScene extends XvrScene {
	
	private XvrImage bg =null;
	private XvrAnimatedImage pSpr =null;
	
	private float alpha =0;
	private float x =0;
	private float y =0;
	
	@Override
	public void initialize() {

		// 이미지 등록
		bg = rmgr.addImage("bg","img/BG.png");
		pSpr = rmgr.addAnimatedImage("pSpr", "img/playerSpr.png", 64, 64);
		
		// 이미지 생성
		rmgr.create();
		
		x = inputMgr.getX();
		y = inputMgr.getY();
		
		pSpr.play();
	}

	@Override
	public void draw() {
		
		bg.draw(0, 0);
		pSpr.draw(5, y, 1, 1, 32, 32 , 3.1415f / 2.0f );
		pSpr.draw(x, (465.0f - 64.0f), 1, 1, 32, 32);
		pSpr.draw(x, y, 1, 1, 32, 32, alpha * 5);
		//pSpr.draw(0, 0);
		//pSpr.draw(0, 70 , new XvrRect(0,0,320,64));
		
		pSpr.draw(100, 100);
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
		
		Log.d("timeDelta", "timeDelta ="+ timeDelta +"accureTime ="+ alpha);
	}

	

}
