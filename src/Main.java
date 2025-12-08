import java.util.Scanner;

public class Main {

    public static boolean isPlaying = true;
    public static boolean wantsToPlayAgain;

    private static final Scanner scanner = new Scanner(System.in);

    private static int printMenu() {
        System.out.println("""
                                Welcome to Hangman!
                1) New Game
                2) Exit""");
        System.out.println("Please enter in the number of your choice: ");
        int menuChoice = scanner.nextInt();
        scanner.nextLine();
        return menuChoice;
    }

    private static int chooseCategory() {
        System.out.println("""
                                Please choose a catergory:
                1) Cars
                2) Fast Food
                3) Video Games
                4) Programming Languages""");
        System.out.println("Please enter in the number of your choice: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();
        return categoryChoice;

    }

    private static int chooseDifficulty() {
        System.out.println("""         
                                Please choose a difficulty:
                1) Easy
                2) Medium
                3) Hard""");
        System.out.println("Please enter in the number of your choice: ");
        int difficultyChoice = scanner.nextInt();
        scanner.nextLine();
        return difficultyChoice;
    }

    private static boolean menuInstructor(int userMenuChoice) {
        if (userMenuChoice == 1) {
            int categoryChoice = chooseCategory();
            int difficultyChoice = chooseDifficulty();
            createGameInstance(categoryChoice, difficultyChoice);
            return true;
        }
        else if (userMenuChoice == 2) {
            endGame();
            return false;
        }
        return false;
    }

    private static void createGameInstance(int category, int difficulty) {
        HangmanGame game = new HangmanGame(category, difficulty);
        game.run();
    }

    private static void askAgain() {

        System.out.println("Would you like to play again? Y/N: ");
        String userOption = scanner.nextLine();
        while(!userOption.equals("Y") && !userOption.equals("N")) {
            System.out.println("Please enter only Y or N");
            System.out.println("Would you like to play again? Y/N: ");
            userOption = scanner.nextLine();
        }

        if (userOption.equals("Y")) {
            wantsToPlayAgain = true;
        }
        else {
            wantsToPlayAgain = false;
        }

    }

    private static void endGame() {
        System.out.println("Thanks so much for playing, enjoy your day!");
    }

    public static void main(String[] args) {


        while(isPlaying) {
            int userChoice = printMenu();
            isPlaying = menuInstructor(userChoice);
            askAgain();
            if (!wantsToPlayAgain){
                break;
            }
        }
        endGame();
    }
}

