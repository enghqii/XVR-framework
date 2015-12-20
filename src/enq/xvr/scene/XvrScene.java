package enq.xvr.scene;

import android.app.Activity;
import enq.xvr.core.XvrInputManager;
import enq.xvr.graphic.XvrResourceManager;

public abstract class XvrScene {
	
	abstract public void initialize();
	
	abstract public void draw();
	abstract public void frameMove(float timeDelta);
	
	public void setSceneManager(XvrSceneManager smgr){
		
		this.smgr = smgr;
	}
	
	public void setActivity(Activity activity){
		
		this.mActivity = activity;
	}
	
	public void createResourceManager(){

		this.rmgr = new XvrResourceManager(mActivity);
	}
	
	public static void setInputManager(XvrInputManager inputMgr){
		
		XvrScene.inputMgr = inputMgr;
	}
	
	protected XvrSceneManager smgr =null;
	protected XvrResourceManager rmgr =null;
	
	protected Activity mActivity = null;
	protected static XvrInputManager inputMgr =null;
}
