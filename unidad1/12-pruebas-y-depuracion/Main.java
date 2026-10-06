public class Main {
    static long calcularMulta(int dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("Días negativos");
        }
        return Math.max(0, dias - 7) * 1000L;
    }

    static void comprobar(long esperado, long obtenido) {
        if (esperado != obtenido) {
            throw new AssertionError("Esperado " + esperado + ", obtenido " + obtenido);
        }
    }

    public static void main(String[] args) {
        comprobar(0, calcularMulta(0));
        comprobar(0, calcularMulta(7));
        comprobar(1000, calcularMulta(8));
        comprobar(3000, calcularMulta(10));
        boolean rechazado = false;
        try {
            calcularMulta(-1);
        } catch (IllegalArgumentException e) {
            rechazado = true;
        }
        if (!rechazado) {
            throw new AssertionError("Se aceptaron días negativos");
        }
        System.out.println("5 casos comprobados");
    }
}
