package com.dinodevs.pacecalendarwidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by GreatApo on 06/04/2018.
 */

public class APcalendar {
    // Calendar variables
    private int color;
    private int year;
    private int month;
    private int day;
    public boolean isMondayFirst;

    // Calendar View objects
    private TextView view_monthName;
    private TextView view_year;
    private TextView[] view_boxes;
    private TextView[] view_boxes_rtl;
    private TextView[] view_colorable;
    private TextView[] view_week;

    // Calendar translations
    private APtranslations tranlations;

    private Context mContext;
    private List<List<Integer>> eventsList;
    private List<List<String>> eventsTitle;
    private List<List<String>> eventsHours;

    // Constructor
    APcalendar(View view, Context context, Calendar date, int color){
        this.isMondayFirst = false;

        mContext = context;

        // Create translations instance
        this.tranlations = new APtranslations();

        // Init view objects
        this.initViewObjects(view, context);

        // Refresh calendar
        this.refresh(date, color);
        this.refreshDays();
    }

    // Initialize View objects
    private void initViewObjects(View view, Context context) {
        // Get header objects
        this.view_monthName = (TextView) view.findViewById(R.id.textMonth);
        this.view_year = (TextView) view.findViewById(R.id.textYear);

        // Get color-able view objects
        this.view_colorable = new TextView[]{
            (TextView) view.findViewById(R.id.day1),
            (TextView) view.findViewById(R.id.day2),
            (TextView) view.findViewById(R.id.day3),
            (TextView) view.findViewById(R.id.day4),
            (TextView) view.findViewById(R.id.day5),
            (TextView) view.findViewById(R.id.day6),
            (TextView) view.findViewById(R.id.day7),
            (TextView) view.findViewById(R.id.arrow_down),
            (TextView) view.findViewById(R.id.arrow_up),
            (TextView) view.findViewById(R.id.week1),
            (TextView) view.findViewById(R.id.week2),
            (TextView) view.findViewById(R.id.week3),
            (TextView) view.findViewById(R.id.week4),
            (TextView) view.findViewById(R.id.week5),
            (TextView) view.findViewById(R.id.week6)
        };

        // Get dates boxes
        this.view_boxes = new TextView[]{
            (TextView) view.findViewById(R.id.calbox1),
            (TextView) view.findViewById(R.id.calbox2),
            (TextView) view.findViewById(R.id.calbox3),
            (TextView) view.findViewById(R.id.calbox4),
            (TextView) view.findViewById(R.id.calbox5),
            (TextView) view.findViewById(R.id.calbox6),
            (TextView) view.findViewById(R.id.calbox7),
            (TextView) view.findViewById(R.id.calbox8),
            (TextView) view.findViewById(R.id.calbox9),
            (TextView) view.findViewById(R.id.calbox10),
            (TextView) view.findViewById(R.id.calbox11),
            (TextView) view.findViewById(R.id.calbox12),
            (TextView) view.findViewById(R.id.calbox13),
            (TextView) view.findViewById(R.id.calbox14),
            (TextView) view.findViewById(R.id.calbox15),
            (TextView) view.findViewById(R.id.calbox16),
            (TextView) view.findViewById(R.id.calbox17),
            (TextView) view.findViewById(R.id.calbox18),
            (TextView) view.findViewById(R.id.calbox19),
            (TextView) view.findViewById(R.id.calbox20),
            (TextView) view.findViewById(R.id.calbox21),
            (TextView) view.findViewById(R.id.calbox22),
            (TextView) view.findViewById(R.id.calbox23),
            (TextView) view.findViewById(R.id.calbox24),
            (TextView) view.findViewById(R.id.calbox25),
            (TextView) view.findViewById(R.id.calbox26),
            (TextView) view.findViewById(R.id.calbox27),
            (TextView) view.findViewById(R.id.calbox28),
            (TextView) view.findViewById(R.id.calbox29),
            (TextView) view.findViewById(R.id.calbox30),
            (TextView) view.findViewById(R.id.calbox31),
            (TextView) view.findViewById(R.id.calbox32),
            (TextView) view.findViewById(R.id.calbox33),
            (TextView) view.findViewById(R.id.calbox34),
            (TextView) view.findViewById(R.id.calbox35),
            (TextView) view.findViewById(R.id.calbox36),
            (TextView) view.findViewById(R.id.calbox37),
            (TextView) view.findViewById(R.id.calbox38),
            (TextView) view.findViewById(R.id.calbox39),
            (TextView) view.findViewById(R.id.calbox40),
            (TextView) view.findViewById(R.id.calbox41),
            (TextView) view.findViewById(R.id.calbox42)
        };

        // Get date boxes for rtl
        this.view_boxes_rtl = new TextView[]{
                (TextView) view.findViewById(R.id.calbox7),
                (TextView) view.findViewById(R.id.calbox6),
                (TextView) view.findViewById(R.id.calbox5),
                (TextView) view.findViewById(R.id.calbox4),
                (TextView) view.findViewById(R.id.calbox3),
                (TextView) view.findViewById(R.id.calbox2),
                (TextView) view.findViewById(R.id.calbox1),
                (TextView) view.findViewById(R.id.calbox14),
                (TextView) view.findViewById(R.id.calbox13),
                (TextView) view.findViewById(R.id.calbox12),
                (TextView) view.findViewById(R.id.calbox11),
                (TextView) view.findViewById(R.id.calbox10),
                (TextView) view.findViewById(R.id.calbox9),
                (TextView) view.findViewById(R.id.calbox8),
                (TextView) view.findViewById(R.id.calbox21),
                (TextView) view.findViewById(R.id.calbox20),
                (TextView) view.findViewById(R.id.calbox19),
                (TextView) view.findViewById(R.id.calbox18),
                (TextView) view.findViewById(R.id.calbox17),
                (TextView) view.findViewById(R.id.calbox16),
                (TextView) view.findViewById(R.id.calbox15),
                (TextView) view.findViewById(R.id.calbox28),
                (TextView) view.findViewById(R.id.calbox27),
                (TextView) view.findViewById(R.id.calbox26),
                (TextView) view.findViewById(R.id.calbox25),
                (TextView) view.findViewById(R.id.calbox24),
                (TextView) view.findViewById(R.id.calbox23),
                (TextView) view.findViewById(R.id.calbox22),
                (TextView) view.findViewById(R.id.calbox35),
                (TextView) view.findViewById(R.id.calbox34),
                (TextView) view.findViewById(R.id.calbox33),
                (TextView) view.findViewById(R.id.calbox32),
                (TextView) view.findViewById(R.id.calbox31),
                (TextView) view.findViewById(R.id.calbox30),
                (TextView) view.findViewById(R.id.calbox29),
                (TextView) view.findViewById(R.id.calbox42),
                (TextView) view.findViewById(R.id.calbox41),
                (TextView) view.findViewById(R.id.calbox40),
                (TextView) view.findViewById(R.id.calbox39),
                (TextView) view.findViewById(R.id.calbox38),
                (TextView) view.findViewById(R.id.calbox37),
                (TextView) view.findViewById(R.id.calbox36)
        };

        // Get week num view objects
        this.view_week = new TextView[]{
                (TextView) view.findViewById(R.id.week1),
                (TextView) view.findViewById(R.id.week2),
                (TextView) view.findViewById(R.id.week3),
                (TextView) view.findViewById(R.id.week4),
                (TextView) view.findViewById(R.id.week5),
                (TextView) view.findViewById(R.id.week6)
        };

        /*
        // Alternative way but not tested
        this.view_boxes = new TextView[42];
        for (int i = 0; i < 42; i++) {
            this.view_boxes[i] = (TextView) view.findViewById(
                context.getResources().getIdentifier("calbox" + (i + 1), "id", context.getPackageName())
            );
        }
        */
    }

