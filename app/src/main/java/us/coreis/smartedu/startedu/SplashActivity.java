package us.coreis.smartedu.startedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

public class SplashActivity extends Activity{
    private final int DISPLAY_SECONDS = 2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this , Login.class));
                finish();
            }
        } ,DISPLAY_SECONDS*1000 );
    }
}
