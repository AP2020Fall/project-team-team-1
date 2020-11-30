package Model.DataBase;

import Model.PlatoModel.Admin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataBase {
    private static com.google.gson.Gson gson = new Gson();
    private static ArrayList<File> filesForLoad = new ArrayList<>();

    public static void save(ArrayList object, File file) throws IOException {

        if (file.exists())
            file.delete();

        file.createNewFile();

        String outForData = new Gson().toJson(object);

//        FileWriter fWrite = null;
//        BufferedWriter bWrite = null;

        FileWriter fWrite = new FileWriter(file, true); // true for appending content to the existing file
        BufferedWriter bWrite = new BufferedWriter(fWrite);
        bWrite.write(outForData);
        bWrite.close();
    }

//    public static void saveWithArrayList(ArrayList arrayList, boolean New) {
//
//    }
//
//    public static void remove(Object object) {
//
//    }

    public static void loadAllDataFromJsonFiles() {
        Admin.loadFromJsonFile();


    }
}