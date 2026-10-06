# U3_12 · Proyecto integrador: registro de un taller

[Anterior](../../unidad3/10-archivos-binarios/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../docs/cierre-y-continuidad.md)

## Objetivo

Construir un registro de participantes con unicidad, cupos y persistencia verificable.

Tema de la unidad: **Integración de las tres unidades**.

## Situación que resolveremos

Una biblioteca comunitaria organiza un taller. Necesita registrar personas por código, impedir inscripciones repetidas, consultar cupos y guardar el estado para otra sesión. La solución funciona en consola y permite una demostración reproducible o un menú interactivo.

## Conceptos y decisiones

## Requisitos y límites

| Regla | Comportamiento |
|---|---|
| Identidad | Código no vacío; distingue mayúsculas |
| Datos | Nombre no vacío; sin separador ni saltos de línea |
| Capacidad | Entero positivo; no se sobreasignan cupos |
| Duplicados | Se rechazan sin reemplazar a la persona anterior |
| Guardado | Sobrescribe solo `salida/registro.txt` |
| Carga | Rechaza el archivo completo si cualquier fila es inválida |
| Estado ante error | Una carga fallida conserva el registro en memoria |

La capacidad y los participantes se conservan juntos. El archivo usa UTF-8, primera línea `capacidad;N`, segunda línea `codigo;nombre` y las filas de personas. No admite punto y coma dentro de los campos ni implementa CSV general. El proyecto trabaja con archivos pequeños y un solo proceso; no ofrece una base de datos ni escritura atómica ante un corte de energía.

## Responsabilidades

`Participante` valida los datos de una persona. `Registro` protege unicidad y cupos con un LinkedHashMap. `Estado` representa una instantánea para intercambio. `Repositorio` declara el contrato de almacenamiento. `RepositorioTexto` codifica y decodifica el archivo. `Main` elige modo, coordina la interacción y muestra resultados.

La restauración construye un registro candidato antes de reemplazar el actual. Si encuentra un duplicado o demasiadas filas, falla sin publicar un estado parcial. El orden de inserción se conserva para que la salida y el guardado sean previsibles. Los snapshots usan records y una lista no modificable; no exponen el mapa interno.

## Modos de ejecución

Desde esta carpeta, `java Main.java` ejecuta la demostración. `java Main.java --interactivo` abre el menú (alta, listado, guardado, carga y salida). El menú empieza con capacidad 3; cargar recupera la capacidad del archivo. `java Main.java --verificar` ejecuta comprobaciones de dominio y persistencia en una carpeta temporal que limpia al terminar. No altera el archivo de la demostración.

En el menú, guarda antes de salir para conservar tus cambios. Al iniciar de nuevo, elige cargar. Los mensajes no confirman una operación que haya fallado. La demostración crea Ana y Luis, guarda, recupera y muestra dos personas con un cupo restante. Cada ejecución sobrescribe deliberadamente su archivo de muestra.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
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
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/11-proyecto-integrador
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

Para verificar sus reglas y el guardado/carga:

```bash
java Main.java --verificar
```

## Resultado esperado

```text
P01: Ana
P02: Luis
Disponibles: 1
Estado recuperado desde archivo
```

## Seguir la ejecución

1. La demostración crea un registro de capacidad 3.
2. Las altas válidas consumen dos cupos y conservan códigos únicos.
3. El repositorio guarda capacidad y personas en un formato acordado.
4. La carga valida el archivo y construye un registro nuevo.
5. El listado muestra los mismos datos y un cupo restante, comprobando recuperación real.

## Errores frecuentes y cómo interpretarlos

Reemplazar el mapa antes de terminar la carga puede publicar datos parciales. Guardar solo nombres impide recuperar identidad. Notificar «guardado» antes de Files.write afirma éxito aunque el disco falle. Este proyecto no resuelve concurrencia entre procesos.

## Práctica guiada

Añade búsqueda por código y cancelación. Define si cancelar un código inexistente devuelve false o lanza una excepción. Comprueba que la cancelación libere un cupo y que guardar/cargar conserve el resultado. Mantén el menú separado del modelo.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Registro puede ofrecer una consulta Optional<Participante> y una cancelación que use `remove(codigo) != null`. Al quitar P01, el tamaño disminuye y disponibles aumenta. Las comprobaciones deben cubrir código ausente, cancelación repetida y recuperación del archivo sin la persona eliminada.

</details>

## Preguntas para explicar con tus palabras

¿Por qué se construye un candidato antes de reemplazar el registro? ¿Qué reglas se comprueban por fila y cuáles sobre el conjunto?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/10-archivos-binarios/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../docs/cierre-y-continuidad.md)
