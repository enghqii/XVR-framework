package enq.xvr;

import android.app.Activity;
import android.os.Bundle;
import enq.xvr.core.XVR;
import enq.xvr.scene.splashScene;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
   
    private XVR xvr =null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        xvr = new XVR();
        xvr.setScreenMode(true, XVR.XVR_SCREEN_LANDSCAPE);
        xvr.create(this);
        xvr.setEntryScene(new splashScene());
        
    }
    
    public void onResume(){
    	super.onResume();
    	xvr.onResume();
    }
    
    public void onPause(){
    	super.onPause();
    	xvr.onPause();
    }
}