package com.crash.dev.offertunity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class ZonasDeOfertasListViewAdapter extends ParseQueryAdapter<ZonaDeOfertas>{

	public ZonasDeOfertasListViewAdapter(Context context){
		
		/*Aqui podemos modificar el query que solicitamos a Parse*/
		super(context, new ParseQueryAdapter.QueryFactory<ZonaDeOfertas>() {
            public ParseQuery<ZonaDeOfertas> create() {
                // Here we can configure a ParseQuery to display
                // only top-rated meals.
                ParseQuery query = new ParseQuery("ZonaDeOfertas");
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
		
		if (imagenDeZona != null) {
	        imagen.setParseFile(imagenDeZona);
	        imagen.loadInBackground();
	    }
		
		TextView titulo = (TextView) v.findViewById(R.id.titulo);
		titulo.setText(zona.getNombre());
		
		return v;
	}

}
