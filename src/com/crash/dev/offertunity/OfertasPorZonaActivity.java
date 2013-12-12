package com.crash.dev.offertunity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQuery.CachePolicy;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class OfertasPorZonaActivity extends SherlockActivity {

	private JazzyListView offersListView;
    //private int mCurrentTransitionEffect = JazzyHelper.CARDS;
    String idDeZona;
    //ZonaDeOfertas currentZona;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ofertas_por_zona);
		View header = getLayoutInflater().inflate(R.layout.parse_imageview_header, null);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			idDeZona = extras.getString("idDeZona");
			Log.e("idDeZona", idDeZona);
			//this.setTitle(idDeZona);
			//getZonaDeOfertasById(idDeZona);
		}
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("ZonaDeOfertas");
		query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
		query.getInBackground(idDeZona, new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject zona, ParseException e) {
				if (e != null) {
					Log.i("Error de Zona", ""+e);
			    } else {
			        ZonaDeOfertas currentZona = (ZonaDeOfertas) zona;
			        setTitle(currentZona.getNombre());
			        ParseImageView imagen = (ParseImageView) findViewById(R.id.headerImageView);
			        ParseFile imagenDeZona = currentZona.getParseFile("imagen");
			        
			        if (imagenDeZona != null) {
				        imagen.setParseFile(imagenDeZona);
				        imagen.loadInBackground();
				    }

			    }
			}
		});
		
		offersListView = (JazzyListView) findViewById(R.id.offersListView);
		offersListView.addHeaderView(header);
		offersListView.setAdapter(new OfertasListViewAdapter(this));
		//offersListView.setAdapter(new OfertasListViewAdapter(this, nombreDeZona));
		offersListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//Toast.makeText(getApplicationContext(), "Id: "+view.getTag(), Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(), DetallesDeOfertaActivity.class);
				intent.putExtra("idDeOferta", ""+view.getTag());
				startActivity(intent);
			}
		});
        
        if(savedInstanceState != null){
        	//mCurrentTransitionEffect = savedInstanceState.getInt("transition_effect", JazzyHelper.CARDS);
        	//setupJazziness(mCurrentTransitionEffect);
        }
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.ofertas_por_zona, menu);
		return true;
	}
	
	/*private void setupJazziness(int effect) {
        mCurrentTransitionEffect = effect;
        offersListView.setTransitionEffect(mCurrentTransitionEffect);
    }*/
	
}
