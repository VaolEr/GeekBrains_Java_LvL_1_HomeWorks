package Lesson7;

import java.util.Random;

public class MainClass {
    public static void main(String[] args) {

        Random rand = new Random();

        Cat[] cats = new Cat[10];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("CatName_"+i, rand.nextInt(10) + 1);
        }

        Plate plate = new Plate(rand.nextInt(50) + 1);
        plate.info();
        feedTheCats(cats, plate);

    }

    public static void feedTheCats(Cat[] cats, Plate plate){

        Random rand = new Random(); //for independence of main function code

        boolean allCatsAreSatiety = false;
        int a = 0;

        for (Cat value : cats) {
            value.eat(plate);
            while (!value.isSatiety()){
                System.out.printf("Аппетит котика %s - %d, а в тарелке осталось %d.\n", value.getName(), value.getAppetite(), plate.getFood());
                plate.increaseFood(rand.nextInt(15) + 1);
                value.eat(plate);
            }
            if (value.isSatiety()){
                System.out.println(value.getName() + " накормлен!");
                System.out.println("//****************************//");
                plate.info();
                a++;
                if(a == 10){
                    System.out.println("Все котики сыты!");
                }
            }
        }
    }

}
