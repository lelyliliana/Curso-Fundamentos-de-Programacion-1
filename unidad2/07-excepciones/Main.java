public class Main {
    static int convertirCupos(String texto) {
        int cupos = Integer.parseInt(texto.strip());
        if (cupos < 1 || cupos > 30) {
            throw new IllegalArgumentException("Debe estar entre 1 y 30");
        }
        return cupos;
    }
    public static void main(String[] args) {
        String[] entradas = {"dos", "0", "3"};
        for (String entrada : entradas) {
            try {
                System.out.println("Aceptado: " + convertirCupos(entrada));
            } catch (NumberFormatException e) {
                System.out.println("Formato inválido: " + entrada);
            } catch (IllegalArgumentException e) {
                System.out.println("Rango inválido: " + e.getMessage());
            }
        }
    }
}
