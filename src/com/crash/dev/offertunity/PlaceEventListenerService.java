package com.crash.dev.offertunity;

import com.qualcommlabs.usercontext.ConnectorPermissionChangeListener;
import com.qualcommlabs.usercontext.ContentListener;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.ContentEvent;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class PlaceEventListenerService extends Service implements PlaceEventListener, ContentListener{

	public static final String PLACE_EVENT_DESCRIPTION_KEY = "PLACE_EVENT_DESCRIPTION_KEY";

    static int notificationId = 1;
	
	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;
	//private ContextInterestsConnector contextInterestsConnector;
	
	private final ConnectorPermissionChangeListener subscriptionPermissionChangeListener = new ConnectorPermissionChangeListener() {
        @Override
        public void permissionChanged(Boolean enabled) {
            if (enabled == false) {
                permissionDisabled();
            }
        }
    };
	
    @Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//Toast.makeText(this, "Servicio de Lugares en Ejecucion", Toast.LENGTH_SHORT).show();
    	super.onStartCommand(intent, flags, startId);
		return START_STICKY;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		contextCoreConnector = ContextCoreConnectorFactory.get(this);
		contextCoreConnector.addContentListener(this);
		contextCoreConnector.addConnectorPermissionChangeListener(subscriptionPermissionChangeListener);
		
        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        contextPlaceConnector.addPlaceEventListener(this);
        
        //contextInterestsConnector = ContextInterestsConnectorFactory.get(this);
        //contextInterestsConnector.addInterestChangeListener(this);
        
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	

	@Override
	public void onDestroy() {
		super.onDestroy();
		//Toast.makeText(this, "Servicio de Lugares Destruido", Toast.LENGTH_SHORT).show();
		contextCoreConnector.removeContentListener(this);
        contextCoreConnector.removeConnectorPermissionChangeListener(subscriptionPermissionChangeListener);
        contextPlaceConnector.removePlaceEventListener(this);
	}

	@Override
	public void contentEvent(ContentEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void placeEvent(PlaceEvent event) {
		String placeNameAndId = "id: " + event.getPlace().getId() + " name: " + event.getPlace().getPlaceName();
        //Toast.makeText(getApplicationContext(), placeNameAndId, Toast.LENGTH_LONG).show();
        Log.i("(SERVICE) found place", placeNameAndId);
        
        /*if(event.getEventType() == "AT")
        	Toast.makeText(getApplicationContext(), "(SERVICE) Has llegado a: "+ event.getPlace().getPlaceName(), Toast.LENGTH_LONG).show();
        else
        	Toast.makeText(getApplicationContext(), "(SERVICE) Has salido de: "+ event.getPlace().getPlaceName(), Toast.LENGTH_LONG).show();*/
        
        Intent intent = new Intent(this, NotificationReceiver.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
		notification.setSmallIcon(R.drawable.ic_launcher);
	    notification.setContentTitle("Offertunity");
	    notification.setContentIntent(pIntent);
		notification.setDefaults(Notification.DEFAULT_ALL);
		notification.setAutoCancel(true);
		
		if(event.getEventType() == "AT")
			notification.setContentText("Bienvenido a "+event.getPlace().getId()+", ÁEntraste en zona de ofertas! ");
		else
			notification.setContentText("Hasta luego, haz salido de zona de ofertas");
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(1234, notification.build() );
		
	}
	
	private void permissionDisabled() {
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancelAll();
        clearPlaceEvent();
    }
		
	private void clearPlaceEvent() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(PlaceEventListenerService.this).edit();
        editor.remove(PLACE_EVENT_DESCRIPTION_KEY);
        editor.commit();
    }

}
