package Model.PlatoModel;

import Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Games {
    private static final File gamesFile = new File("src\\main\\java\\Model\\Database\\Games.json");

    private static ArrayList<Games> games = new ArrayList<Games>();
    private int gameID;
    private String gameName;
    private boolean activation;
    private boolean update;

    public Games(int gameID, String gameName) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.activation = true;
        this.update = false;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }



    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public static ArrayList<Games> getGames() {
        return games;
    }

    public static void setGames(ArrayList<Games> games) {
        Games.games = games;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public static void addNewGame(Games games) {
        getGames().add(games);

        try {
            DataBase.save(getGames(), gamesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void loadFromJsonFile() {

        if (!gamesFile.exists())
            return;

        StringBuilder read = new StringBuilder();

        try {
            Scanner myReader = new Scanner(gamesFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Type type = new TypeToken<ArrayList<Games>>() {
        }.getType();

        ArrayList<Games> output = new Gson().fromJson(String.valueOf(read), type);

        getGames().clear();
        getGames().addAll(output);
    }

    @Override
    public String toString() {
        return "Games{" +
                "gameID=" + gameID +
                ", gameName='" + gameName + '\'' +
                ", activation=" + activation +
                ", update=" + update +
                '}';
    }
}
