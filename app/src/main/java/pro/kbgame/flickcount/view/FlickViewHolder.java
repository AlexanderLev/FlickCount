package pro.kbgame.flickcount.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import pro.kbgame.flickcount.R;
import pro.kbgame.flickcount.model.Flick;

public class FlickViewHolder extends RecyclerView.ViewHolder {
/*    @BindView(R.id.cardViewFlickCard)
    CardView cardView;

    @BindView(R.id.archTxtID)
    TextView textViewID;

    @BindView(R.id.archTxtDate)
    TextView textDate;

    @BindView(R.id.archTxtCause)
    TextView textCause;*/

CardView cardView;
TextView textViewID;
TextView textDate;
TextView textCause;

    public FlickViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewID = itemView.findViewById(R.id.archTxtID);
        textDate = itemView.findViewById(R.id.archTxtDate);
        textCause = itemView.findViewById(R.id.archTxtCause);
        /*ButterKnife.bind(this, itemView);*/
    }

    public void bind (Flick flick){
        textViewID.setText("" + flick.getId());
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(flick.getDate());
        textDate.setText(date);
        textCause.setText(flick.getCause());

    }
}
