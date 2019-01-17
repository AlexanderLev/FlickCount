package pro.kbgame.flickcount.common;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class FileReadTask extends AsyncTask {

    private Context ctx;
    private String fileName;
    private OnReadFileCallBack callBack;


    public interface OnReadFileCallBack {

        public void onReadFile(String fileData);

    }

    public FileReadTask(Context ctx, String fileName, OnReadFileCallBack callBack) {
        this.ctx = ctx;
        this.fileName = fileName;
        this.callBack = callBack;

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        //emulateLongOperation();

        return readFromFile();
    }

    private void emulateLongOperation() {
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 10000; j++){
                double sqrt = Math.sqrt(i*i*j*j);
            }
            Log.d("Delay", "" + i);
        }
    }


    private String readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        File dir = ctx.getFilesDir();
        File file = new File(dir, fileName);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = null;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    @Override
    protected void onPostExecute(Object stringData) {
        callBack.onReadFile((String) stringData);
    }

}
