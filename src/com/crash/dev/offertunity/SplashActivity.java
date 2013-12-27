package com.crash.dev.offertunity;



import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		
		LayoutParams lp = new LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
		lp.setMargins(0, display.getHeight(), 0, 0);
		
		
		//se obtiene textview para poner posicion relativa respecto a top
		TextView tv = (TextView) findViewById(R.id.textView1);
		LayoutParams tvlp = new LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
		tvlp.setMargins(0, display.getHeight()/2, 0, 0); //distancia respecto a top, es la mitad de la pantalla
		tvlp.addRule(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
		
		tv.setLayoutParams(tvlp);
		
		ImageView	iv = (ImageView) findViewById(R.id.logotipo);
        iv.setLayoutParams(lp);
		
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -((display.getHeight()/2)+400));
        ta.setDuration(2000);
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(3000);
     
        
        AnimationSet animSet = new AnimationSet(true);
        animSet.addAnimation(ta);
        animSet.addAnimation(fadeIn);
        animSet.setFillAfter(true);
        
        iv.setAnimation(animSet);
		tv.setAnimation(fadeIn);
		
        final Handler handler;
    	Timer tiempoDelay;
    	handler = new Handler(); 
        tiempoDelay = new Timer();
        tiempoDelay.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Intent i = new Intent(getApplicationContext(), HomeActivity.class);
						startActivity(i);
						finish();
					}
				});
			}
		},5000);
		
		
		
	}

	

}
