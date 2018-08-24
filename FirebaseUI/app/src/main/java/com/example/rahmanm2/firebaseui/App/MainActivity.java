package com.example.rahmanm2.firebaseui.App;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rahmanm2.firebaseui.DataModel.DealModel;
import com.example.rahmanm2.firebaseui.FirebaseUtil.FirebaseUi;
import com.example.rahmanm2.firebaseui.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE =42;
    Toolbar mToolbar;
    private static String CONNECTION_URI = "FirebaseUI";
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    EditText txtTitle, txtDesc, txtPrice;
    Button uploadButton;
    ImageView uploadImage;
    public String imageUrlData = "";
    private ArrayList<DealModel>dealList = new ArrayList<>();
    private DealModel model = new DealModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        FirebaseUi.openFirebase(CONNECTION_URI,this);

        mFirebaseDatabase = FirebaseUi.mFirebaseDatabase;
        mDatabaseReference = FirebaseUi.mDatabaseReference;
        setupEditText();

        final Intent intent = getIntent();
        DealModel dealModel = (DealModel)intent.getSerializableExtra("Deal");

        if(dealModel == null){
            dealModel = new DealModel();
        }
        this.model = dealModel;
        txtTitle.setText(model.getTitle());
        txtDesc.setText(model.getDesc());
        txtPrice.setText(model.getPrice());

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imgUploadIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imgUploadIntent.setType("image/jpeg");
                imgUploadIntent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(imgUploadIntent,"Insert Picture"),REQUEST_CODE);
            }
        });
        showImage(model.getImageUrl());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Uri uri = data.getData();
            StorageReference reference = FirebaseUi.mStorageReference.child(uri.getLastPathSegment());
            reference.putFile(uri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    imageUrlData =taskSnapshot.getStorage().getDownloadUrl().toString();
                    Log.d("Imageurl",imageUrlData);
                    model.setImageUrl(imageUrlData);
                    showImage(imageUrlData);
                }
            });
        }
    }
    private void setUpToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.MaintoolbarID);
        mToolbar.setTitle("Home Page");
        setSupportActionBar(mToolbar);
    }

    private void setupEditText(){
        txtTitle = (EditText)findViewById(R.id.txtTitle);
        txtDesc = (EditText)findViewById(R.id.txtDesrc);
        txtPrice = (EditText)findViewById(R.id.txtPric);
        uploadButton = (Button)findViewById(R.id.UploadImageButton);
        uploadImage = (ImageView)findViewById(R.id.Image_Insert_ID);
    }
    private void mDealModel(){
        model.setTitle(txtTitle.getText().toString());
        model.setTitle(txtDesc.getText().toString());
        model.setTitle(txtPrice.getText().toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        MenuItem deleteMenu = menu.findItem(R.id.delete_item);
        MenuItem updateItem = menu.findItem(R.id.update_item);
        if(FirebaseUi.IsAdmain == true){
            deleteMenu.setVisible(true);
            updateItem.setVisible(true);
            EnableEditText(true);
        }else{
            deleteMenu.setVisible(false);
            updateItem.setVisible(false);
            EnableEditText(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.addItem:
                SaveDeal();
                Toast.makeText(this,"Deal Save",Toast.LENGTH_LONG).show();
                clearData();
                return true;
            case R.id.delete_item:
                deleteIteam();
                Toast.makeText(this,"Deal Delete",Toast.LENGTH_LONG).show();
                return true;
            case R.id.ShowData:
               startActivity(new Intent(MainActivity.this, ViewListActivity.class));
                return true;
            case R.id.update_item:
                updateIteam();
                Toast.makeText(this,"Deal Update",Toast.LENGTH_LONG).show();
                clearData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void updateIteam(){

        model.setTitle(txtTitle.getText().toString());
        model.setDesc(txtDesc.getText().toString());
        model.setPrice(txtPrice.getText().toString());
        model.setImageUrl("");

        if(model.getID()!=null) {

            mDatabaseReference.child(model.getID()).setValue(model);
        }

    }

    private void deleteIteam(){

        if(model == null){
            Toast.makeText(this,"User Does not exist",Toast.LENGTH_LONG).show();
        }
        else{
            mDatabaseReference.child(model.getID()).removeValue();
        }
    }
    private void SaveDeal(){
        String title = txtTitle.getText().toString();
        String desc = txtDesc.getText().toString();
        String price = txtPrice.getText().toString();
       // model.setImageUrl(imageUrlData);
        showImage(imageUrlData);
        DealModel model1 = new DealModel(title,desc,price,imageUrlData);
        mDatabaseReference.push().setValue(model1);
    }

    private void clearData(){
        txtTitle.setText("");
        txtDesc.setText("");
        txtPrice.setText("");
    }
    private void showImage(String url){
        if(url!=null && url.isEmpty()==false){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.with(this).load(url).resize(width,width*2/3).centerCrop().into(uploadImage);
        }
    }

    private void EnableEditText(boolean isEnable){
        txtTitle.setEnabled(isEnable);
        txtDesc.setEnabled(isEnable);
        txtPrice.setEnabled(isEnable);
    }
}
