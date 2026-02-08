/**
 * name: Himansu Yapa
 * Class: CST 348
 * Project: Jotto first assignment
 *
 **/

import java.util.ArrayList;

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

    public ArrayList<String> readWords(String word){
        Scanner
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
        readWords(this.filename);
    }


}