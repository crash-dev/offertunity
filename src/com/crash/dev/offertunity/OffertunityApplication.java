package com.crash.dev.offertunity;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;
import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OffertunityApplication extends Application{
	
	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		setupParse();
	}
	
	public void setupParse(){ 
		
		ParseObject.registerSubclass(Oferta.class);
		ParseObject.registerSubclass(ZonaDeOfertas.class);
		Parse.initialize(this, "fGChkFITD5vaE3N0INg5neDfjW4TPX9brSkum6G8", "iHheq8svB9iaIaOT4bJR6Zoz04drJ6kHxIcMRgk6");
	
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
		
		PushService.setDefaultPushCallback(this, HomeActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
		
		
	}
	
}
