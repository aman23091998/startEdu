package us.coreis.smartedu.startedu;

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

public class RegisterHere extends AppCompatActivity {
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    public String name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register_here);
        Typeface latoFont = Typeface.createFromAsset(getAssets(), "fonts/Lato/Lato-Regular.ttf");
        TextView registerHeading = (TextView) findViewById(R.id.register_header);
        registerHeading.setTypeface(latoFont);
        final TextInputLayout emailWrapper = (TextInputLayout) findViewById(R.id.wrapper_email_register);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.wrapper_password_register);
        final TextInputLayout passwordConfirmWrapper = (TextInputLayout) findViewById(R.id.wrapper_confirm_password_register);
        final TextInputLayout nameWrapper = (TextInputLayout) findViewById(R.id.wrapper_name_register);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  hideKeyboard();
                                                  name = nameWrapper.getEditText().getText().toString();
                                                  String email = emailWrapper.getEditText().getText().toString();
                                                  String password = passwordWrapper.getEditText().getText().toString();
                                                  String confirmPassword = passwordConfirmWrapper.getEditText().getText().toString();
                                                  if (!validateEmail(email))
                                                      emailWrapper.setError("Not a valid email address!");
                                                  else emailWrapper.setErrorEnabled(false);
                                                  if (!validatePassword(password))
                                                      passwordWrapper.setError("Password should have at-least 8 characters ");
                                                  else if (!confirmPasswords(password, confirmPassword)) {
                                                      passwordWrapper.setError("Passwords don't match");
                                                      passwordConfirmWrapper.setError("Password don't match ");
                                                  } else {
                                                      passwordWrapper.setErrorEnabled(false);
                                                      passwordConfirmWrapper.setErrorEnabled(false);
                                                  }
                                                  if (name.length() == 0)
                                                      nameWrapper.setError("Field can't be left Empty ");
                                                  if (validateEmail(email) && validatePassword(password) && confirmPasswords(password, confirmPassword)) {
                                                      emailWrapper.setErrorEnabled(false);
                                                      passwordWrapper.setErrorEnabled(false);
                                                      passwordConfirmWrapper.setErrorEnabled(false);
                                                      gotoMain();
                                                  }
                                              }
                                          }
        );
    }

    public boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
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
        Toast.makeText(getApplicationContext(), "Finalising your registration . ", Toast.LENGTH_SHORT).show();
    }

    public boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    public boolean confirmPasswords(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}