import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path archivo = Path.of("salida", "taller.bin");
        Files.createDirectories(archivo.getParent());
        try (DataOutputStream salida = new DataOutputStream(Files.newOutputStream(archivo))) {
            salida.writeInt(1);
            salida.writeUTF("T01");
            salida.writeInt(3);
        }
        try (DataInputStream entrada = new DataInputStream(Files.newInputStream(archivo))) {
            int version = entrada.readInt();
            if (version != 1) throw new IOException("Versión no admitida");
            String codigo = entrada.readUTF();
            int cupos = entrada.readInt();
            if (cupos < 0) throw new IOException("Cupos negativos");
            System.out.println("Código: " + codigo);
            System.out.println("Cupos: " + cupos);
        }
    }
}
