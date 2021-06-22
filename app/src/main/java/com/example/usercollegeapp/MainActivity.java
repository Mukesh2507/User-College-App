package com.example.usercollegeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.DownloadManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.usercollegeapp.authentication.LoginActivity;
import com.example.usercollegeapp.ebook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    private int  checkItem;
    private  String selected;
    private FirebaseAuth auth;

    private  final  String CHECKEDITEM = "checked_item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("notification");
        auth =FirebaseAuth.getInstance();
        sharedPreferences = this.getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getCheckItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;

            case 1 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
        }
        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this,R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.start,R.string.close);

                drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return  true;
        }
        if (item.getItemId()==R.id.logout){
            auth.signOut();
            openLogin();
        }
        return true;
    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() == null){
            openLogin();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_video:
                Toast.makeText(this, "videos", Toast.LENGTH_SHORT).show();
              break;
            case R.id.navigation_ebook:
                 startActivity(new Intent(this, EbookActivity.class));
                break;

            case R.id.navigation_website:
                 try {


                     String q = "https://www.silveroakuni.ac.in/";
                     Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
                     i.putExtra(SearchManager.QUERY, q);
                     startActivity(i);
                 }catch (Exception e){
                     Toast.makeText(this, "Unable to open", Toast.LENGTH_SHORT).show();
                 }
                break;
            case R.id.navigation_share:
                  try {
                      Intent i = new Intent(Intent.ACTION_SEND);
                      i.setType("text/plain");
                      i.putExtra(Intent.EXTRA_SUBJECT, "User College App");
                      i.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/stor/apps/details?id=" + getApplicationContext().getPackageName());
                      startActivity(Intent.createChooser(i, "share with"));
                  } catch (Exception e){
                      Toast.makeText(this, "Unabale to share this app", Toast.LENGTH_SHORT).show();
                  }
                break;
            case R.id.navigation_rate:
                Uri  uri = Uri.parse("https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName());
                Intent  i = new Intent(Intent.ACTION_VIEW,uri);
                try {
                    startActivity(i);
                } catch (Exception e) {
                    Toast.makeText(this, "unable to open\n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.navigation_developer:
                startActivity(new Intent(this,DeveloperActivity.class));
                break;
            case R.id.navigation_color :
                showDialog();


        }
        return true;

    }
    private  int getCheckItem(){
        return  sharedPreferences.getInt(CHECKEDITEM,0);
    }
    private  void setCheckItem(int i){
        editor.putInt(CHECKEDITEM,i);
        editor.apply();
    }

    private void showDialog() {
        String[] themes = this.getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("select theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                selected = themes[i];
                checkItem = i;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (selected==null){
                    selected = themes[i];
                    checkItem = i;
                }
                switch (selected){
                    case "System Default":
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;

                    case "Dark" :
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light" :
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;
                }
                setCheckItem(checkItem);
            }
        });
        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}