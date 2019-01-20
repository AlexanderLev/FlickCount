package pro.kbgame.flickcount.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;


import java.util.Calendar;

import pro.kbgame.flickcount.R;

public class DateSelectActivity extends AppCompatActivity {
    private DatePicker datePickerStartDate;
    private DatePicker datePickerEndDate;
    private int clickCounter;
    Button buttonSetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);

        datePickerStartDate = findViewById(R.id.datePickerStartDate);
        datePickerEndDate = findViewById(R.id.datePickerEndDate);
        setMaxDateInDataPickers();
    }



    private void setMaxDateInDataPickers(){
        Calendar calendar = Calendar.getInstance();
        datePickerStartDate.setMaxDate(calendar.getTimeInMillis());
        datePickerEndDate.setMaxDate(calendar.getTimeInMillis());
    }



}
