import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Mathgame {
    // Newline
    public static String newline = System.getProperty("line.separator");

    // Defaults
    public static String title = "Mathgame (Java, Version 1.2)";
    public static String copyright = "(C) 2016 Tatu Arvela. All rights reserved.";
    public static String header = " " + title + newline
            + " " + copyright + newline
            + "-------------------------------------------------------------------------------" + newline;
    public static String[] ops = { "+", "-", "*", "/" };
    public static int score = 0;
    public static int goal = 10;
    public static int rndmin = 2;
    public static int rndmax = 12;
    public static int maxdecimals = 1;

    // Global variables
    public static Random rnd = new Random();
    public static int num01;
    public static int num02;
    public static String op;
    public static double answer;

    // Clear screen
    public static void cls() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("cls");
        } catch (final Exception e) {
        }
    }

    // Get decimal places
    public static int getDecimalPlaces(double d) {
        String text = Double.toString(Math.abs(d));
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        return decimalPlaces;
    }

    // Read input
    public static Scanner scanner = new Scanner(System.in);

    // Randomize number
    public static void randomizeNumbers() {
        num01 = rnd.nextInt(rndmax - rndmin+1) + rndmin;
        num02 = rnd.nextInt(rndmax - rndmin+1) + rndmin;
    }

    // Input validation
    public static boolean isNaN(String input) {
        double output;
        try {
            output = Double.parseDouble(input);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    // Game loop start point
    public static void runGame() {
        op = ops[rnd.nextInt(ops.length)];
        randomizeNumbers();
        answer = 0;

        if (op == "+") {
            answer = num01 + num02;
        }
        if (op == "-") {
            answer = num01 - num02;
        }
        if (op == "*") {
            answer = num01 * num02;
        }
        if (op == "/") {
            answer = ((double) num01) / num02;
            // If the answer has more than one decimal place, a new task is calculated
            while (getDecimalPlaces(answer) > maxdecimals) {
                num01 = rnd.nextInt(rndmax - rndmin+1) + rndmin;
                num02 = rnd.nextInt(rndmax - rndmin+1) + rndmin;
                answer = ((double) num01) / num02;
            }
        }

        printHeader();
        printStatus();
        userInput();
    };

    // Print header
    public static void printHeader() {
        cls();
        System.out.println(header);
    }

    // Print status
    public static void printStatus() {
        if (score == 0) {
            System.out.println(" Your goal is to answer " + goal + " math problems correctly in a row.");
        } else if (score == goal) {
            System.out.println(" " + score + " out of " + goal + "! Good job!");
            System.out.println(" Type 'q' to quit. Press ENTER to play again.");
            System.out.print(" >");
            String key = scanner.nextLine();
            if (key.equals("q")) {
                System.exit(0);
            }
            else {
                score = 0;
                runGame();
            };
        } else {
            System.out.println(" " + score + " correct answers. " + (goal - score) + " math problems left.");
        }
    }

    // Input
    public static void userInput() {
        System.out.println(" What is " + num01 + op + num02 + "?");
        System.out.print(" Answer: ");
        String useranswer = scanner.nextLine();
        useranswer = useranswer.replace(',', '.');
        if (isNaN(useranswer)) {
            printHeader();
            System.out.println(" Your input was incorrect. Try again.");
            userInput();
        } else {
            response(useranswer);
        }
    }

    public static void response(String useranswer) {
        if (Double.parseDouble(useranswer) == answer) {
            score++;
            System.out.println(newline + " Your answer is correct. You have so far answered " + score + " problems correctly.");
            System.out.println(" Type 'q' to quit. Press ENTER to continue. ");
            System.out.print(" >");
            String key = scanner.nextLine();
            if (key.equals("q")) {
                System.exit(0);
            }
            else {
                runGame();
            };
        } else {
            System.out.println(newline + " Your answer is wrong. You answered " + score + " problems correctly.");
            System.out.println(" Type 'q' to quit. Press ENTER to restart. ");
            System.out.print(" >");
            String key = scanner.nextLine();
            if (key.equals("q")) {
                System.exit(0);
            }
            else {
                score = 0;
                runGame();
            };
        }
    }


	public static void main(String[] args) {
        runGame();
	}
}