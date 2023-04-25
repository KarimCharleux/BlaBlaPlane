package com.example.blablaplane;

import android.content.Context;
import android.location.Address;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class PlacesAdapter extends BaseAdapter {

    private Context context;
    private List<Address> AddressList;
    private LayoutInflater inflater;


    @Override
    public int getCount() {
        return this.AddressList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.AddressList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
