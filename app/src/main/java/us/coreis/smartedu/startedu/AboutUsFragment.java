package us.coreis.smartedu.startedu;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class AboutUsFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_about_us, null);
        ScrollView SV = (ScrollView) view.findViewById(R.id.aboutSV);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        SV.setLayoutParams(params);
        return view;
    }
}
