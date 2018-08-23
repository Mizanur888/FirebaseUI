package com.example.rahmanm2.firebaseui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SetupToolbar();
        setupRecyclerView();
    }
    private void SetupToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.menuToolbar);
        mToolbar.setTitle("Menu Items");
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rouder_menu,menu);
        return true;
    }

    private void setupRecyclerView(){
        mRecyclerView = findViewById(R.id.MenuRecyclerID);
        MenuModelAdapter adapter = new MenuModelAdapter(this,MenuDataModel.getAllData());
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }
}
