package Hanoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

public class Hanoi {
    private int numDiscos, numPostes;
    private List<int[]> movimientos;
    private Stack<Integer>[] torres;
    private int movimientoActual;
    private boolean terminado;

    public Hanoi(int numDiscos, int numPostes) {
        this.numDiscos = numDiscos;
        this.numPostes = Math.max(numPostes, 3); 
        this.movimientos = new ArrayList<>();
        this.movimientoActual = 0;
        this.terminado = false;
        this.torres = new Stack[this.numPostes];
        for (int i = 0; i < this.numPostes; i++) {
            torres[i] = new Stack<>();
        }
        for (int i = numDiscos; i > 0; i--) {
            torres[0].push(i); 
        }
    }

    public List<int[]> resolver() {
        movimientos.clear();
        resolverHanoi(numDiscos, 0, numPostes - 1, obtenerPalosIntermedios());
        return movimientos;
    }

    private void resolverHanoi(int n, int origen, int destino, List<Integer> intermedios) {
        if (n == 0) return;
        if (intermedios.isEmpty()) {
            moverHanoi(n, origen, destino);
        } else {
            int k = (int) Math.round(n - Math.sqrt(2 * n + 1)) + 1;
            int aux = intermedios.remove(0);

            resolverHanoi(k, origen, aux, intermedios);
            resolverHanoi(n - k, origen, destino, intermedios);
            resolverHanoi(k, aux, destino, intermedios);

            intermedios.add(0, aux); 
        }
    }

    private void moverHanoi(int n, int origen, int destino) {
        if (n == 1) {
            if (esMovimientoValido(origen, destino)) {
                realizarMovimiento(origen, destino);
            }
            return;
        }
        int aux = obtenerPaloAuxiliar(origen, destino);
        moverHanoi(n - 1, origen, aux);
        if (esMovimientoValido(origen, destino)) {
            realizarMovimiento(origen, destino);
        }
        moverHanoi(n - 1, aux, destino);
    }
    
    private void moverDisco(){
        if (movimientoActual < movimientos.size()){
            int[] mov= movimientos.get(movimientoActual);
            int origen = mov[0];
            int destino = mov[1];   
            
            if (!torres[origen].isEmpty()){
                int disco = torres[origen].pop();
                if (torres[destino].isEmpty()){
                    torres[destino].push(disco);
                    movimientoActual++;            
                    
                } else {
                    torres[origen].push(disco);
                }
            }
        } else if(!terminado){
            if(torres[numPostes - 1].size() == numDiscos){
                terminado = true;
                
            }
        }
    }

    private boolean esMovimientoValido(int origen, int destino) {
        return !torres[origen].isEmpty() && (torres[destino].isEmpty() || torres[destino].peek() > torres[origen].peek());
    }

    private void realizarMovimiento(int origen, int destino) {
        torres[destino].push(torres[origen].pop());
        movimientos.add(new int[]{origen, destino});
    }

    private int obtenerPaloAuxiliar(int origen, int destino) {
        for (int i = 0; i < numPostes; i++) {
            if (i != origen && i != destino) return i;
        }
        return -1;
    }

    private List<Integer> obtenerPalosIntermedios() {
        List<Integer> intermedios = new ArrayList<>();
        for (int i = 1; i < numPostes - 1; i++) {
            intermedios.add(i);
        }
        return intermedios;
    }
}
