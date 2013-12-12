package com.crash.dev.offertunity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class OfertasCercanasFragment extends Fragment 
	implements
	ConnectionCallbacks,
	OnConnectionFailedListener,
	LocationListener{
	
	MapView mv;
	GoogleMap map;
	private LocationClient mLocationClient;
	View fragmentView;
	FragmentManager myFM;
	
	private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	public OfertasCercanasFragment() {
		// Required empty public constructor
		//Log.d("ENTRO", "ENTRANDO");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		//Log.d("ENTRO", "ENTRANDO");

		fragmentView = inflater.inflate(R.layout.fragment_ofertas_cercanas, container, false);
		
		/*
        myFM = getActivity().getSupportFragmentManager();

        final SupportMapFragment myMAPF = (SupportMapFragment) myFM
                .findFragmentById(R.id.map);

        map = myMAPF.getMap();*/
        
        
        
        //Log.d("ENTRO", "ENTRANDO A ONCREATE *************");
/*
        map.addMarker(new MarkerOptions()
                .position(new LatLng(19.271362956590064, -99.12188379999998))
                .title("Current Location")
                .snippet("Accuracyt to 20 meters"));*/
        /*
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4326058056025, -99.13325399999997), 11.0f));
        
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(
                    getActivity().getApplicationContext(),
                    this,  // ConnectionCallbacks
                    this); // OnConnectionFailedListener
        }
        
        Toast.makeText(getActivity().getApplicationContext(), "Loc: "+mLocationClient.getLastLocation().getLatitude()+
        		","+mLocationClient.getLastLocation().getLongitude(), Toast.LENGTH_LONG).show();*/
        

        return fragmentView;
		
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		mLocationClient.requestLocationUpdates(
                REQUEST,
                this);
		
		if (mLocationClient != null && mLocationClient.isConnected()) {
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.4326058056025, -99.13325399999997), 11.0f));
            String msg = "Location = " + mLocationClient.getLastLocation();
            Toast.makeText(getActivity().getApplicationContext(), "Loc: "+mLocationClient.getLastLocation().getLatitude()+
            		","+mLocationClient.getLastLocation().getLongitude(), Toast.LENGTH_LONG).show();
        }
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setUpMapIfNeeded();
        setUpLocationClientIfNeeded();
        mLocationClient.connect();
        
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mLocationClient != null) {
            mLocationClient.disconnect();
        }
	}
	
	private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map == null) {
            // Try to obtain the map from the SupportMapFragment.
            //map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            myFM = getActivity().getSupportFragmentManager();

            final SupportMapFragment myMAPF = (SupportMapFragment) myFM
                    .findFragmentById(R.id.map);

            map = myMAPF.getMap();
            // Check if we were successful in obtaining the map.
            if (map != null) {
                map.setMyLocationEnabled(true);
            }
        }
    }

    private void setUpLocationClientIfNeeded() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(
                    getActivity().getApplicationContext(),
                    this,  // ConnectionCallbacks
                    this); // OnConnectionFailedListener
        }
    }
    
    
	

}
