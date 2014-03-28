package com.lastowski.dekorator.api;

import com.foxykeep.datadroid.requestmanager.Request;
import com.lastowski.dekorator.Utils;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class RequestFactory {
    public static final int REQUEST_TYPE_CATALOG_ITEMS = 1;
    public static final int REQUEST_TYPE_SPECIFIC_ITEM = 2;
    public static final int REQUEST_TYPE_RESERVATIONS = 3;
    public static final int REQUEST_TYPE_ADD_RESERVATION = 4;
    public static final int REQUEST_TYPE_RENT_ITEM = 5;
    public static final int REQUEST_TYPE_RETURN_ITEM = 6;

    public static Request getCatalogItems(){
        Request request = new Request(REQUEST_TYPE_CATALOG_ITEMS);
        return request;
    }

    public static Request getSpecificItem(int id){
        Request request = new Request(REQUEST_TYPE_SPECIFIC_ITEM);
        request.put(Utils.Const.RequestParam.ITEM_ID, id);
        return request;
    }

    public static Request returnItem(int id){
        Request request = new Request(REQUEST_TYPE_RETURN_ITEM);
        request.put(Utils.Const.RequestParam.ITEM_ID, id);
        return request;
    }

    public static Request getReservations(int id){
        Request request = new Request(REQUEST_TYPE_RESERVATIONS);
        request.put(Utils.Const.RequestParam.ITEM_ID, id);
        return request;
    }

    public static Request addReservation(int itemId, String clientName, String date){
        Request request = new Request(REQUEST_TYPE_ADD_RESERVATION);
        request.put(Utils.Const.RequestParam.ITEM_ID, itemId);
        request.put(Utils.Const.RequestParam.CLIENT_NAME, clientName);
        request.put(Utils.Const.RequestParam.RESERVATION_DATE, date);
        return request;
    }

    public static Request rentIten(int resId){
        Request request = new Request(REQUEST_TYPE_RENT_ITEM);
        request.put(Utils.Const.RequestParam.RESERVATION_ID, resId);
        return request;
    }
}
