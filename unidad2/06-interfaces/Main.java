import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Notificador canal = new NotificadorConsola();
        ServicioInscripcion servicio = new ServicioInscripcion(canal);
        servicio.confirmar("Ana");
    }
}

interface Notificador {
    void enviar(String mensaje);
}

class NotificadorConsola implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Confirmación: " + mensaje);
    }
}

class ServicioInscripcion {
    private final Notificador notificador;
    ServicioInscripcion(Notificador notificador) {
        this.notificador = Objects.requireNonNull(notificador);
    }
    void confirmar(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        notificador.enviar("Inscripción de " + nombre);
    }
}
