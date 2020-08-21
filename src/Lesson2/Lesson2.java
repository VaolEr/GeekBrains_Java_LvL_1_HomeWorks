package Lesson2;

public class Lesson2 {
    public static void main(String[] args) {

        //1. Задать целочисленный массив, состоящий из элементов 0 и 1.
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;
        System.out.println();
        int[] testArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        firstTask(testArray);

        System.out.println();
        System.out.println();
        //2. Задать пустой целочисленный массив размером 8.
        // С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
        int[] test2Array = new int[8];
        for (int i = 0; i < test2Array.length; i++)
        {
            test2Array[i] = i*3;
        }
        printResult(test2Array, 2);

        System.out.println();
        System.out.println();

        //3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        // пройти по нему циклом, и числа меньшие 6 умножить на 2;
        int[] test3Array = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 } ;
        for (int i = 0; i < test3Array.length; i ++) {
            if(test3Array[i] < 6) test3Array[i] = test3Array[i] * 2;
        }
        printResult(test3Array, 3);

        System.out.println();
        System.out.println();

        //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
        // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
        System.out.println("Task 4 result:");
        diagonalMatrix(3);

        System.out.println();
        System.out.println();

        //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
        int[] test5Array = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 } ;
        System.out.println("Task 5 result for array { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 }:");
        getMinMaxFromArray(test5Array);

        System.out.println();
        System.out.println();

        //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
        // метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
        // Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
        // checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.

        int[] test6Array1 = {2, 2, 2, 1, 2, 2, 10, 1};
        int[] test6Array2 = {2, 3, 1, 3, 2, 1};
        int[] test6Array3 = {1, 1, 3, 2, 1};
        System.out.println("Task 6 result for array {2, 2, 2, 1, 2, 2, 10, 1}: " + checkBalance(test6Array1));
        System.out.println("Task 6 result for array {2, 3, 1, 3, 2, 1}: " + checkBalance(test6Array2));
        System.out.println("Task 6 result for array {1, 1, 3, 2, 1}: " + checkBalance(test6Array3));

        System.out.println();
        System.out.println();

        //7. **** Написать метод, которому на вход подается одномерный массив и число n
        // (может быть положительным, или отрицательным),
        // при этом метод должен сместить все элементымассива на n позиций.
        // Для усложнения задачи нельзя пользоваться вспомогательными массивами.
        int[] test7Array1 = {1, 2, 3, 4, 5};
        int[] test7Array2 = {1, 2, 3, 4, 5};
        changeArray(test7Array1, 3);
        System.out.println("Result of right (->>) shift array [1, 2, 3, 4, 5] on 3 positions");
        printResult(test7Array1, 7);
        System.out.println();
        System.out.println();
        System.out.println("Result of left (<<-) shift array [1, 2, 3, 4, 5] on 3 positions");
        changeArray(test7Array2, -3);
        printResult(test7Array2, 7);
    }

    //1. Задать целочисленный массив, состоящий из элементов 0 и 1.
    // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void firstTask(int[] array)
    {
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] == 1) {
                array[i] = 0;
            }
            else
            {
                array[i] = 1;
            }
        }
        printResult(array, 1);
    }

    //This function created for print data from arrays
    public static void printResult(int[] array, int taskNumber){
        for(int i = 0; i < array.length; i++)
        {
            if(i == 0) {
                System.out.print("Task " + taskNumber + " result is: [");
            }
            if(i<array.length-1) {
                System.out.print(array[i] + ", ");
            }
            if(i == array.length-1){
                System.out.print(array[i] + "]");
            }
        }
    }

    //4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    // и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void diagonalMatrix(int rowsColumns){
        int[][] diagonalArray = new int[rowsColumns][rowsColumns];
        for(int i = 0; i<rowsColumns; i++)
        {
            for(int j = 0; j < rowsColumns; j++)
            {
                if(j==i){
                    diagonalArray[i][j] = 1;
                }
                else
                {
                    diagonalArray[i][j] = 0;
                }
                System.out.print(diagonalArray[i][j]);
            }
            System.out.println();
        }
    }

    //5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    public static void getMinMaxFromArray(int[] array)
    {
        int min = array[0];
        int max = array[0];
        for (int a:array) {
            if(a > max){
                max = a;
            }
            if(a < min){
                min = a;
            }
        }
        System.out.println("Minimum array value is: " + min);
        System.out.println("Maximum array value is: " + max);
    }

    //6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
    // метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
    // Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
    // checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
    public static boolean checkBalance(int[] array){

        int arrayLength = array.length;
        int left = 0;
        int right = 0;

        boolean result = false;

        for (int a:array) {
            right = right + a;
        }

        for (int j : array) {
            left = left + j;
            right = right - j;
            if (left == right) {
                result = true;
            }
        }
        return result;
    }

    //7. **** Написать метод, которому на вход подается одномерный массив и число n
    // (может быть положительным, или отрицательным),
    // при этом метод должен сместить все элементымассива на n позиций.
    // Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    public static void changeArray(int[] array, int n){
        int midValue;

        int rightShiftCount = Math.abs(n);
        int leftShiftCount = Math.abs(n);

        int arrayLength = array.length;

        if(n > 0) {
            while (rightShiftCount > 0) {
                midValue = array[arrayLength - 1];
                for (int i = arrayLength - 1; i > 0; i--) {
                    array[i] = array[i - 1];
                }
                array[0] = midValue;
                rightShiftCount--;
            }
        }
        else if(n < 0){
            while (leftShiftCount > 0) {
                midValue = array[0];
                for (int i = 0; i < arrayLength - 1; i++) {
                    array[i] = array[i + 1];
                }
                array[arrayLength-1] = midValue;
                leftShiftCount--;
            }
        }
        else{
            System.out.println("There is no shifts in array, because n equals 0!");
        }
    }
}
