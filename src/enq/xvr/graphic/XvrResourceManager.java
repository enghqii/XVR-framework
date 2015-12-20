package enq.xvr.graphic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class XvrResourceManager {
	
	private Context context;
	
	private int 	nTex =0; // number of textures
	private int[] 	mTextures = null; //텍스쳐 배열
	
	private Map<String,XvrTexture> texPool = null;
	private boolean bCreated = false;
	
	
	public XvrResourceManager(Context context){
		this.context = context;
		texPool = new HashMap<String,XvrTexture>();
	}
	
	public void addPool(String index,XvrTexture tex){
		if(bCreated == false){
			texPool.put(index, tex);
			nTex++;
		}
	}
	
	public void createAllTexrures(){
		
		//GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 4);
		
		Log.e("create all textures", "errorcode = " + GLES20.glGetError());
		
		Bitmap 	bitmap = null;
		BitmapFactory.Options opts =null;
		// 임시 비트맵과 옵션 준비완료
		
		mTextures = new int[nTex];
		//텍스쳐 인덱스 할당
		GLES20.glGenTextures(nTex, mTextures, 0);
		//gl 텍스쳐 공간 할당
		Log.e("create all textures", "errorcode = " + GLES20.glGetError());
		
		Iterator<XvrTexture> it = texPool.values().iterator();
		XvrTexture tex =null;
		// 이터레이터및 임시 텍스쳐 준비
		
		int i=0;
		// 카운터 대기
		
		while(it.hasNext()){
			
			tex = it.next();
		
			bitmap =null;
	    	opts = new BitmapFactory.Options();
			
			try {
				bitmap = BitmapFactory.decodeStream(context.getAssets().open(tex.getPath()), null, opts);
			} catch (IOException e) {
			    Log.e( "Texture", "loadDrawable exception" + e.toString());
				return;
			}
			// bitmap loaded
			
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
			// 가장 가까운 2의 제곱수의 크기로 만들어진 캔버스에 원래 이미지를 그리고 그걸로 텍스쳐 생성을 하겠다
			
			
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextures[i]);
			// 메모리상의 텍스쳐데이터와 우리의 텍스쳐 배열을 잇는다

			Log.e("create all textures", "errorcode = " + GLES20.glGetError());
	
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
			
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_REPEAT);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_REPEAT);


			Log.e("create all textures", "errorcode = " + GLES20.glGetError());
	
			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp2, 0);

			Log.e("create all textures", "errorcode = " + GLES20.glGetError());
			
			bitmap.recycle();
			bmp2.recycle();
			// free memory of used bitmap
			
			tex.setTexProperties(mTextures[i], makeWidth, makeHeight);
			//tex.setTexProperties(mTextures[i], imgWidth, imgHeight);
			// setting texture properties (little bit strange)
			i++;
			// and counting
		}
		bCreated = true;
	}
	
	void deleteAllTexutres(){
		GLES10.glDeleteTextures(nTex, mTextures, 0);
		nTex = 0;
		bCreated = false;
	}
}
