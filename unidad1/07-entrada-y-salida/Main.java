import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            System.out.println("Nombre:");
            String nombre = teclado.nextLine().strip();
            System.out.println("Cantidad de cupos (entero positivo):");
            int cupos = Integer.parseInt(teclado.nextLine().strip());
            if (nombre.isBlank() || cupos <= 0) {
                System.out.println("Nombre y cantidad no válidos");
                return;
            }
            System.out.println("Hola, " + nombre);
            System.out.println("Total en pesos: " + cupos * 15000L);
        }
    }
}
