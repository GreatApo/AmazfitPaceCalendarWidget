package com.dinodevs.pacecalendarwidget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import clc.sliteplugin.flowboard.AbstractPlugin;
import clc.sliteplugin.flowboard.ISpringBoardHostStub;

public class widget extends AbstractPlugin {

    // Version
    public String version = "n/a";
    // Errors for debugging
    private String errors;


    // Activity variables
    private boolean isActive = false;
    private Context mContext;
    private View mView;

    // Calendar vars
    private Vibrator vibe;
    private Calendar shown_date;
    private boolean shown_year;
    private boolean show_week;
    public boolean doIvibrate;
    private int current_color;
    private TextView current_color_element;

    private APcalendar apcalendar;
    private APtranslations aptranslations;
    private APsettings settings;


    // Set up the widget's layout
    @Override
    public View getView(Context paramContext) {
        // Save Activity variables
        this.mContext = paramContext;
        this.mView = LayoutInflater.from(paramContext).inflate(R.layout.widget_calendar, null);

        Log.d(Constants.TAG, "Starting calendar...");

        // Initialize variables
        this.init();

        Log.d(Constants.TAG, "Attaching listeners...");
        // Attach event listeners
        this.initListeners();

        Log.d(Constants.TAG, "Done...");
        return this.mView;
    }

