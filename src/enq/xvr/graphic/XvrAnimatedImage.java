package enq.xvr.graphic;

import android.content.Context;
import android.graphics.Rect;

public class XvrAnimatedImage extends XvrImage {
	
	// time
	private float secondPerFrame =0;
	private float curFrameTime =0;
	
	// frame
	private int nFrame =1;
	private int curFrame =0;
	
	private float frameSizeX =1;
	private float frameSizeY =1;
	
	//UV
	private XvrRect[] uv = null;
	
	public XvrAnimatedImage(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public XvrAnimatedImage(String path, float frameSizeX, float frameSizeY) {
		super(path);
		setFrameSize(frameSizeX, frameSizeY);
	}

	@Override
	public void create(Context context){
		super.create(context);
		
		nFrame = (int) (width / frameSizeX);
		uv = new XvrRect[nFrame];
		
		for(int i=0; i < nFrame; i++){
			// and here we should create UVs.
			//uv[i] = new XvrRect(1,2,3,4);
		}
		
	}
	
	public void setSpeed(float spd){
		secondPerFrame = spd;
	}
	
	public void setFrameSize(float sizeX, float sizeY){
		frameSizeX = sizeX;
		frameSizeY = sizeY;
	}
	
	public void update(float timeDelta){
		curFrameTime += timeDelta;
		
		if(curFrameTime >= secondPerFrame){
			// change to next frame
			curFrame++;
			curFrame %= nFrame;
			curFrameTime =0;
		}
	}
}
