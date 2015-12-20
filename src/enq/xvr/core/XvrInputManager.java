package enq.xvr.core;

import java.util.LinkedList;
import java.util.Queue;

import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class XvrInputManager {
	
	private View mainView =null;
	
	private int viewWidth =1;
	private int viewHeight =1;
	
	private MotionEvent event =null;
	
	public static final int ACTION_NONE = -1;
	public static final int ACTION_DOWN = MotionEvent.ACTION_DOWN;
	public static final int ACTION_MOVE = MotionEvent.ACTION_MOVE;
	public static final int ACTION_UP = MotionEvent.ACTION_UP;
	
	private int state = ACTION_NONE;
	private Queue<Integer> messageQueue = null;
	
	private boolean isTouched = false;
	
	private float x =0;
	private float y =0;
	
	public XvrInputManager(View mainView){
		
		this.mainView = mainView;
		
		viewWidth = this.mainView.getWidth();
		viewHeight = this.mainView.getHeight();
		
		messageQueue = new LinkedList<Integer>();

        Log.i("XVR","XvrInputManager constructed.");
	}
	
	public void onTouchEvent(MotionEvent event){
		
		this.event = event;
		//state = this.event.getAction();
		messageQueue.add(this.event.getAction());
		state = messageQueue.poll();
		this.x = this.event.getX();
		this.y = this.event.getY();
		
		// TODO 여기서 view 의 크기랑 해상도로 x,y를 조절한다
		
		switch(state){
		
		case ACTION_UP:
			isTouched = false;
			break;
			
		case ACTION_DOWN:
		case ACTION_MOVE:
			isTouched = true;
			break;
		}
	}
	
	public void onBackPressed() {
		// do something
	}
	
	public MotionEvent getEvent(){
		return this.event;
	}
	
	public int getState(){
		
		return state;
	}
	
	public boolean isTouched(){
		return isTouched;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	protected void update(){
		
		if(messageQueue.isEmpty() == false){
			state = messageQueue.poll();
		}
		
		if(state == ACTION_UP){
			state = ACTION_NONE;
		}
	}

}
