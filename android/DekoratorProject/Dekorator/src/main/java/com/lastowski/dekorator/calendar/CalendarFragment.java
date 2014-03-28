package com.lastowski.dekorator.calendar;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.foxykeep.datadroid.requestmanager.Request;
import com.lastowski.dekorator.R;
import com.lastowski.dekorator.api.RequestFactory;
import com.lastowski.dekorator.reservations.ReservationActivity;
import com.lastowski.dekorator.reservations.ReservationItem;

import java.util.List;

public class CalendarFragment extends Fragment {
    private CalendarAdapter adapter;
    private TextView monthName;


    public int getTopOffset() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result + getActivity().getActionBar().getHeight();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        monthName = (TextView) view.findViewById(R.id.month_name);

        GridView gv = (GridView) view.findViewById(R.id.grid_view);
        adapter = new CalendarAdapter(inflater, getActivity());
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                View popUp = view.findViewById(R.id.pop_up_dialog);
                View calendarTitle = view.findViewById(R.id.calendar_title);

                Object obj = adapter.getItem(i);

                if(obj == null){
                    popUp.setVisibility(View.GONE);
                    calendarTitle.setVisibility(View.VISIBLE);
                }
                else {
                    ReservationItem ri = (ReservationItem) obj;

                    TextView nameDialog = (TextView) popUp.findViewById(R.id.name_dialog);
                    nameDialog.setText(ri.getName());

                    if(!ri.getItem().isAway()) {
                        addRentListener(ri.getId(), popUp.findViewById(R.id.rent_item));
                    }
                    else {
                        popUp.findViewById(R.id.rent_item).setAlpha(0.1f);
                        popUp.findViewById(R.id.cancel).setAlpha(0.1f);
                    }
                    popUp.setVisibility(View.VISIBLE);
                    popUp.postInvalidate();
                    calendarTitle.setVisibility(View.GONE);
                }
            }
        });

        view.findViewById(R.id.prev_month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.prevMonth();
                monthName.setText(adapter.getMonthName());
                adapter.notifyDataSetChanged();
            }
        });

        view.findViewById(R.id.next_month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.nextMonth();
                monthName.setText(adapter.getMonthName());
                adapter.notifyDataSetChanged();
            }
        });

        monthName.setText(adapter.getMonthName());

        return view;
    }

    public void addRentListener(final int resId, View v){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if(activity instanceof ReservationActivity){
                    ((ReservationActivity) activity).rentItem(resId);
                }

            }
        });
    }

    public void setReservations(List<ReservationItem> reservations){
        adapter.setReservations(reservations);
        adapter.notifyDataSetChanged();
    }
}
