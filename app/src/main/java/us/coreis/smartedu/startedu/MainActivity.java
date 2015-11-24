package us.coreis.smartedu.startedu;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;
    static String name = null, email = null, company = null, website = null, number = null;
    static int age = -1;
    static View navHeader;
    static TextView nav_header_name;
    static NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        nav_header_name = (TextView) navHeader.findViewById(R.id.nav_header_name);
        TextView nav_header_email = (TextView) navHeader.findViewById(R.id.nav_header_email);
        if (RegisterHere.getName() != null) {
            name = RegisterHere.getName();
            email = RegisterHere.getEmail();
        } else email = Login.getEmail();
        nav_header_email.setText(email);
        nav_header_name.setText(name);
        if(name == null) nav_header_name.setVisibility(View.GONE);
        navigationView.addHeaderView(navHeader);
        navigationView.setNavigationItemSelectedListener(this);
        android.support.v4.app.Fragment feed = new startFeed();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerView, feed);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                if (RegisterHere.name == null) Login.loginActivity.finish();
                else RegisterHere.registerActivity.finish();
                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_feed:
                android.support.v4.app.Fragment feed = new startFeed();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerView, feed);
                fragmentTransaction.commit();
                break;
            case R.id.nav_inspire:
                Fragment Inspire = new InspireFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerView, Inspire);
                fragmentTransaction.commit();
                break;
            case R.id.nav_profile:
                Fragment Profile = new ProfileFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerView, Profile);
                fragmentTransaction.commit();
                break;
            case R.id.nav_about:
                Fragment aboutUs = new AboutUsFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerView, aboutUs);
                fragmentTransaction.commit();
                break;
            case R.id.nav_contact:
                Fragment contactUs = new ContactUsFragment();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerView, contactUs);
                fragmentTransaction.commit();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
