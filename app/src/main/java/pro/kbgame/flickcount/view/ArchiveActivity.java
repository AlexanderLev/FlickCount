package pro.kbgame.flickcount.view;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
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
    private ArrayList<Flick> currentFlicks = new ArrayList<Flick>();
    FlickAdapter flickAdapter;

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
        flickAdapter = new FlickAdapter(currentFlicks);
        recyclerView.setAdapter(flickAdapter);

        filtration();

    }

    public void onLblStartDateClick(View view) {
        selectDateDialog(selectStartDate, new OnGetSelectedDateCallBack() {
            @Override
            public void onGetSelectedDate(Date selectedDate) {
                selectStartDate = selectedDate;
            }
        });

    }

    public void onLblEndDateClick(View view) {
        selectDateDialog(selectEndDate, new OnGetSelectedDateCallBack() {
            @Override
            public void onGetSelectedDate(Date selectedDate) {
                selectEndDate = selectedDate;
            }
        });

    }


    protected void selectDateDialog(final Date initDate, final OnGetSelectedDateCallBack callBack) {

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(initDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month,day);
                Date selectedDate = calendar.getTime();
                callBack.onGetSelectedDate(selectedDate);

            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, listener, year, month, day);

        datePickerDialog.show();

    }

    public interface OnGetSelectedDateCallBack{
        public void onGetSelectedDate(Date selectedDate);
    }



    private void initStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        selectStartDate = calendar.getTime();
    }

    private void initEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        calendar.set(calendar.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59, 59);

        calendar.set(Calendar.MILLISECOND, 0);

        selectEndDate = calendar.getTime();
    }

    private void testDateDisplaying() {
        TextView lblStartDate = findViewById(R.id.lblStartDate);
        TextView lblEndDate = findViewById(R.id.lblEndDate);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String startDate = sdf.format(selectStartDate);
        lblStartDate.setText(startDate);
        String endDate = sdf.format(selectEndDate);
        lblEndDate.setText(endDate);

    }

    private void filtration() {
        FlickRepository.getInstance().getAllFlicks(new FlickRepository.OnGetAllFlicksCallBack() {
            @Override
            public void onGetAllFlicks(ArrayList<Flick> allFlicks) {
                currentFlicks.clear();
                for (Flick flick : allFlicks) {
                    if (flick.getDate().after(selectStartDate) && flick.getDate().before(selectEndDate)) {
                        currentFlicks.add(flick);
                    }
                }
                flickAdapter.notifyDataSetChanged();
            }
        });
    }

}
