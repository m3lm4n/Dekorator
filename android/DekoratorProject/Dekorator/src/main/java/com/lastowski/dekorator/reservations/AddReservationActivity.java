package com.lastowski.dekorator.reservations;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.foxykeep.datadroid.requestmanager.Request;
import com.foxykeep.datadroid.requestmanager.RequestManager;
import com.lastowski.dekorator.R;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.api.CommsActivity;
import com.lastowski.dekorator.api.RequestFactory;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class AddReservationActivity extends CommsActivity implements RequestManager.RequestListener{


    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemId = getIntent().getIntExtra(Utils.Const.BundleExtra.ITEM_ID, 0);
    }

    public void onAddReservationButtonClick(View v){
        EditText nameInput = (EditText) findViewById(R.id.name_input);
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        String clientName = nameInput.getText().toString();
        String date = datePicker.getYear() + "-";
        if(datePicker.getMonth()+1<10){
            date += "0";
        }
        date += datePicker.getMonth()+1 + "-";
        if(datePicker.getDayOfMonth()<10){
            date += "0";
        }
        date += datePicker.getDayOfMonth();

        Request request = RequestFactory.addReservation(itemId, clientName, date);
        startApiRequest(request, this);

    }

    @Override
    public void onRequestFinished(Request request, Bundle bundle) {
        finish();
    }

    @Override
    public void onRequestConnectionError(Request request, int i) {

    }

    @Override
    public void onRequestDataError(Request request) {

    }

    @Override
    public void onRequestCustomError(Request request, Bundle bundle) {

    }
}

