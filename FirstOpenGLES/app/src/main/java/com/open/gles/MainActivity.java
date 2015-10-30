package com.open.gles;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.opengl.*;
import android.content.pm.*;


public class MainActivity extends Activity
{
	private GLSurfaceView glsurfaceView ;
	private boolean rendererSet = false;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		
		glsurfaceView= new GLSurfaceView(this);
        final ActivityManager activityManager = (ActivityManager)getSystemService(Service.ACTIVITY_SERVICE);
		final ConfigurationInfo info = activityManager.getDeviceConfigurationInfo();
		final boolean suportsEs2 = info.reqGlEsVersion >= 0x20000 
		    ||(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
			&& (Build.FINGERPRINT.startsWith("generic")
			||Build.FINGERPRINT.startsWith("unknown")
			||Build.MODEL.contains("Emulator")
			||Build.MODEL.contains("google_sdk")
			||Build.MODEL.contains("Android SDK build for x86")));
		if(suportsEs2){
			glsurfaceView.setEGLContextClientVersion(2);
			glsurfaceView.setRenderer(new 
			AirHockeyRenderer(this));
			rendererSet = true;
		}else{
			Toast.makeText(this,"This device does not supports openGLES v 2.0", Toast.LENGTH_LONG).show();
			return;
		}
		setContentView(glsurfaceView);
	}
		
		
	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		if(rendererSet)
			glsurfaceView.onPause();
	}

	@Override
	protected void onResume()
	{
		// TODO: Implement this method
		super.onResume();
		if(rendererSet){
			glsurfaceView.onResume();
		}
	}
	
	
			
   
}
