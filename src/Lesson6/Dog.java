package Lesson6;

import java.util.Objects;
import java.util.Random;

public class Dog extends Animal {

    private String breed;

    Random random = new Random();

    public Dog(String name, String color, int age) {
        super(name, color, age);
        this.breed = "/EmptyField/";
        this.runDistanceLimit = random.nextInt(1500);
        this.swimDistanceLimit = random.nextInt(200);
        this.jumpHeightLimit = random.nextInt(4);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public void voice() {
        this.bark();
    }

    public Dog(String name, String color, String breed, int age) {
        super(name, color, age);
        this.breed = breed;
        this.runDistanceLimit = random.nextInt(1500);
        this.swimDistanceLimit = random.nextInt(200);
        this.jumpHeightLimit = random.nextInt(4);
    }

    @Override
    public  void jump(float height) {
        if(height > this.jumpHeightLimit){
            System.out.println("Пёс по кличке " + this.name + " не может прыгнуть выше " + this.jumpHeightLimit + "м!");
        }
        else{
            if(height <= 0){
                System.out.println("Пёс по кличке " + this.name + " не может прыгнуть ниже уровня земли!");
            }
            else {
                System.out.println("Пёс по кличке " + this.name + " прыгнул на " + height + "м!");
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

    private void bark(){
        System.out.println("Пёс по кличке " + this.name + " гавкнул!");
    }

    public void swim(float distance){
        if(distance > this.swimDistanceLimit){
            System.out.println("Пёс по кличке " + this.name + " не может проплыть больше " + this.swimDistanceLimit + "м!");
        }
        else{
            if(distance <= 0){
                System.out.println("Пёс по кличке " + this.name + " не может плавать по суше!");
            }
            else {
                System.out.println("Пёс по кличке " + this.name + " проплыл " + distance + "м!");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return Objects.equals(breed, dog.breed) &&
                Objects.equals(random, dog.random);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), breed, random);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", runDistanceLimit=" + runDistanceLimit +
                ", jumpHeightLimit=" + jumpHeightLimit +
                ", swimDistanceLimit=" + swimDistanceLimit +
                '}';
    }
}
