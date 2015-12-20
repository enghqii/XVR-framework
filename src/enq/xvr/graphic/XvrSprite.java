package enq.xvr.graphic;

import android.opengl.GLES20;
import android.opengl.Matrix;


public class XvrSprite extends XvrQuad {

	public XvrSprite() {
		super();
	}
	
	void draw(XvrTexture tex,int modelHandle){
		
		Matrix.setIdentityM(mModelMatrix, 0);

		Matrix.setIdentityM(mTranslate, 0);
		Matrix.translateM(mTranslate, 0, this.x, this.y, 0);
		
		Matrix.setIdentityM(mRotate, 0);
		Matrix.rotateM(mRotate, 0, this.rotation , 0, 0, 1);
		
		Matrix.setIdentityM(mScale, 0);
		Matrix.scaleM(mScale, 0, tex.getWidth() * this.scaleX , tex.getHeight() * this.scaleY , 0);
		
		Matrix.multiplyMM(mModelMatrix, 0, mTranslate, 0, mRotate, 0);
		Matrix.multiplyMM(mModelMatrix, 0, mModelMatrix, 0, mScale, 0);
		GLES20.glUniformMatrix4fv(modelHandle, 1, false, mModelMatrix, 0);
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTexIndex());
    	
    	super.draw();
	}
	
	public void draw(XvrTexture tex,int modelHandle,float x,float y,float scaleX,float scaleY,float rotation){
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.rotation = rotation;
		
		this.draw(tex,modelHandle);
	}

	public float x;
	public float y;
	public float rotation;
	public float scaleX;
	public float scaleY;
	
	private float[] mModelMatrix = new float[16];
    private float[] mTranslate = new float[16];
    private float[] mRotate = new float[16];
    private float[] mScale = new float[16];
}
