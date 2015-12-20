package enq.xvr.graphic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.content.Context;

public class XvrResourceManager {
	
	// essential android class
	private Context context;
	
	// managing classes
	private Map<String,XvrImage> imgPool = null;
	private boolean bCreated = false;
	
	// graphical classes
	private XvrSprite spr = null;
	
	public XvrResourceManager(Context context){
		
		this.context = context;
		
		imgPool = new HashMap<String,XvrImage>();
		spr = new XvrSprite();
		XvrImage.setSprite(spr);
		
	}
	
	public XvrImage addImage(String index,String path){
		if(bCreated == false){
			XvrImage img = new XvrImage(path);
			imgPool.put(index, img);
			return img;
		}
		return null;
	}
	
	public XvrImage getImage(String index){
		if(bCreated){
			return imgPool.get(index);
		}
		return null;
	}
	
	public void create(){
		
		if(bCreated == false){
			
			Iterator<XvrImage> it = imgPool.values().iterator();
			XvrImage img =null;
		
			while(it.hasNext()){
				img = it.next();
				img.create(context);
			}
			bCreated = true;
		}
	}
	
	void delete(){
		// do not use now
		
		if(bCreated == true){
			
			Iterator<XvrImage> it = imgPool.values().iterator();
			XvrImage img =null;
		
			while(it.hasNext()){
				img = it.next();
			
				//delete texture here
			}
		
			bCreated = false;
		}
	}
}
