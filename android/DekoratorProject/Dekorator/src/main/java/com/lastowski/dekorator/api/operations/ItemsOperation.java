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
public class ItemsOperation implements RequestService.Operation {
    String endpoint = "/items";

    @Override
    public Bundle execute(Context context, Request request) throws ConnectionException, DataException, CustomRequestException {
        NetworkConnection networkConnection = new NetworkConnection(context,
                Utils.Const.API_ADDRESS+endpoint);
        networkConnection.setMethod(NetworkConnection.Method.GET);

        NetworkConnection.ConnectionResult result = networkConnection.execute();

        Gson gson = new Gson();
        CatalogItem[] items = gson.fromJson(result.body, CatalogItem[].class);

        Bundle bundle = new Bundle();
        bundle.putParcelableArray(Utils.Const.Response.CATALOG_ITEMS, items);

        return bundle;
    }
}
