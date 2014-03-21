package com.lastowski.dekorator.reservations;

import android.os.Bundle;
import android.os.Parcelable;

import com.foxykeep.datadroid.requestmanager.Request;
import com.foxykeep.datadroid.requestmanager.RequestManager;
import com.lastowski.dekorator.R;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.api.CommsActivity;
import com.lastowski.dekorator.api.RequestFactory;
import com.lastowski.dekorator.calendar.CalendarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class ReservationActivity extends CommsActivity implements RequestManager.RequestListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int itemId = getIntent().getIntExtra(Utils.Const.BundleExtra.ITEM_ID, 0);

        if(itemId != 0){
            Request request = RequestFactory.getReservations(itemId);
            startApiRequest(request, this);
        }

    }

    @Override
    public void onRequestFinished(Request request, Bundle bundle) {
        if(request.getRequestType() == RequestFactory.REQUEST_TYPE_RENT_ITEM){
            finish();
        }
        else {
            Parcelable[] objs = bundle.getParcelableArray(Utils.Const.Response.RESERVATIONS);
            List<ReservationItem> items = new ArrayList<ReservationItem>();
            for(Parcelable p: objs){
                items.add((ReservationItem)p);
            }

            CalendarFragment fragment = (CalendarFragment) getFragmentManager().findFragmentById(R.id.calendar_fragment);
            fragment.setReservations(items);
        }
    }

    public void rentItem(int resId){
        Request request = RequestFactory.rentIten(resId);
        startApiRequest(request, this);
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

