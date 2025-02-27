package Hanoi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de discos: ");
        int numDiscos = scanner.nextInt();

        System.out.print("Ingrese el número de postes (mínimo 3): ");
        int numPostes = scanner.nextInt();

        Hanoi hanoi = new Hanoi(numDiscos, numPostes);
        hanoi.resolver();

        scanner.close();
    }
}
