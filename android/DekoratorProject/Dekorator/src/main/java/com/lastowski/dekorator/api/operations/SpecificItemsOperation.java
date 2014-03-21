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
import com.lastowski.dekorator.item.SpecificItem;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class SpecificItemsOperation implements RequestService.Operation {
    String endpoint = "/items/";

    @Override
    public Bundle execute(Context context, Request request) throws ConnectionException, DataException, CustomRequestException {
        int itemId = request.getInt(Utils.Const.RequestParam.ITEM_ID);

        NetworkConnection networkConnection = new NetworkConnection(context,
                Utils.Const.API_ADDRESS+endpoint+itemId);
        networkConnection.setMethod(NetworkConnection.Method.GET);

        NetworkConnection.ConnectionResult result = networkConnection.execute();

        Gson gson = new Gson();
        SpecificItem item = gson.fromJson(result.body, SpecificItem.class);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Utils.Const.Response.SPECIFIC_ITEM, item);

        return bundle;
    }
}
