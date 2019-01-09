package pro.kbgame.flickcount.repository;

import java.util.ArrayList;

import pro.kbgame.flickcount.model.Flick;

public class FlickRepository {

    private static FlickRepository instance;
    private ArrayList<Flick> allFlicks;

    public static FlickRepository getInstance() {
        if (instance == null) {
            instance = new FlickRepository();
        }
        return instance;
    }

    public interface OnGetAllFlicksCallBack  {

        public void onGetAllFlicks(ArrayList<Flick> allFlicks);

    }


    public void getAllFlicks(OnGetAllFlicksCallBack callBack){
        if (allFlicks == null){
            allFlicks = new ArrayList<>();
            allFlicks.add(Flick.getFakeFlick());
            allFlicks.add(Flick.getFakeFlick());
            allFlicks.add(Flick.getFakeFlick());
            allFlicks.add(Flick.getFakeFlick());
        }

        callBack.onGetAllFlicks(allFlicks);

    }

    //addFlick (Flick flick)
    //check load arrayList

}