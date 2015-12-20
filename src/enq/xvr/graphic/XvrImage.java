package enq.xvr.graphic;

import android.content.Context;

public class XvrImage extends XvrTexture {

	public XvrImage(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public void create(Context context){
		super.create(context);
	}
	
	public void draw(float x, float y){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, null);
	}
	
	public void draw(float x, float y, float rotation){
		spr.draw(this, x, y, 1, 1, 0, 0, rotation, null);
	}
	
	public void draw(float x, float y, XvrRect clippingRect){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, clippingRect);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY){
		spr.draw(this, x, y, scaleX, scaleY, 0, 0, 0, null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation, XvrRect clippingRect){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, clippingRect);
	}

	public static void setSprite(XvrSprite spr){
		XvrImage.spr = spr;
	}
	
	private static XvrSprite spr;
}
