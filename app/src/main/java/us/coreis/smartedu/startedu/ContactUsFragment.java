package us.coreis.smartedu.startedu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aman2 on 24-11-2015.
 */
public class ContactUsFragment extends android.support.v4.app.Fragment {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_contact, null);
        final TextInputLayout emailWrapper = (TextInputLayout) view.findViewById(R.id.wrapper_email_contactUs);
        final TextInputLayout msgWrapper = (TextInputLayout) view.findViewById(R.id.wrapper_msg_contactUs);
        ScrollView SV  = (ScrollView) view.findViewById(R.id.contactSV);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        int height = size.y;
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(width , height);
        SV.setLayoutParams(params);
        Button submit = (Button) view.findViewById(R.id.submitButtonContactUS);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                String email = emailWrapper.getEditText().getText().toString();
                if (!validateEmail(email))
                    emailWrapper.setError("Not a valid email address!");
                else {
                    emailWrapper.setErrorEnabled(false);
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                    dialogBuilder.setTitle(" Message Received ");
                    dialogBuilder.setMessage(" We will try to contact you as soon as possible. ");
                    dialogBuilder.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            msgWrapper.getEditText().setText("");
                            dialog.cancel();
                        }
                    });
                    dialogBuilder.create().show();
                }
            }
        });

        return view;
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
