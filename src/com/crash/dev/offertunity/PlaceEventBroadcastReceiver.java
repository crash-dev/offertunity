package com.crash.dev.offertunity;

import com.qualcommlabs.usercontext.Callback;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PlaceEventBroadcastReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context, PlaceEventListenerService.class);
		context.startService(service);
	}

}
