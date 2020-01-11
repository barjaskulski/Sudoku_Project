import java.util.Random;
import java.util.Scanner;

class Field {
    private int fieldSize;
    private int difficulty;
    private int boxSize;
    private int[][] field;
    private int[][] copyOfField;

    Field(int fieldSize, int difficulty) {
        this.fieldSize = fieldSize;
        this.difficulty = difficulty;
        double boxSizeD = Math.sqrt(fieldSize);
        boxSize = (int) boxSizeD;
        field = new int[fieldSize][fieldSize];
        copyOfField = new int[fieldSize][fieldSize];
    }

    void fillField() {
        //System.out.println(Arrays.toString(field));
        //-------------------------------------------------------UZUPELNIENIE TABLICY ZERAMI
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++)
                field[x][y] = 0;
        }

        //-------------------------------------------------------WYPELNIENIE BOXOW NIEZALEZNYCH - BRAK SASIADUJACYCH BOXOW - MOZLIWOSC RANDOMOWEGO WYPELNIENIA
        for (int i = 0; i < fieldSize; i = i + boxSize) {
            fillBox(i, i);
        }

        //-------------------------------------------------------WYPELNIENIE RESZTY --- NIEDZIALASDHBUDFHBUSHVFDBSHVFDNSVFDNIFDSVNISVFDNIECNBVRIAEIJDVBADV
