import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> personas = new LinkedHashMap<>();
        personas.put("P01", "Ana");
        personas.put("P02", "Luis");
        String anterior = personas.putIfAbsent("P01", "Otra persona");
        System.out.println("Duplicado detectado: " + (anterior != null));
        System.out.println("P01: " + personas.get("P01"));
        System.out.println("P99 existe: " + personas.containsKey("P99"));
        Map<String, Integer> conteo = new LinkedHashMap<>();
        for (String taller : new String[]{"Robótica", "Dibujo", "Robótica"}) {
            conteo.merge(taller, 1, Integer::sum);
        }
        System.out.println("Conteo: " + conteo);
    }
}
