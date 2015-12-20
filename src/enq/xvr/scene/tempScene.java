package enq.xvr.scene;

import enq.xvr.global;
import enq.xvr.graphic.XvrResourceManager;
import enq.xvr.graphic.XvrTexture;
import android.content.Context;
import android.util.Log;

public class tempScene extends XvrScene {
	
	private XvrTexture bg =null;
	private XvrTexture player =null;
	
	private float alpha =0;
	private float x =0;
	private float y =0;

	public tempScene(Context mContext) {
		super(mContext);
			
		rmgr = new XvrResourceManager(mContext);
		rmgr.addPool("bg",bg = new XvrTexture("img/BG.png"));
		rmgr.addPool("player",player= new XvrTexture("img/player.png"));
		rmgr.createAllTexrures();
		
		x = global.x;
		y = global.y;
	}

	@Override
	void draw() {
		spr.draw(bg, 0, 0, 1, 1, 0);
		spr.draw(player, global.x+103, global.y+103, 1, 1, alpha);
	}

	@Override
	void frameMove(float timeDelta) {
		
		alpha += timeDelta * 60;
		
		if( Math.abs(global.x - x) > 2 ){
			x += ( global.x - x ) / 5.0f * 60 * timeDelta;
		}
		
		if( Math.abs(global.y - y) > 2 ){
			y += ( global.y - y ) / 5.0f * 60 * timeDelta;
		}
		Log.d("FRAME", ""+(1/timeDelta));
	}

}
