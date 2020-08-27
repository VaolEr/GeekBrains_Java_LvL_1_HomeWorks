package Lesson3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessTheNumber {
    //Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3
    //попытки угадать это число. При каждой попытке компьютер должен сообщить, больше ли
    //указанное пользователем число, чем загаданное, или меньше. После победы или проигрыша
    //выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

    static final int RANGE = 9;
    static final int TRYCOUNTS = 3;

    static int gameTryCounts;
    static int answer;

    public static void main(String[] args) {

        initNewGame();

        int number;

        //I am using try/catch for catch the error if non Int value will be written in console
        try {

            while (gameTryCounts > 0) {

                number = getNumberFromConsole();

                if (number > answer) {
                    System.out.printf("Your number %d more than correct answer.\n", number);
                    gameTryCounts--;
                } else if (number < answer) {
                    System.out.printf("Your number %d less than correct answer.\n", number);
                    gameTryCounts--;
                } else {
                    System.out.println("You are awesome! You win!");
                    System.out.println("Do You want to play again? Type 1 for YES, 0 for NO.");

                    number = getNumberFromConsole();

                    if(number == 0){
                        break;
                    }
                    else{
                        initNewGame();
                    }
                }
                if (gameTryCounts == 0) {
                    System.out.printf("\nSorry, but You are loose the game =(\n The correct answer is %d.\n", answer);
                    System.out.println("Do You want to play again? Type 1 for YES, 0 for NO.");

                    number = getNumberFromConsole();

                    if(number == 0){
                        break;
                    }
                    else{
                        initNewGame();
                    }
                }

            }
        } catch (InputMismatchException ex) {
            System.out.println("The program was closed, because of input error! Insert the number, please!");
        }
    }

    // this method for read number from console
    public static int getNumberFromConsole(){
        int number;

        Scanner scanner = new Scanner(System.in);

        if(!scanner.hasNextInt()) {
            System.out.println("Insert the Integer number, please!");
            scanner.next();
        }

        number = scanner.nextInt(); // Read numerical value from input
        return number;
    }

    //this method for start and restart the game
    public static void initNewGame(){
        System.out.println("\nLets play the game!");
        System.out.printf("Insert the integer number in range from 0 til %d, please! \nYou have %d attempts.\n", RANGE, TRYCOUNTS);

        gameTryCounts = TRYCOUNTS;
        answer = (int) (Math.random() * RANGE);
    }
}
