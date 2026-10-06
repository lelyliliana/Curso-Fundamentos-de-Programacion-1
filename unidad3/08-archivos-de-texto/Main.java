import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path archivo = Path.of("salida", "participantes.txt");
        try {
            Files.createDirectories(archivo.getParent());
            Files.write(archivo, List.of("Ana", "José"), StandardCharsets.UTF_8);
            List<String> nombres = Files.readAllLines(archivo, StandardCharsets.UTF_8);
            System.out.println("Leídos: " + nombres.size());
            for (String nombre : nombres) System.out.println(nombre);
        } catch (IOException e) {
            System.err.println("No se pudo guardar o leer: " + e.getMessage());
            System.exit(1);
        }
    }
}
