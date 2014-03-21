package com.lastowski.dekorator.api;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.foxykeep.datadroid.requestmanager.Request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class CommsActivity extends Activity {

        private static final String TAG = CommsActivity.class.getSimpleName();
        private static final String SAVED_STATE_REQUEST_LIST = "savedStateRequestList";
        public RequestManager mRequestManager;
        public ArrayList<Request> mRequestList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


            mRequestManager = RequestManager.from(this);

            if (savedInstanceState != null) {
                mRequestList = savedInstanceState.getParcelableArrayList(SAVED_STATE_REQUEST_LIST);
            } else {
                mRequestList = new ArrayList<Request>();
            }
        }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putParcelableArrayList(SAVED_STATE_REQUEST_LIST, mRequestList);
        }

        /**
         * Start API request call.
         * @param request
         */
        public void startApiRequest(Request request, RequestManager.RequestListener listener) {
            mRequestManager.execute(request, listener);
            mRequestList.add(request);
            Log.d(TAG, "startApiRequest(): list size: " + mRequestList.size());
        }

        /**
         * Retrieve list of currently pending request. List is unmodifiable.
         * @return Unmodifiable list of currently pending requests.
         */
        protected List<Request> requestList() {
            return Collections.unmodifiableList(mRequestList);
        }
}
