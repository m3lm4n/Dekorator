package com.lastowski.dekorator.calendar;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lastowski.dekorator.R;
import com.lastowski.dekorator.reservations.ReservationItem;

import org.joda.time.DateTime;

public class CalendarAdapter extends BaseAdapter{

    Calendar calendar;
    private LayoutInflater inflater;
    int dayPadding;
    private Context mContext;

    HashMap<Integer, HashMap<Integer, ReservationItem>> mReservations;


    public void setReservations(List<ReservationItem> reservations) {
        mReservations = new HashMap<Integer, HashMap<Integer, ReservationItem>>();
        for(ReservationItem ri: reservations){
            String[] tokens = ri.getDate().split("-");
            int month = Integer.parseInt(tokens[1]) - 1;
            int day = Integer.parseInt(tokens[2]);
            if(!mReservations.containsKey(month)){
               mReservations.put(month, new HashMap<Integer, ReservationItem>());
            }
            mReservations.get(month).put(day, ri);
        }
    }

    private class ViewHolder {
        TextView dayNumber;
    }

    public String getMonthName(){
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public void nextMonth(){
        calendar.add(Calendar.MONTH, 1);
        DateTime date = new DateTime(calendar.getTimeInMillis());
        dayPadding = date.withDayOfMonth(1).dayOfWeek().get();
    }

    public void prevMonth(){
        calendar.add(Calendar.MONTH, -1);
        DateTime date = new DateTime(calendar.getTimeInMillis());
        dayPadding = date.withDayOfMonth(1).dayOfWeek().get();
    }

    public CalendarAdapter(LayoutInflater inflater, Context context) {
        calendar = Calendar.getInstance();
        DateTime date = new DateTime(calendar.getTimeInMillis());
        dayPadding = date.withDayOfMonth(1).dayOfWeek().get();
        this.inflater = inflater;
        mContext = context;
    }

    @Override
    public int getCount() {
        return dayPadding + calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;
    }

    @Override
    public Object getItem(int position) {
        int day = position - dayPadding + 2;

        if(mReservations!=null){
            if(mReservations.containsKey(calendar.get(Calendar.MONTH))){
                HashMap<Integer, ReservationItem> month = mReservations.get(calendar.get(Calendar.MONTH));
                if(month.containsKey(day)){
                    return month.get(day);
                }
            }
        }
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder vh;
        if (position+1 < dayPadding ){
            view = inflater.inflate(R.layout.empty_layout, parent, false);
            view.setTag("no recycle");
            return view;
        }
        if (convertView == null || convertView.getTag().equals("no recycle")) {  // if it's not recycled, initialize some attributes
            view = inflater.inflate(R.layout.list_item_calendar, parent, false);
            vh = new ViewHolder();
            vh.dayNumber = (TextView) view.findViewById(R.id.day_num);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        if(DateTime.now().monthOfYear().get()-1 == calendar.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == position - dayPadding + 2){
            view.setBackgroundColor(mContext.getResources().getColor(R.color.pink));
            vh.dayNumber.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        else {
            view.setBackgroundColor(mContext.getResources().getColor(R.color.light_pink));
            vh.dayNumber.setTextColor(mContext.getResources().getColor(R.color.pink));
        }

        int day = position - dayPadding + 2;

        if(mReservations!=null){
            view.setBackgroundColor(mContext.getResources().getColor(R.color.green));
            vh.dayNumber.setTextColor(mContext.getResources().getColor(R.color.pink));
            if(mReservations.containsKey(calendar.get(Calendar.MONTH))){
                HashMap<Integer, ReservationItem> month = mReservations.get(calendar.get(Calendar.MONTH));
                if(month.containsKey(day)){
                    view.setBackgroundColor(mContext.getResources().getColor(R.color.red));
                    vh.dayNumber.setTextColor(mContext.getResources().getColor(R.color.white));
                }
            }
        }
        vh.dayNumber.setText(""+day);

        return view;
    }

}
