public class Main {
    static void informar(Recurso recurso) {
        System.out.println(recurso.titulo() + ": " + recurso.diasPrestamo() + " días");
    }
    public static void main(String[] args) {
        informar(new Recurso("Libro de algoritmos"));
        informar(new Revista("Ciencia hoy"));
    }
}

class Recurso {
    private final String titulo;
    Recurso(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título vacío");
        }
        this.titulo = titulo;
    }
    String titulo() { return titulo; }
    int diasPrestamo() { return 7; }
}

class Revista extends Recurso {
    Revista(String titulo) { super(titulo); }
    @Override
    int diasPrestamo() { return 3; }
}
