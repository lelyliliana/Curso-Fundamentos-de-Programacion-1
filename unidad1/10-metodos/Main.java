public class Main {
    static long calcularMulta(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Días negativos");
        }
        return Math.max(0, dias - 7) * 1000L;
    }

    static void mostrarMulta(int dias) {
        System.out.println("Días " + dias + ": " + calcularMulta(dias));
    }

    public static void main(String[] args) {
        mostrarMulta(7);
        mostrarMulta(10);
        long total = calcularMulta(8) + calcularMulta(9);
        System.out.println("Total: " + total);
    }
}
