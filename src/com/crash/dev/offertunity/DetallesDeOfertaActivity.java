package com.crash.dev.offertunity;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.view.View;


public class DetallesDeOfertaActivity extends SherlockActivity {
	
	String idDeOferta;
	Oferta oferta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles_de_oferta);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			idDeOferta = extras.getString("idDeOferta");
		}
		
		this.setTitle("Detalles de Offerta");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.detalles_de_oferta, menu);
		return true;
	}
	
	/*public void getHeaderImage(String idDeOferta){
	
		final ParseImageView imagen = (ParseImageView) findViewById(R.id.imagen);
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Oferta");
		query.getInBackground(idDeOferta, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject oferta, ParseException e) {
				// TODO Auto-generated method stub
				ParseFile imagenDeOferta = oferta.getParseFile("imagen");

				if (imagenDeOferta != null) {
			        imagen.setParseFile(imagenDeOferta);
			        imagen.loadInBackground();
			    }
			}
		});
		
	}*/
	
	/*public Oferta getOferta(String idDeOferta) {
		
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Oferta");
		query.getInBackground(idDeOferta, new GetCallback<ParseObject>() {
			@Override
			public void done(ParseObject ofertaResult, ParseException e) {
				
			}
		});
		
		return oferta;
	}*/

}
