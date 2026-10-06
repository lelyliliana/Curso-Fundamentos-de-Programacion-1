public class Main {
    public static void main(String[] args) {
        int total = 0;
        for (int numero = 1; numero <= 4; numero++) {
            total += numero;
        }
        System.out.println("Suma: " + total);
        int turno = 1;
        while (turno <= 3) {
            System.out.println("Turno: " + turno);
            turno++;
        }
        int intentos = 0;
        do {
            intentos++;
        } while (intentos < 1);
        System.out.println("Intentos: " + intentos);
    }
}
