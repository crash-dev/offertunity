package com.crash.dev.offertunity;

import com.twotoasters.jazzylistview.JazzyHelper;
import com.twotoasters.jazzylistview.JazzyListView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class ZonasDeOfertasFragment extends Fragment {
	
	private JazzyListView zonasListView;
    private int mCurrentTransitionEffect = JazzyHelper.CARDS;

	public ZonasDeOfertasFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_zonas_de_ofertas, container, false);
		zonasListView =  (JazzyListView) view.findViewById(R.id.zonasListView);
		zonasListView.setAdapter(new ZonasDeOfertasListViewAdapter(getActivity()));
		zonasListView.setOnItemClickListener(new OnItemClickListener() {
			
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getActivity(), OfertasPorZonaActivity.class);
				startActivity(intent);
				
			}
		});
		
		if(savedInstanceState != null){
        	mCurrentTransitionEffect = savedInstanceState.getInt("transition_effect", JazzyHelper.CARDS);
        	setupJazziness(mCurrentTransitionEffect);
        }
		
		//return inflater.inflate(R.layout.fragment_zonas_de_ofertas, container, false);
		return view;
	}
	
	private void setupJazziness(int effect) {
        mCurrentTransitionEffect = effect;
        zonasListView.setTransitionEffect(mCurrentTransitionEffect);
    }

}
