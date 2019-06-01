# Defaults
$programName = "Mathgame"
$programLanguage = "Ruby"
$programVersion = "1.2"
$title = %{#{$programName} (#{$programLanguage}, version #{$programVersion}\)}
$copyright = "(C) 2016 Tatu Arvela. All rights reserved."
$header = %{ #{$title}
 #{$copyright}
-------------------------------------------------------------------------------

}
# (doesn't set the process title...)
Process.setproctitle($title)
$ops = ["+", "-", "×", "÷"]
$score = 0
$goal = 10
$rndmin = 2
$rndmax = 12
$maxdecimals = 1

# Randomization
def randomizeNumbers()
	$num01 = rand($rndmin..$rndmax)
	$num02 = rand($rndmin..$rndmax)
end

# Length of decimal places in a number
def getDecimalPlaces(input)
    output = 0
    while(input != input.to_i)
        output += 1
        input *= 10
    end
    output   
end

# Input validation
def isNaN string
  false if Float(string) rescue true
end

# Game loop start point
def runGame()
	$op = $ops.sample
    randomizeNumbers()
    $answer = 0

    if ($op == "+")
        $answer = $num01 + $num02
    elsif ($op == "-")
        $answer = $num01 - $num02
    elsif ($op == "×")
        $answer = $num01 * $num02
    elsif ($op == "÷")
        $answer = $num01.fdiv($num02)
        # If the answer has more than one decimal place, a new task is calculated
        while (getDecimalPlaces($answer) > $maxdecimals)
            randomizeNumbers()
            $answer = $num01.fdiv($num02)
		end
	end
    
    printHeader()
    printStatus()
    userInput()
end

# Header
def printHeader()
	system "clear" or system "cls"
	puts($header)
end

# Print status
def printStatus()
    if ($score == 0)
        puts(" Your goal is to answer #{$goal} math problems correctly in a row.")
    elsif ($score == $goal)
        puts(" #{$score} out of #{$goal}! Good job!")
        puts(" Type \'q\' to quit. Press ENTER to play again.")
        print(" >")
        key = gets.chomp
        if key == "q"
            exit
        else
            $score = 0
            runGame()
		end
    else
        puts(" #{$score} correct answers. #{$goal - $score} math problems left.")
	end
end

# User input
def userInput()
	puts(" What is #{$num01}#{$op}#{$num02}?")
	print(" Answer: ")
    useranswer = gets.chomp
    useranswer.gsub! ",", "."
    if (isNaN(useranswer))
		printHeader()
        puts(" Your input was incorrect. Try again.")
        userInput()
    else
        response(useranswer)
	end
end

# Response
def response(useranswer)
    if (useranswer.to_f == $answer)
        $score += 1
        puts("\n Your answer is correct. You have so far answered #{$score} problems correctly.")
        puts(" Type \'q\' to quit. Press ENTER to continue. ")
		print(" >")
        key = gets.chomp
        if key == "q"
            exit
        else
            runGame()
		end
    else
        puts("\n Your answer is wrong. You answered #{$score} problems correctly.")
        puts(" Type \'q\' to quit. Press ENTER to restart. ")
		print(" >")
        key = gets.chomp
        if key == "q"
            exit
        else
            $score = 0
            runGame()
		end
	end
end

runGame()