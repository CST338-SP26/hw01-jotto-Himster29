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
        return playGuesses;
    }

    void playerGuessScores(ArrayList<String> word) {

    }

    public void setCurrentWord(String word){

    }

    public ArrayList<String> readWords(){
        //clears the array before adding anything
        wordList.clear();

        //error catch block
        try{
            //file and scanner objects
            File file = new File(this.filename);
            Scanner sc = new Scanner(file);

            //adds each word to the array
            while (sc.hasNextLine()){
                String input = sc.nextLine().trim();
                if(!input.isEmpty()){
                    if(!wordList.contains(input)){
                        wordList.add(input);
                    }

                }

            }

            sc.close();

        //error message if file not found
        } catch(FileNotFoundException e){
            System.out.println("Couldn't open " + this.filename);
        }

        return wordList;
    }

    public void play(){
        Scanner sc = new Scanner(System.in);

        String input = sc.next().trim().toLowerCase();


    }

    int guess(){
        return 2;
    }

    public ArrayList<String> getPlayedWords() {

        return playWords;
    }

    public String getCurrentWord(){
        return currentWord;
    }

    public int getLetterCount(String word){
        int count = 0;
        return count;
    }

    public String showPlayedWords(){
        return currentWord;
    }

    public boolean addPlayerGuess(String word){
        boolean check= false;

        return check;
    }

    void updateWordList(){

    }

    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }


}