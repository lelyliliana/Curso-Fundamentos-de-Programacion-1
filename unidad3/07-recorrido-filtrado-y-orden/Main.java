import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Taller> talleres = List.of(
                new Taller("Robótica", 2),
                new Taller("Dibujo", 0),
                new Taller("Arduino", 1));
        List<String> disponibles = talleres.stream()
                .filter(taller -> taller.cupos() > 0)
                .sorted(Comparator.comparing(Taller::nombre))
                .map(Taller::nombre)
                .toList();
        System.out.println("Disponibles: " + disponibles);
        System.out.println("Original primero: " + talleres.get(0).nombre());
    }
}

record Taller(String nombre, int cupos) {
    Taller {
        if (nombre == null || nombre.isBlank() || cupos < 0) {
            throw new IllegalArgumentException("Taller inválido");
        }
    }
}
