package com.dinodevs.pacecalendarwidget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Timeline extends Activity {

    // Activity variables
    private Context mContext;
    private Activity activity;

    private View mView;
    private ListView lv;
    private TextView time;
    private ImageView refresh;
    private int current_color;

    private ArrayList<HashMap<String, String>> eventsList;

    private String header_pattern;
    private String time_pattern;
    private static String toastMsg;

    private static final String TITLE = "title";
    private static final String SUBTITLE = "subtitle";
    private static final String DOT = "dot";

    private static String calendarEvents;
    private static APsettings settings;

    // Set up
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Save Activity variables
        this.mContext = this;
        activity = this;
        setContentView(R.layout.widget_timeline);
        this.mView = this.findViewById(android.R.id.content);

        settings = new APsettings(Constants.TAG, mContext);

        Intent myIntent = getIntent(); // gets the previously created intent
        this.current_color = myIntent.getIntExtra("color", 0);

        // Initialize variables
        Log.d(Constants.TAG, "Timeline: Starting...");
        this.init();

        // Attach event listeners
        Log.d(Constants.TAG, "Timeline: Attaching listeners...");
        this.initListeners();

        // Finish
        Log.d(Constants.TAG, "Timeline: Done...");
    }

    @Override
    public void onDestroy() {
        Log.d(Constants.TAG, "Timeline onDestroy");
        super.onDestroy();
    }

    // Initialize
    private void init() {

        boolean is24h = Settings.System.getString(mContext.getContentResolver(), Constants.TIME_FORMAT_SETTING).equals(Constants.TIME_FORMAT_SETTING_DEFAULT);
        Log.d(Constants.TAG, "Timeline init is24h: " + is24h);

        if (is24h) {
            header_pattern = Constants.HEADER_PATTERN_24H;
            time_pattern = Constants.TIME_PATTERN_24H;
        } else {
            header_pattern = Constants.HEADER_PATTERN_12H;
            time_pattern = Constants.TIME_PATTERN_12H;
        }

        // Show Time/Date
        time = this.mView.findViewById(R.id.time);
        refresh_time();


        // Refresh button
        refresh = this.mView.findViewById(R.id.refresh);

        // Calendar Events Data
        eventsList = new ArrayList<>();
        lv = this.mView.findViewById(R.id.list);
        loadCalendarEvents();
    }

    // Attach listeners
    @SuppressLint("ClickableViewAccessibility")
    private void initListeners(){

        // About button event
        /*
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh_time();
                Timeline.this.toast("Calendar Widget v" + Timeline.this.version + " by GreatApo, LFOM & DarkThanos");
            }
        });

        // Refresh events
        time.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                refresh_time();
                loadiCalData();
                return true;
            }
        });
        */

        // Refresh events
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh_time();
                loadiCalData();
            }
        });

        // Scroll to top
        TextView top = this.mView.findViewById(R.id.backToTop);
        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView list = Timeline.this.mView.findViewById(R.id.list);
                list.setSelectionAfterHeaderView();
                //Timeline.this.toast("▲ Top");
            }
        });

    }

    private void refresh_time(){
        TextView time = this.mView.findViewById(R.id.time);
        time.setText(dateToString(Calendar.getInstance(), header_pattern));
    }

    private void loadCalendarEvents() {
        eventsList = new ArrayList<>();
        long next_event = 0;

        // Load data
        calendarEvents = Settings.System.getString(mContext.getContentResolver(), Constants.CALENDAR_DATA);

        // Default when undefined/empty
        if (calendarEvents == null || calendarEvents.isEmpty()) {
            calendarEvents = "{\"events\":[]}";
            Timeline.this.toast("Empty or null events data!", true);
        }

        // Read Data
        try {
            // Check if correct form of JSON
            JSONObject json_data = new JSONObject(calendarEvents);

            // If there are events
            if (json_data.has(Constants.EVENTS_DATA)) {
                int event_number = json_data.getJSONArray(Constants.EVENTS_DATA).length();

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.MINUTE,-10); // Show only future events + 10 minutes old
                long current_time = calendar.getTimeInMillis();
                String current_loop_date = "";

                // Get data
                for(int i=0; i<event_number; i++) {
                    JSONArray data = json_data.getJSONArray(Constants.EVENTS_DATA).getJSONArray(i);

                    HashMap<String, String> event = new HashMap<>();
                    event.put(DOT, mContext.getResources().getString(R.string.bull) );

                    // adding each child node to HashMap key => value
                    event.put(TITLE, data.getString(0));
                    //event.put("description", data.getString(1));
                    //event.put("start", data.getString(2));
                    //event.put("end", data.getString(3));
                    //event.put("location", data.getString(4));
                    //event.put("account", data.getString(5));

                    // Get event "start" data
                    String start;
                    if(!data.getString(2).equals("") && !data.getString(2).equals("null")) {
                        calendar.setTimeInMillis(Long.parseLong(data.getString(2)));

                        if(current_time > calendar.getTimeInMillis()){
                            // Event expired, go to next
                            continue;
                        }

                        // Mark first next event
                        if( next_event == 0 )
                            next_event = calendar.getTimeInMillis();// TODO not used

                        start = dateToString(calendar, time_pattern);

                        // Insert day separator, or not :P
                        if( !current_loop_date.equals(dateToString( calendar,Constants.ELEMENT_PATTERN )) ){
                            current_loop_date = dateToString(calendar, Constants.ELEMENT_PATTERN);

                            HashMap<String, String> date_elem = new HashMap<>();
                            date_elem.put(TITLE, "");
                            date_elem.put(SUBTITLE, (current_loop_date.equals(dateToString(Calendar.getInstance(), Constants.ELEMENT_PATTERN)))?getString(R.string.today):current_loop_date );
                            date_elem.put(DOT, "" );
                            eventsList.add(date_elem);
                        }
                    }else{
                        // Event has no date, go to next
                        continue;
                    }

                    // Get event "end" data
                    String end = "";

                    // All day events
                    if((start.startsWith("00") || start.startsWith("12")) && ("null".equals(data.getString(3)) || data.getString(3).isEmpty())) {
                        start = getString(R.string.all_day);
                    }else{
                        if(!data.getString(3).equals("") && !data.getString(3).equals("null")) {
                            calendar.setTimeInMillis(Long.parseLong(data.getString(3)));
                            end = " - "+ dateToString(calendar, time_pattern);
                        }
                    }

                    // Get location data
                    String location = "";
                    if(!data.getString(4).equals("") && !data.getString(4).equals("null"))
                        location = "\n@ "+data.getString(4);

                    event.put(SUBTITLE, start + end + location );

                    // Add event to events list
                    eventsList.add(event);
                }
            }else{
                Timeline.this.toast("No events data in JSON.", true);
            }
        } catch (JSONException e) {
            Timeline.this.toast("Corrupted data!", true);
        }

        // No events message
        if(eventsList.isEmpty()){
            HashMap<String, String> elem = new HashMap<>();
            elem.put("title", "\n"+mContext.getResources().getString(R.string.no_events));
            elem.put("subtitle", mContext.getResources().getString(R.string.no_events_description));
            elem.put("dot", "" );
            eventsList.add(elem);
        }

        ListAdapter adapter = new SimpleAdapter(mContext, eventsList, R.layout.list_item, new String[]{"title", "subtitle", "dot"}, new int[]{R.id.title, R.id.description, R.id.dot}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = convertView;
                if(v== null){
                    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v=vi.inflate(R.layout.list_item, null);
                }

                TextView title = v.findViewById(R.id.title);
                TextView subtitle = v.findViewById(R.id.description);
                TextView dot = v.findViewById(R.id.dot);
                LinearLayout header = v.findViewById(R.id.header);

                title.setText(eventsList.get(position).get("title"));
                subtitle.setText(eventsList.get(position).get("subtitle"));
                dot.setText(eventsList.get(position).get("dot"));

                if(Timeline.this.current_color!=0) {
                    dot.setTextColor(Timeline.this.current_color);

                    // Day separator
                    if(eventsList.get(position).get("dot").isEmpty() && eventsList.get(position).get("title").isEmpty())
                        subtitle.setTextColor(Timeline.this.current_color);
                    else
                        subtitle.setTextColor(getResources().getColor(R.color.description));
                }

                return v;
            }
        };
        lv.setAdapter(adapter);
    }

    private void loadiCalData() {
        final String icalURL = FilesUtil.getiCalURL(mContext);
        new Thread(new Runnable() {
            @Override
            public void run() {
                    toastMsg = "Getting iCal data,\nplease wait...";
                    activity.runOnUiThread(showToast);
                    if (iCalSupport.checkICSFile(mContext, icalURL)) {
                        calendarEvents = iCalSupport.getICSCalendarEvents(mContext);
                    } else {
                        toastMsg = "\niCal data not found.\nPlease write/place your ICS feed URL/file at:\n/sdcard/Android/data/com.dinodevs.pacecalendarwidget/files/pacecalendar.txt";
                        activity.runOnUiThread(showToast);
                    }
                    if (calendarEvents == null) {
                        toastMsg = "No new events!";
                        activity.runOnUiThread(showToast);
                        settings.set(Constants.PREF_CALENDAR_URL, "");
                    } else {
                        toastMsg = "Refreshing events...";
                        activity.runOnUiThread(showToast);
                        lv.post(loadEvents);
                    }
            }
        }).start();
    }

    private final Runnable showToast = new Runnable() {
        public void run() {
            toast(toastMsg, false);
        }
    };

    private final Runnable loadEvents = new Runnable() {
        public void run() {
            loadCalendarEvents();
        }
    };

    // Toast wrapper
    private void toast (String message, boolean shortTime) {
        Toast toast;
        if (shortTime)
            toast = Toast.makeText(this.mContext, message, Toast.LENGTH_SHORT);
        else
            toast = Toast.makeText(this.mContext, message, Toast.LENGTH_LONG);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    // Convert a date to format
    private String dateToString (Calendar date) {
        return (new SimpleDateFormat(Constants.DATE_PATTERN, Locale.US)).format(date.getTime());
    }
    private String dateToString (Calendar date, String pattern) {
        return (new SimpleDateFormat(pattern, Locale.US)).format(date.getTime());
    }

}

