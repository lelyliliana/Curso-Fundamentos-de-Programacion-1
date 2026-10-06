public class Main {
    public static void main(String[] args) {
        int[][] consumos = {{12, 8}, {10, 5, 7}};
        int total = 0;
        for (int fila = 0; fila < consumos.length; fila++) {
            int subtotal = 0;
            for (int columna = 0; columna < consumos[fila].length; columna++) {
                subtotal += consumos[fila][columna];
            }
            total += subtotal;
            System.out.println("Aula " + (fila + 1) + ": " + subtotal);
        }
        System.out.println("Total: " + total);
    }
}
