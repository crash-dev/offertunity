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
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class DetallesDeOfertaActivity extends SherlockActivity {
	
	String idDeOferta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles_de_oferta);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			idDeOferta = extras.getString("idDeOferta");
		}
		
		this.setTitle("Detalles de Offerta");
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Oferta");
		query.getInBackground(idDeOferta, new GetCallback<ParseObject>() {
			
			@Override
			public void done(ParseObject oferta, ParseException e) {
				if (e != null) {
					Log.i("TORZON de Oferta", ""+e);
			    } else {
			        Oferta currentOferta = (Oferta) oferta;
			        setTitle(currentOferta.getTitulo());
			        ParseImageView imagen = (ParseImageView) findViewById(R.id.imagen);
			        ParseFile imagenDeZona = currentOferta.getParseFile("imagen");
			        
			        if (imagenDeZona != null) {
				        imagen.setParseFile(imagenDeZona);
				        imagen.loadInBackground();
				    }
			        
			        TextView titulo = (TextView) findViewById(R.id.titulo);
			        titulo.setText(currentOferta.getTitulo());
			        TextView descripcion = (TextView) findViewById(R.id.descripcion);
			        descripcion.setText(currentOferta.getDescripcion());
			    }
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.detalles_de_oferta, menu);
		return true;
	}
	
}
