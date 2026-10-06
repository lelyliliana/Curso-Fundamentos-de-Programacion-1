public class Main {
    static void validar(int n) {
        if (n < 0 || n > 20) throw new IllegalArgumentException("Use 0..20");
    }
    static long factorialRecursivo(int n) {
        validar(n);
        if (n == 0) return 1;
        return n * factorialRecursivo(n - 1);
    }
    static long factorialIterativo(int n) {
        validar(n);
        long resultado = 1;
        for (int i = 2; i <= n; i++) resultado *= i;
        return resultado;
    }
    public static void main(String[] args) {
        System.out.println("Recursivo: " + factorialRecursivo(4));
        System.out.println("Iterativo: " + factorialIterativo(4));
        System.out.println("Caso base: " + factorialRecursivo(0));
    }
}
