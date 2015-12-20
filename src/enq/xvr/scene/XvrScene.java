package enq.xvr.scene;

import android.content.Context;

public abstract class XvrScene {
	
	public XvrScene(Context mContext ,int modelHandle){
		this.modelHandle = modelHandle;
		this.mContext = mContext;
	}
	
	abstract void draw();
	abstract void frameMove(float timeDelta);
	
	public void setSceneManager(XvrSceneManager smgr){
		this.smgr = smgr;
	}
	
	protected XvrSceneManager smgr=null;
	protected int modelHandle =0;
	protected Context mContext = null;
}
