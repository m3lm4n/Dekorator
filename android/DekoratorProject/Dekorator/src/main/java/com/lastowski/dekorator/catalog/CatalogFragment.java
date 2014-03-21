package com.lastowski.dekorator.catalog;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.foxykeep.datadroid.requestmanager.Request;
import com.foxykeep.datadroid.requestmanager.RequestManager;
import com.lastowski.dekorator.MainActivity;
import com.lastowski.dekorator.R;
import com.lastowski.dekorator.Utils;
import com.lastowski.dekorator.api.RequestFactory;
import com.lastowski.dekorator.item.SpecificItemActivity;

import java.util.ArrayList;
import java.util.List;

public class CatalogFragment extends Fragment implements RequestManager.RequestListener{
    CatalogAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        GridView gv = (GridView) view.findViewById(R.id.grid_view);
        mAdapter = new CatalogAdapter(getActivity(), inflater);
        gv.setAdapter(mAdapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), SpecificItemActivity.class);
                CatalogItem item = (CatalogItem) mAdapter.getItem(i);
                intent.putExtra(Utils.Const.BundleExtra.ITEM_ID, item.getId());
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity activity = (MainActivity) getActivity();

        Request request = RequestFactory.getCatalogItems();
        activity.startApiRequest(request, this);
    }

    @Override
    public void onRequestFinished(Request request, Bundle bundle) {
        Parcelable[] objs = bundle.getParcelableArray(Utils.Const.Response.CATALOG_ITEMS);
        List<CatalogItem> items = new ArrayList<CatalogItem>();
        for(Parcelable p: objs){
            items.add((CatalogItem)p);
        }
        mAdapter.setData(items);
        mAdapter.notifyDataSetChanged();
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
