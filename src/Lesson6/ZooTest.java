package Lesson6;

public class ZooTest {
    public static void main(String[] args) {
        Dog doggy1 = new Dog("Maybah","White", "Spitz", 1);
        doggy1.voice();
        doggy1.jump(2);
        doggy1.swim(3.56f);
        doggy1.run(580);
        System.out.println(doggy1);

        System.out.println("\n//-------------------------------------------------//\n");

        Dog doggy2 = new Dog("Bobik","Black", "Dvornyaga", 7);
        doggy2.voice();
        doggy2.jump(3);
        doggy2.swim(14f);
        doggy2.run(398);
        System.out.println(doggy2);

        System.out.println("\n//-------------------------------------------------//\n");

        Cat cat1 = new Cat("Smoky","Grey", "Dvorovoy", 1);
        cat1.voice();
        cat1.jump(7);
        cat1.run(125);
        System.out.println(cat1);

        System.out.println("\n//-------------------------------------------------//\n");

        Cat cat2 = new Cat("Fluffy","Black", "MainKun", 5);
        cat2.voice();
        cat2.jump(6);
        cat2.run(325);
        System.out.println(cat2);
    }
}
