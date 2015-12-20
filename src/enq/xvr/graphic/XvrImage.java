package enq.xvr.graphic;

import android.content.Context;
import enq.xvr.core.XvrColour;

public class XvrImage extends XvrTexture {

	protected static XvrSprite spr;
	
	public XvrImage(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public void create(Context context){
		super.create(context);
	}
	
	public void draw(float x, float y){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, null, null);
	}
	
	public void draw(float x, float y, float rotation){
		spr.draw(this, x, y, 1, 1, 0, 0, rotation, null, null);
	}
	
	public void draw(float x, float y, XvrRect clippingRect){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, clippingRect, null);
	}
	
	public void draw(float x, float y, XvrColour colour){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, null, colour);
	}
	
	public void draw(float x, float y, XvrRect clippingRect, XvrColour colour){
		spr.draw(this, x, y, 1, 1, 0, 0, 0, clippingRect, colour);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY){
		spr.draw(this, x, y, scaleX, scaleY, 0, 0, 0, null, null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, XvrColour colour){
		spr.draw(this, x, y, scaleX, scaleY, 0, 0, 0, null, colour);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, null, null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation, XvrRect clippingRect){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, clippingRect, null);
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float centreX, float centreY,float rotation, XvrRect clippingRect, XvrColour colour){
		spr.draw(this, x, y, scaleX, scaleY, centreX, centreY, rotation, clippingRect, colour);
	}

	public static void setSprite(XvrSprite spr){
		XvrImage.spr = spr;
	}
}
