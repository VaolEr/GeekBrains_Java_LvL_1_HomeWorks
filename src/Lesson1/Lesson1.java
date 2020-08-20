package Lesson1;

public class Lesson1 {

    // 1. Создать пустой проект в IntelliJ IDEA и прописать метод main();
    public static void main(String[] args) {

        //2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        boolean hw_bool = true;
        char    hw_char = 'c';
        String  hw_string = "My homework string";

        byte    hw_byte = 27;
        short   hw_short = 85;
        int     hw_int = 1024_2048;
        long    hw_long = 257_568_398_446L;

        float   hw_float = 25.7f;
        float   hw_float2 = (float) 25.7;
        double  hw_double = 36.6;

        System.out.println("Task 3 result " + MyMethod(1,2,5,7));

        System.out.println("Task 4 result 1 " + CheckRange(10, 0));
        System.out.println("Task 4 result 2 " + CheckRange(0, 20));
        System.out.println("Task 4 result 3 " + CheckRange(10, 20));

        System.out.print("Task 5 result 1 "); PositiveNumberCheck(25);
        System.out.print("Task 5 result 2 "); PositiveNumberCheck(-47);

        System.out.println("Task 6 result 1 " + NegativeNumberCheck(25));
        System.out.println("Task 6 result 2 " + NegativeNumberCheck(-47));

        SayHelloMethod("Смоки");

        Bissextile(2020);
        Bissextile(100);
        }
        //3. Написать метод вычисляющий выражение a * (b + (c / d))
        // и возвращающий результат,где a, b, c, d – входные параметры этого метода;
        public static double MyMethod(int a, int b, int c, int d)
        {
            double result;
            if(d!=0) result = a * (b + ((double)c / d));
            else result = 0;
            return result;
        }

        //4. Написать метод, принимающий на вход два числа,
        // и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
        // если да – вернуть true, в противном случае – false;
        public static boolean CheckRange(int a, int b) {
            return (a + b) >= 10 && (a + b) <= 20;
        }

        //5. Написать метод, которому в качестве параметра передается целое число,
        // метод должен напечатать в консоль положительное ли число передали, или отрицательное;
        // Замечание: ноль считаем положительным числом.
        public static void PositiveNumberCheck (int num) {
            if(num >= 0) System.out.println(num + " is positive!");
            else System.out.println(num + " is negative!");
        }

        //6. Написать метод, которому в качестве параметра передается целое число,
        // метод должен вернуть true, если число отрицательное;
        public static boolean NegativeNumberCheck (int num) {
            return num < 0;
        }

        //7. Написать метод, которому в качестве параметра передается строка,
        // обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
        public static void SayHelloMethod(String name){
            System.out.println("Привет, " + name + "!");
        }

        //8. * Написать метод, который определяет является ли год високосным,
        // и выводит сообщение в консоль.
        // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
        public static void Bissextile(int year){
            if((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) System.out.println(year + " високосный!");
            else System.out.println(year + " НЕ високосный!");
        }
}


