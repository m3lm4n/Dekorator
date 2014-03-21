package com.lastowski.dekorator.item;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lastowski.dekorator.R;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.reservations.ReservationActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class SpecificItemFragment extends Fragment {
    private TextView nameField;
    private TextView sizeField;
    private ImageView colorField;
    private TextView awayField;
    private int itemId;
    private ImageView imageField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        
        nameField = (TextView) view.findViewById(R.id.name);
        sizeField = (TextView) view.findViewById(R.id.size);
        colorField = (ImageView) view.findViewById(R.id.color);
        awayField = (TextView) view.findViewById(R.id.away);
        imageField = (ImageView) view.findViewById(R.id.image);
        
        return view;
    }

    public void setItemInfo(SpecificItem item){
        itemId = item.getId();
        nameField.setText(item.getName());
        sizeField.setText(item.getSize());
        colorField.setImageDrawable(new ColorDrawable(Color.parseColor(item.getColor())));
        awayField.setText(item.isAway() ? "Wydane" : "Na miejscu");

        Picasso.with(getActivity()).load(item.getImage()).into(imageField);
    }

}
