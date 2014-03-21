package com.lastowski.dekorator;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class Utils {
    public static class Const {

        public static final String API_ADDRESS = "http://ec2-54-72-114-6.eu-west-1.compute.amazonaws.com";
        public static final String API_MEDIA_ADDRESS = API_ADDRESS + "/media";

        public class BundleExtra{

            public static final String ITEM_ID = "const.bundle.item.id";
        }

        public class Response {
            public static final String CATALOG_ITEMS = "const.response.catalog.items" ;
            public static final String SPECIFIC_ITEM = "const.response.specific.item";
            public static final String RESERVATIONS = "const.response.reservations";
            public static final String RESERVATION = "const.response.reservation";
        }

        public class RequestParam {
            public static final String ITEM_ID = "const.request.param.item.id";
            public static final String CLIENT_NAME = "const.request.param.client.name";
            public static final String RESERVATION_DATE = "const.request.param.reservation.date";
            public static final String RESERVATION_ID = "const.request.param.reservation.id";
        }
    }
}
