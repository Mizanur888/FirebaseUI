package com.example.rahmanm2.firebaseui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahmanm2.firebaseui.App.MainActivity;
import com.example.rahmanm2.firebaseui.DataModel.DealModel;
import com.example.rahmanm2.firebaseui.FirebaseUtil.FirebaseUi;
import com.example.rahmanm2.firebaseui.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class dealViewAdapter extends RecyclerView.Adapter<dealViewAdapter.viewHolder>{
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    ChildEventListener mChildEventListener;
    public ArrayList<DealModel>mDealModels;
    private ImageView mImageViewDeal;
    Activity caller;
    public dealViewAdapter(Activity cttx){
        this.caller = cttx;
        FirebaseUi.openFirebase("FirebaseUI",caller);
        mFirebaseDatabase = FirebaseUi.mFirebaseDatabase;
        mDatabaseReference = FirebaseUi.mDatabaseReference;
        mDealModels = FirebaseUi.mDeals;
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DealModel model = dataSnapshot.getValue(DealModel.class);
                Log.d(model.getTitle()," | "+model.getPrice());
                model.setID(dataSnapshot.getKey());
                mDealModels.add(model);
                notifyItemInserted(mDealModels.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildEventListener);
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.deal_view,parent,false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DealModel deal = mDealModels.get(position);
        holder.bindData(deal);
    }

    @Override
    public int getItemCount() {
        return mDealModels.size();
    }

    public class viewHolder extends ViewHolder implements View.OnClickListener{
        TextView dealTitle;
        TextView tvDesc;
        TextView tvPric;
       // ImageView mImageView;
        public viewHolder(View itemView) {
            super(itemView);
            dealTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView)itemView.findViewById(R.id.tvDesc);
            tvPric = (TextView)itemView.findViewById(R.id.tvPrice);
            mImageViewDeal = (ImageView) itemView.findViewById(R.id.imageId);
            itemView.setOnClickListener(this);
        }
        public void bindData(DealModel model){
            dealTitle.setText(model.getTitle());
            tvDesc.setText(model.getDesc());
            tvPric.setText("$"+model.getPrice());
            showImage(model.getImageUrl());
            //mImageView.setImageResource(model.getImageUrl());
        }

        @Override
        public void onClick(View view) {
            int postion = getAdapterPosition();
            Log.d("on click",String.valueOf(postion));
            DealModel model = mDealModels.get(postion);

            Intent intent = new Intent(view.getContext(), MainActivity.class);
            intent.putExtra("Deal",model);
            view.getContext().startActivity(intent);
        }
        private void showImage(String url){
            if(url!=null && url.isEmpty()==false){
               // int width = Resources.getSystem().getDisplayMetrics().widthPixels;
                Picasso.with(mImageViewDeal.getContext()).
                         load(url).
                        resize(100,100).
                        centerCrop().
                        into(mImageViewDeal);
            }
        }
    }
}
