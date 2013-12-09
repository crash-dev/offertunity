package com.crash.dev.offertunity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQueryAdapter;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class OfertasPorZonaActivity extends SherlockActivity {
	
	//ListView offersListView;
	/*String [] datos = { "Actividad Vulnerable 1",
			"Actividad Vulnerable 2",
			"Actividad Vulnerable 3",
			"Actividad Vulnerable 4",
			"Actividad Vulnerable 5",
			"Actividad Vulnerable 6", 
			"Actividad Vulnerable 7",
			"Actividad Vulnerable 8",
			"Actividad Vulnerable 9",
			"Actividad Vulnerable 10",
			"Actividad Vulnerable 11",
			"Actividad Vulnerable 12",
			"Actividad Vulnerable 13",
			"Actividad Vulnerable 14",
			"Actividad Vulnerable 15",
			"Actividad Vulnerable 16", 
			"Actividad Vulnerable 17",
			"Actividad Vulnerable 2",
			"Actividad Vulnerable 3",
			"Actividad Vulnerable 4",
			"Actividad Vulnerable 5",
			"Actividad Vulnerable 6", 
			"Actividad Vulnerable 7",
			"Actividad Vulnerable 8",
			"Actividad Vulnerable 9",
			"Actividad Vulnerable 10",
			"Actividad Vulnerable 11",
			"Actividad Vulnerable 12",
			"Actividad Vulnerable 13",
			"Actividad Vulnerable 14",
			"Actividad Vulnerable 15",
			"Actividad Vulnerable 16", 
			"Actividad Vulnerable 17",
			"Actividad Vulnerable 18"};*/

	private JazzyListView offersListView;
    private int mCurrentTransitionEffect = JazzyHelper.CARDS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ofertas_por_zona);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			String nombreDeZona = extras.getString("nombreDeZona");
			this.setTitle(nombreDeZona);
		}
		
		/*Parse.initialize(this, "fGChkFITD5vaE3N0INg5neDfjW4TPX9brSkum6G8", "iHheq8svB9iaIaOT4bJR6Zoz04drJ6kHxIcMRgk6");
		ParseHelper.getOffertasPorZona("La Condesa");*/
		
		/*offersListView = (ListView) findViewById(R.id.offersListView);
		View header = getLayoutInflater().inflate(R.layout.header, null);
		offersListView.addHeaderView(header);
		offersListView.setAdapter(new OffersListViewAdapter(getApplicationContext(), datos));*/
		
		offersListView = (JazzyListView) findViewById(R.id.offersListView);
		View header = getLayoutInflater().inflate(R.layout.header, null);
		offersListView.addHeaderView(header);
		//offersListView.setAdapter(new OffersListViewAdapter(getApplicationContext(), datos));
		
		//AdapatadorParse por Default
		/*ParseQueryAdapter<Oferta> mainAdapter = new ParseQueryAdapter<Oferta>(this, Oferta.class);
		mainAdapter.setTextKey("titulo");
		mainAdapter.setImageKey("imagen");
		offersListView.setAdapter(mainAdapter);*/
		
		offersListView.setAdapter(new OfertasListViewAdapter(this));
		offersListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(getApplicationContext(), "A webo!!!", Toast.LENGTH_SHORT).show();
			}
		});
        
        if(savedInstanceState != null){
        	mCurrentTransitionEffect = savedInstanceState.getInt("transition_effect", JazzyHelper.CARDS);
        	setupJazziness(mCurrentTransitionEffect);
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
	
	private void setupJazziness(int effect) {
        mCurrentTransitionEffect = effect;
        offersListView.setTransitionEffect(mCurrentTransitionEffect);
    }
}
