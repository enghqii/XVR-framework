package enq.xvr.graphic;

import android.opengl.GLES20;
import android.opengl.Matrix;


public class XvrSprite extends XvrQuad {
	
	private static int modelHandle =0;

	public float x =0;
	public float y =0;
	public float rotation =0;
	public float scaleX =0;
	public float scaleY =0;
	public float centreX =0;
	public float centreY =0;
	public XvrRect clippingRect = null;
	
	
	private float[] uvs = new float[8];
	
	private float[] mModelMatrix = new float[16];
	
    private float[] mTranslate = new float[16];
    //t
    private float[] mRotate = new float[16];
    private float[] mTr1 = new float[16];
    private float[] mTr2 = new float[16];
    //r
    private float[] mScale = new float[16];
    //s
    
    
	public XvrSprite() {
		
		super();
	
	}

	public static void setModelHandle(int modelHandle){
		
		XvrSprite.modelHandle = modelHandle;
	
	}
	
	private void draw(XvrTexture tex){
		
		setMatrix(tex.getWidth(), tex.getHeight());
		
		setUV(tex.getWidth(), tex.getHeight());
		
		GLES20.glUniformMatrix4fv(modelHandle, 1, false, mModelMatrix, 0);
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex.getTexIndex());
    	
		if(clippingRect == null){
			
			super.draw(null);
			
		}else{
			
			super.draw(uvs);
			
		}
	}
	
	public void draw(XvrTexture tex,float x, float y, float scaleX, float scaleY, float centreX, float centreY, float rotation, XvrRect clippingRect){
		
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.centreX = centreX;
		this.centreY = centreY;
		this.rotation = rotation;
		this.clippingRect = clippingRect;
		
		this.draw(tex);
	}
	
	private void setMatrix(float textureWidth, float textureHight){
		
		Matrix.setIdentityM(mModelMatrix, 0);

		Matrix.setIdentityM(mTranslate, 0);
		Matrix.translateM(mTranslate, 0, this.x, this.y, 0);
		//Translation
		
		Matrix.setIdentityM(mRotate, 0);
		Matrix.setIdentityM(mTr1, 0);
		Matrix.setIdentityM(mTr2, 0);
		
		Matrix.rotateM(mRotate, 0, this.rotation * 180.f / 3.1415f , 0, 0, 1);
		Matrix.translateM(mTr1, 0, -centreX, -centreY, 0);
		Matrix.translateM(mTr2, 0, +centreX, +centreY, 0);
		
		Matrix.multiplyMM(mRotate, 0, mTr2, 0, mRotate, 0);
		Matrix.multiplyMM(mRotate, 0, mRotate, 0, mTr1, 0);
		//Rotation
		
		Matrix.setIdentityM(mScale, 0);
		
		if(clippingRect == null)
			Matrix.scaleM(mScale, 0, textureWidth * this.scaleX , textureHight * this.scaleY , 0);
		else
			Matrix.scaleM(mScale, 0, clippingRect.width * this.scaleX , clippingRect.height * this.scaleY , 0);
		//Scale
		
		Matrix.multiplyMM(mModelMatrix, 0, mTranslate, 0, mRotate, 0);
		Matrix.multiplyMM(mModelMatrix, 0, mModelMatrix, 0, mScale, 0);
	}
	
	private void setUV(float textureWidth, float textureHight){
		
		if(clippingRect != null){
		
			uvs[0] = clippingRect.left * 1 / textureWidth;
			uvs[1] = clippingRect.bottom * 1 / textureHight;
		
			uvs[2] = clippingRect.left * 1 / textureWidth;
			uvs[3] = clippingRect.top * 1 / textureHight;

			uvs[4] = clippingRect.right * 1 / textureWidth;
			uvs[5] = clippingRect.bottom * 1 / textureHight;
		
			uvs[6] = clippingRect.right * 1 / textureWidth;
			uvs[7] = clippingRect.top * 1 / textureHight;
		
		}
	}
}
