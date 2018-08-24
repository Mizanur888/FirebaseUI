package com.example.rahmanm2.firebaseui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rahmanm2.firebaseui.DataModel.navDataModel;

public class NavigationDrawerFragment extends Fragment {
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        SetUpNavMenuRecyclerList(view);
        return view;
    }

    private void SetUpNavMenuRecyclerList(View view){
        RecyclerView nabRecycler =(RecyclerView)view.findViewById(R.id.drawList);
        NavigationListDrawerAdapter adapter = new NavigationListDrawerAdapter(getActivity(), navDataModel.getAllNavDataModel());
        nabRecycler.setAdapter(adapter);
        nabRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }



    public void setUpDrawer(int postion, DrawerLayout drawerLayout, Toolbar toolbar){
        mDrawerLayout = drawerLayout;
        mActionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.Draw_Open,R.string.Draw_Closed ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
            mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
            mDrawerLayout.post(new Runnable() {
                @Override
                public void run() {
                    mActionBarDrawerToggle.syncState();
                }
            });
    }
}
