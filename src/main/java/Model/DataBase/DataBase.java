package Model.DataBase;

import Model.DotsAndBoxesModel.Details;
import Model.PlatoModel.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;

public class DataBase {

    public static void save(ArrayList arrayList, File file) throws IOException {
        if (file.exists())
            file.delete();

        file.createNewFile();
        Base64.Encoder encoder = Base64.getEncoder();
        String outForData = new Gson().toJson(arrayList);
        FileWriter fWrite = new FileWriter(file, true);
        BufferedWriter bWrite = new BufferedWriter(fWrite);
        bWrite.write(encoder.encodeToString(outForData.getBytes()));
        bWrite.close();

    }

    public static void loadAllDataFromJsonFiles() throws FileNotFoundException {
        Player.loadFromJsonFile();
        Event.loadFromJsonFile();
        Suggestion.loadFromJsonFile();
        Message.loadFromJsonFile();
        PlayerLog.loadFromJsonFile();
        User.loadFromJsonFile();
        Admin.loadFromJsonFile();
        Details.loadFromJsonFile();
        Model.BattleSeaModel.Details.loadFromJsonFile();
        Games.loadFromJsonFile();

    }

}