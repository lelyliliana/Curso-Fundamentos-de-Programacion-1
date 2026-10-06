public class Main {
    public static void main(String[] args) {
        Registro registro = new Registro(3);
        registro.agregar(12);
        registro.agregar(18);
        System.out.println("Cantidad: " + registro.cantidad());
        System.out.println("Promedio: " + registro.promedio());
    }
}

class Registro {
    private final double[] datos;
    private int cantidad;
    Registro(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad inválida");
        datos = new double[capacidad];
    }
    void agregar(double valor) {
        if (!Double.isFinite(valor) || valor < 0) {
            throw new IllegalArgumentException("Medición inválida");
        }
        if (cantidad == datos.length) throw new IllegalStateException("Registro lleno");
        datos[cantidad++] = valor;
    }
    int cantidad() { return cantidad; }
    double promedio() {
        if (cantidad == 0) throw new IllegalStateException("Sin mediciones");
        double suma = 0;
        for (int i = 0; i < cantidad; i++) suma += datos[i];
        return suma / cantidad;
    }
}
