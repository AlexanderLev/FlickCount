package pro.kbgame.flickcount.common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import pro.kbgame.flickcount.view.MainActivity;

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

    public String readFromFile() {
        StringBuilder stringBuilder = new StringBuilder();
        File dir = MainActivity.getInstance().getApplicationContext().getFilesDir();
        File file = new File(dir, FILE_NAME);
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

    public static FileUtils getInstance() {
        if (instance == null) {
            instance = new FileUtils();
        }
        return instance;
    }
}
