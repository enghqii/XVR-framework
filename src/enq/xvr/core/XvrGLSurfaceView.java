package enq.xvr.core;

import enq.xvr.global;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class XvrGLSurfaceView extends GLSurfaceView {

	public XvrGLSurfaceView(Context context) {
		super(context);
		
		setEGLContextClientVersion(2);
		xvrRenderer = new XvrGLRenderer(context); 
		setRenderer(xvrRenderer);
		
		//setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);//이거 하면 졸라느려짐
		//위 소스 주석을 절대로 해제하지 마라 그렇지 않으면 파멸을 불러일으킬것이니
		
		setRenderMode(RENDERMODE_CONTINUOUSLY);
		//setRenderMode(RENDERMODE_WHEN_DIRTY);
	}
	
	public boolean onTouchEvent(final MotionEvent event){
		global.event = event;
		global.x = event.getX();
		global.y = event.getY();
		return true;
	}
	
	private XvrGLRenderer xvrRenderer = null;
}
