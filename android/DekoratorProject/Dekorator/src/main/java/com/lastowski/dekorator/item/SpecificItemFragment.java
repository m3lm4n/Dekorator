package com.lastowski.dekorator.item;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foxykeep.datadroid.requestmanager.Request;
import com.lastowski.dekorator.R;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.api.RequestFactory;
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
    private Button awayButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        
        nameField = (TextView) view.findViewById(R.id.name);
        sizeField = (TextView) view.findViewById(R.id.size);
        colorField = (ImageView) view.findViewById(R.id.color);
        awayField = (TextView) view.findViewById(R.id.away);
        imageField = (ImageView) view.findViewById(R.id.image);
        awayButton = (Button) view.findViewById(R.id.away_button);
        
        return view;
    }

    public void setItemInfo(final SpecificItem item){
        itemId = item.getId();
        nameField.setText(item.getName());
        sizeField.setText(item.getSize());
        colorField.setImageDrawable(new ColorDrawable(Color.parseColor(item.getColor())));
        //awayField.setText(item.isAway() ? "Wydane" : "Na miejscu");

        awayField.setBackgroundColor(getResources().getColor(item.isAway() ? R.color.red : R.color.green));
        awayButton.setAlpha(item.isAway() ? 1f : 0.1f);

        if(item.isAway()) {
            awayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity activity = getActivity();
                    if(activity instanceof SpecificItemActivity){
                        ((SpecificItemActivity) activity).returnItem(item.getId());
                    }
                }
            });
        }

        Picasso.with(getActivity()).load(item.getImage()).into(imageField);
    }

}
