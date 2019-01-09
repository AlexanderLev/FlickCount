package pro.kbgame.flickcount.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.kbgame.flickcount.R;
import pro.kbgame.flickcount.model.Flick;
import pro.kbgame.flickcount.repository.FlickRepository;

public class ArchiveActivity extends AppCompatActivity {
    /*@BindView(R.id.recyclerViewAllFlicks)
    RecyclerView recyclerView;

    @BindView(R.id.testButton)
    Button testButton;
*/
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        /*ButterKnife.bind(this);*/
        recyclerView = findViewById(R.id.recyclerViewAllFlicks);
        Button tstBtn = findViewById(R.id.testButton);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        FlickRepository.getInstance().getAllFlicks(new FlickRepository.OnGetAllFlicksCallBack() {
            @Override
            public void onGetAllFlicks(ArrayList<Flick> allFlicks) {
                FlickAdapter flickAdapter = new FlickAdapter(allFlicks);
                recyclerView.setAdapter(flickAdapter);
            }
        });




    }
}
