package com.crash.dev.offertunity;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.Menu;
import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextCoreStatus;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class TestGimbalServicesActivity extends SherlockActivity {

	private TextView tv;
	
	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;
    private PlaceEventListener placeEventListener = new PlaceEventListener() {

            @Override
            public void placeEvent(PlaceEvent event) {
                String placeNameAndId = "id: " + event.getPlace().getId() + " name: " + event.getPlace().getPlaceName();
                Toast.makeText(getApplicationContext(), "(Activity) " + placeNameAndId, Toast.LENGTH_LONG).show();
                Log.i("(Activity) found place", placeNameAndId);
                
                if(event.getEventType() == "AT")
                	tv.setText("Has llegado a: "+ event.getPlace().getPlaceName());
                else
                	tv.setText("Has salido de: "+ event.getPlace().getPlaceName());
                
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
        
        /*Parse.initialize(this, "fGChkFITD5vaE3N0INg5neDfjW4TPX9brSkum6G8", "iHheq8svB9iaIaOT4bJR6Zoz04drJ6kHxIcMRgk6");
        ParseAnalytics.trackAppOpened(getIntent());
        ParseObject mytestObject = new ParseObject("Prueba de a solapa");
        mytestObject.put("Q onda", "bandita!!");
        mytestObject.saveInBackground();
        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();*/

    }
    
    //Activity Lifecycle
    @Override
    protected void onResume() {
    	super.onResume();
    	contextCoreConnector.setCurrentActivity(this);
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
    
    //Testing for Gimbal
    private void checkContextConnectorStatus(){
    	if (contextCoreConnector.isPermissionEnabled()) {
    		startGimbalService();
            startListeningForGeofences();
        }
        else {
            contextCoreConnector.enable(this, new Callback<Void>() {

                @Override
                public void success(Void arg0) {
                	startGimbalService();
                    startListeningForGeofences();
                }

                @Override
                public void failure(int arg0, String arg1) {
                    Log.e("failed to enable", arg1);
                }
            });
        }
    };
    
    private void startGimbalService() {
        Intent gimbalServiceIntent = new Intent(this, PlaceEventListenerService.class);
        gimbalServiceIntent.setAction("com.crash.dev.offertunity.PLACE_EVENT_LISTENER_SERVICE");
        startService(gimbalServiceIntent);
    }
    
    private void startListeningForGeofences() {
        contextPlaceConnector.addPlaceEventListener(placeEventListener);
    }
    
    //Testing for Services
    /*public void comenzarServicio(View v){
        startService(new Intent(getBaseContext(), PlaceEventListenerService.class));
    }
    
    public void detenerServicio(View v){
        stopService(new Intent(getBaseContext(), PlaceEventListenerService.class));
    }
    
    //Testing Notifications
	public void lanzarNotificacion(View v){
		
		Intent intent = new Intent(this, NotificationReceiver.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
	    .setContentTitle("Offertunity")
	    .setContentText("Bienvendo, ÁEstas en zona de ofertas!");
		notification.setContentIntent(pIntent);
		notification.setDefaults(Notification.DEFAULT_ALL);
		notification.setAutoCancel(true);
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(1234, notification.build() );
    }*/
    
}
