package pro.kbgame.flickcount.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

import pro.kbgame.flickcount.R;

public class DateSelectActivity extends AppCompatActivity {
    private DatePicker datePickerStartDate;
    private DatePicker datePickerEndDate;
    Button buttonSetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_select);

        datePickerStartDate = findViewById(R.id.datePickerStartDate);
        datePickerEndDate = findViewById(R.id.datePickerEndDate);
        setMaxDateInDataPickers();
    }




    public void onButtonSetDateClick(View view) {
        collectDatesFromPickers();
    }

    private void setMaxDateInDataPickers(){
        Calendar calendar = Calendar.getInstance();
        datePickerStartDate.setMaxDate(calendar.getTimeInMillis());
        datePickerEndDate.setMaxDate(calendar.getTimeInMillis());
    }

    private void collectDatesFromPickers() {
        int startDay = datePickerStartDate.getDayOfMonth();
        int startMonth = datePickerStartDate.getMonth();
        int startYear = datePickerStartDate.getYear();
        int endDay = datePickerEndDate.getDayOfMonth();
        int endMonth = datePickerEndDate.getMonth();
        int endYear = datePickerEndDate.getYear();
    }


}
