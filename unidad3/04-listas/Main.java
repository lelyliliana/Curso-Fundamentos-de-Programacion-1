import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> participantes = new ArrayList<>(List.of("Ana", "Luis"));
        participantes.add("Marta");
        participantes.set(1, "Luisa");
        participantes.removeIf(nombre -> nombre.equals("Ana"));
        System.out.println("Participantes: " + participantes);
        System.out.println("Primero: " + participantes.get(0));
        System.out.println("Cantidad: " + participantes.size());
        List<String> copia = List.copyOf(participantes);
        participantes.add("Pedro");
        System.out.println("Copia: " + copia);
    }
}
