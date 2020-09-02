package Lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static boolean SHOW_IA_INFO = true; // If You don't want to see AI info set it to false

    public static Character[][] map;

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {

        initMap();      // init game field
        printMap();     // show game map in console

        while (true) {
            try{
                humanTurn();
                printMap();
                if (checkWin(map,DOTS_TO_WIN,DOT_X)) {
                    System.out.println("Победил человек!");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
                //aiTurn();
                cleverAiTurn(SHOW_IA_INFO);
                printMap();
                if (checkWin(map,DOTS_TO_WIN,DOT_O)) {
                    System.out.println("Победил Искуственный Интеллект!");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья!");
                    break;
                }
            }
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    // Old chek win method, we can delete it!
//    public static boolean checkWin(char symb) {
//        if(map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
//        if(map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
//        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
//        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
//        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
//        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
//        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
//        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
//        return false;
//    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void cleverAiTurn(boolean showAiInfo) {
        int x, y;
        int blockChance = (int)(Math.random() * 3);       // this is random block chance from 0 to 3
        int cleverMoveChance = (int)(Math.random() * 3);  // this is random clever AI move chance from 0 to 3
        final int  CLEVER_AI = 1;
        int[] winPredict = {-1,-1};
        int[] aiWinPredict = {-1,-1};

        try {
            winPredict = chekWinPrediction(map, DOTS_TO_WIN, DOT_X, DOT_EMPTY);        // win predicted move for human
            aiWinPredict = chekWinPrediction(map, DOTS_TO_WIN, DOT_O, DOT_EMPTY);      // win predicted move for AI
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        if(showAiInfo) {
            System.out.println("BlockChance:      " + blockChance + "; 'X'-win predict [X Y](like array coordinates): " + Arrays.toString(winPredict));
            System.out.println("CleverMoveChance: " + cleverMoveChance + "; '0'-win predict [X Y](like array coordinates): " + Arrays.toString(aiWinPredict));
        }

        // First we have to check AI win position (if we have coordinates different from [-1 -1]).
        if(aiWinPredict[0] >= 0){
            x = aiWinPredict[0];
            y = aiWinPredict[1];
            //Next if statement for create little random situations for make AI not so smart =)
            if(cleverMoveChance < CLEVER_AI) {
                do {
                    x = rand.nextInt(SIZE);
                    y = rand.nextInt(SIZE);
                    if(showAiInfo) System.out.println("Non clever move;\n");
                } while (!isCellValid(x, y));
            }
        } // If there is no one win AI positions we have to check next human win position for block it
        else if(winPredict[0] >= 0){
            x = winPredict[0];
            y = winPredict[1];
            //Next if statement for create little random situations for make AI not so smart =)
            if(blockChance < CLEVER_AI) {
                do {
                    x = rand.nextInt(SIZE);
                    y = rand.nextInt(SIZE);
                    if(showAiInfo) System.out.println("Non block move;\n");
                } while (!isCellValid(x, y));
            }
        }
        else {
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
                if(showAiInfo) System.out.println("Play move;\n");
            } while (!isCellValid(x, y));
        }

        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
        map[y][x] = DOT_X;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        return map[y][x] == DOT_EMPTY;
    }

    public static void initMap() {
        map = new Character[SIZE][SIZE];
        for (Character[] characters : map) {
            Arrays.fill(characters, DOT_EMPTY);
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Check win in game map.
     *
     * @param array is square matrix (game map) in which we are searching win
     * @param winStreak is number of sequential elements "dotSymbol" needs to win the game
     * @param dotSymbol is type of symbol for which we are searching win
     * @return true if win for dotSymbol
     */
    public static boolean checkWin(Object[][] array, int winStreak, Object dotSymbol){

        //This algorithm is correct for lines and main amd side diagonals;
        //For check the winStreaks in other diagonals need to upgrade algorithm.

        //When I wrote this function I thought that it is good to combine all checks in
        //one functions. But it is not. The functions for predict the win position was
        //written separately for columns, rows and diagonals.

        boolean winDetect = false;

        String initSymbolRows = dotSymbol.toString();
        int symbolsSumRows;

        String initSymbolColumns = dotSymbol.toString();
        int symbolsSumColumns;

        String initSymbolMainDiag = dotSymbol.toString();
        int symbolsSumMainDiag = 0;

        String initSymbolSideDiag = dotSymbol.toString();
        int symbolsSumSideDiag = 0;

        if(winStreak > 2 && winStreak <= array.length){

            //This block of code for check columns and rows
            for(int i = 0; i< array.length; i++){
                //Delete if You need to check all win combinations
                if(winDetect){
                    continue;
                }

                symbolsSumRows = 0;
                symbolsSumColumns = 0;

                for (int j = 0; j < array[i].length; j++) {
                    if(j == 0){
                        if( array[i][j].toString().equals(initSymbolRows) ){
                            symbolsSumRows++;
                        }
                        if( array[j][i].toString().equals(initSymbolColumns) ){
                            symbolsSumColumns++;
                        }
                    }
                    else{
                        if( array[i][j].toString().equals(array[i][j-1].toString()) && array[i][j].toString().equals(initSymbolRows) ){
                            symbolsSumRows++;
                        }
                        else{
                            symbolsSumRows = 1;
                        }
                        if( array[j][i].toString().equals(array[j-1][i].toString()) && array[j][i].toString().equals(initSymbolColumns) ){
                            symbolsSumColumns++;
                        }
                        else
                        {
                            symbolsSumColumns = 1;
                        }
                    }
                    if(symbolsSumRows == winStreak){
                        System.out.printf("Победная серия из %d символов '%s' в строке %d. ",  symbolsSumRows,   dotSymbol, (i+1));
                        winDetect = true;
                        //break;
                    }
                    if(symbolsSumColumns == winStreak){
                        System.out.printf("Победная серия из %d символов '%s' в столбце %d. ", symbolsSumColumns, dotSymbol, (i+1));
                        winDetect = true;
                        //break;
                    }
                }

            }

            //Delete if You need to check all win combinations
            if(winDetect){
                return true; //  This return is needed to skip the diagonal check
            }

            //This block of code for check main and side diagonals
            for(int i = 0, j = 0, k = array.length - 1; i < array.length; i++, j++, k--){
                if(winDetect){
                    continue;
                }
                // check main diagonal symbols for equivalent
                if( initSymbolMainDiag.equals(array[i][j].toString()) ){
                    symbolsSumMainDiag++;
                    if(symbolsSumMainDiag == winStreak){
                        System.out.printf("Победная серия из %d символов '%s' в главной диагонали. ",  symbolsSumMainDiag,   dotSymbol);
                        winDetect = true;
                        //break;
                    }
                }
                else{
                    symbolsSumMainDiag = 0;
                }
                // check side diagonal symbols for equivalent
                if( initSymbolSideDiag.equals(array[i][k].toString()) ){
                    symbolsSumSideDiag++;
                    if(symbolsSumSideDiag == winStreak){
                        System.out.printf("Победная серия из %d символов '%s' в побочной диагонали. ",  symbolsSumSideDiag,   dotSymbol);
                        winDetect = true;
                        //break;
                    }
                }
                else{
                    symbolsSumSideDiag = 0;
                }
            }
        }
        else
        {
            if(winStreak<=2){
                throw new IllegalArgumentException("Введите победную серию > 2!");
            }
            else{
                throw new IllegalArgumentException("Победная серия не может быть больше размера матрицы!");
            }
        }
        return winDetect;
    }

    /**
     * Returns the [X Y] coordinates with win on next step in rows from array[][].
     * X and Y are from 0 to (array.length - 1) values.
     *
     * @param array is square matrix (game map) in which we need to get win position prediction
     * @param winStreak is number of sequential elements "dotSymbol" needs to win the game
     * @param dotSymbol is type of symbol for which we are searching win position
     * @param emptyDotSymbol is type of empty field symbol in game map
     */
    public static int[] getWinStepInRow(Object[][] array, int winStreak, Object dotSymbol,Object emptyDotSymbol){
        int winX = -1;
        int winY = -1;

        int winXL = -1;
        int winYL = -1;

        int[] winPosition = {winX,winY};

        boolean preWinDetect = false;

        String winSymbol = dotSymbol.toString();
        String emptySymbol = emptyDotSymbol.toString();

        int symbolsSumRows;
        int nonWinSymbolsSumRows;

        Object[] subArray;
        int matrixSize = array.length;

        if(winStreak > 2 && winStreak <= array.length) {
            for (int i = 0; i < matrixSize; i++) {
                if (preWinDetect) {
                    continue;
                }
                for(int j = 0; j <= (matrixSize - winStreak); j++){
                    subArray = Arrays.copyOfRange(array[i], j, j + winStreak); // create subArray "window" for search the preWin position

                    //System.out.println(Arrays.toString(subArray));

                    symbolsSumRows = 0;
                    nonWinSymbolsSumRows = 0;

                    for(int l = 0; l < subArray.length; l++){
//
                        if(subArray[l].toString().equals(emptySymbol)){
                            winXL = l + j;
                            winYL = i;
                            //System.out.printf("XL %d YL %d\n", winXL, winYL);
                        }
                        if(subArray[l].toString().equals(winSymbol)){
                            symbolsSumRows++;
                            //System.out.println("SSR " + symbolsSumRows);
                        }
                        if(!subArray[l].toString().equals(winSymbol) && !subArray[l].toString().equals(emptySymbol)){
                            symbolsSumRows = 0;
                            nonWinSymbolsSumRows++;
                            //System.out.println("ZSSR " + nonWinSymbolsSumRows);
                        }
                        if(symbolsSumRows == (winStreak-1) && nonWinSymbolsSumRows == 0 && l == (subArray.length-1)){
                            //System.out.println("WS-1 " + (winStreak-1));
                            winY = winYL;
                            winX = winXL;
                            preWinDetect = true;
                        }
                    }
                }
            }
            winPosition[1] = winY;
            winPosition[0] = winX;
        }
        else
        {
            if(winStreak<=2){
                throw new IllegalArgumentException("Введите победную серию > 2!");
            }
            else{
                throw new IllegalArgumentException("Победная серия не может быть больше размера матрицы!");
            }
        }
        return winPosition;
    }

    /**
     * Returns the array of elements from array[][] column
     *
     * @param array is square matrix (game map)
     * @param column_index is index of researching column
     * @param from is left value of researching column
     * @param to is right value of researching column
     *
     * Result is subColumn[from ... (to - 1)]
     */
    public static Object[] getSubColumn(Object[][] array, int column_index, int from, int to){
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        if (column_index < 0)
            throw new IllegalArgumentException(column_index + " < 0");
        if (column_index > array.length)
            throw new IllegalArgumentException(column_index + " > " + array.length);

        Object[] column = new Character[newLength];

        for(int i=0; i<newLength; i++){
            column[i] = array[i + from][column_index];
        }
        return column;
    }

    /**
     * Returns the [X Y] coordinates with win on next step in columns from array[][].
     * X and Y are from 0 to (array.length - 1) values.
     *
     * @param array is square matrix (game map) in which we need to get win position prediction
     * @param winStreak is number of sequential elements "dotSymbol" needs to win the game
     * @param dotSymbol is type of symbol for which we are searching win position
     * @param emptyDotSymbol is type of empty field symbol in game map
     */
    public static int[] getWinStepInColumn(Object[][] array, int winStreak, Object dotSymbol,Object emptyDotSymbol){
        int winX = -1;
        int winY = -1;

        int winXL = -1;
        int winYL = -1;

        int[] winPosition = {winX,winY};

        boolean preWinDetect = false;

        String winSymbol = dotSymbol.toString();
        String emptySymbol = emptyDotSymbol.toString();

        int symbolsSumColumns;
        int nonWinSymbolsColumns;

        Object[] subArray;
        int matrixSize = array.length;

        if(winStreak > 2 && winStreak <= array.length) {
            for (int i = 0; i < matrixSize; i++) {
                if (preWinDetect) {
                    continue;
                }
                for(int j = 0; j <= (matrixSize - winStreak); j++) {
                    subArray = getSubColumn(array, i, j, j + winStreak); // create subArray "window" for search the preWin position

                    //System.out.println(Arrays.toString(subArray));

                    symbolsSumColumns = 0;
                    nonWinSymbolsColumns = 0;

                    for (int l = 0; l < subArray.length; l++) {
                        if (subArray[l].toString().equals(emptySymbol)) {
                            winYL = l + j;
                            winXL = i;
                            //System.out.printf("XL %d YL %d\n", winXL, winYL);
                        }
                        if (subArray[l].toString().equals(winSymbol)) {
                            symbolsSumColumns++;
                            //System.out.println("SSC " + symbolsSumColumns);
                        }
                        if(!subArray[l].toString().equals(winSymbol) && !subArray[l].toString().equals(emptySymbol)){
                            symbolsSumColumns = 0;
                            nonWinSymbolsColumns++;
                            //System.out.println("ZSSR " + nonWinSymbolsColumns);
                        }
                        if (symbolsSumColumns == (winStreak - 1) && nonWinSymbolsColumns == 0 && l == (subArray.length-1)) {
                            //System.out.println("WS-1 " + (winStreak - 1));
                            winY = winYL;
                            winX = winXL;
                            preWinDetect = true;
                        }
                    }
                }
            }
            winPosition[1] = winY;
            winPosition[0] = winX;
        }
        else
        {
            if(winStreak<=2){
                throw new IllegalArgumentException("Введите победную серию > 2!");
            }
            else{
                throw new IllegalArgumentException("Победная серия не может быть больше размера матрицы!");
            }
        }
        return winPosition;
    }

    /**
     * Returns the array of elements from array[][] main diagonal
     *
     * @param array is square matrix (game map)
     * @param from is left value of main diagonal array
     * @param to is right value of main diagonal array
     *
     * Result is subMainDiagonal[from ... (to - 1)]
     */
    public static Object[] getMainSubDiagonal(Object[][] array, int from, int to){
        int  newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);

        if (array.length != array[0].length)
            throw new IllegalArgumentException("Array not square");

        Object[] diagonal = new Character[newLength];

        for (int i = 0; i < newLength; i++) {
            diagonal[i] = array[i+from][i+from];
        }
        return diagonal;
    }

    /**
     * Returns the array of elements from array[][] side diagonal
     *
     * @param array is square matrix (game map)
     * @param from is left value of sub diagonal array
     * @param to is right value of sub diagonal array
     *
     * Result is subSideDiagonal[from ... (to - 1)]
     */
    public static Object[] getSideSubDiagonal(Object[][] array, int from, int to){
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);

        if (array.length != array[0].length)
            throw new IllegalArgumentException("Array not square");

        Object[] diagonal = new Character[newLength];

        for(int i=0, j = array.length - 1 - from; i<newLength; i++, j--){
            diagonal[i] = array[j][i + from];
        }

        return diagonal;
    }

    /**
     * Returns the [X Y] coordinates with win on next step in main and side matrix diagonals.
     * X and Y are from 0 to (array.length - 1) values.
     *
     * @param array is square matrix (game map) in which we need to get win position prediction
     * @param winStreak is number of sequential elements "dotSymbol" needs to win the game
     * @param dotSymbol is type of symbol for which we are searching win position
     * @param emptyDotSymbol is type of empty field symbol in game map
     */
    public static int[] getWinStepInDiagonals(Object[][] array, int winStreak, Object dotSymbol,Object emptyDotSymbol){
        int winX = -1;
        int winY = -1;

        int winXL = -1;
        int winYL = -1;

        int[] winPosition = {winX,winY};

        boolean preWinDetect = false;

        String winSymbol = dotSymbol.toString();
        String emptySymbol = emptyDotSymbol.toString();

        int symbolsSumDiagonals;
        int nonWinSymbolsDiagonals;

        Object[] subArray;
        int matrixSize = array.length;

        if(winStreak > 2 && winStreak <= array.length){

            //This block of code for check main diagonal
            for (int i = 0; i <= (matrixSize - winStreak); i++) {
                if (preWinDetect) {
                    continue;
                }

                subArray = getMainSubDiagonal(array, i, i + winStreak); // create subArray "window" for search the preWin position

                symbolsSumDiagonals = 0;
                nonWinSymbolsDiagonals = 0;

                for (int l = 0; l < subArray.length; l++) {
                    if (subArray[l].toString().equals(emptySymbol)) {
                        winYL = l + i;
                        winXL = l + i;
                    }
                    if (subArray[l].toString().equals(winSymbol)) {
                        symbolsSumDiagonals++;
                    }
                    if(!subArray[l].toString().equals(winSymbol) && !subArray[l].toString().equals(emptySymbol)){
                        symbolsSumDiagonals = 0;
                        nonWinSymbolsDiagonals++;
                    }
                    if (symbolsSumDiagonals == (winStreak - 1) && nonWinSymbolsDiagonals == 0 && l == (subArray.length-1)) {
                        winY = winYL;
                        winX = winXL;
                        preWinDetect = true;
                    }
                }

                if (preWinDetect) {
                    continue;
                }

                subArray = getSideSubDiagonal(array, i , i + winStreak ); // create subArray "window" for search the preWin position

                symbolsSumDiagonals = 0;
                nonWinSymbolsDiagonals = 0;

                for (int l = 0; l < subArray.length; l++) {
                    if (subArray[l].toString().equals(emptySymbol)) {
                        winYL = array.length - l - i - 1;
                        winXL = l + i;
                    }
                    if (subArray[l].toString().equals(winSymbol)) {
                        symbolsSumDiagonals++;
                    }
                    if(!subArray[l].toString().equals(winSymbol) && !subArray[l].toString().equals(emptySymbol)){
                        symbolsSumDiagonals = 0;
                        nonWinSymbolsDiagonals++;
                    }
                    if (symbolsSumDiagonals == (winStreak - 1) && nonWinSymbolsDiagonals == 0 && l == (subArray.length-1)) {
                        winY = winYL;
                        winX = winXL;
                        preWinDetect = true;
                    }
                }
            }
            winPosition[1] = winY;
            winPosition[0] = winX;
        }
        else
        {
            if(winStreak<=2){
                throw new IllegalArgumentException("Введите победную серию > 2!");
            }
            else{
                throw new IllegalArgumentException("Победная серия не может быть больше размера матрицы!");
            }
        }
        return winPosition;
    }

    /**
     * Returns the [X Y] coordinates with win on next step.
     * X and Y are from 0 to (array.length - 1) values.
     *
     *
     * @param array is square matrix (game map) in which we need to get win position prediction
     * @param winStreak is number of sequential elements "dotSymbol" needs to win the game
     * @param dotSymbol is type of symbol for which we are searching win position
     * @param emptyDotSymbol is type of empty field symbol in game map
     */
    public static int[] chekWinPrediction(Object[][] array, int winStreak, Object dotSymbol,Object emptyDotSymbol) {

        int[] winCoordinates;

        winCoordinates = getWinStepInRow(array, winStreak, dotSymbol, emptyDotSymbol);
        if (winCoordinates[0] == -1) {
            winCoordinates = getWinStepInColumn(array, winStreak, dotSymbol, emptyDotSymbol);
            if (winCoordinates[0] == -1) {
                winCoordinates = getWinStepInDiagonals(array, winStreak, dotSymbol, emptyDotSymbol);
            }
        }
        return winCoordinates;
    }
}
