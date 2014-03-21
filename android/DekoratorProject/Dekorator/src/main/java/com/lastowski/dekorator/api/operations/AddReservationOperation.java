package com.lastowski.dekorator.api.operations;

import android.content.Context;
import android.os.Bundle;

import com.foxykeep.datadroid.exception.ConnectionException;
import com.foxykeep.datadroid.exception.CustomRequestException;
import com.foxykeep.datadroid.exception.DataException;
import com.foxykeep.datadroid.network.NetworkConnection;
import com.foxykeep.datadroid.requestmanager.Request;
import com.foxykeep.datadroid.service.RequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.catalog.CatalogItem;
import com.lastowski.dekorator.reservations.ReservationItem;

/**
 * Created by adamastowski on 01.01.2014.
 */
public class AddReservationOperation implements RequestService.Operation{
    String endpoint = "/items/";

    @Override
    public Bundle execute(Context context, Request request) throws ConnectionException, DataException, CustomRequestException {

        int itemId = request.getInt(Utils.Const.RequestParam.ITEM_ID);
        String name = request.getString(Utils.Const.RequestParam.CLIENT_NAME);
        String date = request.getString(Utils.Const.RequestParam.RESERVATION_DATE);

        ReservationItem rItem = new ReservationItem(itemId, name, date);


        NetworkConnection networkConnection = new NetworkConnection(context,
                Utils.Const.API_ADDRESS+endpoint+itemId+"/reservations/");
        networkConnection.setMethod(NetworkConnection.Method.POST);

        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        networkConnection.setPostText(gson.toJson(rItem));

        NetworkConnection.ConnectionResult result = networkConnection.execute();


        ReservationItem items = gson.fromJson(result.body, ReservationItem.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Utils.Const.Response.RESERVATION, items);

        return bundle;
    }
}
