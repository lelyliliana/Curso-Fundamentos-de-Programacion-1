import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        int suma = 10;
        int cantidad = 3;
        System.out.println("División entera: " + suma / cantidad);
        System.out.println("Media decimal: " + (double) suma / cantidad);
        System.out.println("Residuo: " + suma % cantidad);
        System.out.println("Agrupación: " + (2 + 3) * 4);
        System.out.println("Aproximado: " + (0.1 + 0.2));
        BigDecimal exacto = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("Decimal exacto: " + exacto);
    }
}
