package enq.xvr.scene;


public class XvrSceneManager {
	
	public XvrSceneManager(){
		
	}
	
	public void changeScene(XvrScene scene){
		scene.setSceneManager(this);
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
}
