package us.coreis.smartedu.startedu;


import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import java.util.ArrayList;

public class InspireFragment extends android.support.v4.app.Fragment {
    String name[], company[];
    TypedArray images;
    ArrayList<InspireCard> details  ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_inspire, null);
        ScrollView SV = (ScrollView) view.findViewById(R.id.inspireSV);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(width, height);
        SV.setLayoutParams(params);

        name = getResources().getStringArray(R.array.inspire_names);
        company = getResources().getStringArray(R.array.inspire_comapny);
        images = getResources().obtainTypedArray(R.array.inspire_image);
        details = new ArrayList<>();
        for( int i = 0 ; i < name.length ; ++i )details.add(new InspireCard( name[i] , company[i] ,images.getResourceId(i, -1)));
        InspireCardAdapter adapter = new InspireCardAdapter( getContext() , details );
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.inspireRV);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}
