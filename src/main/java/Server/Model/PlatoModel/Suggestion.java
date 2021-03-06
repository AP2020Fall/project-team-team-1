package Server.Model.PlatoModel;

import Server.Model.DataBase.DataBase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Suggestion {
    private static final File suggestionFile = new File("src\\main\\resources\\DataBase\\Suggestion.json");

    private static ArrayList<Suggestion> allSuggestions = new ArrayList<>();
    private int suggestionID;
    private Player playerName;
    private String suggestedGame;


    public Suggestion(int suggestionID, Player playerName, String suggestedGame) {
        this.playerName = playerName;
        this.suggestedGame = suggestedGame;
        this.suggestionID = suggestionID;
        playerName.suggestedGamesID.add(suggestionID);
    }


    public static ArrayList<Suggestion> getAllSuggestions() {
        return allSuggestions;
    }

    public int getSuggestionID() {
        return suggestionID;
    }

    public Player getPlayerName() {
        return playerName;
    }

    public String getSuggestedGame() {
        return suggestedGame;
    }

    public static void addNewSuggestion(Suggestion suggestion) {
        allSuggestions.add(suggestion);
        try {
            DataBase.save(allSuggestions, suggestionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveInJsonFile() {
        try {
            DataBase.save(allSuggestions, suggestionFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFromJsonFile() {
        if (!suggestionFile.exists())
            return;
        StringBuilder read = new StringBuilder();
        try {
            Scanner myReader = new Scanner(suggestionFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                read.append(data);
            }
            myReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type type = new TypeToken<ArrayList<Suggestion>>() {
        }.getType();
        ArrayList<Suggestion> output = new Gson().fromJson(String.valueOf(read), type);
        allSuggestions.clear();
        allSuggestions.addAll(output);
    }


    @Override
    public String toString() {
        return "Suggestion{" +
                "suggestionID=" + suggestionID +
                ", playerName=" + playerName +
                ", suggestedGame='" + suggestedGame + '\'' +
                '}';
    }
}
