package com.example.litpromreader;


import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.litpromreader.fragments.BlankFragment;
import com.example.litpromreader.fragments.CreotiveFragment;
import com.example.litpromreader.fragments.LoadingFragment;
import com.example.litpromreader.fragments.NewTextFragment;
import com.example.litpromreader.fragments.NewTextFragment2;
import com.example.litpromreader.fragments.PulseFragment;
import com.example.litpromreader.fragments.SectionFragment;
import com.example.litpromreader.fragments.SettingsFragment;
import com.example.litpromreader.fragments.StartFragment;
import com.example.litpromreader.model.LitpomPresText;
import com.example.litpromreader.model.StartPage;


public class MainActivity extends AppCompatActivity  {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;

    private Boolean exit = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        NavigationView mNavigationView =(NavigationView)findViewById(R.id.mNavigation);

        mToolBar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolBar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(mNavigationView);

        //тут пробуем сразу создать фрагмент
        Intent intent = getIntent();


        String creoUrl = intent.getStringExtra("creoUrl");
        String sectionUrl = intent.getStringExtra("sectionUrl");


        if(creoUrl!=null && creoUrl.trim().length()>0){

            Bundle bundle = new Bundle();
            bundle.putString("creoUrl", creoUrl);

            CreotiveFragment creotiveFragment = new CreotiveFragment();
            creotiveFragment.setArguments(bundle);
//
//            BlankFragment blankFragment = new BlankFragment();
           // setFragmentInMainContent(new LoadingFragment());
            setFragmentInMainContent(creotiveFragment);

        }else if(sectionUrl !=null && sectionUrl.length()>0){
            Bundle bundle = new Bundle();
            bundle.putString("sectionUrl", sectionUrl);
            NewTextFragment2 newTextFragment2 = new NewTextFragment2();
            newTextFragment2.setArguments(bundle);
            setFragmentInMainContent(newTextFragment2);

        }else{
            StartFragment startFragment = new StartFragment();
            setFragmentInMainContent(startFragment);

        }





    }

    private void setFragmentInMainContent(Fragment Fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.mainContent, Fragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if(mToggle.onOptionsItemSelected(item)){
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer (MenuItem menuItem){
        Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.nav_pulse:
                fragmentClass = PulseFragment.class;
                break;
            case R.id.nav_new_texts:
                fragmentClass = NewTextFragment.class;
                break;
            case R.id.nav_sections:
                fragmentClass = SectionFragment.class;
                break;
//            case R.id.nav_important:
//                fragmentClass = LoadingFragment.class;
//                break;
            case R.id.nav_start_page:
                fragmentClass = StartFragment.class;
                break;
            case R.id.nav_settings:
                fragmentClass = SettingsFragment.class;
                break;
            default:
                fragmentClass = StartFragment.class;
        }
        try {
            myFragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e){
            e.printStackTrace();

        }
        setFragmentInMainContent(myFragment);
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();


    }
    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }
}
