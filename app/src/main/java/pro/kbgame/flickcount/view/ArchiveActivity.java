package pro.kbgame.flickcount.view;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pro.kbgame.flickcount.R;
import pro.kbgame.flickcount.model.Flick;
import pro.kbgame.flickcount.repository.FlickRepository;

public class ArchiveActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private Date selectStartDate;
    private Date selectEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        recyclerView = findViewById(R.id.recyclerViewAllFlicks);

        initStartDate();
        initEndDate();

        testDateDisplaying();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        FlickRepository.getInstance().getAllFlicks(new FlickRepository.OnGetAllFlicksCallBack() {
            @Override
            public void onGetAllFlicks(ArrayList<Flick> allFlicks) {
                FlickAdapter flickAdapter = new FlickAdapter(allFlicks);
                recyclerView.setAdapter(flickAdapter);
            }
        });
    }

    public void onLblStartDateClick(View view){

    }

    public void onLblEndDateClick(View view) {
    }

/*    protected DatePickerDialog selectDateDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
    }*/


    private void initStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        selectStartDate = calendar.getTime();
    }

    private void initEndDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59, 59);

        calendar.set(Calendar.MILLISECOND, 0);

        selectEndDate = calendar.getTime();
    }

    private void testDateDisplaying(){
        TextView lblStartDate = findViewById(R.id.lblStartDate);
        TextView lblEndDate = findViewById(R.id.lblEndDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String startDate = sdf.format(selectStartDate);
        lblStartDate.setText(startDate);
        String endDate = sdf.format(selectEndDate);
        lblEndDate.setText(endDate);

    }

}