    // Initialize widget variables
    private void init() {
        // Get widget version number
        try {
            PackageInfo pInfo = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
            this.version = pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        // Set date to current
        this.shown_date = Calendar.getInstance();

        // Init vibration service
        this.vibe = (Vibrator) this.mContext.getSystemService(Context.VIBRATOR_SERVICE);

        // Init variables
        this.apcalendar = null;
        this.aptranslations = null;
        this.current_color_element = null;
        this.settings = null;
        this.errors = "";

        // Load settings
        this.settings = new APsettings(Constants.TAG, mContext);

        // Set default settings
        this.shown_year = this.settings.get("show_year", true);
        this.show_week = this.settings.get("show_week", false);
        this.changeColorByName(this.settings.get("color", "orange"));
        this.doIvibrate = this.settings.get("vibrate", true);

        // Init Calendar
        try {
            // Create calendar
            this.apcalendar = new APcalendar(this.mView, this.mContext, this.shown_date, this.current_color);
        } catch (Exception e) {
            e.printStackTrace();
            this.errors += e.getMessage();
        }

        // Get translations
        this.aptranslations = this.apcalendar.getTranlations();

        // Check lang
        String saved_lang = this.settings.get("lang", "en");
        if (!saved_lang.equals(this.aptranslations.getCode())) {
            this.aptranslations.setLang(saved_lang);
            this.updateLanguage();
        }

        // If monday first
        if (this.settings.get("monday_first", false)) {
            this.changeMondayFirst(true);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initListeners(){
        // About / Errors button event
        TextView about = this.mView.findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.toast("Calendar Widget\n v" + widget.this.version + "\n\nby GreatApo,\nLFOM & DarkThanos" + (widget.this.errors.length() > 0 ? " Errors: " + widget.this.errors : ""));
                widget.this.vibrate();
            }
        });

        APtouch handler = new APtouch() {
            @Override
            public boolean onSwipeUp() {
                widget.this.changeMonth(1);
                widget.this.vibrate();
                return true;
            }
            @Override
            public boolean onSwipeDown() {
                widget.this.changeMonth(-1);
                widget.this.vibrate();
                return true;
            }

            @Override
            public boolean onDoubleClick() {// Not onLongClick
            //public boolean onLongClick() {
                if(widget.this.isActive) {
                    // Open settings
                    // Log.d(Constants.TAG, "Pop settings");
                    widget.this.mView.findViewById(R.id.settings_box).setVisibility(View.VISIBLE);
                    widget.this.vibrate(50);
                }
                return true;
            }
        };
        ConstraintLayout layout = this.mView.findViewById(R.id.container);
        layout.setOnTouchListener(handler.listen());

        // Open Settings button
        ImageView open_settings_button = this.mView.findViewById(R.id.settings_icon);
        open_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.mView.findViewById(R.id.settings_box).setVisibility(View.VISIBLE);
                widget.this.vibrate(50);
            }
        });

        // Close Settings button
        TextView close_settings_button = this.mView.findViewById(R.id.close_settings);
        close_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.mView.findViewById(R.id.settings_box).setVisibility(View.GONE);
                widget.this.vibrate();
            }
        });

        // Change Color Buttons
        View.OnClickListener onColorChange = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.onColorChangeBtn(v);
            }
        };
        this.mView.findViewById(R.id.color1).setOnClickListener(onColorChange);
        this.mView.findViewById(R.id.color2).setOnClickListener(onColorChange);
        this.mView.findViewById(R.id.color3).setOnClickListener(onColorChange);
        this.mView.findViewById(R.id.color4).setOnClickListener(onColorChange);
        this.mView.findViewById(R.id.color5).setOnClickListener(onColorChange);
        this.mView.findViewById(R.id.color6).setOnClickListener(onColorChange);

        // Next Translation
        this.mView.findViewById(R.id.language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.rotateLanguage();
                widget.this.vibrate();
            }
        });

        // Year switch
        CheckBox year_checkbox = this.mView.findViewById(R.id.year_switch);
        year_checkbox.setChecked(this.shown_year);
        this.changeYearVisibility(this.shown_year);
        year_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                widget.this.changeYearVisibility(isChecked);
                widget.this.vibrate();
            }
        });

        // Monday switch (first day of the week)
        boolean isMondayFirst = this.settings.get("monday_first", false);
        CheckBox monday_first_checkbox = this.mView.findViewById(R.id.monday_switch);
        monday_first_checkbox.setChecked(isMondayFirst);
        monday_first_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                widget.this.changeMondayFirst(isChecked);
                widget.this.vibrate();
            }
        });

        // Vibration switch
        CheckBox vibrate_checkbox = this.mView.findViewById(R.id.vibrate_switch);
        vibrate_checkbox.setChecked(this.doIvibrate);
        vibrate_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                widget.this.changeVibration(isChecked);
                widget.this.vibrate();
            }
        });

        // Week switch (show week number)
        CheckBox week_checkbox = this.mView.findViewById(R.id.week_switch);
        week_checkbox.setChecked(this.show_week);
        this.changeWeekVisibility(this.show_week);
        week_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                widget.this.changeWeekVisibility(isChecked);
                widget.this.vibrate();
            }
        });

        // Refresh current date
        ImageView refresh_button = this.mView.findViewById(R.id.refresh);
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Refresh View
                widget.this.refreshView();
                // Show date
                widget.this.toast(widget.this.dateToString(Calendar.getInstance()));
                widget.this.vibrate();
            }
        });

        // Prev Month button
        TextView previous_month_button = this.mView.findViewById(R.id.arrow_up);
        previous_month_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.changeMonth(-1);
                widget.this.vibrate();
            }
        });

        // Next Month button
        TextView next_month_button = this.mView.findViewById(R.id.arrow_down);
        next_month_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.changeMonth(1);
                widget.this.vibrate();
            }
        });

        //Timeline
        ImageView events = this.mView.findViewById(R.id.events_icon);
        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.this.vibrate();
                toast("Loading events...");
                final Intent timelineIntent = new Intent(mContext, Timeline.class);
                timelineIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                timelineIntent.putExtra("color",widget.this.current_color);
                mContext.startActivity(timelineIntent);
            }
        });
    }


    // Change widget color
    private void changeColor(int id, int color) {
        // Uncheck old color
        if (this.current_color_element != null)
            this.current_color_element.setText(" ");

        // Save and check new color
        this.current_color = this.mContext.getResources().getColor(color);
        this.current_color_element = this.mView.findViewById(id);
        this.current_color_element.setText("✔");

        // Change close settings color
        ((GradientDrawable) this.mView.findViewById(R.id.close_settings).getBackground()).setColor(current_color);

        // Reload calendar with the new color
        if (this.apcalendar != null)
            this.apcalendar.refresh(this.shown_date, this.current_color);

        // Save settings
        this.settings.set("color", this.parseColorToString(color));
    }
    private void changeColorByName(String color_name) {
        switch (color_name) {
            case "red":
                this.changeColor(R.id.color1, R.color.basecolor1);
                return;
            case "blue":
                this.changeColor(R.id.color2, R.color.basecolor2);
                return;
            case "green":
                this.changeColor(R.id.color3, R.color.basecolor3);
                return;
            case "purple":
                this.changeColor(R.id.color4, R.color.basecolor4);
                return;
            case "orange":
                this.changeColor(R.id.color5, R.color.basecolor5);
                return;
            case "yellow":
                this.changeColor(R.id.color6, R.color.basecolor6);
                return;
        }
        this.changeColor(R.id.color5, R.color.basecolor5);
    }

    private String parseColorToString(int color) {
        switch (color) {
            case R.color.basecolor1:
                return "red";
            case R.color.basecolor2:
                return "blue";
            case R.color.basecolor3:
                return "green";
            case R.color.basecolor4:
                return "purple";
            case R.color.basecolor5:
                return "orange";
            case R.color.basecolor6:
                return "yellow";
        }
        return "orange";
    }

    // Change month by n
    private void changeMonth(int n) {
        // Move n months
        this.shown_date.add(Calendar.MONTH, n);
        // Show month
        this.apcalendar.refresh(this.shown_date);
    }

    private void onColorChangeBtn(View v) {
        // Find caller and change to the appropriate color
        switch (v.getId()) {
            case R.id.color1: this.changeColor(R.id.color1, R.color.basecolor1); break;
            case R.id.color2: this.changeColor(R.id.color2, R.color.basecolor2); break;
            case R.id.color3: this.changeColor(R.id.color3, R.color.basecolor3); break;
            case R.id.color4: this.changeColor(R.id.color4, R.color.basecolor4); break;
            case R.id.color5: this.changeColor(R.id.color5, R.color.basecolor5); break;
            case R.id.color6: this.changeColor(R.id.color6, R.color.basecolor6); break;
        }
        this.vibrate();
    }

    // Show/Hide year
    private void changeYearVisibility(boolean visible){
        // If show year
        if (visible) {
            this.shown_year = true;
            this.mView.findViewById(R.id.textYear).setVisibility(View.VISIBLE);
        }
        // If hide year
        else {
            this.shown_year = false;
            this.mView.findViewById(R.id.textYear).setVisibility(View.GONE);
        }

        // Save setting
        this.settings.set("show_year", this.shown_year);
    }

    // Show/Hide week number
    private void changeWeekVisibility(boolean visible){
        // If show week
        if (visible) {
            this.show_week = true;
            this.mView.findViewById(R.id.week1).setVisibility(View.VISIBLE);
            this.mView.findViewById(R.id.week2).setVisibility(View.VISIBLE);
            this.mView.findViewById(R.id.week3).setVisibility(View.VISIBLE);
            this.mView.findViewById(R.id.week4).setVisibility(View.VISIBLE);
            this.mView.findViewById(R.id.week5).setVisibility(View.VISIBLE);
            this.mView.findViewById(R.id.week6).setVisibility(View.VISIBLE);
        }
        // If hide week
        else {
            this.show_week = false;
            this.mView.findViewById(R.id.week1).setVisibility(View.GONE);
            this.mView.findViewById(R.id.week2).setVisibility(View.GONE);
            this.mView.findViewById(R.id.week3).setVisibility(View.GONE);
            this.mView.findViewById(R.id.week4).setVisibility(View.GONE);
            this.mView.findViewById(R.id.week5).setVisibility(View.GONE);
            this.mView.findViewById(R.id.week6).setVisibility(View.GONE);
        }

        // Save setting
        this.settings.set("show_week", this.show_week);
    }

    // Sunday/Monday first day of the week
    private void changeMondayFirst(boolean mondayFirst){
        this.apcalendar.isMondayFirst = mondayFirst;
        this.apcalendar.refresh();
        this.apcalendar.refreshDays();

        // Save setting
        this.settings.set("monday_first", mondayFirst);
    }


    // Vibration
    private void changeVibration(boolean vibrate){
        this.doIvibrate = vibrate;

        // Save setting
        this.settings.set("vibrate", vibrate);
    }

    // Change to the next language
    private void rotateLanguage() {
        // Load next lang
        this.aptranslations.nextLang();
        // Show ui in new language
        this.updateLanguage();
        // Save lang
        this.settings.set("lang", this.aptranslations.getCode());
    }

    private void updateLanguage() {
        // Update language name text
        ((TextView) this.mView.findViewById(R.id.language)).setText(
                this.aptranslations.getName()
        );

        TextView[] view_other = new TextView[]{
                this.mView.findViewById(R.id.select_color),
                this.mView.findViewById(R.id.year_switch),
                this.mView.findViewById(R.id.monday_switch),
                this.mView.findViewById(R.id.vibrate_switch),
                this.mView.findViewById(R.id.week_switch)
        };

        String[] other = this.aptranslations.getOther();
        for (int i = view_other.length - 1; i >= 0; i--) {
            view_other[i].setText(other[i]);
        }

        // Reload day names
        this.apcalendar.refreshDays();
        // Refresh calendar UI (month name etc)
        this.apcalendar.refresh();
        //this.apcalendar.refreshMonthName();
    }


    // Refresh Calendar (set it to current month)
    private void refreshView() {
        // Get current date
        Calendar now = Calendar.getInstance();
        this.shown_date = now;

        // Refresh calendar
        this.apcalendar.refresh(now);
    }


    // Vibrator wrappers
    private void vibrate () {
        if(doIvibrate)
            this.vibrate(10);
    }
    private void vibrate (long milliseconds) {
        if(doIvibrate)
            this.vibe.vibrate(milliseconds);
    }

    // Toast wrapper
    private void toast (String message) {
        Toast toast;
        toast = Toast.makeText(this.mContext, message, Toast.LENGTH_SHORT);
        TextView v = toast.getView().findViewById(android.R.id.message);
        if( v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    // Convert a date to dd/MM/yyyy format
    private String dateToString (Calendar date) {
        return (new SimpleDateFormat("dd/MM/yyyy", Locale.US)).format(date.getTime());
    }



    /*
     * Widget active/deactivate state management
     */

    // On widget show
    private void onShow() {
        // If view loaded (and was inactive)
        if (this.mView != null && !this.isActive) {
            String now = this.dateToString(Calendar.getInstance());
            String shown = this.dateToString(this.shown_date);
            // If not the correct view
            if (!shown.equals(now)) {
                // Refresh the view
                this.refreshView();
            }
        }

        // Save state
        this.isActive = true;
    }

    // On widget hide
    private void onHide() {
        // Save state
        this.isActive = false;
    }


    // Events for widget hide
    @Override
    public void onInactive(Bundle paramBundle) {
        super.onInactive(paramBundle);
        this.onHide();
    }
    @Override
    public void onPause() {
        super.onPause();
        this.onHide();
    }
    @Override
    public void onStop() {
        super.onStop();
        this.onHide();
    }

    // Events for widget show
    @Override
    public void onActive(Bundle paramBundle) {
        super.onActive(paramBundle);
        this.onShow();
    }
    @Override
    public void onResume() {
        super.onResume();
        this.onShow();
    }




    /*
     * Below where are unchanged functions that the widget should have
     */

    // Return the icon for this page, used when the page is disabled in the app list. In this case, the launcher icon is used
    @Override
    public Bitmap getWidgetIcon(Context paramContext) {
        return ((BitmapDrawable) this.mContext.getResources().getDrawable(R.mipmap.ic_launcher)).getBitmap();
    }


    // Return the launcher intent for this page. This might be used for the launcher as well when the page is disabled?
    @Override
    public Intent getWidgetIntent() {
        //Intent localIntent = new Intent();
        //localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        //localIntent.setAction("android.intent.action.MAIN");
        //localIntent.addCategory("android.intent.category.LAUNCHER");
        //localIntent.setComponent(new ComponentName(this.mContext.getPackageName(), "com.huami.watch.deskclock.countdown.CountdownListActivity"));
        return new Intent();
    }


    // Return the title for this page, used when the page is disabled in the app list. In this case, the app name is used
    @Override
    public String getWidgetTitle(Context paramContext) {
        return this.mContext.getResources().getString(R.string.calendar_name);
    }


    // Save springboard host
    private ISpringBoardHostStub host = null;

    // Returns the springboard host
    public ISpringBoardHostStub getHost() {
        return this.host;
    }

    // Called when the page is loading and being bound to the host
    @Override
    public void onBindHost(ISpringBoardHostStub paramISpringBoardHostStub) {
        // Log.d(Constants.TAG, "onBindHost");
        //Store host
        this.host = paramISpringBoardHostStub;
    }


    // Not sure what this does, can't find it being used anywhere. Best leave it alone
    @Override
    public void onReceiveDataFromProvider(int paramInt, Bundle paramBundle) {
        super.onReceiveDataFromProvider(paramInt, paramBundle);
    }


    // Called when the page is destroyed completely (in app mode). Same as the onDestroy method of an activity
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
