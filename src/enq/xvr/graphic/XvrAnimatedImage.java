package enq.xvr.graphic;

import android.content.Context;
import android.util.Log;
import enq.xvr.core.XvrColour;

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
	
	private boolean playUnlimited = true;
	private int nTimes = 0;
	private int curTimes = 0;
	
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
	
	public XvrAnimatedImage deepCopy(){

		XvrAnimatedImage tempAnim = new XvrAnimatedImage(this.getPath(), frameSizeX, frameSizeY);
		
		tempAnim.setTexIndex(this.getTexIndex());
		
		tempAnim.isCreated = this.isCreated;
		tempAnim.makeHeight = this.makeHeight;
		tempAnim.makeWidth = this.makeWidth;
		tempAnim.textureHeight = this.textureHeight;
		tempAnim.textureWidth = this.textureWidth;
	
		tempAnim.curFrame = this.curFrame;
		tempAnim.curFrameTime = this.curFrameTime;
		tempAnim.frameSizeX = this.frameSizeX;
		tempAnim.frameSizeY = this.frameSizeY;
		
		tempAnim.isPlaying = this.isPlaying;
		tempAnim.nFrame = this.nFrame;
		tempAnim.secondPerFrame = this.secondPerFrame;
		tempAnim.uv = this.uv;
		
		tempAnim.playUnlimited = this.playUnlimited;
		tempAnim.nTimes = this.nTimes;
		tempAnim.curTimes = this.curTimes;
		
		return tempAnim;
	}
	
	public void play(){
		
		isPlaying = true;
		playUnlimited = true;
	}
	
	public void play(int nTimes){
		
		isPlaying = true;
		this.nTimes = nTimes;
		playUnlimited = false;
	}
	
	public void pause(){
		
		isPlaying = false;
	}
	
	public void stop(){

		isPlaying = false;
		curTimes = 0;
		curFrame = 0;
	}
	
	public void setSpeed(float spd){
		secondPerFrame = spd;
	}
	
	private void setFrameSize(float sizeX, float sizeY){
		frameSizeX = sizeX;
		frameSizeY = sizeY;
	}
	
	public int getCurrentFrame(){
		return curFrame;
	}
	
	public boolean isPlaying(){
		return isPlaying;
	}
	
	public void update(float timeDelta){
		
		if(isPlaying == true){
			
			curFrameTime += timeDelta;
		
			if(curFrameTime >= secondPerFrame){
				// change to next frame
				
				curFrame++;
				
				if(curFrame >= nFrame){

					curFrame %= nFrame;	
					
					if(playUnlimited == false){
						curTimes++;
						Log.d("animated", "curTimes " + curTimes);
					
						if(curTimes >= nTimes){
							stop();
						}
					}
				}
				curFrameTime =0;
			}
		}
		
	}
	
	public void draw(float x, float y){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, uv[curFrame] ,null);
	}
	
	public void draw(float x, float y, XvrColour colour){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, uv[curFrame] ,colour);
	}
	
	public void draw(float x, float y, float rotation){
		spr.draw(this, x, y, 1, 1, 0, 0, rotation, uv[curFrame],null);
	}
	
	public void draw(float x, float y, float rotation, XvrColour colour){
		spr.draw(this, x, y, 1, 1, 0, 0, rotation, uv[curFrame], colour);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY){
		spr.draw(this, x, y, scaleX, scaleY, 0, 0, 0, uv[curFrame],null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, XvrColour colour){
		spr.draw(this, x, y, scaleX, scaleY, 0, 0, 0, uv[curFrame],colour);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, 0, uv[curFrame],null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY, XvrColour colour){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, 0, uv[curFrame],colour);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, uv[curFrame], null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation, XvrColour colour){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, uv[curFrame], colour);
	}
	
}
