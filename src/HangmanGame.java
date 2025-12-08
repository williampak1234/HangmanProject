import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private int category;
    private int difficulty;
    private int chancesLeft = 6;

    private String incorrectGuesses = "";

    private boolean gameIsWon = false;

    private Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    private char[] hiddenWordState;
    private String userWordState;

    private String[] carsEasy = {"Toyota", "Honda", "Tesla", "Ford", "Chevy", "Dodge", "BMW", "Mercedes"};
    private String[] carsMedium = {"Lamborghini", "Ferrari", "Corvette", "Volkswagen", "Rolls Royce", "Maserati"};
    private String[] carsHard = {"Pagani", "Bugatti", "Koenigsegg", "Alfa Romeo", "Viper"};

    private String[] fastFoodEasy = {"McDonalds", "Burger King", "Wendys", "Chick Fil A", "KFC"};
    private String[] fastFoodMedium = {"Chipotle", "Five Guys", "Popeyes", "Subway", "Jersey Mikes", "Dominoes"};
    private String[] fastFoodHard = {"Jolibee", "Cinnabon", "White Castle", "Panda Express"};

    private String[] gamesEasy = {"Mario", "Sonic", "Wii Sports", "Super Smash Bros", "Halo", "Call of Duty", "Angry Birds"};
    private String[] gamesMedium = {"Overwatch", "Valorant", "CSGO", "Rainbow Six Siege", "Minecraft", "OSU", "GTA"};
    private String[] gamesHard = {"Cyberpunk", "Red Dead Redemption", "Phasmaphobia", "Starcraft", "League of Legends", "Jetpack Joyride" };

    private String[] proLangEasy = {"HTML", "Java", "C", "Python"};
    private String[] proLangMedium = {"Rust", "C Plus Plus", "C Sharp", "JavaScript", "CSS"};
    private String[] proLangHard = {"Ruby on Rails", "TypeScript", "Swift", "Go", "PHP"};

    public HangmanGame(int category, int difficulty) {
        this.category = category;
        this.difficulty = difficulty;

    }

    // Returns a random word based on user's category and difficulty choice
    private String returnWord() {
        if (this.category==1) {
            if (this.difficulty == 1) {
                return carsEasy[random.nextInt(carsEasy.length)];
            }
            else if (this.difficulty == 2) {
                return carsMedium[random.nextInt(carsMedium.length)];
            }
            else if (this.difficulty == 3) {
                return carsHard[random.nextInt(carsHard.length)];
            }
        }

        else if (this.category==2) {
            if (this.difficulty == 1) {
                return fastFoodEasy[random.nextInt(fastFoodEasy.length)];
            }
            else if (this.difficulty == 2) {
                return fastFoodMedium[random.nextInt(fastFoodMedium.length)];
            }
            else if (this.difficulty == 3) {
                return fastFoodHard[random.nextInt(fastFoodHard.length)];
            }
        }

        else if (this.category==3) {
            if (this.difficulty == 1) {
                return gamesEasy[random.nextInt(gamesEasy.length)];
            }
            else if (this.difficulty == 2) {
                return gamesMedium[random.nextInt(gamesMedium.length)];
            }
            else if (this.difficulty == 3) {
                return gamesHard[random.nextInt(gamesHard.length)];
            }
        }

        else if (this.category==4) {
            if (this.difficulty == 1) {
                return proLangEasy[random.nextInt(proLangEasy.length)];
            }
            else if (this.difficulty == 2) {
                return proLangMedium[random.nextInt(proLangMedium.length)];
            }
            else if (this.difficulty == 3) {
                return proLangHard[random.nextInt(proLangHard.length)];
            }
        }
        return null;
    }

    // Print out incorrect guesses
    private void incorrectGuesses(char userGuess) {

        incorrectGuesses += userGuess + " ";
        System.out.println();
        System.out.println("Incorrect Guesses: " + incorrectGuesses);
    }

    // Print out guesses left
    private void displayGuessesLeft() {

        System.out.println("Chances left: " + chancesLeft);
    }

    // Draw the hangman picture based on how many chances user has left
    private String drawHangman() {
        if (chancesLeft == 6) {
            return"""
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     / \\  |
                          |
                    =========""";
        }
        else if (chancesLeft == 5) {
            return"""
                      +---+
                      |   |
                      O   |
                     /|\\  |
                     /    |
                          |
                    =========""";

        }
        else if (chancesLeft == 4) {
            return"""
                      +---+
                      |   |
                      O   |
                     /|\\  |
                          |
                          |
                    =========""";

        }
        else if (chancesLeft == 3) {
            return"""
                      +---+
                      |   |
                      O   |
                     /|   |
                          |
                          |
                    =========""";

        }
        else if (chancesLeft == 2) {
            return"""
                      +---+
                      |   |
                      O   |
                      |   |
                          |
                          |
                    =========""";

        }
        else if (chancesLeft == 1) {
            return"""
                      +---+
                      |   |
                      O   |
                          |
                          |
                          |
                    =========""";

        }
        else if (chancesLeft == 0) {
            return"""
                      +---+
                      |   |
                          |
                          |
                          |
                          |
                    =========""";

        }
        else return "hello";
    }


    // Get user guess
    private String getGuess() {
        System.out.println("What letter is your guess?: ");
        String userGuess = scanner.nextLine();
        if (userGuess.length() == 1) {
            return userGuess;
        }
        else {
            while (userGuess.length() != 1) {
                System.out.println("Please only enter 1 letter.");
                System.out.println("What letter is your guess?: ");
                userGuess = scanner.nextLine();
            }
        }
        return userGuess;

    }

    // Check whether user guess was correct, do actions accordingly
    private void checkGuess(String userGuess, String userWord) {
        if (userWord.contains(userGuess)) {
            char userGuessChar = userGuess.charAt(0);
            System.out.println("You got a letter correct!");
            for(int i = 0; i<userWord.length(); i++) {
                if(userWord.charAt(i) == userGuessChar) {
                    hiddenWordState[i] = userGuessChar;
                }
            }

        }
        else {
            char userGuessChar = userGuess.charAt(0);
            System.out.println("Wrong!");
            chancesLeft--;
            if (chancesLeft > 0) {
                incorrectGuesses(userGuessChar);
            }
            else if (chancesLeft == 0) {
                System.out.println("You lost!");

            }
        }

    }

    private void displayGame() {
        displayGuessesLeft();
        System.out.println(drawHangman());
    }

    private boolean checkIfWin(char[] hiddenWord, String userWord) {
        userWordState = new String(hiddenWord);

        if (userWordState.equals(userWord)) {
            System.out.println("You Win!");
            return true;
        }
        return false;
    }


    public void run() {
        String userWord = returnWord();

        hiddenWordState = new char[userWord.length()];
        for (int i = 0; i < userWord.length(); i++) {
            hiddenWordState[i] = '_';
        }

        while(chancesLeft > 0 && !gameIsWon) {
            displayGame();
            System.out.println(hiddenWordState);
            String userGuess = getGuess();
            checkGuess(userGuess, userWord);
            gameIsWon = checkIfWin(hiddenWordState, userWord);
        }


    }
}
