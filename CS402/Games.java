package CS402;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Games {

    private static char[][] board;
    private static char currentPlayer;

    private static boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        return board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard() {
        for (int i = 0; i < 3; i++) { // row
            for (int j = 0; j < 3; j++) { // column
                System.out.print(" " + board[i][j]);
                if (j < 2)
                    System.out.print(" |");
            }
            System.out.println();
            if (i < 2)
                System.out.println("-----------");
        }
        System.out.println();
    }

    private static void printHangman(int incorrectAttempts) {
        String[] hangmanStates = {
                """
                          +---+
                          |   |
                              |
                              |
                              |
                              |
                        =========
                        """,
                """
                          +---+
                          |   |
                          O   |
                              |
                              |
                              |
                        =========
                        """,
                """
                          +---+
                          |   |
                          O   |
                          |   |
                              |
                              |
                        =========
                        """,
                """
                          +---+
                          |   |
                          O   |
                         /|   |
                              |
                              |
                        =========
                        """,
                """
                          +---+
                          |   |
                          O   |
                         /|\\  |
                              |
                              |
                        =========
                        """,
                """
                          +---+
                          |   |
                          O   |
                         /|\\  |
                         /    |
                              |
                        =========
                        """,
                """
                          +---+
                          |   |
                          O   |
                         /|\\  |
                         / \\  |
                              |
                        =========
                        """
        };

        System.out.println(hangmanStates[Math.min(incorrectAttempts, hangmanStates.length - 1)]);
    }

    public static void GuessNumber(String[] args) {
        Random random = new Random();

        // select difficulty
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose difficulty level: Easy, Medium, Hard");
            String difficulty = scanner.nextLine().toLowerCase();

            int attempts = 0;
            switch (difficulty) {
                case "easy" -> attempts = 10;
                case "medium" -> attempts = 5;
                case "hard" -> attempts = 3;
                default -> {
                    System.out.println("Invalid choice. Defaulting to Medium difficulty.");
                    attempts = 5;
                }
            }

            int numberToGuess = random.nextInt(100) + 1;
            int min = 1;
            int max = 100;
            boolean hasGuessedCorrectly = false;

            System.out.printf(
                    "Welcome to the Number Guessing Game!%nThe program has generated a number between 1 and 100.%nYou have chosen %s difficulty with %d attempts to guess the correct number.%n%n",
                    difficulty, attempts);

            // loop for [chances]
            for (int i = 1; i <= attempts; i++) {
                int guess = 0;
                boolean validInput = false;

                // check input validity
                while (!validInput) {
                    System.out.printf("Guess %d: ", i);
                    try {
                        guess = scanner.nextInt();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        scanner.next(); // Clear the invalid input
                    }
                }

                // check if equal/bigger/smaller than rng value
                if (guess == numberToGuess) {
                    System.out.printf("Congratulations! You have guessed the correct number %d in %d attempts.%n",
                            numberToGuess,
                            i);
                    hasGuessedCorrectly = true;
                    break;
                } else if (guess > numberToGuess && true) {

                    if (guess < max) { // if value is higher than max possible value then dont edit max val
                        max = guess;
                    }

                    System.out.printf("Your guess is too high. The number is between %d and %d.%n%n", min, max);
                } else {

                    if (guess > min) { // self explanatory
                        min = guess;
                    }

                    System.out.printf("Your guess is too low. The number is between %d and %d.%n%n", min, max);
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.printf("Sorry, you've used all your attempts. The correct number was %d.%n", numberToGuess);
            }
            System.out.println("Thanks for playing the number guessing game!");

            PlayAgain(args, "Number Guessing");
        }
    }

    public static void TicTacToe(String[] args) {
        Scanner scanner = new Scanner(System.in);
        board = new char[3][3];
        currentPlayer = 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        System.out.println("Welcome to Tic Tac Toe!");
        printBoard();

        while (true) {
            System.out.printf("Player %c, enter a number (1-9) to choose a space: ", currentPlayer);
            int move = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    move = scanner.nextInt();
                    if (move < 1 || move > 9) {
                        System.out.println("Invalid move. Please enter a number between 1 and 9.");
                    } else {
                        int row = (move - 1) / 3;
                        int col = (move - 1) % 3;
                        if (board[row][col] != ' ') {
                            System.out.println("This space is already occupied. Choose another space.");
                        } else {
                            board[row][col] = currentPlayer;
                            validInput = true;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); // Clear the invalid input
                }
            }

            printBoard();

            if (checkWinner()) {
                System.out.printf("Player %c wins!%n", currentPlayer);
                break;
            }

            if (isBoardFull()) {
                System.out.println("It's a draw!");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        scanner.close();

        PlayAgain(args, "Tic Tac Toe");
    }

    public static void Hangman(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to Hangman!");
            String[] words = { "apple", "banana", "orange", "grapes", "mango" }; // add more words here if you feel like
                                                                                 // it
            String word = words[(int) (Math.random() * words.length)];
            char[] guessedWord = new char[word.length()];
            for (int i = 0; i < word.length(); i++) {
                guessedWord[i] = '_';
            }

            int attemptsLeft = 7;

            HashSet<Character> guessedLetters = new HashSet<>();
            boolean wordGuessed = false;
            int incorrectAttempts = 0;

            while (attemptsLeft > 0 && !wordGuessed) {
                System.out.println("Current word: " + new String(guessedWord));
                System.out.println("Hangman status: ");
                System.out.print("Guess a letter: ");
                char guess = scanner.nextLine().toLowerCase().charAt(0);

                guessedLetters.add(guess);

                boolean correctGuess = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == guess) {
                        guessedWord[i] = guess;
                        correctGuess = true;
                    }
                }

                if (correctGuess) {
                    printHangman(incorrectAttempts);
                    System.out.println("Correct guess!");
                } else {
                    incorrectAttempts++;
                    attemptsLeft--;
                    printHangman(incorrectAttempts);
                    System.out.println("Incorrect guess. You have " + attemptsLeft + " tries left.");
                }

                wordGuessed = true;
                for (char c : guessedWord) {
                    if (c == '_') {
                        wordGuessed = false;
                        break;
                    }
                }
            }

            if (wordGuessed) {
                System.out.println("Congratulations! You've guessed the word: " + word);
            } else {
                printHangman(incorrectAttempts);
                System.out.println("You've run out of attempts. The word was: " + word);
            }

            PlayAgain(args, "Hangman");
        }
    }

    public static void PlayAgain(String[] args, String lastGamePlayed) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Would you like to play again or choose another game? (play again/choose another/quit)");
            String playAgainResponse = scanner.nextLine().toLowerCase();

            switch (playAgainResponse) {
                case "play again" -> {
                    switch (lastGamePlayed) {
                        case "Number Guessing" -> GuessNumber(args);
                        case "Tic Tac Toe" -> TicTacToe(args);
                        case "Hangman" -> Hangman(args);
                        default -> throw new AssertionError("Unknown game: " + lastGamePlayed);
                    }
                }
                case "choose another" -> {
                    System.out.println("Choosing another game...");
                    GameSelect(args);
                }
                case "quit" -> System.out.println("Thanks for playing!");
                default -> throw new AssertionError("Invalid choice: " + playAgainResponse);
            }
        }
    }

    public static void GameSelect(String[] args) {
        String[] games = { "Number Guessing", "Tic Tac Toe", "Hangman" };

        System.out.println("input numerical index of game to play or press any non-numeric key to quit.");

        int index = 0;
        for (String game : games) {
            System.out.println((++index) + ". " + game);
        }

        String PickedGame = "";
        boolean gameChosen = false;
        try (Scanner scanner = new Scanner(System.in)) {
            do {

                try {

                    int gameIndex = scanner.nextInt();

                    if (gameIndex - 1 < games.length) {
                        System.out.println("Chosen game: " + games[gameIndex - 1]);
                        PickedGame = games[gameIndex - 1];
                        gameChosen = true;

                    } else {
                        System.out.println("Game does not exist, reenter a value");

                    }

                } catch (Exception e) {
                    break;

                }

            } while (!gameChosen);

            switch (PickedGame) {
                case "Tic Tac Toe" -> Games.TicTacToe(args);
                case "Hangman" -> Games.Hangman(args);
                case "Number Guessing" -> Games.GuessNumber(args);
                default -> System.out.println("quitted");
            }
        }
    }
}
