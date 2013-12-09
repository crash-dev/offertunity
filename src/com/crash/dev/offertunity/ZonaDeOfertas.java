package com.crash.dev.offertunity;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("ZonaDeOfertas")
public class ZonaDeOfertas extends ParseObject{
	
	public ZonaDeOfertas(){
		
	}
	
	public String getNombre(){
		return getString("nombre");
	}
	
	public void setNombre(String nombre){
		put("nombre", nombre);
	}
	
	public ParseFile getImagen() {
        return getParseFile("imagen");
    }
 
    public void setPhotoFile(ParseFile imagen) {
        put("imagen", imagen);
    }

}
