package Server.Model.DotsAndBoxesModel;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class Details {
    private static final File detailsDotsFile = new File("src\\main\\java\\Model\\Database\\DetailsDots.json");

    private static String details ="Dots";

    public static String getDetails() {
        return details;
    }

    public static void setDetails(String details) {
        Details.details = details;
    }

    public static void saveInJsonFile() throws IOException {
        if (detailsDotsFile.exists())
            detailsDotsFile.delete();

        detailsDotsFile.createNewFile();

        String outForData = new Gson().toJson(details);
        FileWriter fWrite = new FileWriter(detailsDotsFile, true); // true for appending content to the existing file
        BufferedWriter bWrite = new BufferedWriter(fWrite);
        bWrite.write(outForData);
        bWrite.close();
    }

    public static void loadFromJsonFile() {
        if (!detailsDotsFile.exists())
            return;

        StringBuilder read = new StringBuilder();

        try {
            Scanner myReader = new Scanner(detailsDotsFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Type type = new TypeToken<String>() {}.getType();
        String output = new Gson().fromJson(String.valueOf(read), type);
        details = output;
    }
}
