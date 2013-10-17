package com.crash.dev.offertunity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class MainActivity extends SherlockActivity {

	private TextView tv;
	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;
    private PlaceEventListener placeEventListener = new PlaceEventListener() {

            @Override
            public void placeEvent(PlaceEvent event) {
                String placeNameAndId = "id: " + event.getPlace().getId() + " name: " + event.getPlace().getPlaceName();
                Toast.makeText(getApplicationContext(), placeNameAndId, Toast.LENGTH_LONG).show();
                Log.i("found place", placeNameAndId);
                
                if(event.getEventType() == "AT")
                	tv.setText("Has llegado a: "+ event.getPlace().getPlaceName());
                else
                	tv.setText("Has salidp de: "+ event.getPlace().getPlaceName());
                
            }
        };
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextCoreConnector = ContextCoreConnectorFactory.get(this);
        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        checkContextConnectorStatus();
        
        tv = (TextView) findViewById(R.id.label); 
        
        Parse.initialize(this, "fGChkFITD5vaE3N0INg5neDfjW4TPX9brSkum6G8", "iHheq8svB9iaIaOT4bJR6Zoz04drJ6kHxIcMRgk6");
        ParseAnalytics.trackAppOpened(getIntent());
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("Q onda", "bandita!!");
        testObject.saveInBackground();
        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	contextCoreConnector.setCurrentActivity(this);
    	
    	Toast.makeText(getApplicationContext(), "Welcome back Crash!", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	contextCoreConnector.setCurrentActivity(null); 

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	getSupportMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void checkContextConnectorStatus(){
    	if (contextCoreConnector.isPermissionEnabled()) {
            startListeningForGeofences();
        }
        else {
            contextCoreConnector.enable(this, new Callback<Void>() {

                @Override
                public void success(Void arg0) {
                    startListeningForGeofences();
                }

                @Override
                public void failure(int arg0, String arg1) {
                    Log.e("failed to enable", arg1);
                }
            });
        }
    };
    
    private void startListeningForGeofences() {
        contextPlaceConnector.addPlaceEventListener(placeEventListener);
    }
    
    //Testing
    public void comenzarServicio(View v){
        startService(new Intent(getBaseContext(), PlaceEventListenerService.class));
    }
    
    public void detenerServicio(View v){
        stopService(new Intent(getBaseContext(), PlaceEventListenerService.class));
    }
    
}
