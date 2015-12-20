package enq.xvr.scene;

import android.content.Context;
import android.util.Log;
import enq.xvr.core.global;
import enq.xvr.graphic.XvrResourceManager;
import enq.xvr.graphic.XvrSprite;
import enq.xvr.graphic.XvrTexture;

public class tempScene extends XvrScene {
	
	private XvrResourceManager rmgr =null;
	private XvrTexture tex =null;
	private XvrTexture tex2 =null;
	private XvrTexture player =null;
	private XvrSprite spr = null;
	
	private float alpha =0;
	private float x =0;
	private float y =0;

	public tempScene(Context mContext, int modelHandle) {
		super(mContext,modelHandle);
		
		spr = new XvrSprite();
		
		rmgr = new XvrResourceManager(mContext);
		rmgr.addPool("xvr",tex = new XvrTexture("img/xvrs.png"));
		rmgr.addPool("an2",tex2 = new XvrTexture("img/tile.png"));
		rmgr.addPool("player",player= new XvrTexture("img/player.png"));
		rmgr.createAllTexrures();
		
		x = global.x;
		y = global.y;
	}

	@Override
	void draw() {
		
		spr.draw(tex2,super.modelHandle, 0 , 0 , 1.0f , 1.0f, 0);
		spr.draw(tex2,super.modelHandle, 206 , 0 , 1.0f , 1.0f, 0);
		spr.draw(tex2,super.modelHandle, 0 , 207 , 1.0f , 1.0f, 0);
		spr.draw(tex2,super.modelHandle, 206 , 207 , 1.0f , 1.0f, 0);
		
		spr.draw(tex2,super.modelHandle, 0 , 414 , 1.0f , 1.0f, 0);
		spr.draw(tex2,super.modelHandle, 206 , 414 , 1.0f , 1.0f, 0);
		spr.draw(tex2,super.modelHandle, 0 , 621 , 1.0f , 1.0f, 0);
		spr.draw(tex2,super.modelHandle, 206 , 621 , 1.0f , 1.0f, 0);
		
		spr.draw(player, modelHandle, 0, 800-64, 1, 1, 0);
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
