package com.crash.dev.offertunity;

import java.util.ArrayList;
import java.util.List;

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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
			
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude()), 16.0f));
            String msg = "Location = " + mLocationClient.getLastLocation();
           // Toast.makeText(getActivity().getApplicationContext(), "Loc: "+mLocationClient.getLastLocation().getLatitude()+
            //		","+mLocationClient.getLastLocation().getLongitude(), Toast.LENGTH_LONG).show();
            // ***** con toast, se puede saber momento en q ejecuta consulta
            
            //hace consulta marcadores
            ParseGeoPoint userLocation = new ParseGeoPoint(mLocationClient.getLastLocation().getLatitude(), mLocationClient.getLastLocation().getLongitude()); //ubicacion actual del usuario
    		ParseQuery<ParseObject> query = ParseQuery.getQuery("Establecimiento"); //inicializa query con el nombre de la clase a consultar en parse
    		query.whereNear("ubicacion", userLocation); //query para encontrar los puntos mas cercanos a la localizacion actual
    		query.setLimit(10); //con un maximo de 10
    		query.orderByAscending("ubicacion"); 
    		
    		query.findInBackground(new FindCallback<ParseObject>() {
    			
    			@Override
    			public void done(List<ParseObject> list, ParseException error) {
    				// TODO Auto-generated method stub
    				String res="";
    				MarkerOptions mo = new MarkerOptions();
    				Drawable img = null;
    				byte[] data;
    				//Log.d("ParseGEO", "ENTRO");
    				if(error==null){
    					for(int i=0;i<list.size();i++){
    						//res = res+"--"+list.get(i).getString("PFFF");
    						Log.d("ParseGEO", "**"+list.get(i).getString("nombre")+" loc: "+((ParseGeoPoint)list.get(i).get("ubicacion")).getLatitude()+".."+list.get(i).get("img"));
    						mo.position(new LatLng(((ParseGeoPoint)list.get(i).get("ubicacion")).getLatitude(), ((ParseGeoPoint)list.get(i).get("ubicacion")).getLongitude()));
    						mo.title(list.get(i).getString("nombre"));
    						//obtiene imagen
    						
    						  try {
    						   data = ((ParseFile)list.get(i).get("img")).getData();
    						   Bitmap   bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
    						   mo.icon(BitmapDescriptorFactory.fromBitmap(bmp));

    						  } catch (ParseException e) {
    						   // TODO Auto-generated catch block
    						   //e.printStackTrace();
    							  Log.d("PFFF", "No agrego imagen");
    						  }
    						
    						
    						map.addMarker(mo);
    					}
    				}
    				else{
    					Log.d("ParseGEO", "No hubo resultado");
    				}
    				
    				//tv.setText(""+res);
    			}
    		});
            
            
            
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
