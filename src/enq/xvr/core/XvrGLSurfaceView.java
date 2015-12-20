package enq.xvr.core;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import enq.xvr.scene.XvrScene;

public class XvrGLSurfaceView extends GLSurfaceView {
	
	private XvrGLRenderer xvrRenderer = null;
	private XvrInputManager xvrInputMgr = null;

	public XvrGLSurfaceView(Activity activity) {
		super(activity);
		
		xvrInputMgr = new XvrInputManager(this);
		xvrInputMgr.setResolution(800, 480);
		XvrScene.setInputManager(xvrInputMgr);
		// create input manager 
		
		setEGLContextClientVersion(2);
		xvrRenderer = new XvrGLRenderer(activity,xvrInputMgr); 
		setRenderer(xvrRenderer);
		
		setRenderMode(RENDERMODE_CONTINUOUSLY);
		// create renderer
		
        Log.i("XVR"," XvrGLSurfaceView constructed.");
	}
	
	public boolean onTouchEvent(final MotionEvent event){
		
		xvrInputMgr.onTouchEvent(event);
		
		return true;
	}
	
	public XvrGLRenderer getRenderer(){
		
		if(xvrRenderer != null){
			
			return xvrRenderer;
			
		}else{
			
			return null;
			
		}
	}
	
	public XvrInputManager getInputManager(){
		
		if(xvrInputMgr != null){
			
			return xvrInputMgr;
			
		}else{
			
			return null;
			
		}
	}
}
