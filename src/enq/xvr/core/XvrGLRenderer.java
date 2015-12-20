package enq.xvr.core;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.opengl.GLSurfaceView.Renderer;
import android.os.SystemClock;
import android.util.Log;
import enq.xvr.scene.XvrSceneManager;
import enq.xvr.scene.splashScene;

public class XvrGLRenderer implements Renderer {

	private Context mContext = null;
	private XvrSceneManager smgr =null;
	
    private float[] mProjMatrix = new float[16];
    
	private int projHandle =0;
	private int modelHandle =0;
	private int maTextureHandle=0;
	private int rmVertexHandle =0;
	
	private int mProgram =0;
	
	long bfTime =0;
	long elapsedTime =0;
	float timeDelta =0;
	
	public XvrGLRenderer(Context context){
		mContext = context;
		bfTime = SystemClock.uptimeMillis();
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		
		String vertexShaderSrc = 
			"uniform mat4 uProjMatrix; 	  \n" +
			"uniform mat4 uModelMatrix; \n" +
			"attribute vec4 rm_Vertex;    \n" + 
			"attribute vec2 aTextureCoord; \n" +
			"varying vec2 vTextureCoord; \n" +
			"void main()                  \n" +
			"{                            \n" +
			"   gl_Position = uProjMatrix * uModelMatrix * rm_Vertex;  \n" +
			"	vTextureCoord = aTextureCoord; \n" +
			"}";
		
		String fragmentShaderSrc = 
				"precision mediump float;\n" +
				"varying vec2 vTextureCoord;\n" +
				"uniform sampler2D sTexture;\n" +
				"void main() {\n" +
				"  gl_FragColor = texture2D(sTexture, vTextureCoord);\n" +
				"}\n";

		mProgram = createProgram(vertexShaderSrc,fragmentShaderSrc);
		
		projHandle = GLES20.glGetUniformLocation(mProgram, "uProjMatrix");
        if (projHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uProjMatrix");
        }//������� �ڵ�
        
        modelHandle = GLES20.glGetUniformLocation(mProgram, "uModelMatrix");
        if (modelHandle == -1) {
            throw new RuntimeException("Could not get attrib location for uModelMatrix");
        }// ����� �ڵ�
		
		rmVertexHandle = GLES20.glGetAttribLocation(mProgram, "rm_Vertex");
        if (rmVertexHandle == -1) {
            throw new RuntimeException("Could not get attrib location for rm_Vertex");
        }//���ؽ� ��ǥ �ڵ�
        
        maTextureHandle= GLES20.glGetAttribLocation(mProgram, "aTextureCoord");
        if (maTextureHandle == -1) {
            throw new RuntimeException("Could not get attrib location for maTextureHandle");
        }//���ؽ� ��ǥ �ڵ�
		
		GLES20.glUseProgram ( mProgram );
		
		GLES20.glClearColor(1.0f, 0.0f, 1.0f, 1.0f);
		GLES20.glEnable(GLES20.GL_TEXTURE);
		GLES20.glEnable(GLES20.GL_BLEND);
		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {

		GLES20.glViewport ( 0, 0, width, height );
		ortho2D(mProjMatrix, width, height);
		GLES20.glUniformMatrix4fv(projHandle, 1,false, mProjMatrix, 0);
		// set 2D projection

		smgr = new XvrSceneManager();
		smgr.changeScene(new splashScene(mContext,modelHandle));
	}

	public void onDrawFrame(GL10 gl) {
		
		elapsedTime = SystemClock.uptimeMillis() - bfTime;
		timeDelta = elapsedTime / 1000.f;
		
		smgr.frameMove(timeDelta);

		GLES20.glClear( GLES20.GL_COLOR_BUFFER_BIT );
		
		smgr.draw();
		
		bfTime = SystemClock.uptimeMillis();
	}
	
	private void ortho2D(float[] mat,int width,int height){
		
		Matrix.setIdentityM(mat, 0);
		mat[0] = 2.0f / width;
		mat[5] = 2.0f /-height;
		mat[10] = -2;
		mat[12] = -1;
		mat[13] =  1;
		mat[14] = -1;
	}

	private int loadShader(int shaderType, String source) {
        int shader = GLES20.glCreateShader(shaderType);
        if (shader != 0) {
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
            int[] compiled = new int[1];
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
            if (compiled[0] == 0) {
                Log.e("XVRENDERER", "Could not compile shader " + shaderType + ":");
                Log.e("XVRENDERER", GLES20.glGetShaderInfoLog(shader));
                GLES20.glDeleteShader(shader);
                shader = 0;
            }
        }
        return shader;
    }
	
	private int createProgram(String vertexSource, String fragmentSource) {
		
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
        if (vertexShader == 0) {
            return 0;
        }

        int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
        if (pixelShader == 0) {
            return 0;
        }

        int program = GLES20.glCreateProgram();
        
        if (program != 0) {
        	
            GLES20.glAttachShader(program, vertexShader);
            //checkGlError("glAttachShader");
            GLES20.glAttachShader(program, pixelShader);
            //checkGlError("glAttachShader");
            GLES20.glLinkProgram(program);
            int[] linkStatus = new int[1];
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
            if (linkStatus[0] != GLES20.GL_TRUE) {
                Log.e("Create Program", "Could not link program: ");
                Log.e("Create Program", GLES20.glGetProgramInfoLog(program));
                GLES20.glDeleteProgram(program);
                program = 0;
            }
        }
        
        return program;
    }
}