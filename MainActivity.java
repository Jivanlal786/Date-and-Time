package com.shawnlin.numberpicker.sample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.shawnlin.numberpicker.NumberPicker;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "NumberPicker";
    android.widget.NumberPicker year,month,date,hour,minute;
    Calendar c = Calendar.getInstance();
    int daysInMonth, curryear, currmonth, currdate, totaldays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        final NumberPicker numberPicker = findViewById(R.id.number_picker);
        year = findViewById(R.id.year);
        month = findViewById(R.id.month);
        date = findViewById(R.id.date);
        hour = findViewById(R.id.hour);
        minute = findViewById(R.id.minute);

        int years = Calendar.getInstance().get(Calendar.YEAR);
        int months = Calendar.getInstance().get(Calendar.MONTH);
        int dates = Calendar.getInstance().get(Calendar.DATE);
        int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minutes = Calendar.getInstance().get(Calendar.MINUTE);

        System.out.println("QQQQQyear : "+years);
        this.year.setMaxValue(Calendar.getInstance().get(Calendar.YEAR));
        this.year.setMinValue(Calendar.getInstance().get(Calendar.YEAR)-10);

        this.year.setWrapSelectorWheel(false);
        setSupportActionBar(toolbar);

        year.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(android.widget.NumberPicker picker, int oldVal, int newVal) {
                curryear = newVal;

                if (newVal == years){
                    month.setMaxValue(months+1);
                    month.setMinValue(c.getActualMinimum(Calendar.MONTH)+1);
                }else {
                    month.setMaxValue(c.getActualMaximum(Calendar.MONTH)+1);
                    month.setMinValue(c.getActualMinimum(Calendar.MONTH)+1);
                }

                month.setWrapSelectorWheel(false);
            }
        });


        month.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(android.widget.NumberPicker picker, int oldVal, int newVal) {



// Get the number of days in that month
                currmonth = newVal;
                YearMonth yearMonthObject = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    yearMonthObject = YearMonth.of(curryear, currmonth);
                    int daysInMonth = yearMonthObject.lengthOfMonth(); //28
                    System.out.println("QQQQQQdays : "+daysInMonth);
                    totaldays = daysInMonth;
                    date.setMaxValue(totaldays);
                    date.setMinValue(c.getActualMinimum(Calendar.DAY_OF_MONTH));
                    date.setWrapSelectorWheel(false);
                }else {

                    int iDay = 1;

// Create a calendar object and set year and month
                    Calendar mycal = new GregorianCalendar(curryear, currmonth, iDay);

// Get the number of days in that month
                    daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 2
                    totaldays = daysInMonth;

                    date.setMaxValue(totaldays);
                    date.setMinValue(c.getActualMinimum(Calendar.DAY_OF_MONTH));
                    date.setWrapSelectorWheel(false);

                }
            }
        });
        date.setOnValueChangedListener(new android.widget.NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(android.widget.NumberPicker picker, int oldVal, int newVal) {
                if (newVal > c.get(Calendar.DATE)){

                }
            }
        });



        hour.setMaxValue(c.getActualMaximum(Calendar.HOUR_OF_DAY));
        hour.setMinValue(c.getActualMinimum(Calendar.HOUR_OF_DAY));
        hour.setWrapSelectorWheel(false);

        minute.setMaxValue(c.getActualMaximum(Calendar.MINUTE));
        minute.setMinValue(c.getActualMinimum(Calendar.MINUTE));
        minute.setWrapSelectorWheel(false);



        /*// Set divider color
        numberPicker.setDividerColor(ContextCompat.getColor(this, R.color.colorPrimary));
        numberPicker.setDividerColorResource(R.color.colorPrimary);

        // Set formatter
        numberPicker.setFormatter(getString(R.string.number_picker_formatter));
        numberPicker.setFormatter(R.string.number_picker_formatter);

        // Set selected text color
        numberPicker.setSelectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        numberPicker.setSelectedTextColorResource(R.color.colorPrimary);

        // Set selected text size
        numberPicker.setSelectedTextSize(getResources().getDimension(R.dimen.selected_text_size));
        numberPicker.setSelectedTextSize(R.dimen.selected_text_size);

        // Set selected typeface
        numberPicker.setSelectedTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        numberPicker.setSelectedTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        numberPicker.setSelectedTypeface(getString(R.string.roboto_light));
        numberPicker.setSelectedTypeface(R.string.roboto_light, Typeface.NORMAL);
        numberPicker.setSelectedTypeface(R.string.roboto_light);

        // Set text color
        numberPicker.setTextColor(ContextCompat.getColor(this, R.color.dark_grey));
        numberPicker.setTextColorResource(R.color.dark_grey);

        // Set text size
        numberPicker.setTextSize(getResources().getDimension(R.dimen.text_size));
        numberPicker.setTextSize(R.dimen.text_size);

        // Set typeface
        numberPicker.setTypeface(Typeface.create(getString(R.string.roboto_light), Typeface.NORMAL));
        numberPicker.setTypeface(getString(R.string.roboto_light), Typeface.NORMAL);
        numberPicker.setTypeface(getString(R.string.roboto_light));
        numberPicker.setTypeface(R.string.roboto_light, Typeface.NORMAL);
        numberPicker.setTypeface(R.string.roboto_light);

        // Set value
        numberPicker.setMaxValue(59);
        numberPicker.setMinValue(0);
        numberPicker.setValue(3);

        // Set string values
//        String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
//        numberPicker.setMinValue(1);
//        numberPicker.setMaxValue(data.length);
//        numberPicker.setDisplayedValues(data);

        // Set fading edge enabled
        numberPicker.setFadingEdgeEnabled(true);

        // Set scroller enabled
        numberPicker.setScrollerEnabled(true);

        // Set wrap selector wheel
        numberPicker.setWrapSelectorWheel(true);

        // Set accessibility description enabled
        numberPicker.setAccessibilityDescriptionEnabled(true);

        // OnClickListener
        numberPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on current value");
            }
        });

        // OnValueChangeListener
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.d(TAG, String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal));
            }
        });

        // OnScrollListener
        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker picker, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    Log.d(TAG, String.format(Locale.US, "newVal: %d", picker.getValue()));
                }
            }
        });*/
    }

}
