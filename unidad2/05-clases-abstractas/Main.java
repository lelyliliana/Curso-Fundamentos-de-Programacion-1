public class Main {
    public static void main(String[] args) {
        Actividad actividad = new TallerPractico("Sensores");
        System.out.println(actividad.descripcion());
    }
}

abstract class Actividad {
    private final String nombre;
    Actividad(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        this.nombre = nombre;
    }
    abstract int minutos();
    String descripcion() {
        return nombre + ": " + minutos() + " minutos";
    }
}

class TallerPractico extends Actividad {
    TallerPractico(String nombre) { super(nombre); }
    @Override
    int minutos() { return 90; }
}
