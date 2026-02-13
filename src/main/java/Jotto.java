/**
 * name: Himansu Yapa
 * Class: CST 348
 * Project: Jotto first assignment
 *
 * Description:
 * This assignment implements a console-based version of the word game Jotto.
 * The program reads a list of 5 words from a file, randomly selects
 * a secret word, and allows the player to guess words until the
 * correct word is found or the player quits. For each guess, the program
 * calculates a Jotto score. The game tracks previously played
 * words, player guesses, and maintains a running score across all rounds.
 *
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class Jotto {
    //Variables
    private static final int WORD_SIZE = 5;
    private int score = 0;
    private String currentWord = null;
    private String filename = "wordList.txt";
    private final ArrayList<String> playGuesses = new ArrayList<>();
    private final ArrayList<String> playWords = new ArrayList<>();
    private final ArrayList<String> wordList = new ArrayList<>();
    private static final boolean DEBUG = true;

    private Random rand = new Random();



    //Methods
    public boolean pickWord(){

        //checks to see if all words were guessed
        if(playWords.size() == wordList.size()){
            System.out.println("You've guessed them all!" );
            return false;
        }

        //gets a random word from the list
        currentWord = wordList.get(rand.nextInt(wordList.size()));

        //recursively checks for word was used and picks a new word
            if(playWords.contains(currentWord)){
                return pickWord();
            }

        playWords.add(currentWord);

        if(DEBUG){
            System.out.println(currentWord);
        }
            return true;
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
        Scanner sc = new Scanner(System.in);

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
            System.out.print("Would you like to add the words to the word list? (y/n) ");
            String input = sc.nextLine().trim().toLowerCase();

            if(input.equals("y") || input.equals("yes")){
                updateWordList();
                System.out.println("Updating word list");
                System.out.println(showWordList());
            }
        }
        return playGuesses;
    }

    void playerGuessScores(ArrayList<String> guesses) {

        //displays the score in propper format
        System.out.println("Guess\t\tScore");

        for (String guess : guesses) {
            System.out.println(guess + "\t\t" + getLetterCount(guess));
        }
    }

    public void setCurrentWord(String word){
        word = word.toLowerCase();
        currentWord= word;
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
                String input = sc.nextLine().trim().toLowerCase();
                if(!input.isEmpty()){
                    if(!wordList.contains(input)){
                        wordList.add(input);
                    }
                }
            }

        //error message if file not found
        } catch(FileNotFoundException e){
            System.out.println("Couldn't open " + this.filename);
        }

        return wordList;
    }

    public void play(){
        Scanner sc = new Scanner(System.in);
        boolean playing = true;

        //main game loop
        while(playing){
            //game menu
            System.out.println("Welcome to the game.");
            System.out.println("Current Score: " + score);
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

            //game menu options
            switch(input){
                case "1", "one" ->{
                    if (pickWord()) {
                        score += guess();
                        System.out.println("Your score is " + score);
                    } else {
                        showPlayerGuesses();
                    }
                }
                case "2", "two" ->{
                    System.out.println(showWordList());
                }
                case "3", "three" ->{
                    System.out.println(showPlayedWords());
                }
                case "4", "four" ->{
                    showPlayerGuesses();
                }
                case "zz" ->{
                    System.out.println("Final score: " + score);
                    System.out.println("Thank you for playing");
                    playing = false;

                }
                default -> {
                    System.out.printf("I don't know what \"%s\" is.%n", input);
                }
            }

            //pause for input
            System.out.println("Press enter to continue");
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

        }

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
                System.out.println("Current Score: " + score);
                System.out.print("Current Word: " + currentWord);
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

    }

    public ArrayList<String> getPlayedWords() {
        return playWords;
    }

    public String getCurrentWord(){
        return currentWord;
    }

    public int getLetterCount(String word) {
        word = word.toLowerCase();

        // If the guess exactly matches the secret word, return full score
        if (currentWord.equals(word)) {
            return WORD_SIZE;
        }

        Set<Character> guessSet = new HashSet<>();
        Set<Character> wordSet = new HashSet<>();

        //stores unique letters from guess and current word in sets
        for (char c : word.toCharArray()) {
            guessSet.add(c);
        }

        for (char c : currentWord.toCharArray()) {
            wordSet.add(c);
        }

        //only keeps the letters in both sets
        guessSet.retainAll(wordSet);

        //returns common  letters as the score
        return guessSet.size();
    }


    public String showPlayedWords() {
        //checks if the list is empty
        if (playWords.isEmpty()) {
            return "No words have been played.";
        }

        StringBuilder sb = new StringBuilder("Current list of played words:\n");

        //adding all the words for the list
        for (String word : playWords) {
            sb.append(word).append("\n");
        }

        return sb.toString();
    }


    public boolean addPlayerGuess(String wordGuess){
        wordGuess = wordGuess.toLowerCase();

        //checks if the word is in the list
        if(!playGuesses.contains(wordGuess)){
            playGuesses.add(wordGuess);
            return true;
        }

        return false;
    }

    void updateWordList() {

        // check for duplicates
        for (String word : playGuesses) {
            word = word.trim().toLowerCase();
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