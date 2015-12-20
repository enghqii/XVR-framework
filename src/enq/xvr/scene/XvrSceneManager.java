package enq.xvr.scene;

import java.util.HashMap;

import android.app.Activity;
import android.util.Log;

public class XvrSceneManager {
	
	private Activity mActivity =null;
	
	private XvrScene curScene =null;
	private XvrScene entryScene =null;
	
	private HashMap<String,XvrScene> scenePool = null;
	
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
	
	public void changeScene(String index){
		
		changeScene(scenePool.get(index));
	}
	
	public void addScene(String index, XvrScene scene){
		scenePool.put(index,scene);
	}
	
	public void deleteScene(String index){
		scenePool.remove(index);
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
}
