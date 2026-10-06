public class Main {
    public static void main(String[] args) {
        Taller taller = new Taller("Robótica", 1);
        System.out.println("Primera: " + taller.inscribir());
        System.out.println("Segunda: " + taller.inscribir());
        System.out.println("Disponibles: " + taller.cuposDisponibles());
    }
}

class Taller {
    private final String nombre;
    private int cupos;

    Taller(String nombre, int cupos) {
        if (nombre == null || nombre.isBlank() || cupos < 0) {
            throw new IllegalArgumentException("Nombre o cupos inválidos");
        }
        this.nombre = nombre.strip();
        this.cupos = cupos;
    }

    boolean inscribir() {
        if (cupos == 0) {
            return false;
        }
        cupos--;
        return true;
    }

    int cuposDisponibles() {
        return cupos;
    }

    String nombre() {
        return nombre;
    }
}
