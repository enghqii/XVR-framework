package enq.xvr.graphic;

import android.text.style.SuperscriptSpan;

public class XvrImage extends XvrTexture {

	public XvrImage(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}
	
	public void create(){
		//super.create();
	}
	
	public void draw(float x, float y, float scaleX, float scaleY, float rotation){
		spr.draw(this , x, y, scaleX, scaleY, rotation);
	}

	private static XvrSprite spr;
}
