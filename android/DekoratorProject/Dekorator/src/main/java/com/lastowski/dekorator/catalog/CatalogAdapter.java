package com.lastowski.dekorator.catalog;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lastowski.dekorator.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends BaseAdapter {

    int num;
    private LayoutInflater inflater;
    private List<CatalogItem> data;
    private Context context;

    public CatalogAdapter(Context c, LayoutInflater inflater) {
        this.inflater = inflater;
        context = c;
        data = new ArrayList<CatalogItem>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            view = inflater.inflate(R.layout.list_item_catalog, parent, false);
        } else {
            view = convertView;
        }

        CatalogItem item = data.get(position);
        ImageView img = (ImageView) view.findViewById(R.id.imag);
        Picasso.with(context).load(item.getImage()).into(img);
        TextView itemName = (TextView) view.findViewById(R.id.item_name);
        itemName.setText(data.get(position).name);

        return view;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setData(List<CatalogItem> data) {
        this.data = data;
    }
}
