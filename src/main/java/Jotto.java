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
import java.util.Set;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;



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

    private Scanner sc = new Scanner(System.in);


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

        //checks if the list is empty
        if (playGuesses.isEmpty()) {
            System.out.println("No guesses yet");
            return playGuesses;
        } else {
            System.out.println("Current player guesses:");

            //print each guess on its own line
            for (String word : playGuesses) {
                System.out.println(word);
            }

            //ask user if they want to update the list
            System.out.print("Update word list (y/n)? ");
            String input = sc.nextLine().trim().toLowerCase();

            if(input.equals("y") || input.equals("yes")){
                updateWordList();
                System.out.println(showWordList());
            }
        }
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
        //Scanner sc = new Scanner(System.in);

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
        ArrayList<String> currentGuesses = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int letterCount = 0;
        int score = WORD_SIZE+1;
        String wordGuess = "";

        //enters loop, exits loop when pressed q or correct guess
        while(true){

            System.out.println("Current Score: " + score);
            System.out.print("What is your guess (q to quit):");

            wordGuess = sc.nextLine().toLowerCase().trim();

            //checks for quit condition
            if(wordGuess.equals("q")){
                return Math.min(score, 0);
            }

            //checking for word length
            if(wordGuess.length() != WORD_SIZE){
                System.out.println("Word must be 5 characters (" + wordGuess + " is " + wordGuess.length() + ")");
                continue;
            }

            //checking for repeat guesses
            if(currentGuesses.contains(wordGuess)){
                System.out.println(wordGuess + " has already been guessed");// exact wording needed
                continue;
            }

            addPlayerGuess(wordGuess);

            //checking for correct word guess
            if(wordGuess.equals(currentWord)){
                System.out.println("DINGDINGDING!!! the word was " + wordGuess);
                currentGuesses.add(wordGuess);
                playerGuessScores(currentGuesses);
                return score;
            }

            currentGuesses.add(wordGuess);

            //checking for how many matches
            letterCount = getLetterCount(wordGuess);

            if(letterCount != WORD_SIZE){
                System.out.println(wordGuess + " has a Jotto score of " + letterCount);
            } else{
                System.out.println("Word you chose is an anagram."); // exact wording needed
            }

            score--;

            playerGuessScores(currentGuesses);

        }
        //return score;
    }

    public ArrayList<String> getPlayedWords() {

        return playWords;
    }

    public String getCurrentWord(){
        return currentWord;
    }

    public int getLetterCount(String word) {
        if (currentWord.equals(word)) {
            return WORD_SIZE;
        }

        Set<Character> guessSet = new HashSet<>();
        Set<Character> wordSet = new HashSet<>();

        for (char c : word.toCharArray()) {
            guessSet.add(c);
        }

        for (char c : currentWord.toCharArray()) {
            wordSet.add(c);
        }

        guessSet.retainAll(wordSet);

        return guessSet.size();
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

    void updateWordList() {

        // check for duplicates
        for (String word : playGuesses) {
            if (!wordList.contains(word)) {
                wordList.add(word);
            }
        }

        //writes to the file
        try (FileWriter writer = new FileWriter(filename)) {

            for (String word : wordList) {
                writer.write(word + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }


}