package com.crash.dev.offertunity;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class OfertasRelevantesFragment extends Fragment {
	
	private JazzyListView ofertasRelevantesListView;
    private int mCurrentTransitionEffect = JazzyHelper.CARDS;

	public OfertasRelevantesFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_ofertas_relevantes, container, false);
		ofertasRelevantesListView = (JazzyListView) view.findViewById(R.id.ofertasRelevantesListView);
		View header = getActivity().getLayoutInflater().inflate(R.layout.parse_imageview_header, null);
		ofertasRelevantesListView.addHeaderView(header);
		ofertasRelevantesListView.setAdapter(new OfertasListViewAdapter(getActivity()));
		
		if(savedInstanceState != null){
        	mCurrentTransitionEffect = savedInstanceState.getInt("transition_effect", JazzyHelper.CARDS);
        	setupJazziness(mCurrentTransitionEffect);
        }
		
		return view;
	}
	
	private void setupJazziness(int effect) {
        mCurrentTransitionEffect = effect;
        ofertasRelevantesListView.setTransitionEffect(mCurrentTransitionEffect);
    }

	/*private View getHeaderView(){
		
		ParseImageView imagen = (ParseImageView) header.findViewById(R.id.headerImageView);
		ParseQuery query = new ParseQuery("ZonaDefertas");
        query.whereEqualTo("nombre", "Madero");  
        query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> zona, ParseException e) {
				if(e == null){
					ParseFile imagenHeader = zona.get(0).getParseFile("imagen");
					
					if (imagenHeader != null) {
				        imagen.setParseFile(imagenHeader);
				        imagen.loadInBackground();
				    }
				}
			}
		});
				
		View header = View.inflate(getActivity(), R.layout.parse_imageview_header, null);
		ParseImageView imagen = (ParseImageView) header.findViewById(R.id.headerImageView);
		//ParseFile imagenHeader = zona.getParseFile("imagen");
		
		if (imagenHeader != null) {
	        imagen.setParseFile(imagenHeader);
	        imagen.loadInBackground();
	    }
		
		return header;
	}*/
}
