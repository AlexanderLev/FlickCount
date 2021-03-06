package pro.kbgame.flickcount.common;

import android.content.Context;
import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileSaveTask extends AsyncTask {
    private Context ctx;
    private OnSaveFileCallBack callBack;
    private String formattedString;
    private String fileName;

    public interface OnSaveFileCallBack {

        public void onSaveFile();

    }

    public FileSaveTask (Context ctx, String formattedString, String fileName,  OnSaveFileCallBack callBack){
        this.ctx = ctx;
        this.formattedString = formattedString;
        this.fileName = fileName;
        this.callBack = callBack;

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return writeInFile(ctx, formattedString);
    }

    @Override
    protected void onPostExecute(Object o) {
        callBack.onSaveFile();
    }

    public Object writeInFile(Context ctx, String formattedString) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = ctx.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            outputStreamWriter.write(formattedString);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();

        } catch (IOException ex) {

        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException ex) {
            }
        }
        return null;
    }

}
