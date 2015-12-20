package enq.xvr.core;

import android.view.MotionEvent;
import android.view.View;

public class XvrInputManager {
	
	private View mainView =null;
	
	private int viewWidth =1;
	private int viewHeight =1;
	
	private MotionEvent event =null;
	
	private float x =0;
	private float y =0;
	
	public XvrInputManager(View mainView){
		
		this.mainView = mainView;
		
		viewWidth = this.mainView.getWidth();
		viewHeight = this.mainView.getHeight();
	}
	
	public void setEvent(MotionEvent event){
		
		this.event = event;
		this.x = this.event.getX();
		this.y = this.event.getY();
		
		// TODO ���⼭ view �� ũ��� �ػ󵵷� x,y�� �����Ѵ�
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
}
