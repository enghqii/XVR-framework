package enq.xvr.core;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ConfigurationInfo;
import android.util.Log;
import android.view.WindowManager;
import enq.xvr.scene.XvrScene;
import enq.xvr.scene.XvrSceneManager;

public class XVR {
	
	private boolean isCreated = false;
	
	private XvrGLSurfaceView glView =null;
	private Activity mActivity =null;
	
	private boolean forceFullscreen = false;
	private int screenMode = XVR_SCREEN_PORTRAIT;
	
	public final static int XVR_SCREEN_LANDSCAPE = 0;
	public final static int XVR_SCREEN_PORTRAIT  = 1;
	public final static int XVR_SCREEN_NORMAL = 2;
	
	public XVR(){
		isCreated = false;
	}
	
	public void onResume(){
		glView.onResume();
	}

	public void onPause(){
		glView.onPause();
	}
	
	public void onStop(){
		
	}
	
	public void create(Activity activity){
		
		this.mActivity = activity;
		
		if( detectOpenGLES20() == false ){
        	// Sorry. your phone does not support open gl ES 2.0
    		this.mActivity.finish();
        }
        
        adjustScreenMode();
        
        glView = new XvrGLSurfaceView(this.mActivity);
        this.mActivity.setContentView(glView);
     
        isCreated = true;
        
        Log.i("XVR","XVR created.");
	}
	
	public void setEntryScene(XvrScene entryScene){
		
		getSceneManager().setEntryScene(entryScene);
	}
	
	private XvrSceneManager getSceneManager(){
		
		if(isCreated){
			return glView.getRenderer().getSceneManager();
		}else{
			return null;
		}
		
	}
	
	public void setScreenMode(boolean forceFullscreen,int screenMode){
		
		this.forceFullscreen = forceFullscreen;
		this.screenMode = screenMode;
		
	}
	
	private void adjustScreenMode(){
		   
    	if(forceFullscreen) {
    		this.mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    		this.mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    		this.mActivity.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
		}
		if(screenMode == XVR.XVR_SCREEN_LANDSCAPE) {
			this.mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		else if(screenMode == XVR.XVR_SCREEN_PORTRAIT) {
			this.mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		else if(screenMode == XVR.XVR_SCREEN_NORMAL){
			// nothing to do
		}
    }
	
	private boolean detectOpenGLES20() {
		
        ActivityManager am = (ActivityManager) this.mActivity.getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo info = am.getDeviceConfigurationInfo();
        return (info.reqGlEsVersion >= 0x20000);
    }
}
