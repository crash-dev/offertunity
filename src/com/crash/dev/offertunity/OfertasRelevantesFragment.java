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
    //private int mCurrentTransitionEffect = JazzyHelper.CARDS;

	public OfertasRelevantesFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		View view = inflater.inflate(R.layout.fragment_ofertas_relevantes, container, false);
		ofertasRelevantesListView = (JazzyListView) view.findViewById(R.id.ofertasRelevantesListView);
		View header = getActivity().getLayoutInflater().inflate(R.layout.header, null);
		ofertasRelevantesListView.addHeaderView(header);
		ofertasRelevantesListView.setAdapter(new OfertasListViewAdapter(getActivity()));
		
		if(savedInstanceState != null){
        	//mCurrentTransitionEffect = savedInstanceState.getInt("transition_effect", JazzyHelper.CARDS);
        	//setupJazziness(mCurrentTransitionEffect);
        }
		
		return view;
	}
	
}
