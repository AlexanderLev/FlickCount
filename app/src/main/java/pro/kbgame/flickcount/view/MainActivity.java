package pro.kbgame.flickcount.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.MathUtils;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import pro.kbgame.flickcount.R;
import pro.kbgame.flickcount.common.FileUtils;
import pro.kbgame.flickcount.common.JSONHelper;
import pro.kbgame.flickcount.model.Flick;
import pro.kbgame.flickcount.repository.FlickRepository;

public class MainActivity extends AppCompatActivity {
    private static MainActivity instance;
    private TextView txtId;
    private TextView txtCause;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instance = this;
        txtId = findViewById(R.id.txtId);
        txtCause = findViewById(R.id.txtCause);

        int lastId = FlickRepository.getInstance().getLastFlickId();
        int currentID = lastId++;
        String currentIsInText = String.valueOf(currentID);
        txtId.setText(currentIsInText);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
            Intent intent = new Intent(MainActivity.this, ArchiveActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public static MainActivity getInstance(){
        return instance;
    }
}
