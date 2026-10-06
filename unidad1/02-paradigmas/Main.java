import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] consumos = {12, 8, 10};
        int total = 0;
        for (int consumo : consumos) {
            total += consumo;
        }
        System.out.println("Imperativo: " + total);
        Medicion medicion = new Medicion(12, 8, 10);
        System.out.println("Objetos: " + medicion.total());
        System.out.println("Funcional: " + Arrays.stream(consumos).sum());
    }
}

class Medicion {
    private final int dia1;
    private final int dia2;
    private final int dia3;

    Medicion(int dia1, int dia2, int dia3) {
        this.dia1 = dia1;
        this.dia2 = dia2;
        this.dia3 = dia3;
    }

    int total() {
        return dia1 + dia2 + dia3;
    }
}
