package us.coreis.smartedu.startedu;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ScrollView;
import android.widget.TextView;

public class ProfileFragment extends android.support.v4.app.Fragment {
    TextInputLayout name_wrapper;
    String fieldName[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.layout_profile, null);
        ScrollView SV = (ScrollView) view.findViewById(R.id.profileSV);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(width, height);
        SV.setLayoutParams(params);

        TextView emailTV = (TextView) view.findViewById(R.id.email_profile);
        emailTV.setText(MainActivity.email);

        fieldName = getResources().getStringArray(R.array.field_name);

        name_wrapper = (TextInputLayout) view.findViewById(R.id.wrapper_name_profile);
        name_wrapper.getEditText().setText(MainActivity.name);
        name_wrapper.getEditText().setFocusable(false);
        name_wrapper.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_wrapper.getEditText().setFocusableInTouchMode(true);
                name_wrapper.getEditText().setFocusable(true);
            }
        });
        name_wrapper.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    name_wrapper.getEditText().setFocusable(false);
                        MainActivity.name = name_wrapper.getEditText().getText().toString();
                        MainActivity.navigationView.removeHeaderView(MainActivity.navHeader);
                        MainActivity.nav_header_name.setText(MainActivity.name);
                        if (MainActivity.name.length() > 0)
                            MainActivity.nav_header_name.setVisibility(View.VISIBLE);
                        else MainActivity.nav_header_name.setVisibility(View.GONE);
                        MainActivity.navigationView.addHeaderView(MainActivity.navHeader);
                        hideKeyboard();
                    return true;
                }
                return false;
            }
        });

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.profileRV);
        final ProfileFieldAdapter adapter = new ProfileFieldAdapter(getContext(), fieldName);
        rv.hasFixedSize();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        return view;
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
