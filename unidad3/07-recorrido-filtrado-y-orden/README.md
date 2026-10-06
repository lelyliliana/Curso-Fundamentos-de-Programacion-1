# U3_08 · Recorrido, filtrado y ordenamiento

[Anterior](../../unidad3/06-mapas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/08-archivos-de-texto/README.md)

## Objetivo

Transformar una colección sin confundir modificar el original con producir un resultado.

Tema de la unidad: **Colecciones**.

## Situación que resolveremos

Tenemos tres talleres con cupos diferentes. Ordenaremos los que todavía tienen disponibilidad y compararemos la salida con la lista original. La selección responde a una regla concreta, no a la preferencia por una sintaxis corta.

## Conceptos y decisiones

Un Comparator define cómo ordenar objetos. `Comparator.comparing(Taller::nombre)` compara por nombre; puedes combinar criterios con thenComparing. La comparación de texto natural de Java no equivale a todas las reglas lingüísticas de ordenación; para una interfaz con orden local debes definir esa política.

Un stream representa una secuencia de procesamiento. filter selecciona, sorted ordena y map transforma. Estas operaciones intermedias son perezosas; una terminal como toList inicia el recorrido. La lista obtenida con Stream.toList es no modificable. No confundas esta salida con una ArrayList editable.

También puedes usar un ciclo para seleccionar en una nueva ArrayList y luego llamar a sort. Ambas versiones son válidas. Los streams no sustituyen la comprensión de las reglas ni deben usarse para esconder efectos secundarios. En este ejemplo el origen no se modifica.

Ordenar n elementos suele requerir O(n log n). Filtrar recorre los elementos, O(n). Si solo necesitas contar, no necesitas ordenar primero. Escoger operaciones innecesarias añade trabajo sin mejorar el resultado.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Taller> talleres = List.of(
                new Taller("Robótica", 2),
                new Taller("Dibujo", 0),
                new Taller("Arduino", 1));
        List<String> disponibles = talleres.stream()
                .filter(taller -> taller.cupos() > 0)
                .sorted(Comparator.comparing(Taller::nombre))
                .map(Taller::nombre)
                .toList();
        System.out.println("Disponibles: " + disponibles);
        System.out.println("Original primero: " + talleres.get(0).nombre());
    }
}

record Taller(String nombre, int cupos) {
    Taller {
        if (nombre == null || nombre.isBlank() || cupos < 0) {
            throw new IllegalArgumentException("Taller inválido");
        }
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/07-recorrido-filtrado-y-orden
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Disponibles: [Arduino, Robótica]
Original primero: Robótica
```

## Seguir la ejecución

1. El origen conserva el orden Robótica, Dibujo y Arduino.
2. filter descarta Dibujo porque tiene cero cupos.
3. sorted ordena Arduino antes de Robótica.
4. map obtiene únicamente los nombres.
5. toList materializa la nueva salida y el origen permanece con su primer elemento original.

## Errores frecuentes y cómo interpretarlos

Llamar add sobre el resultado de toList falla por no ser modificable. Crear un stream sin operación terminal no ejecuta este procesamiento. Intentar reutilizar un stream ya consumido produce un error; crea otro desde la colección.

## Práctica guiada

Resuelve la misma tarea con un for-each, una ArrayList<Taller>, sort y un recorrido final. Agrega un taller sin disponibilidad y verifica que ninguna versión lo incluya.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Selecciona solo cupos positivos en la lista nueva, ordénala con el mismo Comparator y extrae los nombres al imprimir. El nuevo taller de cero cupos queda fuera de ambas versiones. El origen no debe modificarse.

</details>

## Preguntas para explicar con tus palabras

¿Qué operación inicia el recorrido? ¿Ordenar es necesario para contar talleres disponibles?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo. Si utilizaste asistencia de IA, registra qué pediste, qué aceptaste y cómo verificaste el resultado.

[Anterior](../../unidad3/06-mapas/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/08-archivos-de-texto/README.md)
