# Mathgame
A mental calculation game recreated in various programming languages. This is a personal learning exercise to get programming experience and learn differences of programming languages.

## Features
* The program prints a header with version and copyright information. It also makes the version information the window title.
* The program asks the user a random elementary arithmetic problem (+, −, ×, ÷) with two random numbers (between 2 and 12 by default), that the user must answer correctly.
* In division problems, the problem is re-randomized until there is no more than X (1 by default) decimal places in the answer
* The user must answer a set of problems in a row (10 by default), but a wrong answer resets the score
* If the user has answered in the wrong format, the program repeats the question
* The user can quit the game by pressing 'ESC' (C#) or by typing 'q' (Python, Java, JavaScript) between questions
* All current versions use an approach where the program is split into smaller functions. These functions have the same names and basic features.
* Some versions have extra functions for simplifying repeated operations (isNaN, cls, decimalPlaces, ) or for customization (consoleSettings)

## Versions
### C\# 
* Console application
* Main version, loosely inspired by <a href="https://www.youtube.com/playlist?list=PLPV2KyIb3jR6ZkG8gZwJYSjnXxmfPAl51">Brackeys' How to program in C# - Beginner Course</a> - go check it out!
* Has black text on a white background
* Tested on Windows (binary), Ubuntu (Mono)

### Java
* Console application
* The Java version does not change the window title, because there is no such feature
* Tested on Windows (cmd), Ubuntu (bash)

### JavaScript
* Console application
* Run with Node.js
* Uses "readline-sync" from npm, I highly recommend it for console application development
* Tested on Windows (cmd), Ubuntu (bash)

### Python
* Console application
* Python 3
* Tested on Windows (cmd), Ubuntu (bash)

### Ruby
* Console application
* Tested on Windows (cmd), Ubuntu (bash)
