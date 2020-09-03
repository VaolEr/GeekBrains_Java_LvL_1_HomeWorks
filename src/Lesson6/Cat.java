package Lesson6;

import java.util.Objects;
import java.util.Random;

public class Cat extends Animal {

    String breed;

    Random random = new Random();

    public Cat(String name, String color, int age) {
        super(name, color, age);
        this.breed = "/EmptyField/";
        this.runDistanceLimit = random.nextInt(1000);
        this.jumpHeightLimit = random.nextInt(10);
    }

    public Cat(String name, String color, String breed, int age) {
        super(name, color, age);
        this.breed = breed;
        this.runDistanceLimit = random.nextInt(1000);
        this.jumpHeightLimit = random.nextInt(10);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public void voice() {
        this.meow();
    }

    @Override
    public  void jump(float height) {
        if(height > this.jumpHeightLimit){
            System.out.println("Кот по кличке " + this.name + " не может прыгнуть выше " + this.jumpHeightLimit + "м!");
        }
        else{
            if(height <= 0){
                System.out.println("Кот по кличке " + this.name + " не может прыгнуть ниже уровня земли!");
            }
            else {
                System.out.println("Кот по кличке " + this.name + " прыгнул на " + height + "м!");
            }
        }
    }

    @Override
    public void run(float distance) {
        if(distance > this.runDistanceLimit){
            System.out.println("Пёс по кличке " + this.name + " не может пробежать больше " + this.runDistanceLimit + "м!");
        }
        else{
            if(distance <= 0){
                System.out.println("Пёс по кличке " + this.name + " не может прибежать в никуда!");
            }
            else {
                System.out.println("Пёс по кличке " + this.name + " пробежал " + distance + "м!");
            }
        }
    }

    private void meow(){
        System.out.println("Кот по кличке " + this.name + " мяукнул!");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cat cat = (Cat) o;
        return Objects.equals(breed, cat.breed) &&
                Objects.equals(random, cat.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), breed, random);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", runDistanceLimit=" + runDistanceLimit +
                ", jumpHeightLimit=" + jumpHeightLimit +
                '}';
    }
}
