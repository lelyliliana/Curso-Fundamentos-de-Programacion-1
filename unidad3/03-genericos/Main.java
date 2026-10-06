import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Caja<String> nombre = new Caja<>("Ana");
        Caja<Integer> cupos = new Caja<>(3);
        String texto = nombre.obtener();
        int cantidad = cupos.obtener();
        System.out.println("Nombre: " + texto);
        System.out.println("Cupos: " + cantidad);
    }
}

class Caja<T> {
    private final T contenido;
    Caja(T contenido) {
        this.contenido = Objects.requireNonNull(contenido, "Contenido nulo");
    }
    T obtener() { return contenido; }
}
