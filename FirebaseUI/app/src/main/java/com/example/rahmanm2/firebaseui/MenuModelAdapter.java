package com.example.rahmanm2.firebaseui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MenuModelAdapter extends RecyclerView.Adapter<MenuModelAdapter.menuViewHolder>{

    private List<MenuDataModel>mMenuDataModels;
    //Context ctx;
    LayoutInflater mLayoutInflater;

    public MenuModelAdapter(Context context, List<MenuDataModel>model){
        this.mMenuDataModels = model;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public menuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.main_menulayout,parent,false);
        menuViewHolder holder = new menuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull menuViewHolder holder, int position) {
        MenuDataModel model = mMenuDataModels.get(position);
        holder.setData(model,position);
    }

    @Override
    public int getItemCount() {
        return mMenuDataModels.size();
    }

    public class menuViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        MenuDataModel current;
        int position;
        public menuViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView)itemView.findViewById(R.id.diff_Layout_TextID);

            mImageView = (ImageView)itemView.findViewById(R.id.diff_layout_ImageID);

        }

        public void setData(MenuDataModel current, int position) {
            this.mTextView.setText(current.getTitle());
            this.mImageView.setImageResource(current.getImageurl());
            this.position = position;
            this.current = current;
        }
    }
}
