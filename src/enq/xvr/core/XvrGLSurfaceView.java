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
		
		//setDebugFlags(DEBUG_CHECK_GL_ERROR | DEBUG_LOG_GL_CALLS);//�̰� �ϸ� ���������
		//�� �ҽ� �ּ��� ����� �������� ���� �׷��� ������ �ĸ��� �ҷ�����ų���̴�
		
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
