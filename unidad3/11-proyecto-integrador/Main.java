import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0 && args[0].equals("--verificar")) {
            verificar();
        } else if (args.length > 0 && args[0].equals("--interactivo")) {
            interactuar();
        } else {
            Registro registro = new Registro(3);
            registro.inscribir(new Participante("P01", "Ana"));
            registro.inscribir(new Participante("P02", "Luis"));
            Repositorio repositorio = new RepositorioTexto(Path.of("salida", "registro.txt"));
            repositorio.guardar(registro.estado());
            Registro recuperado = Registro.restaurar(repositorio.cargar());
            mostrar(recuperado);
            System.out.println("Estado recuperado desde archivo");
        }
    }

    static void mostrar(Registro registro) {
        for (Participante p : registro.estado().participantes()) {
            System.out.println(p.codigo() + ": " + p.nombre());
        }
        System.out.println("Disponibles: " + registro.disponibles());
    }

    static void interactuar() {
        Registro registro = new Registro(3);
        Repositorio repositorio = new RepositorioTexto(Path.of("salida", "registro.txt"));
        try (Scanner teclado = new Scanner(System.in)) {
            while (true) {
                System.out.println("1 Alta / 2 Listar / 3 Guardar / 4 Cargar / 0 Salir");
                if (!teclado.hasNextLine()) return;
                String opcion = teclado.nextLine().strip();
                try {
                    switch (opcion) {
                        case "1" -> {
                            System.out.println("Código:");
                            if (!teclado.hasNextLine()) return;
                            String codigo = teclado.nextLine();
                            System.out.println("Nombre:");
                            if (!teclado.hasNextLine()) return;
                            String nombre = teclado.nextLine();
                            registro.inscribir(new Participante(codigo, nombre));
                            System.out.println("Inscripción registrada");
                        }
                        case "2" -> mostrar(registro);
                        case "3" -> {
                            repositorio.guardar(registro.estado());
                            System.out.println("Guardado completo");
                        }
                        case "4" -> {
                            Registro candidato = Registro.restaurar(repositorio.cargar());
                            registro = candidato;
                            System.out.println("Carga completa");
                        }
                        case "0" -> { return; }
                        default -> System.out.println("Opción desconocida");
                    }
                } catch (IllegalArgumentException | IllegalStateException | IOException e) {
                    System.out.println("No se completó: " + e.getMessage());
                }
            }
        }
    }

    static void comprobar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }

    static void verificar() throws IOException {
        Registro registro = new Registro(2);
        registro.inscribir(new Participante("P01", "Ana"));
        comprobar(registro.disponibles() == 1, "Alta consume un cupo");
        try {
            registro.inscribir(new Participante("P01", "Otra persona"));
            throw new AssertionError("Aceptó un duplicado");
        } catch (IllegalArgumentException esperado) {
            comprobar(registro.disponibles() == 1, "Duplicado altera cupos");
        }
        registro.inscribir(new Participante("P02", "José"));
        try {
            registro.inscribir(new Participante("P03", "Marta"));
            throw new AssertionError("Sobreasignó cupos");
        } catch (IllegalStateException esperado) {
            comprobar(registro.disponibles() == 0, "Cupos negativos");
        }
        for (String nombre : new String[]{"", "Ana;Luis", "Ana\nLuis"}) {
            try {
                new Participante("P10", nombre);
                throw new AssertionError("Aceptó nombre inválido");
            } catch (IllegalArgumentException esperado) { /* rechazo requerido */ }
        }
        Path carpeta = Files.createTempDirectory("registro-prueba-");
        Path archivo = carpeta.resolve("registro.txt");
        try {
            Repositorio repositorio = new RepositorioTexto(archivo);
            repositorio.guardar(registro.estado());
            Registro recuperado = Registro.restaurar(repositorio.cargar());
            comprobar(recuperado.estado().equals(registro.estado()), "Ida y vuelta UTF-8");
            comprobar(recuperado.disponibles() == 0, "Capacidad no recuperada");
            Files.write(archivo, List.of("capacidad;2", "codigo;nombre", "P01;Ana", "P01;Luis"),
                    StandardCharsets.UTF_8);
            boolean rechazo = false;
            try {
                Registro candidato = Registro.restaurar(repositorio.cargar());
                registro = candidato;
            } catch (IllegalArgumentException esperado) {
                rechazo = true;
            }
            comprobar(rechazo, "Carga con duplicados aceptada");
            comprobar(registro.estado().equals(recuperado.estado()), "Carga parcial publicada");
            Files.writeString(archivo, "encabezado incorrecto", StandardCharsets.UTF_8);
            try {
                repositorio.cargar();
                throw new AssertionError("Encabezado inválido aceptado");
            } catch (IllegalArgumentException esperado) { /* rechazo requerido */ }
        } finally {
            Files.deleteIfExists(archivo);
            Files.deleteIfExists(carpeta);
        }
        System.out.println("Verificación de dominio y persistencia aprobada");
    }
}

