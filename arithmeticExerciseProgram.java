package Project;

import java.util.Scanner;

public class arithmeticExerciseProgram {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Arithmetic's Exercise Program");
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
        System.out.println("You have 90 seconds to answer as many questions as possible.");
        System.out.println("You will get 5 seconds bonus if you answer 5 questions in a row.");
        System.out.println("Use java arithmetic precedence rules to find answers!");
        System.out.println("press 'q' to quit or any key to start...");

        String start = input.next();

        termination(start);
        System.out.println("");

        int level = 1; //Current level
        int maxTime = 90; //Maximum time
        long startingTime = System.currentTimeMillis(); //Current time
        int questionCount = 1;
        int correctAnswer = 0;
        int wrongAnswer = 0;
        int timeIncrement = 1; //Variable to increment time
        int starPattern = 1; //Variable to print star pattern
        int starCondition = 1;
        int condition = 1; //Variable condition

        while (timeElapsed(startingTime) <= maxTime) {
            if (condition == 1) { //Deciding wether to restart variables
                starCondition = 1;
                timeIncrement = 1;
                starPattern = 1;
                questionCount = 1;
                correctAnswer = 0;
                wrongAnswer = 0;
                condition--;
            }
            int f = (int) ((Math.random()) * 101);
            int g = (int) ((Math.random()) * 101); //Random numbers
            int h = (int) ((Math.random()) * 101);
            String operation1 = operation();
            String operation2 = operation();//Creating random operations
            questionare(f, g, h, operation1, operation2, questionCount, correctAnswer, wrongAnswer, level); //Asks the user the question
            questionCount++;
            int answer = input.nextInt();
            if (timeElapsed(startingTime) >= maxTime) { //checks time condition before the program checks wether the answer is correct or not
                System.out.println("");
                System.out.println("TIME IS UP!!");
                System.out.println("");
                randomCompliment((double) correctAnswer / (questionCount - 1) >= 70 / 100.0 && (questionCount - 1) >= 10);
                System.out.println("Correct Answers: " + correctAnswer);
                System.out.println("Total Questions: " + (wrongAnswer + correctAnswer));
                System.out.println("Total Time: " + maxTime + "secs");
                if ((double) correctAnswer / (questionCount - 1) >= 70 / 100.0 && (questionCount - 1) >= 10) {
                    System.out.println("Congratulations! You passed level " + level + " of the Arithmetic Game!");
                    System.out.println("");
                    String start3 = "";
                    switch (level) {
                        case 1:
                            System.out.println("Input 'q' to quit or any key to advance!");
                            start3 = input.next();
                            System.out.println("");
                            break;
                        case 2:
                            System.out.println("Input 'q' to quit or any key to restart!");
                            start3 = input.next();
                            System.out.println("");
                            break;

                    }
                    termination(start3);
                    if (level == 1) {
                        level++;
                        maxTime = 180 + ((correctAnswer / 5) * 5);
                        condition++;
                        continue;
                    } else {
                        maxTime = 90;
                        startingTime = System.currentTimeMillis();
                        condition++;
                        continue;
                    }

                } else {
                    System.out.println("Sorry!!! You cannot advance to the next level!");
                    System.out.println("");
                    System.out.println("Input 'q' to quit or any key to restart");
                    String start2 = input.next();
                    System.out.println("");
                    termination(start2);
                    if (level == 2) {
                        maxTime = 90;
                        startingTime = System.currentTimeMillis();
                        condition++;
                        continue;
                    } else {
                        level = 1;
                        maxTime = 90;
                        startingTime = System.currentTimeMillis();
                        condition++;
                        continue;
                    }
                }
            }

            String check = answerChecker(f, g, h, operation1, operation2, answer, level); //Checking if the asnwer is correct or not
            if (check.equals("Correct")) {
                System.out.println("Correct");
                correctAnswer++;
                timeIncrement++;
                starPattern++;
            } else {
                System.out.println("Wrong");
                wrongAnswer++;
                timeIncrement = 1;
                starPattern = 1;
                starCondition = 1;
            }
            if (starPattern % 4 == 0) { //Printing star pattern
                triangle(starPattern - starCondition);
                starPattern++;
                starCondition++;
            }
            if (timeIncrement % 6 == 0) { //Time incremenation
                maxTime += 5;
                timeIncrement = 1;
                System.out.println("(You won 5 seconds)");
            }

        }

    }

    public static int timeElapsed(long startingTime) { //Method to Calculate the time elapsed since the beggining fo the program
        long currentTime = System.currentTimeMillis();
        long elapsed = (currentTime - startingTime) / 1000;
        return (int) elapsed;
    }

    public static String operation() { //Method to randomly pick an operator
        int x = (int) ((Math.random() * 104)); //generates random number

        if (0 <= x && x <= 25) { //relates the random number with an operator which gives each operator a 25% chance of being picked
            return " + ";
        } else if (26 <= x && x <= 51) {
            return " - ";
        } else if (52 <= x && x <= 77) {
            return " * ";
        } else {
            return " / ";
        }
    }

    public static String answerChecker(int f, int g, int h, String operator1, String operator2, int answer, int level) { //method that checks the users answer
        int actualAnswer = 0;
        switch (level) { //Level 1 only 1 operand
            case 1:
                if (operator1.equals(" + ")) {
                    actualAnswer = f + g;
                } else if (operator1.equals(" - ")) {
                    actualAnswer = f - g;
                } else if (operator1.equals(" * ")) {
                    actualAnswer = f * g;
                } else {
                    actualAnswer = f / g;
                }
                break;
            case 2: //level 2 has 2 operands which makes this complicated becuase man if statments are required
                if (operator1.equals(" + ") && operator2.equals(" + ")) {
                    actualAnswer = f + g + h;
                } else if (operator1.equals(" + ") && operator2.equals(" - ")) {
                    actualAnswer = f + g - h;
                } else if (operator1.equals(" + ") && operator2.equals(" * ")) {
                    actualAnswer = f + g * h;
                } else if (operator1.equals(" + ") && operator2.equals(" / ")) {
                    actualAnswer = f + g / h;
                } else if (operator1.equals(" - ") && operator2.equals(" + ")) {
                    actualAnswer = f - g + h;
                } else if (operator1.equals(" - ") && operator2.equals(" - ")) {
                    actualAnswer = f - g - h;
                } else if (operator1.equals(" - ") && operator2.equals(" * ")) {
                    actualAnswer = f - g * h;
                } else if (operator1.equals(" - ") && operator2.equals(" / ")) {
                    actualAnswer = f - g / h;
                } else if (operator1.equals(" * ") && operator2.equals(" + ")) {
                    actualAnswer = f * g + h;
                } else if (operator1.equals(" * ") && operator2.equals(" - ")) {
                    actualAnswer = f * g - h;
                } else if (operator1.equals(" * ") && operator2.equals(" * ")) {
                    actualAnswer = f * g * h;
                } else if (operator1.equals(" * ") && operator2.equals(" / ")) {
                    actualAnswer = f * g / h;
                } else if (operator1.equals(" / ") && operator2.equals(" + ")) {
                    actualAnswer = f / g + h;
                } else if (operator1.equals(" / ") && operator2.equals(" - ")) {
                    actualAnswer = f / g - h;
                } else if (operator1.equals(" / ") && operator2.equals(" * ")) {
                    actualAnswer = f / g * h;
                } else if (operator1.equals(" / ") && operator2.equals(" / ")) {
                    actualAnswer = f / g / h;
                }
                break;

        }
        if (answer == actualAnswer) {
            return "Correct";
        } else {
            return "Wrong";
        }

    }

    public static void questionare(int f, int g, int h, String operation1, String operation2, int questionCount, int correctAnswers, int wrongAnswers, int level) {

        if ((operation1.equals(" / ") && g == 0) || (operation2.equals(" / ") && h == 0)) { //This is done so the program never divides by 0
            while (g == 0 || h == 0) {
                g = (int) ((Math.random()) * 101);
                h = (int) ((Math.random()) * 101);
            }
        }

        switch (level) { //Method that asks questions to the user
            case 1:
                System.out.print("Q" + questionCount + ")" + "  " + f + operation1 + g + " = ");
                break;
            case 2:
                System.out.print("Q" + questionCount + ")" + "  " + f + operation1 + g + operation2 + h + " = ");
                break;

        }
    }

    public static void triangle(int amount) { //Method to print star pattern
        if (amount > 9) { //This is 3xk/4
            amount /= 4;
        }
        for (int i = 0; i < amount; i++) { //This loops prints the first line of the pattern
            System.out.print("*");
            if (i == amount - 1) {
                switch (amount) { //There are 2 cases, it either says "good" or "Very good" depending on how many triangles there are
                    case 3:
                        System.out.print(" good ");
                        break;
                    default:
                        for (int j = 0; j < (((Math.pow((amount - 3), 2) + (5 * (amount - 3)) - 4)) / 2); j++) { //The formula used is 
                            System.out.print(" ");
                        }
                        System.out.print("Very Good");
                        for (int j = 0; j < (((Math.pow((amount - 3), 2) + (5 * (amount - 3)) - 4)) / 2); j++) {
                            System.out.print(" ");
                        }
                }

                for (int j = 0; j < amount; j++) {
                    System.out.print("*");
                    //Print the first line first, then move on to the reverse pyramids in the second nested loop
                }
            }
        }
        System.out.println(""); //move to the next line

        int spaces = 1;
        int spaces2 = 1;
        int stoppingCondition = amount;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < stoppingCondition; j++) { //loop to print the stars

                System.out.print("*");
            }
            for (int j = 0; j < spaces; j++) {
                for (int k = 0; k < spaces2; k++) { //loop to print the spaces
                    System.out.print(" ");
                }

            }
            if (i == amount) {

                spaces++;
                stoppingCondition--;
                i = 0;
                System.out.println("");
                for (int j = amount; j > stoppingCondition; j--) {
                    if (stoppingCondition == 0) {
                        break;
                    }
                    System.out.print(" ");

                }

            }
            if (stoppingCondition == 0) { //stop printing stars
                break;
            }
        }

    }

    public static void randomCompliment(boolean condition) {
        int randNum = (int) (Math.random() * 3) + 1; //random number

        if (!condition) { //if user fails level, it mocks sarcastically
            switch (randNum) {
                case 1:
                    System.out.println("You are as fast as a turtle!");
                    break;
                case 2:
                    System.out.println("You are as smart as a donkey!!");
                    break;
                case 3:
                    System.out.println("Good, but not good enough");
                    break;
            }
        } else { //if user succeeds, it gives nice compliments
            switch (randNum) {
                case 1:
                    System.out.println("Who knew you'd be so good at arithmetic?");
                    break;
                case 2:
                    System.out.println("Bravo, you outsmarted the program!");
                    break;
                case 3:
                    System.out.println("Have you always been this smart?");
                    break;
            }
        }
    }

    public static void termination(String input) {
        switch (input) {
            case "q":
                System.exit(0);
                break;
        }
    }
}
