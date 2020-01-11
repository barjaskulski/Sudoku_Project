import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int trudnosc=-1;
        Board sudoku;
        boolean inCatch;

        //------------------------------------------STILL TO DO----------------------------------------
        //1. wiecej formatów inputu
        //2. dzialajace skalowanie gry, wybor wielkosci z listy
        //3. DZIALAJACY CATCH <--- Zrobione
        //4. Sprawdzenie mozliwosci rozwiazania zadania przy roznych trybach trudnosci <--- Zrobione
        //5. GIT !!!!!!!! <--- Zrobione
        //6. zmienic int na char
        //7. wywalic 0 - zamiana na znaki
        //8. zapamietywanie pozycji 0 w celu sprawdzenia mozliwosci edytowania
        //------------------------------------------STILL TO DO----------------------------------------

        System.out.println();
        System.out.println("----------------------- SUDOKU DLA UBOGICH -----------------------");
        System.out.println();
        System.out.println();
        System.out.print("Podaj trudność, z która chcesz się zmierzyć (1-5): ");
        Scanner scanner = new Scanner(System.in);
        //trudnosc = scanner.nextInt();
        while (trudnosc > 5 || trudnosc < 1) {
            inCatch=false;
            try {
                trudnosc = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println("Podałeś ciąg znaków! Podaj liczbę tym razem!");
                scanner.nextLine();
                inCatch=true;
            }
            if(!inCatch){
                System.out.println();
                System.out.println("Podałeś wartość spoza zakresu!");
            }
            System.out.print("Podaj trudność, z która chcesz się zmierzyć (1-5): ");
        }

        sudoku = new Board(9, trudnosc);
        sudoku.fillBoard();
        sudoku.createMaze();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Sudoku specjalnie przygotowane dla Ciebie, wyglada następująco:\n");
        sudoku.printSudoku();
        sudoku.insertNumbers();
    }
}
