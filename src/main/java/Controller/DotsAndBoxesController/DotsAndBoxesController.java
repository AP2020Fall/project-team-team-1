package Controller.DotsAndBoxesController;

import Controller.Exception.DotsAndBoxes.ExistLineException;
import Controller.Exception.DotsAndBoxes.FindLineException;
import Controller.Exception.DotsAndBoxes.NotEmptyString;
import Controller.Exception.DotsAndBoxes.WrongFormatInDots;
import Model.DotsAndBoxesModel.*;
import View.RunDotsAndBoxes;

import java.util.ArrayList;
import java.util.Scanner;

public class DotsAndBoxesController {
    GameBoard gameBoard = new GameBoard(8,8);
    //----------------lines-------------------
    public ArrayList<Line> findLines(int row1, int column1, int row2, int column2){
        ArrayList<Line> finalLine = new ArrayList<>();
        for(Line line : Lines.lines){
            if (line.getFirstDot().getRow()==row1 && line.getFirstDot().getColumn()==column1 && line.getSecondDot().getRow()==row2 && line.getSecondDot().getColumn()==column2){
                finalLine.add(line);
            }
        }
        return finalLine;
    }
    //---------------------moves--------------
    public Player turn() {
        if (gameBoard.moves % 2 == 0) {
            return Player.RED;
        } else return Player.BLUE;
    }
    public void move(ArrayList<Line> lines) {
        int boxNum = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (!lines.get(i).hasOwner()) {
                lines.get(i).setOwner(turn());
                boxNum = isBoxCompleted();
                if (boxNum != 0) {
                    if (turn() == Player.BLUE) {
                        gameBoard.bluePoints += boxNum;
                        findTheBox(lines.get(i)).setOwner(Player.BLUE);
                    } else if (turn() == Player.RED) {
                        gameBoard.redPoints += boxNum;
                        findTheBox(lines.get(i)).setOwner(Player.RED);
                    }
                    if (i == 0) {
                        gameBoard.moves -= 2;
                    }

                } else {
                    if (i == 0) {
                        gameBoard.moves++;
                    }
                }
            }
        }
    }
    public Box findTheBox(Line line) {
        Box finalBox = null;
        for (Box box : gameBoard.boxes) {
            if (box.getBottom() == line || box.getLeft() == line || box.getTop() == line || box.getRight() == line) {
                finalBox = box;
            }
        }
        return finalBox;
    }
    public int isBoxCompleted() {
        int counter = 0;
        ArrayList<Box> forDelete = new ArrayList<>();
        for (Box box : gameBoard.availableBoxes) {
            if (box.getRight().hasOwner()) {
                if (box.getTop().hasOwner()) {
                    if (box.getLeft().hasOwner()) {
                        if (box.getBottom().hasOwner()) {
                            counter++;
                            forDelete.add(box);
                        }
                    }
                }
            }
        }
        gameBoard.availableBoxes.removeAll(forDelete);

        return counter;
    }
    public Line getLine(int row1,int column1,int row2,int column2){
        Line finalLine = null;
        for(Line line : Lines.lines){
            if (line.getFirstDot().getRow()==row1 && line.getFirstDot().getColumn()==column1 && line.getSecondDot().getRow()==row2 && line.getSecondDot().getColumn()==column2){
                finalLine = line;
            }
        }

        return finalLine;
    }
    public boolean isGameOver(int moves, int bluePoints, int redPoints) {
        int difference = Math.abs(bluePoints - redPoints);
        return difference > gameBoard.boxes.size() / 2 || bluePoints > gameBoard.boxes.size() / 2 || redPoints > gameBoard.boxes.size() / 2 || moves == 112;
    }

    public void startDotsAndBoxes () throws NotEmptyString, WrongFormatInDots, ExistLineException, FindLineException {
//        GameBoard gameBoard = new GameBoard(8,8);
        while (!isGameOver(gameBoard.getMoves(), gameBoard.getBluePoints(), gameBoard.getRedPoints())){
            System.out.println("Please Enter Tow Dots In this Format 2-3,2-4 ");
            RunDotsAndBoxes.printBoard();
//            String command = scanner.nextLine();
            String command = RunDotsAndBoxes.getNextCommand();
            if (command.isEmpty()){
                throw new NotEmptyString("You cant give up your turn , Try again ! ");
            }
            if (!command.matches("^\\d\\-\\d\\,\\d\\-\\d$")){
                throw new WrongFormatInDots("Please enter correct format");
            }
            String[] dots = command.split(",");
            String[] firstDot = dots[0].split("-");
            String[] secondDot = dots[1].split("-");
            if (findLines(Integer.parseInt(firstDot[0]),Integer.parseInt(firstDot[1]),Integer.parseInt(secondDot[0]),Integer.parseInt(secondDot[1])).size() == 0){
                throw new FindLineException("This Line could not be find! Or directions of dots is not correct! Try again");
            }
            if (getLine(Integer.parseInt(firstDot[0]),Integer.parseInt(firstDot[1]),Integer.parseInt(secondDot[0]),Integer.parseInt(secondDot[1])).hasOwner()){
                throw new ExistLineException("This line is already taken, Try again!");
            }
            move(findLines(Integer.parseInt(firstDot[0]),Integer.parseInt(firstDot[1]),Integer.parseInt(secondDot[0]),Integer.parseInt(secondDot[1])));
        }
        if (isGameOver(gameBoard.getMoves(), gameBoard.getBluePoints(), gameBoard.getRedPoints())){
            RunDotsAndBoxes.printWinner();

        }
    }
    //todo add player history
    public String whoIsWinner(){
        if (isGameOver(gameBoard.getMoves(), gameBoard.getBluePoints(), gameBoard.getRedPoints())){
            if (gameBoard.bluePoints>gameBoard.redPoints){
                return "blue";
            }else return "red";
        }else return null;
    }
//    public String blueIsWinner(){
//        return "blue";
//    }
//    public String redIsWinner(){
//        return "red";
//    }
    public int getRedPoints(){
        return gameBoard.redPoints;
    }
    public int getBluePoints(){
        return gameBoard.bluePoints;
    }
    public Player getBluePlayer(){
        return Player.BLUE;
    }
    public Player getRedPlayer(){
        return Player.RED;
    }

}
