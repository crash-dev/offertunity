package com.crash.dev.offertunity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PlaceEventBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent gimbalServiceIntent = new Intent(context, PlaceEventListenerService.class);
		String packageName = context.getPackageName();
		gimbalServiceIntent.setAction(packageName + ".PLACE_EVENT_LISTENER_SERVICE"); 
		
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            context.startService(gimbalServiceIntent);
        }
        else if (Intent.ACTION_SHUTDOWN.equals(intent.getAction())) {
            context.stopService(gimbalServiceIntent);
        }
		
	}

}
