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
	
	public void addAnimatedImage(String index, String path, float frameSizeX, float frameSizeY){
		
		if(bCreated == false){
			XvrAnimatedImage anim = new XvrAnimatedImage(path, frameSizeX, frameSizeY);
			imgPool.put(index, anim);
		}
	}
	
	public XvrImage getImage(String index){
		
		if(bCreated == true){
		
			XvrImage temp = imgPool.get(index);
			return temp;
		
		}
		return null;
	}
	
	public XvrAnimatedImage getAnimatedImage(String index){
		
		if(bCreated == true){
		
			XvrAnimatedImage temp = (XvrAnimatedImage) imgPool.get(index);
			return temp.deepCopy();
		
		}else if(bCreated == false){
			
			return (XvrAnimatedImage) imgPool.get(index);
			
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
	
	public void delete(){
		
		if(bCreated == true){
			
			Iterator<XvrImage> it = imgPool.values().iterator();
			XvrImage img =null;
		
			while(it.hasNext()){
				img = it.next();
				//delete texture here
				img.release();
			}
		
			bCreated = false;
		}
	}
}
