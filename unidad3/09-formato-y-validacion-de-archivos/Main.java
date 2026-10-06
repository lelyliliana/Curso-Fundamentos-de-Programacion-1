import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Taller convertir(String linea) {
        String[] campos = linea.split(";", -1);
        if (campos.length != 3) throw new IllegalArgumentException("Se requieren 3 campos");
        return new Taller(campos[0].strip(), campos[1].strip(),
                Integer.parseInt(campos[2].strip()));
    }
    public static void main(String[] args) throws IOException {
        Path archivo = Path.of("salida", "talleres.txt");
        Files.createDirectories(archivo.getParent());
        Files.write(archivo, List.of("codigo;nombre;cupos", "T01;Robótica;2",
                "T02;Dibujo;-1", "T03;Arduino;dos"), StandardCharsets.UTF_8);
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        if (lineas.isEmpty() || !lineas.get(0).equals("codigo;nombre;cupos")) {
            throw new IllegalArgumentException("Encabezado inválido");
        }
        List<Taller> validos = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            try {
                validos.add(convertir(lineas.get(i)));
            } catch (IllegalArgumentException e) {
                System.out.println("Línea " + (i + 1) + ": datos inválidos");
            }
        }
        System.out.println("Válidos: " + validos.size());
        System.out.println(validos.get(0).nombre());
    }
}

record Taller(String codigo, String nombre, int cupos) {
    Taller {
        if (codigo.isBlank() || nombre.isBlank() || cupos < 0) {
            throw new IllegalArgumentException("Datos de taller inválidos");
        }
    }
}
