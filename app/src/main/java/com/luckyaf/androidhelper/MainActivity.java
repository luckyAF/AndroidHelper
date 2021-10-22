package com.luckyaf.androidhelper;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luckyaf.androidhelper.databinding.ActivityMainBinding;
import com.luckyaf.smartandroid.utils.ScreenAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration mConfiguration = this.getResources().getConfiguration();
        updateScreenAdapter(mConfiguration);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }



    private void updateScreenAdapter(Configuration config) {
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ScreenAdapter.match(this, 866f);
        } else {
            ScreenAdapter.match(this, 480f);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Configuration mConfiguration = this.getResources().getConfiguration();
        int oldScreenWidth = mConfiguration.screenWidthDp;
        if(newConfig.screenWidthDp != oldScreenWidth){
            updateScreenAdapter(newConfig);
        }
        super.onConfigurationChanged(newConfig);
    }


}