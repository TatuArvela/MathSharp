import random
import os
import sys

# Defaults
title = "Mathgame (Python, Version 1.2)"
copyright = "(C) 2016 Tatu Arvela. All rights reserved."
header = (" " + title + "\n"
    " " + copyright + "\n"
    "-------------------------------------------------------------------------------\n")
os.system("title " + title)
ops = ["+", "-", "*", "/"]
score = 0
goal = 10
rndmin = 2
rndmax = 12
maxdecimals = 1

# Randomization
def randomizeNumbers():
    global num01
    global num02
    num01 = random.randint(rndmin, rndmax)
    num02 = random.randint(rndmin, rndmax)

# Input validation
def isNaN(s):
    try:
        str(float(s)) != s
        return False
    except ValueError:
        return True

# Clear screen
def cls():
    osname = os.name
    if osname == 'posix':
        os.system('clear')
    elif osname == 'nt' or osname == 'dos':
        os.system('cls')
    else:
        print("\n" * 30)

# Game loop start point
def runGame():
    global op
    global answer
    op = random.choice(ops)
    randomizeNumbers()
    answer = 0

    if op == "+":
        answer = num01 + num02
    if op == "-":
        answer = num01 - num02
    if op == "*":
        answer = num01 * num02
    if op == "/":
        answer = num01 / num02
        # If the answer has more than one decimal place, a new task is calculated
        while (answer*10) != int(answer*10):
            randomizeNumbers()
            answer = num01 / num02

    printHeader()
    printStatus()
    userInput()

# Header
def printHeader():
    cls()
    print(header)

# Print status
def printStatus():
    global score
    if (score == 0):
        print(" Your goal is to answer " + str(goal) + " math problems correctly in a row.")
    elif (score == goal):
        print(" " + str(score) + " out of " + str(goal) + "! Good job!")
        print(" Type 'q' to quit. Press ENTER to play again.")
        key = input(" >")
        if key == "q":
            sys.exit(0)
        else:
            score = 0
            runGame()
    else:
        print(" " + str(score) + " correct answers. " + str(goal - score) + " math problems left.")

# User input
def userInput():
    global useranswer
    print(" What is " + str(num01) + str(op) + str(num02) + "?")
    useranswer = input(" Answer: ")
    useranswer = useranswer.replace(',', '.')
    if (isNaN(useranswer)):
        printHeader()
        print("\n Your input was incorrect. Try again.")
        userInput()
    else:
        response(useranswer)

# Response
def response(useranswer):
    global score
    if (float(useranswer) == answer):
        score += 1
        print("\n Your answer is correct. You have so far answered " + str(score) + " problems correctly.")
        print(" Type 'q' to quit. Press ENTER to continue. ")
        key = input(" >")
        if key == "q":
            sys.exit(0)
        else:
            runGame()
    else:
        print("\n Your answer is wrong. You answered " + str(score) + " problems correctly.")
        print(" Type 'q' to quit. Press ENTER to restart. ")
        key = input(" >")
        if key == "q":
            sys.exit(0)
        else:
            score = 0
            runGame()

runGame()
