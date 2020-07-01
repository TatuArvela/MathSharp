const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

// Defaults
function setDefaults() {
  title = "Mathgame (JavaScript, Version 1.2.1)";
  copyright = "(C) 2020 Tatu Arvela. All rights reserved.";
  header = ` ${title}
  ${copyright}
-------------------------------------------------------------------------------
`;
  process.title = title;
  ops = ["+", "-", "*", "/"];
  score = 0;
  goal = 10;
  rndmin = 2;
  rndmax = 12;
  maxdecimals = 1;
}

// Randomization
function randomizeNumbers() {
  num01 = Math.floor(Math.random() * (rndmax + 1 - rndmin) + rndmin);
  num02 = Math.floor(Math.random() * (rndmax + 1 - rndmin) + rndmin);
}

// Length of decimal places in a number
function getDecimalPlaces(number) {
  return (+number).toFixed(20).replace(/^-?\d*\.?|0+$/g, "").length;
}

// Game loop start point
async function runGame() {
  op = ops[Math.floor(Math.random() * ops.length)];
  randomizeNumbers();
  answer = 0;

  if (op == "+") answer = num01 + num02;
  if (op == "-") answer = num01 - num02;
  if (op == "*") answer = num01 * num02;
  if (op == "/") answer = num01 / num02;
  // If the answer has more than one decimal place, a new task is calculated
  while (getDecimalPlaces(answer) > maxdecimals) {
    randomizeNumbers();
    answer = num01 / num02;
  }

  printHeader();
  printStatus();
  if (score !== goal) {
    userInput();
  }
}

// Print header
function printHeader() {
  process.stdout.write("\033c");
  console.log(header);
}

// Print status
function printStatus() {
  if (score == 0) {
    console.log(
      " Your goal is to answer " + goal + " math problems correctly in a row."
    );
  } else if (score === goal) {
    console.log(" " + score + " out of " + goal + "! Good job!");
    console.log(" Type 'q' to quit. Press ENTER to play again.");
    rl.question(" >", (key) => {
      if (key == "q") {
        process.exit(0);
      } else {
        score = 0;
        runGame();
      }
    });
  } else {
    console.log(
      " " +
        score +
        " correct answers. " +
        (goal - score) +
        " math problems left."
    );
  }
}

// Input
function userInput() {
  console.log(" What is " + num01 + op + num02 + "?");
  rl.question(" Answer: ", (answer) => {
    useranswer = answer.replace(",", ".");
    if (isNaN(useranswer)) {
      printHeader();
      console.log(" Your input was incorrect. Try again.");
      userInput();
    } else {
      response(useranswer);
    }
  });
}

// Response
function response(useranswer) {
  if (useranswer == answer) {
    score += 1;
    console.log(
      "\n Your answer is correct. You have so far answered " +
        score +
        " problems correctly."
    );
    console.log(" Type 'q' to quit. Press ENTER to continue. ");
    rl.question(" >", (key) => {
      if (key == "q") {
        process.exit(0);
      } else {
        runGame();
      }
    });
  } else {
    console.log(
      "\n Your answer is wrong. You answered " + score + " problems correctly."
    );
    console.log(" Type 'q' to quit. Press ENTER to restart. ");
    rl.question(" >", (key) => {
      if (key == "q") {
        process.exit(0);
      } else {
        score = 0;
        runGame();
      }
    });
  }
}

setDefaults();
runGame();
