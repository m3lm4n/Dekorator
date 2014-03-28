package com.lastowski.dekorator.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.foxykeep.datadroid.requestmanager.Request;
import com.foxykeep.datadroid.requestmanager.RequestManager;
import com.lastowski.dekorator.R;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.api.CommsActivity;
import com.lastowski.dekorator.api.RequestFactory;
import com.lastowski.dekorator.catalog.CatalogItem;
import com.lastowski.dekorator.reservations.AddReservationActivity;
import com.lastowski.dekorator.reservations.ReservationActivity;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class SpecificItemActivity extends CommsActivity implements RequestManager.RequestListener{

    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        itemId = getIntent().getIntExtra(Utils.Const.BundleExtra.ITEM_ID, 0);

        if(itemId != 0){
            Request request = RequestFactory.getSpecificItem(itemId);
            startApiRequest(request, this);
        }

    }

    public void onReservationButtonClick(View v){
        Intent i = new Intent(this, ReservationActivity.class);
        i.putExtra(Utils.Const.BundleExtra.ITEM_ID, itemId);
        startActivity(i);
    }

    public void onAddReservationButtonClick(View v){
        Intent i = new Intent(this, AddReservationActivity.class);
        i.putExtra(Utils.Const.BundleExtra.ITEM_ID, itemId);
        startActivity(i);
    }

    @Override
    public void onRequestFinished(Request request, Bundle bundle) {
        if(request.getRequestType() == RequestFactory.REQUEST_TYPE_RETURN_ITEM){
            Intent intent = new Intent(SpecificItemActivity.this, SpecificItemActivity.class);
            intent.putExtra(Utils.Const.BundleExtra.ITEM_ID, itemId);
            startActivity(intent);
            finish();
        }
        else {
            SpecificItem item = bundle.getParcelable(Utils.Const.Response.SPECIFIC_ITEM);

            SpecificItemFragment fragment = (SpecificItemFragment) getFragmentManager().findFragmentById(R.id.specific_item_fragment);
            fragment.setItemInfo(item);
        }
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

    public void returnItem(int itemId) {
        Request request = RequestFactory.returnItem(itemId);
        startApiRequest(request, this);

    }
}
