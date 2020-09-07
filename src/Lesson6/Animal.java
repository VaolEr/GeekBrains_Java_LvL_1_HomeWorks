package Lesson6;

import java.util.Objects;

abstract public class Animal {
    protected String name;
    protected String color;
    private int age;

    protected int runDistanceLimit;
    protected int jumpHeightLimit;
    protected int swimDistanceLimit;

    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRunDistanceLimit() {
        return runDistanceLimit;
    }


    public int getJumpHeightLimit() {
        return jumpHeightLimit;
    }


    public int getSwimDistanceLimit() {
        return swimDistanceLimit;
    }


    public abstract void voice();

    public abstract void jump(float height);

    public abstract void run(float distance);

}
