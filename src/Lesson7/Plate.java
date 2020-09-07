package Lesson7;

public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(int n) {
        if((food - n) >= 0){
            food -= n;
        }
        else{
            System.out.println("В миске мало еды.");
        }
    }

    public void increaseFood(int value){
        food += value;
        System.out.println("В миску добавили " + value + " вкусняшек.");
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }
}
