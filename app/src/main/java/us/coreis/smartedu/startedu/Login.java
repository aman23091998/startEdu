package us.coreis.smartedu.startedu;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;
    static String email = null ;
    public static Activity loginActivity;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        loginActivity = this ;
        Typeface latoFont = Typeface.createFromAsset(getAssets(), "fonts/Lato/Lato-Regular.ttf");
        TextView loginHeading = (TextView) findViewById(R.id.login_header);
        loginHeading.setTypeface(latoFont);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.wrapper_email);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.wrapper_password);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               hideKeyboard();
                                               email = emailWrapper.getEditText().getText().toString();
                                               String password = passwordWrapper.getEditText().getText().toString();
                                               if (!validateEmail(email))
                                                   emailWrapper.setError("Not a valid email address!");
                                               else emailWrapper.setErrorEnabled(false);
                                               if (!validatePassword(password))
                                                   passwordWrapper.setError("Password should have at-least 8 characters ");
                                               else passwordWrapper.setErrorEnabled(false);
                                               if (validateEmail(email) && validatePassword(password)) {
                                                   emailWrapper.setErrorEnabled(false);
                                                   passwordWrapper.setErrorEnabled(false);
                                                   gotoMain();
                                               }
                                           }
                                       }

        );

        TextView registerText = (TextView) findViewById(R.id.register_here);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(Login.this, RegisterHere.class);
                Login.this.startActivity(register);
            }
        });
    }


    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void gotoMain() {
        Toast.makeText(getApplicationContext(), "Logging In . ", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    public static String getEmail(){
        return email;
    }
    public boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}
