package enq.xvr.scene;

import android.app.Activity;
import enq.xvr.core.XvrInputManager;
import enq.xvr.graphic.XvrResourceManager;

public abstract class XvrScene {
	
	abstract public void initialize();
	
	abstract public void draw();
	abstract public void frameMove(float timeDelta);
	
	protected void setSceneManager(XvrSceneManager smgr){
		
		this.smgr = smgr;
	}
	
	protected void setActivity(Activity activity){
		
		this.mActivity = activity;
	}
	
	protected void createResourceManager(){

		this.rmgr = new XvrResourceManager(mActivity);
	}
	
	public static void setInputManager(XvrInputManager inputMgr){
		
		XvrScene.inputMgr = inputMgr;
	}
	
	public void setIntent(XvrIntent intent){
		this.intent = intent;
	}
	
	public void deleteAllTextures() {
		
		rmgr.delete();
	}
	
	protected XvrSceneManager smgr =null;
	protected XvrResourceManager rmgr =null;
	protected XvrIntent intent = null;
	
	protected Activity mActivity = null;
	protected static XvrInputManager inputMgr =null;
}
