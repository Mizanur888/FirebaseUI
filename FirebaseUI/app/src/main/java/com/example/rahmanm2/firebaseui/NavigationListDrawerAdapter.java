package com.example.rahmanm2.firebaseui;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rahmanm2.firebaseui.DataModel.navDataModel;

import java.io.Serializable;
import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class NavigationListDrawerAdapter extends RecyclerView.Adapter<NavigationListDrawerAdapter.viewHolder>{
    List<navDataModel>mNavDataModels;
    LayoutInflater mLayoutInflater;
    Context ctx;

    public NavigationListDrawerAdapter(Context ctx, List<navDataModel>mNavDataModels){
        this.ctx = ctx;
        this.mLayoutInflater = LayoutInflater.from(ctx);
        this.mNavDataModels = mNavDataModels;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.nav_menu_list,parent,false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder holder, int position) {
        navDataModel model =mNavDataModels.get(position);
        holder.setupdata(model);
    }

    @Override
    public int getItemCount() {
        return mNavDataModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTextView;
        public viewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.navdraw_image_id);
            mTextView = (TextView)itemView.findViewById(R.id.navdraw_textID);
            itemView.setOnClickListener(this);
        }
        public void setupdata(navDataModel model){
            mImageView.setImageResource(model.getImageID());
            mTextView.setText(model.getImageTitle());
          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(ctx,mTextView.getText().toString(),Toast.LENGTH_LONG).show();
                }
            });*/
        }

        @Override
        public void onClick(View view) {
            int postion = getAdapterPosition();
            navDataModel model = mNavDataModels.get(postion);
            Log.d("postion",model.getImageTitle());
            Toast.makeText(ctx,mTextView.getText().toString(),Toast.LENGTH_LONG).show();
        }

    }
}
