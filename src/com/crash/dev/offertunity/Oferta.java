package com.crash.dev.offertunity;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Oferta")
public class Oferta extends ParseObject{

	public Oferta(){
		
	}
	
	public String getTitulo(){
		return getString("titulo");
	}
	
	public void setTitulo(String titulo){
		put("titulo", titulo);
	};
	
	public String getDescripcion(){
		return getString("descripcion");
	}
	
	public void setDescripcion(String descripcion){
		put("descripcion", descripcion);
	}
	
	public ParseFile getImagen() {
        return getParseFile("imagen");
    }
 
    public void setPhotoFile(ParseFile imagen) {
        put("imagen", imagen);
    }
}



	

