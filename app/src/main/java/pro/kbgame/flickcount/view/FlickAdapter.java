package pro.kbgame.flickcount.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pro.kbgame.flickcount.R;
import pro.kbgame.flickcount.model.Flick;


public class FlickAdapter extends RecyclerView.Adapter<FlickViewHolder> {
    private ArrayList<Flick> flicks;

    public FlickAdapter(@NonNull ArrayList<Flick> flicks) {
        this.flicks = flicks;
    }


    @NonNull
    @Override
    public FlickViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.archive_card, viewGroup, false);
        FlickViewHolder flickViewHolder = new FlickViewHolder(view);
        return flickViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FlickViewHolder flickViewHolder, int i) {
        Flick flick = flicks.get(i);
        flickViewHolder.bind(flick);

    }

    @Override
    public int getItemCount() {
        return flicks.size();
    }
}
