package pro.kbgame.flickcount.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pro.kbgame.flickcount.R;
import pro.kbgame.flickcount.model.Flick;
import pro.kbgame.flickcount.repository.FlickRepository;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    private TextView txtId;
    private TextView txtCause;
    private int counter;
    private FloatingActionButton fab;
    private FlickRepository.OnGetAllFlicksCallBack loadCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instance = this;
        txtId = findViewById(R.id.txtId);
        txtCause = findViewById(R.id.txtCause);

        loadCallBack = new FlickRepository.OnGetAllFlicksCallBack() {

            @Override
            public void onGetAllFlicks(ArrayList<Flick> allFlicks) {

                initCounter(allFlicks);

                updateCounterUI();

                setActivityTitle();

                makeUiEnable();
            }
        };

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showSaveConfirmationAlertDialog();
            }
        });

        makeUiDisable();

        loadFlicks();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_archive) {
            /*Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
            startActivity(intent);*/
            Intent intent = new Intent(MainActivity.this, DateSelectActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public void loadFlicks() {
        FlickRepository.getInstance().getAllFlicks(loadCallBack);

    }

    public static MainActivity getInstance() {
        return instance;
    }

    private void prepareUiForNewFlick() {
        counter++;
        String currentIdInText = String.valueOf(counter);
        txtId.setText(currentIdInText);
        txtCause.setText("");
        setActivityTitle();
    }

    private void addNewFlickInRepo() {
        Flick flick = new Flick();
        flick.setId(counter);
        flick.setCause(txtCause.getText().toString());
        flick.setDate(new Date());
        FlickRepository.getInstance().addFlick(flick);
    }

    private void makeUiDisable() {
        fab.setEnabled(false);
    }

    private void makeUiEnable() {
        fab.setEnabled(true);
    }

    private void updateCounterUI() {
        String currentIdInText = String.valueOf(counter);
        txtId.setText(currentIdInText);
    }

    private void initCounter(ArrayList<Flick> allFlicks) {
        if (allFlicks.size() > 0){
            Flick lastFlick = allFlicks.get(allFlicks.size() - 1);
            counter = lastFlick.getId();
        }
        counter++;
    }

    private void setActivityTitle(){
        String idNumber = getResources().getString(R.string.number_of_flick);
        String title = idNumber + ": " + counter;
        setTitle(title);
    }

    private void showSaveConfirmationAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.save_flick)
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                addNewFlickInRepo();
                                prepareUiForNewFlick();

                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
