package enq.xvr.scene;

import enq.xvr.graphic.XvrResourceManager;
import enq.xvr.graphic.XvrSprite;
import android.content.Context;

public abstract class XvrScene {
	
	public XvrScene(Context mContext){
		this.mContext = mContext;
		
		this.rmgr = new XvrResourceManager(mContext);
		this.spr = new XvrSprite();
	}
	
	abstract void draw();
	abstract void frameMove(float timeDelta);
	
	public void setSceneManager(XvrSceneManager smgr){
		this.smgr = smgr;
	}
	
	protected XvrSceneManager smgr =null;
	protected XvrResourceManager rmgr =null;
	protected XvrSprite spr =null;
	
	protected Context mContext = null;
}
