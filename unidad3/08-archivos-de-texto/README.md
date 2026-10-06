# U3_09 · Lectura y escritura de texto con NIO

[Anterior](../../unidad3/07-recorrido-filtrado-y-orden/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/09-formato-y-validacion-de-archivos/README.md)

## Objetivo

Persistir texto indicando ruta, codificación y política de sobrescritura.

Tema de la unidad: **Manipulación de archivos**.

## Situación que resolveremos

Hasta ahora los datos desaparecen cuando el proceso termina. Guardaremos dos nombres en un archivo UTF-8 y los leeremos. Todos los archivos generados quedan dentro de una carpeta `salida` de este ejemplo.

## Conceptos y decisiones

Path representa una ruta; Files ofrece operaciones del sistema de archivos. Una ruta relativa se interpreta desde el directorio de trabajo de la ejecución. No depende automáticamente de dónde está guardado el fuente. Por eso el paso de entrar a la carpeta del ejemplo es parte de su reproducibilidad.

`createDirectories` crea la carpeta y sus padres cuando hacen falta. `Files.write` con líneas y sin opciones adicionales crea el archivo si no existe y sobrescribe su contenido si existe. Esa política es intencional para la demostración; no debes usarla sin pensar sobre datos que quieras conservar.

Indicamos StandardCharsets.UTF_8 para conservar tildes de manera predecible. `readAllLines` carga todas las líneas en memoria y es apropiado para este archivo pequeño. Para archivos grandes, utiliza lectura por flujo con cierre explícito, por ejemplo Files.lines dentro de try-with-resources.

IOException indica fallos del sistema de archivos, como falta de permisos o rutas inaccesibles. Capturamos para explicar el fallo y no afirmamos que el guardado tuvo éxito antes de terminar la operación. Los métodos usados gestionan internamente los recursos; cuando recibes un stream de líneas, tú debes cerrarlo.

## Código completo

Archivo: [Main.java](Main.java). Cada carpeta es un ejemplo independiente; no combines todos los `Main` en un único proyecto sin reorganizar nombres y paquetes.

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path archivo = Path.of("salida", "participantes.txt");
        try {
            Files.createDirectories(archivo.getParent());
            Files.write(archivo, List.of("Ana", "José"), StandardCharsets.UTF_8);
            List<String> nombres = Files.readAllLines(archivo, StandardCharsets.UTF_8);
            System.out.println("Leídos: " + nombres.size());
            for (String nombre : nombres) System.out.println(nombre);
        } catch (IOException e) {
            System.err.println("No se pudo guardar o leer: " + e.getMessage());
            System.exit(1);
        }
    }
}
```

## Ejecutarlo paso a paso

1. Abre una terminal en la raíz del repositorio descargado o clonado. Si no sabes descargarlo, sigue [Cómo estudiar en GitHub](../../docs/como-estudiar-en-github.md).
2. Entra a esta carpeta:

```bash
cd unidad3/08-archivos-de-texto
```

3. Ejecuta el archivo fuente con el JDK instalado:

```bash
java Main.java
```

4. Compara la salida con el resultado esperado. Para repetir, modifica el código, guarda y vuelve a ejecutar desde esta misma carpeta.

No necesitas Maven, servidor web ni librerías externas. Si quieres separar compilación y ejecución, consulta la [guía de herramientas](../../docs/ambiente-y-herramientas.md).

## Resultado esperado

```text
Leídos: 2
Ana
José
```

## Seguir la ejecución

1. La ruta apunta a salida/participantes.txt desde la carpeta actual.
2. La carpeta se crea si aún no existe.
3. El archivo recibe dos líneas en UTF-8 y reemplaza contenido anterior.
4. La lectura devuelve una lista de dos nombres.
5. La salida confirma cantidad y contenido después de completar ambas operaciones.

## Errores frecuentes y cómo interpretarlos

Usar una ruta absoluta de tu equipo rompe la ejecución de otras personas. No crear la carpeta padre puede impedir la escritura. Mezclar codificaciones daña texto aunque el archivo se haya creado.

## Práctica guiada

Ejecuta dos veces y verifica si se duplican los nombres. Después, en una copia, agrega una tercera línea con StandardOpenOption.APPEND y compara el comportamiento.

Antes de ejecutar, anota entrada, resultado esperado y razón. Después registra el resultado observado y explica cualquier diferencia. No cambies varias reglas a la vez: identifica cuál modificación estás comprobando.

<details>
<summary>Orientación de solución (consulta después de intentarlo)</summary>

Con la versión inicial no se duplican porque se sobrescribe el archivo. Para anexar, usa Files.write con CREATE y APPEND de StandardOpenOption. Cada ejecución con anexado puede repetir datos; necesitas decidir si eso coincide con tu requisito.

</details>

## Preguntas para explicar con tus palabras

¿Desde qué carpeta se resuelve la ruta? ¿Cuándo readAllLines dejaría de ser una buena elección?

## Evidencia de aprendizaje

Conserva el código que modificaste, una tabla de casos y una explicación de la regla aplicada. Debes poder justificar el resultado y reconocer los límites del ejemplo.

[Anterior](../../unidad3/07-recorrido-filtrado-y-orden/README.md) · [Índice de la unidad](../README.md) · [Inicio del curso](../../README.md) · [Siguiente recurso](../../unidad3/09-formato-y-validacion-de-archivos/README.md)
