using System;

namespace Mathgame {
    class Mathgame {
        // Defaults
        public static String title = "Mathgame (C#, Version 1.2)";
        public static String copyright = "(C) 2016 Tatu Arvela. All rights reserved.";
        public static String header = @""
                + " " + title + Environment.NewLine
                + " " + copyright + Environment.NewLine
                + "-------------------------------------------------------------------------------" + Environment.NewLine;
        public static String[] ops = { "+", "-", "*", "/" };
        public static int score = 0;
        public static int goal = 10;
        public static int rndmin = 2;
        public static int rndmax = 12;

        // Global variables
        public static Random rnd = new Random();
        public static int num01;
        public static int num02;
        public static string op;
        public static decimal answer;

        // Randomization
        public static void randomizeNumbers() {
            num01 = rnd.Next(rndmin, rndmax + 1);
            num02 = rnd.Next(rndmin, rndmax + 1);
        }

        // Input validation
        public static Boolean isNaN(string input) {
            double output;
            if (Double.TryParse(input, out output)) {
                if (!String.IsNullOrEmpty(input)) {
                    return false;
                }
                return true;
            }
            else {
                return true;
            }
        }

        // Game loop start point
        private static void runGame() {
            op = ops[rnd.Next(ops.Length)];
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
                answer = Decimal.Divide(num01, num02);
                // If the answer has more than one decimal place, a new task is calculated
                while (BitConverter.GetBytes(decimal.GetBits(answer)[3])[2] > 1) {
                    num01 = rnd.Next(rndmin, rndmax + 1);
                    num02 = rnd.Next(rndmin, rndmax + 1);
                    answer = Decimal.Divide(num01, num02);
                }
            }

            printHeader();
            printStatus();
            userInput();
        }

        // Print header
        public static void printHeader() {
            Console.Clear();
            Console.WriteLine(header);
        }

        // Print status
        public static void printStatus() {
            if (score == 0) {
                Console.WriteLine(" Your goal is to answer " + goal + " math problems correctly in a row.");
            }
            else if (score == goal) {
                Console.WriteLine(" " + score + " out of " + goal + "! Good job!");
                Console.WriteLine(" " + "Press ESC to quit. Press ENTER to play again.");
                if (Console.ReadKey(true).Key == ConsoleKey.Escape) {
                    Environment.Exit(0);
                }
                else {
                    score = 0;
                    runGame();
                };
            }
            else {
                Console.WriteLine(" " + score + " correct answers. " + (goal - score) + " math problems left.");
            }
        }

        // Input
        public static void userInput() {
            Console.WriteLine(" What is " + num01 + op + num02 + "?");
            Console.Write(" Answer: ");
            string useranswer = Console.ReadLine();
            useranswer = useranswer.Replace('.', ',');
            if (isNaN(useranswer)) {
                printHeader();
                Console.WriteLine(" Your input was incorrect. Try again.");
                userInput();
            }
            else {
                response(useranswer);
            }
        }

        // Response
        public static void response(string useranswer) {
            if (decimal.Parse(useranswer) == answer) {
                score++;
                Console.WriteLine(Environment.NewLine + " Your answer is correct. You have so far answered " + score + " problems correctly.");
                Console.WriteLine(" Press ESC to quit. Press ENTER to continue. ");
                Console.Write(" >");
                if (Console.ReadKey(true).Key == ConsoleKey.Escape) {
                    Environment.Exit(0);
                }
                else {
                    runGame();
                }
            } else {
                Console.WriteLine(Environment.NewLine + " Your answer is wrong. You answered " + score + " problems correctly.");
                Console.WriteLine(" Press ESC to quit. Press ENTER to restart. ");
                Console.Write(" >");
                if (Console.ReadKey(true).Key == ConsoleKey.Escape) {
                    Environment.Exit(0);
                }
                else {
                    score = 0;
                    runGame();
                }
            }
        }

        public static void Main(string[] args) {
            runGame();
        }
    }
}