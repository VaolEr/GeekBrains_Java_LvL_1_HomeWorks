package Lesson5;

public class TestEmployee {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivan Ivanovich Ivanov",    "Security", "iivaannoovv@googlebox.com","+79121222354", 50000, 30);
        employees[1] = new Employee("Petr Serafimovich Gugl",   "Engineer", "psgugl@mail.com",          "+79375896522", 60000, 28);
        employees[2] = new Employee("Albert Einstein",          "Scientist","AenSt@mymail.com",         "+76325516985", 100000, 37);
        employees[3] = new Employee("Trofim Efimovich Tukin",   "Cleaner",  "tukinTrofim@letterbox.com","+96312525323", 35000, 45);
        employees[4] = new Employee("Maxim Georgievich Valyna", "Boss",     "valbIna@grabster.com",     "+96521325235", 150000, 50);

        int count = 0;
        for (Employee employee:employees){
            if(employee.getAge() > 40){
                System.out.println(employee.toString());
                count++;
            }
        }
        if(count == 0){
            System.out.printf("Сотрудников старше %d лет не обнаружено", count);
        }

    }
}
