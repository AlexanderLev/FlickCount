package pro.kbgame.flickcount.repository;

import android.content.Context;

import java.util.ArrayList;

import pro.kbgame.flickcount.common.FileReadTask;
import pro.kbgame.flickcount.common.FileUtils;
import pro.kbgame.flickcount.common.JSONHelper;
import pro.kbgame.flickcount.model.Flick;
import pro.kbgame.flickcount.view.MainActivity;

public class FlickRepository {

    private static FlickRepository instance;
    private ArrayList<Flick> allFlicks;
    private final String FILE_NAME = "flickers.base";

    public static FlickRepository getInstance() {
        if (instance == null) {
            instance = new FlickRepository();
        }
        return instance;
    }

    public interface OnGetAllFlicksCallBack {

        public void onGetAllFlicks(ArrayList<Flick> allFlicks);

    }


    public void getAllFlicks(final OnGetAllFlicksCallBack callBack) {

        if (allFlicks == null) {

            FileReadTask fileReadTask = new FileReadTask(MainActivity.getInstance(), FILE_NAME, new FileReadTask.OnReadFileCallBack() {
                @Override
                public void onReadFile(String fileData) {
                    allFlicks = JSONHelper.getInstance().getFlickArray(fileData);
                    if (allFlicks == null) {
                        allFlicks = new ArrayList<Flick>();
                    }
                    callBack.onGetAllFlicks(allFlicks);
                }
            });
            fileReadTask.execute();

        }
        else{
            callBack.onGetAllFlicks(allFlicks);
        }
    }



    public void addFlick(final Flick flick) {
        getAllFlicks(new OnGetAllFlicksCallBack() {
            @Override
            public void onGetAllFlicks(ArrayList<Flick> allFlicks) {
                allFlicks.add(flick);

                String data = JSONHelper.getInstance().getArrayInJsonString(allFlicks);
                Context ctx = MainActivity.getInstance().getApplicationContext();
                FileUtils.getInstance().writeInFile(data, ctx); //TODO Repalce on FileSaveTask
            }
        });
    }



    public ArrayList<Flick> getFlicksByDate(){
        return null;
    }
}