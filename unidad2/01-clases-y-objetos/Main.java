public class Main {
    public static void main(String[] args) {
        Taller robotica = new Taller("Robótica", 2);
        Taller dibujo = new Taller("Dibujo", 3);
        System.out.println("Inscripción: " + robotica.inscribir());
        System.out.println(robotica.nombre + ": " + robotica.cupos);
        System.out.println(dibujo.nombre + ": " + dibujo.cupos);
    }
}

class Taller {
    String nombre;
    int cupos;

    Taller(String nombre, int cupos) {
        this.nombre = nombre;
        this.cupos = cupos;
    }

    boolean inscribir() {
        if (cupos <= 0) {
            return false;
        }
        cupos--;
        return true;
    }
}
