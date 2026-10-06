import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Ana");
        Taller taller = new Taller("Robótica");
        Inscripcion inscripcion = new Inscripcion(persona, taller, 15000);
        System.out.println(inscripcion.resumen());
    }
}

class Persona {
    private final String nombre;
    Persona(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre vacío");
        }
        this.nombre = nombre.strip();
    }
    String nombre() { return nombre; }
}

class Taller {
    private final String titulo;
    Taller(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título vacío");
        }
        this.titulo = titulo.strip();
    }
    String titulo() { return titulo; }
}

class Inscripcion {
    private final Persona persona;
    private final Taller taller;
    private final long valorPesos;
    Inscripcion(Persona persona, Taller taller, long valorPesos) {
        this.persona = Objects.requireNonNull(persona, "Falta persona");
        this.taller = Objects.requireNonNull(taller, "Falta taller");
        if (valorPesos < 0) {
            throw new IllegalArgumentException("Valor negativo");
        }
        this.valorPesos = valorPesos;
    }
    String resumen() {
        return persona.nombre() + " / " + taller.titulo() + " / " + valorPesos;
    }
}