//        for (int x=0;x<fieldSize;x=x+boxSize){
//            for(int y=0;y<fieldSize;y=y+boxSize){
//                fillBox(x,y);
//                System.out.println(x+"x");
//                System.out.println(y+"y");
//            }
//        }

        //-------------------------------------------------------WYPELNIENIE RESZTY
        fillRest(0, boxSize);
    }

    //-------------------------------------------------------WYPELNIENIE BOXA 3x3
    private void fillBox(int row, int col) {
        int randomInt;
        //System.out.println("1");
        for (int i = 0; i < boxSize; i++) {
            //System.out.println("2");
            for (int j = 0; j < boxSize; j++) {
                //System.out.println("3");
                if (field[row + i][col + j] == 0) {
                    do {
                        randomInt = generateRandomNumber(fieldSize);
                        //System.out.println("wygenerowanie randomowego znaku");
                    } while (!checkBox(row, col, randomInt) || !checkColumn(col + j, randomInt) || !checkRow(row + i, randomInt));
                    field[row + i][col + j] = randomInt;
                    //printSudoku();
                }
            }
        }
    }

    //-------------------------------------------------------GENEROWANIE RANDOMOWEJ LICZBY
    private int generateRandomNumber(int i) {
        Random r = new Random();
        return r.nextInt(i) + 1;
    }

    //-------------------------------------------------------SPRAWDZENIE POPRAWNOSCI WYPELNIENIA BOXA
    private boolean checkBox(int row, int col, int numberToCheck) {
        //System.out.println("check box");
        for (int i = 0; i < boxSize; i++)
            for (int j = 0; j < boxSize; j++)
                if (field[row + i][col + j] == numberToCheck)
                    return false;
        return true;
    }

    //-------------------------------------------------------SPRAWDZENIE POPRAWNOSCI WYPELNIENIA W KOLUMNIE
    private boolean checkColumn(int col, int numberToCheck) {
        //System.out.println("check column");
        for (int i = 0; i < fieldSize; i++)
            if (field[i][col] == numberToCheck)
                return false;
        return true;
    }

    //-------------------------------------------------------SPRAWDZENIE POPRAWNOSCI WYPELNIENIA W WIERSZU
    private boolean checkRow(int row, int numberToCheck) {
        //System.out.println("check row");
        for (int i = 0; i < fieldSize; i++)
            if (field[row][i] == numberToCheck)
                return false;
        return true;
    }

    //-------------------------------------------------------WYPELNIENIE RESZTY BOXOW
    private boolean fillRest(int i, int j) {
        if (j >= fieldSize && i < fieldSize - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= fieldSize && j >= fieldSize)
            return true;

        if (i < boxSize) {
            if (j < boxSize)
                j = boxSize;
        } else if (i < fieldSize - boxSize) {
            if (j == (int) (i / boxSize) * boxSize)
                j = j + boxSize;
        } else {
            if (j == fieldSize - boxSize) {
                i = i + 1;
                j = 0;
                if (i >= fieldSize)
                    return true;
            }
        }
        for (int num = 1; num <= fieldSize; num++) {
            if (checkRow(i, num) && checkColumn(j, num) && checkBox(i - i % boxSize, j - j % boxSize, num)) {
                field[i][j] = num;
                if (fillRest(i, j + 1))
                    return true;
                field[i][j] = 0;
            }
        }
        return false;
    }

    //-------------------------------------------------------USUNIECIE POL - MIEJSCE DO GRY
    void createMaze() {
        duplicate();
        int count = fieldSize * difficulty;
        while (count != 0) {
            int cellId = generateRandomNumber(fieldSize * fieldSize);
            //System.out.println("cellID: "+cellId);
            int i = (cellId / fieldSize);
            //System.out.println("i: "+i);
            int j = cellId % 9;
            //System.out.println("j: "+j);
            if (j != 0)
                j = j - 1;
            //System.out.println("count: "+count);
            //-----------------------------------------------zapobiega generowania 0 poza wielkoscia tablicy
            if(i==9){
                i--;
            }
            if (field[i][j] != 0) {
                count--;
                field[i][j] = 0;
            }
        }
    }

    //-------------------------------------------------------STWORZENIE KOPII TABLICY DLA POZNIEJSZEGO SPRAWDZENIA
    private void duplicate() {
        //System.arraycopy(field,0,copyOfField,0,field.length);
        for (int i = 0; i < fieldSize; i++) {
            System.arraycopy(field[i], 0, copyOfField[i], 0, fieldSize);
        }

        /*System.out.println("_________________________");
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                System.out.print(copyOfField[i][j] + "  ");
            System.out.println();
        }
        System.out.println("_________________________");
        System.out.println();*/
    }

    //-------------------------------------------------------WPISANIE LICZB
    void insertNumbers() {
        Scanner scanner = new Scanner(System.in);
        int exitButton = 1;
        int licznik = 0;
        int amountLeft = difficulty * fieldSize;
        //System.out.println(amountLeft);
        while (exitButton != 0) {
            System.out.print("Podaj wspołrzędne oraz liczbę do zmiany (format: 'XYLICZBA'): ");
            String tempString = scanner.next();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            if (tempString.length() == 1 || tempString.length() == 3) {
                if (tempString.length() == 1 && Integer.parseInt(tempString) == 0) {
                    exitButton = 0;
                    if (fieldCheck() == 81) {
                        System.out.println("poprawnie wypelniles sudoku! gratulacje!");
                    } else {
                        System.out.println("bledy zostaly popelnione...");
                        break;
                    }
                } else if (tempString.length() == 3) {
                    int y = Integer.parseInt(String.valueOf(tempString.charAt(0))) - 1;
                    int x = Integer.parseInt(String.valueOf(tempString.charAt(1))) - 1;
                    //System.out.println("x: "+x+" y: "+y);
                    if ((x < 0 || x > 8) || (y < 0 || y > 8)) {
                        System.out.println("podałeś wartość poza zakresem planszy");
                    } else {
                        if (field[x][y] != 0) {
                            //System.out.println(field[x][y]);
                            System.out.println("tego pola nie mozesz edytowac!");
                        } else {
                            int value = Integer.parseInt(String.valueOf(tempString.charAt(2)));
                            field[x][y] = value;
                            amountLeft--;
                            System.out.println("pozostało do wypelnienia: " + amountLeft);
                            if (amountLeft == 0) {
                                if (fieldCheck() == 81) {
                                    System.out.println("poprawnie wypelniles sudoku! gratulacje!");
                                    break;
                                } else {
                                    System.out.println("bledy zostaly popelnione...");
                                    break;
                                }
                            }
                        }
                    }
                }else{
                    System.out.println("podałes zly format!");
                }
            } else {
                System.out.println("podałes zly format!");
            }
            // System.out.println(((tempCharArray[0])-1)+" "+((tempCharArray[1])-1)+" "+(tempCharArray[2]));
            System.out.println();

            printSudoku();
        }
    }

    //-------------------------------------------------------SPRAWDZENIE POPRAWNOSCI WYPELNIENIA CALEJ TABLICY + WYPISANIE W PRZYPADKU ZLEGO WYPELNIENIA
    private int fieldCheck() {
        int licznik = 0;
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (field[i][j] == copyOfField[i][j]) {
                    licznik++;
                }
            }
        }
        if (licznik != 81) {
            System.out.println("poprawnie wypelnione pola: " + licznik);
            System.out.println("_________________________");
            for (int i = 0; i < fieldSize; i++) {
                for (int j = 0; j < fieldSize; j++)
                    System.out.print(copyOfField[i][j] + "  ");
                System.out.println();
            }
            System.out.println("_________________________");
            System.out.println();
            printSudoku();
        }

        return licznik;
    }

    //-------------------------------------------------------WYPISANIE SUDOKU
    void printSudoku() {
        System.out.print("  X  ");
        for (int j = 0; j < fieldSize; j++) {
            if (j % 3 == 0 && j != 0) {
                System.out.print("   ");
            }
            System.out.print((j + 1) + "  ");
        }
        System.out.println();
        System.out.println("Y   ________________________________      0 - zakonczenie gry oraz sprawdzenie sudoku");

        for (int i = 0; i < fieldSize; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.print("   - -  -  -  -  -  -  -  -  -  -  -");
                System.out.println();
                System.out.print((i + 1) + "  | ");
                for (int j = 0; j < fieldSize; j++)
                    if (j % 3 == 0 && j != 0) {

                        System.out.print("|  ");
                        System.out.print(field[i][j] + "  ");
                    } else {
                        System.out.print(field[i][j] + "  ");
                    }
                System.out.println();
            } else {
                System.out.print((i + 1) + "  | ");
                for (int j = 0; j < fieldSize; j++)
                    if (j % 3 == 0 && j != 0) {
                        System.out.print("|  ");
                        System.out.print(field[i][j] + "  ");
                    } else
                        System.out.print(field[i][j] + "  ");
                System.out.println();
            }
        }
        System.out.println();
    }
}
