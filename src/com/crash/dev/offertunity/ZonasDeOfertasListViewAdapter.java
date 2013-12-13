package com.crash.dev.offertunity;

import android.content.Context;
import android.graphics.BitmapFactory;
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
import com.parse.ParseQuery.CachePolicy;
import com.parse.ParseQueryAdapter;

public class ZonasDeOfertasListViewAdapter extends ParseQueryAdapter<ZonaDeOfertas>{

	public ZonasDeOfertasListViewAdapter(Context context){
		
		/*Aqui podemos modificar el query que solicitamos a Parse*/
		super(context, new ParseQueryAdapter.QueryFactory<ZonaDeOfertas>() {
            public ParseQuery<ZonaDeOfertas> create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
                ParseQuery query = new ParseQuery("ZonaDeOfertas");
                query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
                query.whereExists("nombre");  
                //query.whereContainedIn("rating", Arrays.asList("5", "4"));
                //query.orderByDescending("rating");
                return query;
            }
        });
	}
	
	@Override
	public View getItemView(ZonaDeOfertas zona, View v, ViewGroup parent) {
		
		if (v == null) {
	        v = View.inflate(getContext(), R.layout.zona_listview_item, null);
	    }
		
		super.getItemView(zona, v, parent);
		
		ParseImageView imagen = (ParseImageView) v.findViewById(R.id.imagen);
		ParseFile imagenDeZona = zona.getImagen();
		
		/*Codigo para generar muestras de la imagenes y no agotar memoria*/
		
		/*BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		try {
			BitmapFactory.decodeByteArray(imagenDeZona.getData(), 0, imagenDeZona.getData().length, options);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		
		
		
		if (imagenDeZona != null) {
	        imagen.setParseFile(imagenDeZona);
	        imagen.loadInBackground();
	    }
		
		TextView titulo = (TextView) v.findViewById(R.id.titulo);
		titulo.setText(zona.getNombre());
		v.setTag(zona.getObjectId());
		
		return v;
	}

}
