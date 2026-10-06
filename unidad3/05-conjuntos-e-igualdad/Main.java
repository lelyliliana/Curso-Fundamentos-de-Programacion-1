import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Participante ana = new Participante("P01", "Ana");
        Participante anaRepetida = new Participante("P01", "Ana");
        System.out.println("Records iguales: " + ana.equals(anaRepetida));
        Set<String> codigos = new LinkedHashSet<>();
        System.out.println("Primera alta: " + codigos.add(ana.codigo()));
        System.out.println("Duplicado: " + codigos.add(anaRepetida.codigo()));
        codigos.add("P02");
        System.out.println("Códigos: " + codigos);
    }
}

record Participante(String codigo, String nombre) {
    Participante {
        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos incompletos");
        }
        codigo = codigo.strip();
        nombre = nombre.strip();
    }
}
