# Mathgame

A mental calculation game implemented in various programming languages.

I made this several years ago as a learning exercise to get programming experience and to learn differences of programming languages.

## Features

### Header

* Header with version and copyright information
* Version number in window title

### Problems

* Random elementary arithmetic problem (+, −, ×, ÷) with two random numbers (between 2 and 12 by default)
* For division problems, the problem is regenerated until there is no more than X (1 by default) decimal places in the answer
* The user must answer a set of problems in a row (10 by default)

### Answering

* A wrong, but correctly formatted answer resets the score
* If the user has answered in the wrong format, the program repeats the question

### Quitting

* The user can quit the game by pressing 'ESC' (C#) or by typing 'q' (Python, Java, JavaScript) between questions

### Program structure

* All versions use an approach where the program is split into smaller functions. These functions have the same names and basic features.
* Some versions have extra functions for simplifying repeated operations (isNaN, cls, decimalPlaces) or for customization (consoleSettings)

## Versions

### C\#

* Main version, loosely inspired by [Brackeys' How to program in C# - Beginner Course](https://www.youtube.com/playlist?list=PLPV2KyIb3jR6ZkG8gZwJYSjnXxmfPAl51)
* Includes .NET project and console binary
* Verified to run with .NET Core or Mono

### Java

* Note: Changing window title is not supported in Java without native libraries or a start script
* Includes compiled Java class

### JavaScript (Node.js)

### Python (Python 3)

### Ruby
