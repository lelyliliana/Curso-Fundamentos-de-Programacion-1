public class Main {
    public static void main(String[] args) {
        int edad = 14;
        boolean tieneBeca = true;
        String modalidad = "virtual";
        if (edad < 0) {
            System.out.println("Edad inválida");
            return;
        }
        if (edad < 14) {
            System.out.println("Aún no cumple la edad mínima");
        } else if (tieneBeca) {
            System.out.println("Admitido. Tarifa: 0");
        } else {
            System.out.println("Admitido. Tarifa: 15000");
        }
        String sesion = switch (modalidad) {
            case "virtual" -> "Sesión por videollamada";
            case "presencial" -> "Sesión en laboratorio";
            default -> "Modalidad desconocida";
        };
        System.out.println(sesion);
    }
}
