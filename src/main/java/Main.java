import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int trudnosc;
        Field sudoku;

        //----------------------TO DO--------------------
        //1. wiecej formatów inputu
        //2. dzialajace skalowanie gry, wybor wielkosci z listy
        //3. DZIALAJACY CATCH
        //4. Sprawdzenie mozliwosci rozwiazania zadania przy roznych trybach trudnosci
        //5. GIT !!!!!!!!
        //6. zmienic int na char
        //7. wywalic 0

        System.out.println();
        System.out.println("----------------------- SUDOKU DLA UBOGICH -----------------------");
        System.out.println();
        System.out.println();
        System.out.print("Podaj trudność, z która chcesz się zmierzyć (1-5): ");
        Scanner scanner = new Scanner(System.in);
        trudnosc = scanner.nextInt();

        while (trudnosc > 5 || trudnosc < 1) {
            System.out.println();
            System.out.print("Podałeś wartość spoza zakresu! Wprowadź wartość jeszcze raz: ");
            try {
                trudnosc = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Podałeś ciąg znaków! Podaj liczbę tym razem: ");
                scanner.nextLine();
            }

        }

        sudoku = new Field(9, trudnosc);
        sudoku.fillField();
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
