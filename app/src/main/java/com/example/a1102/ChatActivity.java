package com.example.a1102;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by 황윤후 on 2017-11-02.
 */

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{
    Button list_btn, home_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chat);
        list_btn = (Button)findViewById(R.id.list_menu_btn);
        home_btn = (Button)findViewById(R.id.home_btn);
        list_btn.setOnClickListener(this);
        home_btn.setOnClickListener(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;
                switch (item.getItemId()){
                    case R.id.action_search:
                        intent = new Intent(getApplicationContext(),SearchActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;

                    case R.id.action_chat:
                        intent = new Intent(getApplicationContext(),ChatActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;

                    case R.id.action_keyword:
                        intent = new Intent(getApplicationContext(),AlertActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;

                    case R.id.action_write:
                        intent = new Intent(getApplicationContext(),WriteActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                    case R.id.action_myinfo:
                        intent = new Intent(getApplicationContext(),MyInfo.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.list_menu_btn:
                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.left_Drawer);
                NavigationView drawerView = (NavigationView) findViewById(R.id.drawer_view);
                drawerLayout.openDrawer(drawerView);
                break;
            case R.id.home_btn:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
                finish();
                break;
        }
    }
}
