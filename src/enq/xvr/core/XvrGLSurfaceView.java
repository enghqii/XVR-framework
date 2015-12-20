package enq.xvr.core;

import enq.xvr.scene.XvrScene;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class XvrGLSurfaceView extends GLSurfaceView {
	
	private XvrGLRenderer xvrRenderer = null;
	private XvrInputManager xvrInputMgr = null;

	public XvrGLSurfaceView(Context context) {
		super(context);
		
		xvrInputMgr = new XvrInputManager(this);
		XvrScene.setInputManager(xvrInputMgr);
		
		setEGLContextClientVersion(2);
		xvrRenderer = new XvrGLRenderer(context); 
		setRenderer(xvrRenderer);
		
		setRenderMode(RENDERMODE_CONTINUOUSLY);
	}
	
	public boolean onTouchEvent(final MotionEvent event){
		
		xvrInputMgr.setEvent(event);
		
		return true;
	}
	
	public XvrGLRenderer getRenderer(){
		
		if(xvrRenderer != null){
			
			return xvrRenderer;
			
		}else{
			
			return null;
			
		}
	}
}
