package enq.xvr.scene;

import enq.xvr.graphic.XvrResourceManager;
import android.content.Context;

public abstract class XvrScene {
	
	public XvrScene(){
		
	}
	
	abstract void draw();
	abstract void frameMove(float timeDelta);
	
	abstract void initialize();
	
	public void setSceneManager(XvrSceneManager smgr){
		
		this.smgr = smgr;
	}
	
	public void setContext(Context context){
		
		this.mContext = context;
	}
	
	public void createResourceManager(){

		this.rmgr = new XvrResourceManager(mContext);
	}
	
	protected XvrSceneManager smgr =null;
	protected XvrResourceManager rmgr =null;
	
	protected Context mContext = null;
}
