import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        double[] temperaturas = {28.0, 30.0, 29.0};
        if (temperaturas.length == 0) {
            System.out.println("Sin mediciones");
            return;
        }
        double suma = 0;
        for (double temperatura : temperaturas) {
            suma += temperatura;
        }
        System.out.println("Primera: " + temperaturas[0]);
        System.out.println("Media: " + suma / temperaturas.length);
        String entrada = "  SI  ";
        String opcion = entrada.strip().toLowerCase(Locale.ROOT);
        System.out.println("Confirmado: " + opcion.equals("si"));
    }
}
