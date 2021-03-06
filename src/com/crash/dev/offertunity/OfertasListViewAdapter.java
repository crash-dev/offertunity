package com.crash.dev.offertunity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseQuery.CachePolicy;

public class OfertasListViewAdapter extends ParseQueryAdapter<Oferta>{

	public OfertasListViewAdapter(Context context){
		
		/*Aqui podemos modificar el query que solicitamos a Parse*/
		super(context, new ParseQueryAdapter.QueryFactory<Oferta>() {
            public ParseQuery<Oferta> create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
                ParseQuery query = new ParseQuery("Oferta");
                query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
                query.whereEqualTo("zona", "La Condesa");  
                //query.whereContainedIn("rating", Arrays.asList("5", "4"));
                //query.orderByDescending("rating");
                return query;
            }
        });
	}
	
	public OfertasListViewAdapter(Context context, final String nombreDeZona){
		
		super(context, new ParseQueryAdapter.QueryFactory<Oferta>() {
            public ParseQuery<Oferta> create() {
                ParseQuery query = new ParseQuery("Oferta");
                query.whereEqualTo("zona", nombreDeZona);  
                return query;
            }
        });
	}

	@Override
	public View getItemView(Oferta oferta, View v, ViewGroup parent) {
		
		if (v == null) {
	        v = View.inflate(getContext(), R.layout.oferta_listview_item, null);
	    }
		
		super.getItemView(oferta, v, parent);
		
		ParseImageView imagen = (ParseImageView) v.findViewById(R.id.imagen);
		ParseFile imagenDeOferta = oferta.getParseFile("imagen");
		
		if (imagenDeOferta != null) {
	        imagen.setParseFile(imagenDeOferta);
	        imagen.loadInBackground();
	    }
		
		//ParseImageView imagen = (ParseImageView) v.findViewById(R.id.imagen);
		//imagen.setBackgroundResource(R.drawable.ic_launcher);
		
		TextView titulo = (TextView) v.findViewById(R.id.titulo);
		titulo.setText(oferta.getTitulo());
		TextView descripcion = (TextView) v.findViewById(R.id.donde);
		descripcion.setText(oferta.getDescripcion());
		v.setTag(oferta.getObjectId());
		
		return v;
	}
	
}
