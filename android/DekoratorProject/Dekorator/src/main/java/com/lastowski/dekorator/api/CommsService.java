package com.lastowski.dekorator.api;

import com.foxykeep.datadroid.service.RequestService;
import com.lastowski.dekorator.api.operations.AddReservationOperation;
import com.lastowski.dekorator.api.operations.ItemReservationsOperation;
import com.lastowski.dekorator.api.operations.ItemsOperation;
import com.lastowski.dekorator.api.operations.RentItemOperation;
import com.lastowski.dekorator.api.operations.ReturnItemOperation;
import com.lastowski.dekorator.api.operations.SpecificItemsOperation;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class CommsService extends RequestService {
    @Override
    public Operation getOperationForType(int i) {
        Operation returnOperation = null;
        switch(i){
            case RequestFactory.REQUEST_TYPE_CATALOG_ITEMS:
                returnOperation = new ItemsOperation();
                break;
            case RequestFactory.REQUEST_TYPE_SPECIFIC_ITEM:
                returnOperation = new SpecificItemsOperation();
                break;
            case RequestFactory.REQUEST_TYPE_RESERVATIONS:
                returnOperation = new ItemReservationsOperation();
                break;
            case RequestFactory.REQUEST_TYPE_ADD_RESERVATION:
                returnOperation = new AddReservationOperation();
                break;
            case RequestFactory.REQUEST_TYPE_RENT_ITEM:
                returnOperation = new RentItemOperation();
                break;
            case RequestFactory.REQUEST_TYPE_RETURN_ITEM:
                returnOperation = new ReturnItemOperation();
                break;
        }
        return returnOperation;
    }
}
