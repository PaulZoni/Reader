package com.bignerdranch.android.reader.model;

import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager implements BaseManager {

    @Override
    public String load(String path) {
        File file;
        if (path != null){
            file = new File(path);
        } else {
            return "Non";
        }

        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                builder.append(text);
                builder.append("\n");
            }
            reader.close();
        }catch (IOException e) {
            Log.e("IOException", String.valueOf(e));
        }
        return String.valueOf(builder);
    }
}