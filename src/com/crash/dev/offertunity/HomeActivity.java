package com.crash.dev.offertunity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;


public class HomeActivity extends SherlockFragmentActivity implements TabListener{
	
	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;
    private PlaceEventListener placeEventListener = new PlaceEventListener() {

   		@Override
        public void placeEvent(PlaceEvent event) {
            //String placeNameAndId = "id: " + event.getPlace().getId() + " name: " + event.getPlace().getPlaceName();
            //Toast.makeText(getApplicationContext(), "(Activity) " + placeNameAndId, Toast.LENGTH_LONG).show();
            //Log.i("(Activity) found place", placeNameAndId);
            
        }
    };
    
	ViewPager viewPager; 
	ActionBar actionBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		contextCoreConnector = ContextCoreConnectorFactory.get(this);
        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        checkContextConnectorStatus();
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new HomePagerAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
				//Log.d("AAA","onPageSelected at position "+position);
			}			
			@Override
			public void onPageScrolled(int position, float from, int pixels) {
				//Log.d("AAA","onPageScrolled at position "+position+" from "+from+" with numer of pixels "+pixels);				
			}
			@Override
			public void onPageScrollStateChanged(int state) {
				/*if(state == ViewPager.SCROLL_STATE_IDLE){
					Log.d("AAA","onPageScrollStateChanged Idle");
				}
				if(state == ViewPager.SCROLL_STATE_DRAGGING){
					Log.d("AAA","onPageScrollStateChanged Dragging");
				}
				if(state == ViewPager.SCROLL_STATE_SETTLING){
					Log.d("AAA","onPageScrollStateChanged Settling");
				}*/
				
			}
		});
        
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.Tab tab1 = actionBar.newTab();
        tab1.setText("Relevantes");
        tab1.setTabListener(this);
        
        ActionBar.Tab tab2 = actionBar.newTab();
        tab2.setText("Cerca de ti");
        tab2.setTabListener(this);
        
        ActionBar.Tab tab3 = actionBar.newTab();
        tab3.setText("Zonas");
        tab3.setTabListener(this);
        
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
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

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		//Log.d("QQQQ","onTabSelected at position "+tab.getPosition()+" name "+tab.getText());
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		//Log.d("QQQQ","onTabUnelected at position "+tab.getPosition()+" name "+tab.getText());
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Log.d("QQQQ","onTabReselected at position "+tab.getPosition()+" name "+tab.getText());
	}
	
	/*class HomePagerAdapter extends FragmentPagerAdapter{

		public HomePagerAdapter(FragmentManager fm) {
				super(fm);
		}
	
		@Override
		public Fragment getItem(int position) {
			
			Fragment fragment = null;
			
			if(position == 0){
				fragment = new OfertasRelevantesFragment();
			}
			if(position == 1){
				fragment = new OfertasCercanasFragment();
			}
			if(position == 2){
				fragment = new ZonasDeOfertasFragment();
			}
			return fragment;
		}
	
		@Override
		public int getCount() {
			return 3;
		}
	
	}*/
	
}
