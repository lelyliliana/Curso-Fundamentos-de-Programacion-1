import java.util.Objects;

public class Main {
    static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }
    public static void main(String[] args) {
        Taller taller = new Taller(1);
        NotificadorMemoria canal = new NotificadorMemoria();
        Servicio servicio = new Servicio(taller, canal);
        comprobar(servicio.inscribir("Ana"), "Primera inscripción");
        comprobar(!servicio.inscribir("Luis"), "No debe sobreasignar");
        comprobar(taller.disponibles() == 0, "Cupos no negativos");
        comprobar(canal.envios() == 1, "Solo confirma aceptaciones");
        System.out.println("Inscritos: 1");
        System.out.println("Disponibles: " + taller.disponibles());
        System.out.println("Confirmaciones: " + canal.envios());
    }
}

class Taller {
    private int cupos;
    Taller(int cupos) {
        if (cupos < 0) throw new IllegalArgumentException("Cupos negativos");
        this.cupos = cupos;
    }
    boolean inscribir() {
        if (cupos == 0) return false;
        cupos--;
        return true;
    }
    int disponibles() { return cupos; }
}

interface Notificador { void enviar(String mensaje); }

class NotificadorMemoria implements Notificador {
    private int cantidad;
    @Override
    public void enviar(String mensaje) { cantidad++; }
    int envios() { return cantidad; }
}

class Servicio {
    private final Taller taller;
    private final Notificador canal;
    Servicio(Taller taller, Notificador canal) {
        this.taller = Objects.requireNonNull(taller);
        this.canal = Objects.requireNonNull(canal);
    }
    boolean inscribir(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        if (!taller.inscribir()) return false;
        canal.enviar("Inscripción de " + nombre);
        return true;
    }
}
