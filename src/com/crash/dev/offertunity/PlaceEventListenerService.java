package com.crash.dev.offertunity;

import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class PlaceEventListenerService extends Service{

	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;
	private PlaceEventListener placeEventListener;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		/*contextCoreConnector = ContextCoreConnectorFactory.get(this);
        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        placeEventListener = new PlaceEventListener() {

            @Override
            public void placeEvent(PlaceEvent event) {
                String placeNameAndId = "id: " + event.getPlace().getId() + " name: " + event.getPlace().getPlaceName();
                Toast.makeText(getApplicationContext(), placeNameAndId, Toast.LENGTH_LONG).show();
                Log.i("found place", placeNameAndId);
                
                if(event.getEventType() == "AT")
                	Toast.makeText(getApplicationContext(), "(SERVICE) Has llegado a:"+ event.getPlace().getPlaceName(), Toast.LENGTH_LONG).show();
                else
                	Toast.makeText(getApplicationContext(), "(SERVICE) Has salido de:"+ event.getPlace().getPlaceName(), Toast.LENGTH_LONG).show();*/
            	
            	//NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            	//Notification notification = new Notification(R.drawable.ic_launcher, "Estas en zona Offertunity", System.currentTimeMillis());
            	
            	
          //  }
        //};
        
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Servicio de Lugares en Ejecucion", Toast.LENGTH_SHORT).show();
		
		//Aqui va el show...
		
		
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Servicio de Lugares Destruido", Toast.LENGTH_SHORT).show();
	}
	
}
