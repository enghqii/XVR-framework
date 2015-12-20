package enq.xvr.scene;

import java.util.HashMap;

import android.app.Activity;
import android.util.Log;

public class XvrSceneManager {
	
	private Activity mActivity =null;
	private XvrIntent intent = null;
	
	private XvrScene curScene =null;
	private XvrScene entryScene =null;
	
	private String lastIndex = "";
	
	private HashMap<String,XvrScene> scenePool = null;
	
	public XvrSceneManager(Activity activity){
		
		this.mActivity = activity;
		scenePool = new HashMap<String, XvrScene>();
		
        Log.i("XVR","XvrSceneManager constructed.");
	}
	
	public void setIntent(XvrIntent intent){
		this.intent = intent;
	}
	
	public void setEntryScene(XvrScene entryScene){
		this.entryScene = entryScene;
	}
	
	public void setEntryScene(String index){
		this.entryScene = scenePool.get(index);
		lastIndex = index;
	}
	
	public void startEntryScene(){
		if(entryScene!=null)
			changeScene(entryScene);
	}
	
	public void changeScene(XvrScene scene){
		
		scene.setSceneManager(this);
		scene.setActivity(mActivity);
		scene.createResourceManager();
		
		if(curScene != null){
			curScene.deleteAllTextures();
		}
		
		scene.initialize();
		curScene = scene;
	}
	
	public void changeScene(String index){
		
		XvrScene scene = scenePool.get(index);
		
		if(curScene != null){
			curScene.deleteAllTextures();
		}

		scene.setIntent(intent);
		scene.initialize();
		curScene = scene;
		
		lastIndex = index;
	}
	
	public void changeSceneWithIntent(String index, XvrIntent intent){
		intent.setFrom(lastIndex);
		setIntent(intent);
		changeScene(index);
	}
	
	public void addScene(String index, XvrScene scene){
		
		scene.setSceneManager(this);
		scene.setActivity(mActivity);
		scene.createResourceManager();
		
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
