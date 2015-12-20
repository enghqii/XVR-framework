package enq.xvr.graphic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES10;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class XvrResourceManager {
	
	private Context context;
	
	private int 	nTex =0; // number of textures
	private int[] 	mTextures = null; //�ؽ��� �迭
	
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
		
		//GLES10.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 4);
		
		Log.e("create all textures", "errorcode = " + GLES20.glGetError());
		
		Bitmap 	bitmap = null;
		BitmapFactory.Options opts =null;
		// �ӽ� ��Ʈ�ʰ� �ɼ� �غ�Ϸ�
		
		mTextures = new int[nTex];
		//�ؽ��� �ε��� �Ҵ�
		GLES20.glGenTextures(nTex, mTextures, 0);
		//gl �ؽ��� ���� �Ҵ�
		Log.e("create all textures", "errorcode = " + GLES20.glGetError());
		
		Iterator<XvrTexture> it = texPool.values().iterator();
		XvrTexture tex =null;
		// ���ͷ����͹� �ӽ� �ؽ��� �غ�
		
		int i=0;
		// ī���� ���
		
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
			
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextures[i]);
			// �޸𸮻��� �ؽ��ĵ����Ϳ� �츮�� �ؽ��� �迭�� �մ´�

			Log.e("create all textures", "errorcode = " + GLES20.glGetError());
	
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
			GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
			
			GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_REPEAT);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_REPEAT);


			Log.e("create all textures", "errorcode = " + GLES20.glGetError());
	
			GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

			Log.e("create all textures", "errorcode = " + GLES20.glGetError());
			
			bitmap.recycle();
			// ���� ��Ʈ���� ��Ȱ���Ϸ�
			
			tex.setTexProperties(mTextures[i], opts.outWidth, opts.outHeight);
			i++;
			// �ؽ��� �Ӽ������� ī����
		}
		bCreated = true;
	}
	
	void deleteAllTexutres(){
		GLES10.glDeleteTextures(nTex, mTextures, 0);
		nTex = 0;
		bCreated = false;
	}
}
