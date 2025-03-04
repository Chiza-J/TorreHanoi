package Hanoi;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        int numDiscos = 6;
        int numPostes = 4;
        Hanoi hanoi = new Hanoi(numDiscos, numPostes);
        List<int[]> movimientos = hanoi.resolver();

        for (int[] movimiento : movimientos) {
            System.out.println("Mover de " + movimiento[0] + " a " + movimiento[1]);
        }
    }
}
