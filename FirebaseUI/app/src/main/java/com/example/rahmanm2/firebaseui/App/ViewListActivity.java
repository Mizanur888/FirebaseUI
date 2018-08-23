package com.example.rahmanm2.firebaseui.App;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahmanm2.firebaseui.Adapter.dealViewAdapter;
import com.example.rahmanm2.firebaseui.DataModel.DealModel;
import com.example.rahmanm2.firebaseui.FirebaseUtil.FirebaseUi;
import com.example.rahmanm2.firebaseui.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewListActivity extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener mChildEventListener;
    TextView textView;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        setUpToolbar();
    }
    @Override
    protected void onPause() {
        super.onPause();
        FirebaseUi.DetechListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FirebaseUi.openFirebase("FirebaseUI",this);
        mFirebaseDatabase = FirebaseUi.mFirebaseDatabase;
        mDatabaseReference = FirebaseUi.mDatabaseReference;
        RecyclerView view = (RecyclerView)findViewById(R.id.recyclerViewID);
        dealViewAdapter adapter = new dealViewAdapter(this);
        view.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(manager);
        FirebaseUi.AttachListener();
    }

    private void setUpToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.intoolbarID);
        mToolbar.setTitle("Home Page");
        setSupportActionBar(mToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu,menu);

        MenuItem insertMenu = menu.findItem(R.id.addItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signOut:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                                Toast.makeText(ViewListActivity.this,"Log out Successful",Toast.LENGTH_LONG).show();
                                FirebaseUi.AttachListener();
                            }
                        });
                 return true;
            case R.id.RemoveUser:
                AuthUI.getInstance()
                        .delete(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // ...
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
