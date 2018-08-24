package com.example.rahmanm2.firebaseui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.rahmanm2.firebaseui.DataModel.navDataModel;

import java.io.Serializable;

public class MenuActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    private MenuDataModel mMenuDataModel;
    public Bitmap bitmap;
    Button btnshowImage;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SetupToolbar();
        SetUpDrawer();
        setupRecyclerView();

    }
    private void SetupToolbar(){
        mToolbar = (Toolbar)findViewById(R.id.menuToolbar);
        mToolbar.setTitle("Menu Items");
        //mToolbar.addView(btnshowImage);
        setSupportActionBar(mToolbar);
    }

  /*  private void setUpButton(){
        btnshowImage = new Button(this);
        int image = 2;
        btnshowImage.setId(image);
        btnshowImage.setTag("Show Iamge");
        btnshowImage.setText("show Image");
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.rouder_menu,menu);
        return true;
    }
    public void SetUpDrawer(){
        NavigationDrawerFragment fragment = (NavigationDrawerFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_drawewr_fragment);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        fragment.setUpDrawer(R.id.nav_drawewr_fragment,drawerLayout,mToolbar);
    }
    private void setupRecyclerView(){
        mRecyclerView = findViewById(R.id.MenuRecyclerID);
        MenuModelAdapter adapter = new MenuModelAdapter(this,MenuDataModel.getAllData());
        mRecyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.takePicID:
                dispatchTakePictureIntent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
           // mImageView.setImageBitmap(imageBitmap);
            //bitmap = imageBitmap;
            Log.d("bitmap",imageBitmap.toString());
        }
    }
   /* public void onClick(View view){
        if(view.getId()==R.id.image){
            showimage();
        }
    }
    private void showimage() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        mImageView = new ImageView(this);
        mImageView.setMaxWidth(200);
        mImageView.setMaxHeight(200);
        mImageView.setImageBitmap(bitmap);
        linearLayout.addView(mImageView);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("showimage");
        builder.setView(linearLayout);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
    }*/
}
