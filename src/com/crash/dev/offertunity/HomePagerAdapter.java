package com.crash.dev.offertunity;

import com.google.android.gms.maps.SupportMapFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HomePagerAdapter extends FragmentPagerAdapter{

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
			//fragment = SupportMapFragment.newInstance();
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

	
}
