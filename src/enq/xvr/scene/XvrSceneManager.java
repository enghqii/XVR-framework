package enq.xvr.scene;

import android.app.Activity;
import android.util.Log;

public class XvrSceneManager {
	
	public XvrSceneManager(Activity activity){
		
		this.mActivity = activity;
		
        Log.i("XVR","XvrSceneManager constructed.");
	}
	
	public void setEntryScene(XvrScene entryScene){
		this.entryScene = entryScene;
	}
	
	public void startEntryScene(){
		if(entryScene!=null)
			changeScene(entryScene);
	}
	
	public void changeScene(XvrScene scene){
		
		scene.setSceneManager(this);
		scene.setActivity(mActivity);
		scene.createResourceManager();
		scene.initialize();
		curScene = scene;
	}
	
	public void draw(){
		if(curScene != null){
			curScene.draw();
		}
	}
	
	public void frameMove(float timeDelta){
		if(curScene != null){
			curScene.frameMove(timeDelta);
		}
	}
	
	private XvrScene curScene =null;
	private XvrScene entryScene =null;
	
	private Activity mActivity =null;
}
