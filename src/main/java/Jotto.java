/**
 * name: Himansu Yapa
 * Class: CST 348
 * Project: Jotto first assignment
 *
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Jotto {
    //Variables
    private int WORD_SIZE = 5;
    private int score = 0;
    private String currentWord = "";
    private String filename = "wordList.txt";
    private ArrayList<String> playGuesses = new ArrayList<>();
    private ArrayList<String> playWords = new ArrayList<>();
    private ArrayList<String> wordList = new ArrayList<>();
    private boolean DEBUG = true;

    //Methods
    public boolean pickWord(){
        return false;
    }

    public String showWordList(){
        return "";
    }

    public ArrayList<String> showPlayerGuesses() {

    }

    void playerGuessScores(ArrayList<String> word) {

    }

    public void setCurrentWord(String word){

    }

    public ArrayList<String> readWords(){
        wordList.clear();

        try{
            File file = new File(this.filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()){
                String input = sc.nextLine().trim();
                if(!input.isEmpty()){
                    if(!wordList.contains(input)){
                        wordList.add(input);
                    }

                }

            }

            sc.close();

        } catch(FileNotFoundException e){
            System.out.println("Couldn't open " + this.filename);
        }

        return wordList;
    }

    public void play(){

    }

    int guess(){

    }

    public ArrayList<String> getPlayedWords() {

    }

    public String getCurrentWord(){

    }

    public int getLetterCount(String word){

    }

    public String showPlayedWords(){

    }

    public boolean addPlayerGuess(String word){

    }

    void updateWordList(){

    }

    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }


}