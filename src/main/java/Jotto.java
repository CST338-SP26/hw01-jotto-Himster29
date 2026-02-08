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
        //checks if the list is empty
        if (wordList.isEmpty()) {
            return "No words are on the list";
        }

        StringBuilder sb = new StringBuilder("Current word list:\n");

        //adding all the words for the list
        for (String word : wordList) {
            sb.append(word).append("\n");
        }

        return sb.toString();
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

        boolean playing = true;



        while(playing){

            System.out.println("Welcome to the game.");
            //System.out.println("Current Score: " + getScore());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Choose one of the following:");
            System.out.println("1:        Start the game");
            System.out.println("2:        See the word list");
            System.out.println("3:        See the chosen words");
            System.out.println("4:        Show Player guesses");
            System.out.println("zz to exit");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("What is your choice: ");

            String input = sc.nextLine().trim().toLowerCase();

            switch(input){
                case "1", "one" ->{
                    if (pickWord()) {
                        score = guess();
                    } else {
                        showPlayerGuesses();
                    }
                }
                case "2", "two" ->{
                    showWordList();
                }
                case "3", "three" ->{
                    showPlayedWords();
                }
                case "4", "four" ->{
                    showPlayerGuesses();
                }
                case "zz" ->{
                    playing = false;

                }
                default -> {
                    System.out.printf("I don't know what \"%s\" is.%n", input);
                }
            }
        }
        sc.close();

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

    public String showPlayedWords() {
        //checks if the list is empty
        if (playWords.isEmpty()) {
            return "No words have been played";
        }

        StringBuilder sb = new StringBuilder("Current list of played words:\n");

        //adding all the words for the list
        for (String word : playWords) {
            sb.append(word).append("\n");
        }

        return sb.toString();
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