    // Set calendar color
    private void setColor(int color) {
        // Save new color
        this.color = color;

        // Update Colors
        for(int i = this.view_colorable.length - 1; i >= 0; i--) {
            this.view_colorable[i].setTextColor(this.color);
        }
    }

    // Set date to show
    private void setDate(Calendar date) {
        this.year = date.get(Calendar.YEAR);
        this.month = date.get(Calendar.MONTH);

        Calendar now = Calendar.getInstance();
        this.day = -1;
        if (year == now.get(Calendar.YEAR) && month == now.get(Calendar.MONTH)) {
            this.day = now.get(Calendar.DAY_OF_MONTH);
        }
    }

    public void refresh(Calendar date, int color) {
        // Update colors
        this.setColor(color);
        // Refresh calendar
        this.refresh(date);
    }

    public void refresh(Calendar date) {
        // Update date
        this.setDate(date);

        //Load days with events
        this.loadCalendarEvents();

        // Refresh calendar
        this.refresh();
    }

    public void refresh() {
        // Change month name
        this.refreshMonthName();
        // Set year
        this.view_year.setText(String.valueOf(this.year));

        // Number to fill the boxes
        GregorianCalendar first = new GregorianCalendar(this.year, this.month, 1);
        int monthStart = first.get(Calendar.DAY_OF_WEEK) - 1;
        int week = first.get(Calendar.WEEK_OF_YEAR);
        if(this.isMondayFirst){
            monthStart = (monthStart - 1 < 0) ? 6 : monthStart - 1;
        }
        int monthDays = first.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Previous month
        Calendar previous = new GregorianCalendar(this.year, this.month, 1);
        previous.add(Calendar.MONTH, -1);
        int previousMonthDays = previous.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Change rotation for right to left languages
        TextView[] temp_view_boxes = (this.tranlations.isRtL()) ? this.view_boxes_rtl.clone() : this.view_boxes.clone();

        // Populate last month boxes
        for (int i = monthStart - 1; i >= 0; i--) {
            int currDay = previousMonthDays - monthStart + i + 1;
            //int prevMonth = this.month > 0 ? this.month - 1 : 11;
            //Log.i(Constants.TAG, "previous month day: " + currDay);
            temp_view_boxes[i].setText(String.valueOf(currDay));
            temp_view_boxes[i].setBackgroundResource(android.R.color.transparent);
            temp_view_boxes[i].setTextColor(Color.parseColor("#505050"));
        }
        // Populate month's boxes
        for (int i = 0; i < monthDays; i++) {
            final int thisDay = i + 1;
            temp_view_boxes[monthStart + i].setText(String.valueOf(thisDay));
            if (thisDay == this.day) {
                temp_view_boxes[monthStart + i].setTextColor(Color.parseColor("#000000"));
                temp_view_boxes[monthStart + i].setBackgroundResource(R.drawable.round_bg);
                ((GradientDrawable) temp_view_boxes[monthStart + i].getBackground()).setColor(this.color);
            }
            else {
                temp_view_boxes[monthStart + i].setTextColor(Color.parseColor("#FFFFFF"));
                temp_view_boxes[monthStart + i].setBackgroundResource(android.R.color.transparent);
            }

            if (eventsList != null) {
                if (eventsList.get(this.month).contains(thisDay)) {
                    //Log.i(Constants.TAG, "this month event: " + thisDay);
                    final int index = eventsList.get(this.month).indexOf(thisDay);

                    if (thisDay == this.day) {
                        LayerDrawable shape = (LayerDrawable) mContext.getResources().getDrawable(R.drawable.round_bg_event_today);
                        GradientDrawable circle = (GradientDrawable) shape.findDrawableByLayerId(R.id.outer_circle);
                        circle.setColor(this.color);
                        circle = (GradientDrawable) shape.findDrawableByLayerId(R.id.inner_circle);
                        circle.setColor(this.color);

                        temp_view_boxes[monthStart + i].setBackground(shape);
                    } else {
                        temp_view_boxes[monthStart + i].setBackgroundResource(R.drawable.round_bg_event);
                        ((GradientDrawable) temp_view_boxes[monthStart + i].getBackground()).setColor(this.color);
                    }

                    temp_view_boxes[monthStart + i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            toast(String.valueOf(thisDay) + "/" + String.valueOf(month + 1) + " - " + eventsHours.get(month).get(index) + "\n" + eventsTitle.get(month).get(index));
                        }
                    });
                } else
                    temp_view_boxes[monthStart + i].setOnClickListener(null);
            } else
                temp_view_boxes[monthStart + i].setOnClickListener(null);
        }
        // Populate next month's boxes
        for (int i = monthStart + monthDays; i < 42; i++) {
            int currDay = i - monthStart - monthDays + 1;
            //int nextMonth = this.month < 11 ? this.month + 1 : 0;
            //Log.i(Constants.TAG, "next month day: " + currDay);
            temp_view_boxes[i].setText(String.valueOf(currDay));
            temp_view_boxes[i].setBackgroundResource(android.R.color.transparent);
            temp_view_boxes[i].setTextColor(Color.parseColor("#505050"));
        }

        // Populate week boxes
        if (this.isMondayFirst)
            week--;
        for (int i = 0; i < 6; i++) {
            this.view_week[i].setText(String.valueOf(week+i));
        }
    }

    public void refreshMonthName() {
        // Set month name
        this.view_monthName.setText(this.tranlations.getMonths()[this.month]);
    }

    public void refreshDays() {
        // Get days translations
        String[] days = this.tranlations.getDays();
        int n = days.length;
        int offset = (this.isMondayFirst)? 1 : 0;
        // Change days names
        for (int i = 0 ; i < 7; i++) {
            this.view_colorable[i].setText(   (days[(offset + i) % n].length()>3) ? days[(offset + i) % n].substring(0, 3) : days[(offset + i) % n] );
        }
    }

    public APtranslations getTranlations() {
        return this.tranlations;
    }

    private void loadCalendarEvents() {

        // Load data
        String calendarEvents = Settings.System.getString(mContext.getContentResolver(), Constants.CALENDAR_DATA);

        List<List<Integer>> events = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            List<Integer> list = new ArrayList<>();
            events.add(list);
        }

        List<List<String>> titles = new ArrayList<>();
        List<List<String>> hours = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            List<String> list = new ArrayList<>();
            titles.add(list);
            List<String> list2 = new ArrayList<>();
            hours.add(list2);
        }

        if(calendarEvents!=null && !calendarEvents.isEmpty()) {
            try {
                // Check if correct form of JSON
                JSONObject json_data = new JSONObject(calendarEvents);

                // If there are events
                if (json_data.has("events")) {
                    int event_number = json_data.getJSONArray("events").length();

                    Calendar calendar = Calendar.getInstance();

                    // Get data
                    for (int i = 0; i < event_number; i++) {
                        JSONArray data = json_data.getJSONArray("events").getJSONArray(i);

                        //Log.i(Constants.TAG, "title: " + data.getString(0) + " \\ start: " + data.getString(2));
                        if (!data.getString(2).equals("") && !data.getString(2).equals("null")) {
                            calendar.setTimeInMillis(Long.parseLong(data.getString(2)));
                            if (calendar.get(Calendar.YEAR) >= this.year) {
                                // Save title
                                titles.get(calendar.get(Calendar.MONTH)).add(data.getString(0));
                                // Save hour (based on 12/24h)
                                hours.get(calendar.get(Calendar.MONTH)).add(
                                        (new SimpleDateFormat( (Settings.System.getString(mContext.getContentResolver(), "time_12_24").equals("24")?"HH:mm":"hh:mm a"), Locale.US)).format(calendar.getTime())
                                );
                                // Save day
                                events.get(calendar.get(Calendar.MONTH)).add(calendar.get(Calendar.DAY_OF_MONTH));
                                //Log.i(Constants.TAG, "month: " + calendar.get(Calendar.MONTH) + " \\ days: " + events.get(calendar.get(Calendar.MONTH)).toString());
                            }
                        }

                    }
                }
            } catch (JSONException e) {
                Log.e(Constants.TAG, e.getLocalizedMessage(), e);
            }
        }

        this.eventsList = events;
        this.eventsTitle = titles;
        this.eventsHours = hours;
    }

    // Toast wrapper
    private void toast(String message) {
        Toast toast = Toast.makeText(this.mContext, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }
}
