package enq.xvr.graphic;

import android.content.Context;

public class XvrAnimatedImage extends XvrImage {
	
	// time
	private float secondPerFrame = 1/60f;
	private float curFrameTime =0;
	
	// frame
	private int nFrame =1;
	private int curFrame =0;
	
	private float frameSizeX =1;
	private float frameSizeY =1;
	
	//UV
	private XvrRect[] uv = null;
	
	//play
	private boolean isPlaying =false;
	
	public XvrAnimatedImage(String path, float frameSizeX, float frameSizeY) {
		super(path);
		setFrameSize(frameSizeX, frameSizeY);
	}

	@Override
	public void create(Context context){
		super.create(context);
		
		nFrame = (int) (textureWidth / frameSizeX);
		uv = new XvrRect[nFrame];
		
		for(int i=0 ; i < nFrame; i++){
			// and here we should create UVs.
			uv[i] = new XvrRect( (int)(frameSizeX * i) , 0, (int)(frameSizeX * (i+1)), (int)(frameSizeY));
		}
		
	}
	
	public void play(){
		
		isPlaying = true;
	}
	
	public void pause(){
		
		isPlaying = false;
	}
	
	public void stop(){

		isPlaying = false;
		curFrame = 0;
	}
	
	public void setSpeed(float spd){
		secondPerFrame = spd;
	}
	
	private void setFrameSize(float sizeX, float sizeY){
		frameSizeX = sizeX;
		frameSizeY = sizeY;
	}
	
	public void update(float timeDelta){
		
		if(isPlaying == true){
			
			curFrameTime += timeDelta;
		
			if(curFrameTime >= secondPerFrame){
				// change to next frame
				curFrame++;
				curFrame %= nFrame;
				curFrameTime =0;
			}
		}
		
	}
	
	public void draw(float x, float y){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, uv[curFrame] );
	}
	
	public void draw(float x, float y, float rotation){
		spr.draw(this, x, y, 1, 1, 0, 0, rotation, uv[curFrame]);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY){
		spr.draw(this, x, y, scaleX, scaleY, 0, 0, 0, uv[curFrame]);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, 0, uv[curFrame]);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, uv[curFrame]);
	}
	
}
