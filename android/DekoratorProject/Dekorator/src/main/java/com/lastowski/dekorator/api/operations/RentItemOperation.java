package com.lastowski.dekorator.api.operations;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.foxykeep.datadroid.exception.ConnectionException;
import com.foxykeep.datadroid.exception.CustomRequestException;
import com.foxykeep.datadroid.exception.DataException;
import com.foxykeep.datadroid.network.NetworkConnection;
import com.foxykeep.datadroid.requestmanager.Request;
import com.foxykeep.datadroid.service.RequestService;
import com.google.gson.Gson;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.catalog.CatalogItem;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class RentItemOperation implements RequestService.Operation {
    String endpoint = "/reservations/";

    @Override
    public Bundle execute(Context context, Request request) throws ConnectionException, DataException, CustomRequestException {
        int reservationId = request.getInt(Utils.Const.RequestParam.RESERVATION_ID);

        NetworkConnection networkConnection = new NetworkConnection(context,
                Utils.Const.API_ADDRESS+endpoint+reservationId+"/rent/");
        networkConnection.setMethod(NetworkConnection.Method.POST);

        NetworkConnection.ConnectionResult result = networkConnection.execute();

        Bundle bundle = new Bundle();
        return bundle;
    }
}
