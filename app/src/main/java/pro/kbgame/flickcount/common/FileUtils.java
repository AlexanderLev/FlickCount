package pro.kbgame.flickcount.common;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class FileUtils {
    private static FileUtils instance;
    private final String FILE_NAME = "flickers.base";


    public void writeInFile(String formattedString, Context ctx) {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = ctx.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
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
    }



    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }
}
