package enq.xvr.scene;

import enq.xvr.core.XvrInputManager;
import enq.xvr.graphic.XvrResourceManager;
import android.content.Context;

public abstract class XvrScene {
	
	abstract public void initialize();
	
	abstract public void draw();
	abstract public void frameMove(float timeDelta);
	
	public void setSceneManager(XvrSceneManager smgr){
		
		this.smgr = smgr;
	}
	
	public void setContext(Context context){
		
		this.mContext = context;
	}
	
	public void createResourceManager(){

		this.rmgr = new XvrResourceManager(mContext);
	}
	
	public static void setInputManager(XvrInputManager inputMgr){
		
		XvrScene.inputMgr = inputMgr;
	}
	
	protected XvrSceneManager smgr =null;
	protected XvrResourceManager rmgr =null;
	
	protected Context mContext = null;
	protected static XvrInputManager inputMgr =null;
}
