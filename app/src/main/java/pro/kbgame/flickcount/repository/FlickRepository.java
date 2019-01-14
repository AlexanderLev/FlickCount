package pro.kbgame.flickcount.repository;

import android.content.Context;

import java.util.ArrayList;

import pro.kbgame.flickcount.common.FileUtils;
import pro.kbgame.flickcount.common.JSONHelper;
import pro.kbgame.flickcount.model.Flick;
import pro.kbgame.flickcount.view.MainActivity;

public class FlickRepository {

    private static FlickRepository instance;
    private ArrayList<Flick> allFlicks;

    public static FlickRepository getInstance() {
        if (instance == null) {
            instance = new FlickRepository();
        }
        return instance;
    }

    public interface OnGetAllFlicksCallBack {

        public void onGetAllFlicks(ArrayList<Flick> allFlicks);

    }


    public void getAllFlicks(OnGetAllFlicksCallBack callBack) {

        if (allFlicks == null) {
            allFlicks = JSONHelper.getInstance().getFlickArray(FileUtils.getInstance().readFromFile());
            if (allFlicks == null) {
                allFlicks = new ArrayList<Flick>();
            }
        }
        callBack.onGetAllFlicks(allFlicks);
    }

    public void addFlick(final Flick flick) {
        getAllFlicks(new OnGetAllFlicksCallBack() {
            @Override
            public void onGetAllFlicks(ArrayList<Flick> allFlicks) {
                allFlicks.add(flick);

                String data = JSONHelper.getInstance().getArrayInJsonString(allFlicks);
                Context ctx = MainActivity.getInstance().getApplicationContext();
                FileUtils.getInstance().writeInFile(data, ctx);
            }
        });
    }

}