record Participante(String codigo, String nombre) {
    Participante {
        codigo = validar(codigo);
        nombre = validar(nombre);
    }
    static String validar(String dato) {
        if (dato == null || dato.isBlank() || dato.contains(";")
                || dato.contains("\n") || dato.contains("\r")) {
            throw new IllegalArgumentException("Campo vacío o con separador no permitido");
        }
        return dato.strip();
    }
}

record Estado(int capacidad, List<Participante> participantes) {
    Estado {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad inválida");
        participantes = List.copyOf(participantes);
    }
}

class Registro {
    private final int capacidad;
    private final Map<String, Participante> personas = new LinkedHashMap<>();
    Registro(int capacidad) {
        if (capacidad <= 0) throw new IllegalArgumentException("Capacidad inválida");
        this.capacidad = capacidad;
    }
    void inscribir(Participante persona) {
        if (persona == null) throw new IllegalArgumentException("Falta participante");
        if (personas.containsKey(persona.codigo())) {
            throw new IllegalArgumentException("Código duplicado: " + persona.codigo());
        }
        if (disponibles() == 0) throw new IllegalStateException("Sin cupos");
        personas.put(persona.codigo(), persona);
    }
    int disponibles() { return capacidad - personas.size(); }
    Estado estado() { return new Estado(capacidad, new ArrayList<>(personas.values())); }
    static Registro restaurar(Estado estado) {
        Registro candidato = new Registro(estado.capacidad());
        for (Participante persona : estado.participantes()) candidato.inscribir(persona);
        return candidato;
    }
}

interface Repositorio {
    void guardar(Estado estado) throws IOException;
    Estado cargar() throws IOException;
}

class RepositorioTexto implements Repositorio {
    private final Path archivo;
    RepositorioTexto(Path archivo) { this.archivo = archivo; }
    @Override
    public void guardar(Estado estado) throws IOException {
        Registro.restaurar(estado); // valida el conjunto antes de escribir
        List<String> lineas = new ArrayList<>();
        lineas.add("capacidad;" + estado.capacidad());
        lineas.add("codigo;nombre");
        for (Participante p : estado.participantes()) {
            lineas.add(p.codigo() + ";" + p.nombre());
        }
        Path padre = archivo.toAbsolutePath().getParent();
        Files.createDirectories(padre);
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }
    @Override
    public Estado cargar() throws IOException {
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        if (lineas.size() < 2 || !lineas.get(0).startsWith("capacidad;")
                || !lineas.get(1).equals("codigo;nombre")) {
            throw new IllegalArgumentException("Encabezados inválidos");
        }
        int capacidad = Integer.parseInt(lineas.get(0).substring("capacidad;".length()));
        List<Participante> personas = new ArrayList<>();
        for (int i = 2; i < lineas.size(); i++) {
            try {
                String[] campos = lineas.get(i).split(";", -1);
                if (campos.length != 2) throw new IllegalArgumentException("Se requieren 2 campos");
                personas.add(new Participante(campos[0], campos[1]));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Línea " + (i + 1) + ": " + e.getMessage(), e);
            }
        }
        Estado estado = new Estado(capacidad, personas);
        Registro.restaurar(estado); // comprueba duplicados y cupos del conjunto
        return estado;
    }
}
