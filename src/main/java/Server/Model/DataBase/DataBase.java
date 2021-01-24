package Server.Model.DataBase;

import Server.Model.DotsAndBoxesModel.Details;
import Server.Model.PlatoModel.*;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class DataBase {

    public static void save(ArrayList arrayList, File file) throws IOException {
        if (file.exists())
            file.delete();

        file.createNewFile();

        String outForData = new Gson().toJson(arrayList);
        FileWriter fWrite = new FileWriter(file, true);
        BufferedWriter bWrite = new BufferedWriter(fWrite);
        bWrite.write(outForData);
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
        Server.Model.BattleSeaModel.Details.loadFromJsonFile();
        Games.loadFromJsonFile();

    }

}