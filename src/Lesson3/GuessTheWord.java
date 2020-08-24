package Lesson3;

import java.util.Scanner;

//* Создать массив из слов
//String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
//"cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea",
//"peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"}.
//При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
//сравнивает его с загаданным словом и сообщает, правильно ли ответил пользователь. Если
//слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
//apple – загаданное
//apricot - ответ игрока
//ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
//Для сравнения двух слов посимвольно можно пользоваться:
//String str = "apple";
//char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
//Играем до тех пор, пока игрок не отгадает слово.
//Используем только маленькие буквы.
public class GuessTheWord {
    public static void main(String[] args) {
        String[] words = {  "apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                            "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        //get random word from words array
        int answerId = (int)(Math.random() * (words.length-1));
        String answer = words[answerId];
        int answerLength = answer.length();

        boolean endGame = false; //end game flag

        StringBuilder gameWord = new StringBuilder("###############"); // stringBuilder object for manipulate characters in string

        int wordLength = 0;

        String userWord;
        int userWordLength;

        System.out.println("\nLets play in the game 'Guess the word'? You have infinite attempts.\nThe list of available words is below:\n");
        for (int i = 0; i < words.length; i++) {
            if((i % 8) != 0) {
                System.out.print(words[i] + " ");
            }
            if(((i % 8) == 0))
            {
                if(i == 0){
                    System.out.print(words[i] + " ");
                }
                else{
                    System.out.println(words[i]);
                }
            }
        }
        /*Main game cycle below*/
        //I am using try/catch for catch the error if non Int value will be written in console
        try(Scanner scanner = new Scanner(System.in)) { // scanner for read the String answer from console
            while (!endGame) {// while loop for play game

                userWord = scanner.nextLine().toLowerCase();     // read user's word from command line
                userWordLength = userWord.length();
                if (userWord.equals("q")) {
                    System.out.println("Program terminated. \nCorrect answer is " + answer);
                    break;
                }
                if (userWord.equals(answer)) {
                    System.out.println("You are win!");
                    endGame = true;
                } else {
                    wordLength = Math.min(userWordLength, answerLength);
                    for (int i = 0; i < wordLength; i++) {
                        if (userWord.charAt(i) == answer.charAt(i)) {
                            gameWord.setCharAt(i, userWord.charAt(i));
                        }
                    }
                    System.out.println(gameWord);
                }
            }
        }
        catch (Exception e){
            System.out.println("We catch the problem " + e.getLocalizedMessage());
        }
    }
}
