package enq.xvr.graphic;

import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class XvrTexture {
	
	// gl vars
	private int texIndex =0;
	private int[] mTexture = null;
	
	private String path;
	
	// properties
	protected float width =0;
	protected float height =0;
	
	// flag
	protected boolean isCreated = false;
	
	public XvrTexture(String path){
		this.path = path;
	}
	
	public void create(Context context){
		
		Log.d("XvrTexture", "createTexture");
		
		Bitmap 	bitmap = null;
		BitmapFactory.Options opts = new BitmapFactory.Options();
		// �ӽ� ��Ʈ�ʰ� �ɼ� �غ�

		mTexture = new int[1];
		GLES20.glGenTextures(1, mTexture, 0);
		//gl �ؽ��� ���� �Ҵ�
		
		try {
			bitmap = BitmapFactory.decodeStream(context.getAssets().open(path), null, opts);
		} catch (IOException e) {
		    Log.e( "XvrTexture", "loadDrawable exception" + e.toString());
			return;
		}
		

		int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        // get the size of image
        
        int makeWidth = 2;
        int makeHeight = 2;
        
        while( makeWidth < imgWidth )
			makeWidth *= 2;
		while( makeHeight < imgHeight )
			makeHeight *= 2;
		// and get nearest power of 2
		
		Bitmap bmp2 = Bitmap.createBitmap(makeWidth, makeHeight, Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp2);
		canvas.drawBitmap(bitmap, new Rect(0,0,imgWidth,imgHeight), new Rect(0,0,imgWidth,imgHeight), null);
		// ���� ����� 2�� �������� ũ��� ������� ĵ������ ���� �̹����� �׸��� �װɷ� �ؽ��� ������ �ϰڴ�
		
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTexture[0]);
		// �޸𸮻��� �ؽ��ĵ����Ϳ� �츮�� �ؽ��� �迭�� �մ´�

		Log.e("XvrTexture", "errorcode = " + GLES20.glGetError());

		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_REPEAT);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_REPEAT);


		Log.e("XvrTexture", "errorcode = " + GLES20.glGetError());

		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp2, 0);

		Log.e("XvrTexture", "errorcode = " + GLES20.glGetError());
		
		bitmap.recycle();
		bmp2.recycle();
		// free memory of used bitmap
		
		this.setTexProperties(mTexture[0], makeWidth, makeHeight);
		//tex.setTexProperties(mTextures[i], imgWidth, imgHeight);
		// setting texture properties (little bit strange)
		
		isCreated = true;
	}
	
	public String getPath(){
		return path;
	}
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public void setTexProperties(int texIndex,float width,float height) {
		this.texIndex = texIndex;
		this.width = width;
		this.height = height;
	}
	public int getTexIndex(){
		return texIndex;
	}
	
}
