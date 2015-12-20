package enq.xvr.scene;

import android.content.Context;

public class XvrSceneManager {
	
	public XvrSceneManager(Context mContext){
		
		this.mContext = mContext;
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
		scene.setContext(mContext);
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
	
	private Context mContext =null;
}
