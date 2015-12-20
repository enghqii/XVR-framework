package enq.xvr.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ConfigurationInfo;
import android.os.Bundle;
import android.view.WindowManager;

public class XvrActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initActivity();
    }
    
    public void initActivity(){
    	
    	if( detectOpenGLES20() == false ){
        	// Sorry. your phone does not support Opengl ES 2.0
    		finish();
        }
        
        setScreenMode(true,XvrActivity.XVR_SCREEN_PORTRAIT);
        setGraphicPath("/img");
        
        enqView = new XvrGLSurfaceView(this);
        inputMgr = new XvrInputManager(enqView);
        setContentView(enqView);
    }
    
    public void onResume(){
    	super.onResume();
    	enqView.onResume();
    }
    
    public void onPause(){
    	super.onPause();
    	enqView.onPause();
    }
    
    void setScreenMode(boolean forceFullscreen,int screenMode){
	   
    	if(forceFullscreen) {
	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	        getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
		}
		if(screenMode == XvrActivity.XVR_SCREEN_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		else if(screenMode == XvrActivity.XVR_SCREEN_PORTRAIT) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		else if(screenMode == XvrActivity.XVR_SCREEN_NORMAL){
			
		}
    }
    
    public void setGraphicPath(String path){
    	graphicPath = path;
    }
    
    private boolean detectOpenGLES20() {
        ActivityManager am =
            (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }
    
    private XvrGLSurfaceView enqView = null;
    protected XvrInputManager inputMgr = null;
    
    protected static String graphicPath = "";
    
    protected final static int XVR_SCREEN_LANDSCAPE = 0;
    protected final static int XVR_SCREEN_PORTRAIT  = 1;
    protected final static int XVR_SCREEN_NORMAL = 2;
    
}