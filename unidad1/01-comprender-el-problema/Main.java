public class Main {
    public static void main(String[] args) {
        int diasTranscurridos = 10;
        int plazo = 7;
        long tarifaPesos = 1000;
        if (diasTranscurridos < 0) {
            System.out.println("Los días no pueden ser negativos");
            return;
        }
        int diasAtraso = Math.max(0, diasTranscurridos - plazo);
        long multaPesos = diasAtraso * tarifaPesos;
        System.out.println("Atraso: " + diasAtraso);
        System.out.println("Multa: " + multaPesos);
    }
}
